package com.abutton.game.base.element;

/**
 * Created by Dragonz on 30/07/2015. <br>
 * Defines an object that can be hidden or shown. <br>
 * This object usually has a boolean flag {@code visible} which defines whether the object can be
 * show or is hidden.
 */
public interface Hideable {
    /**
     * Checks if the object is showing or is hidden and retrieves it.
     * @return boolean, true if the object is showing, false otherwise.
     */
    boolean isVisible();

    /**
     * Sets the object visibility.
     * @param visible boolean that tells if the object will be shown or hidden. <ul>
     *                <li>{@code true} the object will be shown.</li>
     *                <li>{@code false} the object will be hidden.</li>
     * </ul>
     */
    void setVisible(boolean visible);
}
