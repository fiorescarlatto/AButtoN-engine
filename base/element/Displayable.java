package com.abutton.game.base.element;

import android.graphics.Canvas;

/**
 * Created by Dragonz on 30/07/2015. <br>
 * Defines an element that can be displayed on a Canvas
 */
public interface Displayable {
    /**
     * Displays the object on the given canvas. <br>
     * Called by the engine when the object has to be displayed.
     * @param canvas Canvas on which to draw your object
     */
    void display(Canvas canvas);
}
