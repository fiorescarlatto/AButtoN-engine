package com.abutton.game.base.element;

/**
 * Created by Dragonz on 30/07/2015. <br>
 * Interface defining the behaviour of an object that can change it's position over time.
 */
public interface Moveable {

    /**
     * Retrieves the current X coordinate of the object
     * @return float containing the X coordinate. <br>
     *         it can be negative.
     */
    float getX();
    /**
     * Retrieves the current Y coordinate of the object
     * @return float containing the Y coordinate. <br>
     *         it can be negative.
     */
    float getY();

    /**
     * Sets the object new position to the new give x and y coordinates
     * @param x float, new x coordinate of the object
     * @param y float, new y coordinate of the object
     */
    void  setPos(float x, float y);
}
