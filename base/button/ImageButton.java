package com.abutton.game.base.button;

import com.abutton.game.API.Blending;
import com.abutton.game.GameState;
import com.abutton.game.base.Texture;
import com.abutton.game.base.element.Paintable;

/**
 * Created by Dragonz on 12/08/2015. <br>
 * Utility class that represents a button that only displays a texture
 */
public class ImageButton extends Button implements Paintable{

    /**
     * Default constructor. <br>
     * Creates a button from the given {@link Texture}. <br>
     * Every button receives an unique ID that increases by one every time a new one is created.
     * @param texture {@link Texture} that should be made into a button.
     */
    public ImageButton(Texture texture) {
        super(texture);
    }

    /**
     * Constructor. <br>
     * Creates a button from the given {@link Texture}. <br>
     * Every button receives an unique ID that increases by one every time a new one is created. <br>
     * This constructor allows to change the starting ID of a {@link ImageButton}.
     * @param texture {@link Texture} that should be made into a button.
     * @param initialID Integer, initial ID that should be set for the first {@link ImageButton}.
     *                  (this one that is currently being created).
     */
    public ImageButton(Texture texture, int initialID) {
        super(texture, initialID);
    }

    /**
     * @see #getElement()
     */
    public Texture getTexture() {
        return getElement();
    }
    /**
     * Retrieves the {@link Texture} contained in this {@link ImageButton}.
     * @return {@link Texture} that was used in the constructor to create this button.
     */
    @Override
    public Texture getElement() {
        return (Texture) element;
    }

    /**
     * Copies the proprieties of a given button: angle, origin, position, size and visibility.
     * @param button {@link ImageButton} which proprieties have to be copied.
     */
    public void as(ImageButton button) {
        super.as(button);
        this.setAlpha(button.getAlpha());
        this.setColor(button.getColor());
        this.setBlendMode(button.getBlendMode());
    }

    /**
     * Sets the new size of the object. <br>
     * Proportion is maintained when stretching, the {@link Texture} is resized only considering a
     * width change, the height is set accordingly to the Source rectangle size.
     * @param size Float, the new width of the {@link Texture}.
     */
    public void setSize(float size) {
        getTexture().setSize(size);
    }

    @Override
    public Blending getBlendMode() {
        return getTexture().getBlendMode();
    }
    @Override
    public void setBlendMode(Blending mode) {
        getTexture().setBlendMode(mode);
    }

    @Override
    public int getColor() {
        return getTexture().getColor();
    }
    @Override
    public void setColor(int color) {
        getTexture().setColor(color);
    }

    @Override
    public int getAlpha() {
        return getTexture().getAlpha();
    }
    @Override
    public void setAlpha(int alpha) {
        getTexture().setAlpha(alpha);
    }

    @Override
    public void update(GameState state) {
        if (isPressed())
            getTexture().setColorFilter(0xFF444444, Blending.ADD);
        else
            getTexture().setColorFilter(this.getColor(), Blending.MULTIPLY);
    }
}
