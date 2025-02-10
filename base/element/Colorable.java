package com.abutton.game.base.element;

/**
 * Created by Dragonz on 30/07/2015. <br>
 * Interface defining the behaviour of an object that can change its color
 */
public interface Colorable {
    /**
     * Retrieves the color of the object
     * @return an Integer containing the color code of the object expressed as AARRGGBB <br>
     *         alpha, red, green and blue.
     */
    int  getColor();
    /**
     * Sets the color of the object to the new color
     * @param color color of the object, expressed as an integer, in AARRGGBB <br>
     *              alpha, red, green and blue.
     */
    void setColor(int color);
}
