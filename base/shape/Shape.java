package com.abutton.game.base.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;

import com.abutton.game.API.Blending;
import com.abutton.game.GameElement;
import com.abutton.game.GameState;
import com.abutton.game.base.element.Paintable;

/**
 * Created by Dragonz on 01/07/2015.
 * this class manages and display a given Shape
 * used to easily display static or moving Shapes
 */
public class Shape extends GameElement implements Paintable {

    private Path shape;
    private Paint paint;
    private Blending mode;

    private boolean fill;
    private boolean stroke;

    private int fillColor;
    private int strokeColor;

    /**
     * Constructor, creates a new {@link Shape} object from a given shape.
     * The Path linked to that shape won't be cloned.
     */
    public Shape(Shape s) {
        this(s.shape);

        this.setAngle(s.getAngle());
        this.setOrigin(s.getOrigin().x, s.getOrigin().y);
        this.setPos(s.getX(), s.getY());
        this.setSize(s.getWidth(), s.getHeight());
        this.setVisible(s.isVisible());

        this.setAlpha(s.getAlpha());
        this.setBlendMode(s.getBlendMode());
        this.setColor(s.getColor());

        this.setFill(s.isFill());
        this.setFillColor(s.getFillColor());

        this.setStroke(s.isStroke());
        this.setStrokeColor(s.getStrokeColor());
        this.setStrokeWidth(s.getStrokeWidth());
    }

    /**
     * Constructor. <br>
     * Creates a new {@link Shape} from the given {@link Path}
     * @param shape {@link Path} that defines the current shape.
     */
    public Shape(Path shape) {
        this(shape, null);
    }
    public Shape(Path shape, @Nullable Paint paint) {
        this(shape, paint, 0, 0, 0, 0, 0);
    }
    public Shape(Path shape, @Nullable Paint paint, float x, float y, float width, float height, float angle){
        super(x, y, width, height, angle);
        this.setOrigin(0.5f, 0.5f);

        this.shape = shape;
        this.paint = paint;
        this.mode  = Blending.SRC_OVER;

        if (this.paint == null)
            this.paint = new Paint();

        this.fill   = true;
        this.stroke = false;
        this.strokeColor = this.fillColor = this.paint.getColor();
    }

    /**
     * Retrieves the {@link Paint} that is used to color this shape. <br>
     * Any modification of the drawing style or color can be done trough the paint.
     * @return {@link Paint} object that is used to draw this {@link Shape}.
     */
    public Paint getPaint() {
        return paint;
    }

    /**
     * Returns whether this shape's inside is colored or not.
     * @return boolean true: this shape is filled with color, false: this shape is hollow.
     * @see #setFill(boolean)
     * @see #setFillColor(int)
     * @see #getFillColor()
     */
    public boolean isFill() {
        return fill;
    }
    /**
     * Sets the filling mode for this shape.
     * @param fill true: this shape will be filled with color, false: this shape will be hollow
     * @see #isFill()
     * @see #setFillColor(int)
     * @see #getFillColor()
     */
    public void setFill(boolean fill) {
        this.fill = fill;
    }

    /**
     * Returns whether this shape's outer edge is drawn or not.
     * @return boolean true: this shape's edge is displayed, false: this shape's edge is not
     *         displayed.
     * @see #setStroke(boolean)
     * @see #setStrokeColor(int)
     * @see #getStrokeColor()
     */
    public boolean isStroke() {
        return stroke;
    }
    /**
     * Sets the stroke mode for this shape.
     * @param stroke true: this shape's outer edge will be drawn, false: this shape's outer edge
     *               will not be drawn.
     * @see #isStroke()
     * @see #setStrokeColor(int)
     * @see #getStrokeColor()
     */
    public void setStroke(boolean stroke) {
        this.stroke = stroke;
    }

    /**
     * Inverts the filled area of this {@link Shape}, making it showing on the outside rather than
     * the inside and vice versa.
     */
    public void invert() {
        shape.toggleInverseFillType();
    }

    /**
     * Utility method to call {@code setSize(size, size)}.
     * After calling this function the shape will be back to it's original proportions.
     * @see #setSize(float, float)
     */
    public void setSize(float size) {
        setSize(size, size);
    }

    public int getFillColor() {
        return fillColor;
    }
    public void setFillColor(int color) {
        fillColor = color;
    }

    public int getStrokeColor() {
        return strokeColor;
    }
    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public float getStrokeWidth() {
        return paint.getStrokeWidth();
    }
    public void setStrokeWidth(float width) {
        paint.setStrokeWidth(width);
    }

    @Override
    public int getAlpha() {
        return paint.getAlpha();
    }
    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setBlendMode(Blending mode){
        this.mode = mode;
        paint.setXfermode(new PorterDuffXfermode(mode.get()));
    }
    @Override
    public Blending getBlendMode() {
        return mode;
    }

    @Override
    public int getColor() {
        return getFillColor();
    }
    @Override
    public void setColor(int color) {
        setFillColor(color);
    }

    @Override
    public void display(Canvas canvas) {
        if (!isVisible()) return;

        // sets the canvas matrix in order to be able to draw
        // in the correct position
        canvas.translate(getX(), getY());
        canvas.rotate(getAngle());
        canvas.scale(getWidth() / 2, -getHeight() / 2);

        // draws the shape with the given paint
        if (isFill()) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(fillColor);

            canvas.drawPath(shape, paint);
        }

        if (isStroke()) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(strokeColor);

            canvas.drawPath(shape, paint);
        }

        // resets the canvas matrix to the identity matrix
        canvas.setMatrix(null);

        // DEBUG
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawRect(getBoundingRect(), paint);
//        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void update(GameState state) {
        // does nothing
    }
}
