package com.abutton.game.base.element;

/**
 * Created by Dragonz on 30/07/2015. <br>
 * Interface defining the behaviour of an object that can change size over time.
 */
public interface Resizeable {
    /**
     * Retrieves the current width of the object. <br>
     * Width is intended as the longest horizontal line that fits inside of the object.
     * @return float, width of the object.
     */
    float getWidth();
    /**
     * Retrieves the current height of the object. <br>
     * Height is intended as the longest vertical line that fits inside of the object.
     * @return float, height of the object.
     */
    float getHeight();

    /**
     * Sets the new size of the object to the given height and width. <br>
     * If the object has an {@link Origin}, it should be taken into account when resizing, by
     * stretching the object accordingly.
     * @param width float, new width of the object.
     * @param height float, new height of the object.
     */
    void  setSize(float width, float height);
}
