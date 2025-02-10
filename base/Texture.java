package com.abutton.game.base;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;

import com.abutton.game.API.Blending;
import com.abutton.game.GameElement;
import com.abutton.game.GameState;
import com.abutton.game.base.element.Paintable;
import com.abutton.game.utility.MathUtils;

/**
 * Created by Dragonz on 01/07/2015. <br>
 * GameElement that manages and displays a given texture
 * used to easily display static or moving images
 */
public class Texture extends GameElement implements Paintable {

    private Bitmap texture;
    private Paint paint;
    private Blending mode;
    private int color;

    private Rect srcRect;

    /**
     * Creates a new texture from an existing texture.
     * This constructor does not create or load a new bitmap into memory.
     */
    public Texture(Texture t) {
        this(t.texture);

        this.setAngle(t.getAngle());
        this.setOrigin(t.getOrigin().x, t.getOrigin().y);
        this.setPos(t.getX(), t.getY());
        this.setSize(t.getWidth(), t.getHeight());
        this.setVisible(t.isVisible());

        this.setAlpha(t.getAlpha());
        this.setBlendMode(t.getBlendMode());
        this.setColor(t.getColor());

        this.setSource(t.getSource());
    }

    /**
     * Creates a one-pixel texture of the specified color
     */
    public Texture(int color) {
        this(Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888));

        Canvas c = new Canvas(texture);
        c.drawColor(0xFFFFFFFF);

        setColor(color);
    }

    /**
     * This constructor creates a new {@link Bitmap} into memory using the given resource before
     * calling the default constructor {@code Texture (Bitmap texture);} <br>
     * Using this constructor requires loading an image into memory. <br>
     * Only use this constructor inside of a {@link com.abutton.game.GameStateLoader}
     * @param res Resources of the current application.
     * @param resID Resource ID of the image you want to load in this Texture.
     */
    public Texture (Resources res, int resID) {
        this(res, resID, null);
    }
    public Texture (Resources res, int resID, @Nullable Paint paint) {
        super(0, 0, 0, 0, 0);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        this.texture = BitmapFactory.decodeResource(res, resID, options);
        this.setSize(texture.getWidth(), texture.getHeight());

        this.paint = paint;

        if (this.paint == null)
            this.paint = new Paint();

        this.srcRect = new Rect(0, 0, texture.getWidth(), texture.getHeight());
        this.color = Color.WHITE;

        this.mode = Blending.SRC_OVER;
    }

    /**
     * This constructor uses the given {@link Bitmap} to create a new Texture. <br>
     * @param texture the {@link Bitmap} this Texture object should display.
     */
    public Texture (Bitmap texture) {
        this(texture, null);
    }
    public Texture (Bitmap texture, @Nullable Paint paint) {
        this(texture, paint, 0, 0);
    }
    public Texture (Bitmap texture, @Nullable Paint paint, float x, float y) {
        this(texture, paint, x, y, texture.getWidth(), texture.getHeight(), 0);
    }
    public Texture (Bitmap texture, @Nullable Paint paint, float x, float y, float width, float height, float angle) {
        super(x, y, width, height, angle);

        this.texture = texture;
        this.paint = paint;

        if (this.paint == null)
            this.paint = new Paint();

        this.srcRect = new Rect(0, 0, texture.getWidth(), texture.getHeight());
        this.color = Color.WHITE;

        this.mode = Blending.SRC_OVER;
    }

    public int  getAlpha() {
        return paint.getAlpha();
    }
    public int  getColor() {
        return color;
    }
    public Blending getBlendMode() {
        return mode;
    }

    /**
     * Retrieves the rectangle bounding the part of the source {@link Bitmap} that this
     * {@link Texture} should display. <br>
     * By default this is equal to a rectangle containing the whole source image.
     * @return {@link Rect} defining the amount of source to display.
     */
    public Rect getSource() {
        return new Rect(srcRect);
    }

    public void setAlpha(int alpha) {
        alpha = MathUtils.constrain(alpha, 0, 255);
        paint.setAlpha(alpha);
    }
    public void setColor(int color) {
        this.color = color;
        setColorFilter(color, Blending.MULTIPLY);
    }
    public void setBlendMode(Blending mode){
        this.mode = mode;
        paint.setXfermode(new PorterDuffXfermode(mode.get()));
    }

    /**
     * Sets the part of source {@link Bitmap} that this {@link Texture} should display. <br>
     * The default source rectangle is a rectangle containing the whole source image. <br>
     * The source rectangle can't contain negative values or exceed the size of the {@link Bitmap}.
     * @param source {@link Rect} defining the new source rectangle.
     */
    public void setSource(Rect source) {
        setSource(source.left, source.top, source.right, source.bottom);
    }

    /**
     * Sets the part of the source {@link Bitmap} to display using a rectangle. <br>
     * The default source rectangle is a rectangle containing the whole source image. <br>
     * The source rectangle can't contain negative values or exceed the size of the {@link Bitmap}.
     * @param left left edge of the source rectangle.
     * @param top top edge of the source rectangle.
     * @param right right edge of the source rectangle.
     * @param bottom bottom edge of the source rectangle.
     */
    public void setSource(int left, int top, int right, int bottom) {
        if (left < 0 || top < 0)
            throw new IllegalArgumentException("Invalid rectangle, left or top is negative.");
        if (left > right || top > bottom)
            throw new IllegalArgumentException("Invalid rectangle, negative area.");
        if (right > texture.getWidth() || bottom > texture.getHeight())
            throw new IllegalArgumentException("Invalid rectangle, out of bounds.");

        srcRect = new Rect(left, top, right, bottom);
    }

    /**
     * Sets the part of the source {@link Bitmap} to display using a rectangle. <br>
     * The default source rectangle is a rectangle containing the whole source image. <br>
     * The source rectangle can't contain negative values or exceed the size of the {@link Bitmap}.
     * All values has to be between 0 and 1
     * @param left left edge of the source rectangle.
     * @param top top edge of the source rectangle.
     * @param right right edge of the source rectangle.
     * @param bottom bottom edge of the source rectangle.
     */
    public void setSource(float left, float top, float right, float bottom) {
        if (left < 0 || top < 0)
            throw new IllegalArgumentException("Invalid rectangle, left or top is negative.");
        if (left > right || top > bottom)
            throw new IllegalArgumentException("Invalid rectangle, negative area.");
        if (right > 1 || bottom > 1)
            throw new IllegalArgumentException("Invalid rectangle, out of bounds.");

        srcRect = new Rect(
                (int) Math.floor(left * texture.getWidth()), (int) Math.floor(top*texture.getHeight()),
                (int) Math.ceil(right*texture.getWidth()),  (int) Math.ceil(bottom * texture.getHeight())
        );
    }

    /**
     * Sets a {@link android.graphics.ColorFilter} on this {@link Texture}.
     * @param color Integer defining the color of the color filter.
     * @param mode {@link Blending} describing how to blend the color on
     *             this {@link Texture}.
     */
    public void setColorFilter (int color, @Nullable Blending mode) {
        if (mode == null) {
            paint.setColorFilter(new PorterDuffColorFilter(color, Blending.MULTIPLY.get()));
        } else {
            paint.setColorFilter(new PorterDuffColorFilter(color, mode.get()));
        }
    }

    /**
     * Sets the new size of the object. <br>
     * Proportion is maintained when stretching, the {@link Texture} is resized only considering a
     * width change, the height is set accordingly to the Source rectangle size.
     * @param size Float, the new width of the {@link Texture}.
     */
    public void setSize(float size) {
        super.setSize(size, size / srcRect.width() * srcRect.height());
    }

    /**
     * More accurate evaluation of {@code contains(float x, float y)} than specified in the
     * interface. <br>
     * This method returns true only if the given point corresponds to a non-transparent pixel.
     * @param x Float, x coordinate of the point.
     * @param y Float, y coordinate of the point.
     * @return Boolean: <ul>
     *     <li>true, the point corresponds to a non-transparent pixel in the {@link Bitmap}.</li>
     *     <li>false, the point is outside of the bounds or is not a transparent pixel in the
     *     {@link Bitmap}.</li>
     * </ul>
     */
    @SuppressWarnings("all")
    @Override
    public boolean contains(float x, float y) {
        RectF rect = getBoundingRect();
        float sourceX = ((x - rect.left)/ rect.width() * srcRect.width()) + srcRect.left;
        float sourceY = ((y - rect.top) / rect.height()* srcRect.height())+ srcRect.top;

        if (new RectF(srcRect).contains(sourceX, sourceY))
            return ( (texture.getPixel((int)sourceX, (int)sourceY) & 0xFF000000) == 0xFF000000 );
        return false;
    }

    @Override
    public void display(Canvas canvas) {
        if (!isVisible()) return;

        canvas.rotate(getAngle(), getX(), getY());
        canvas.drawBitmap(texture, srcRect, getBoundingRect(), paint);
        canvas.rotate(-getAngle(), getX(), getY());
    }

    @Override
    public void update(GameState state) {
        //nothing to do here
    }
}
