package com.abutton.game.base.element;

import android.view.MotionEvent;
//todo: move this dependency one step away. (in API)

/**
 * Created by Dragonz on 31/07/2015. <br>
 * Interface that defines an object that can be touched. <br>
 * This object has the proprieties of a {@link Geometry}.
 */
public interface Touchable extends Geometry {
    /**
     * Defines the behaviour this object shows when it is touched.
     * @param event MotionEvent describing the touch event happening upon the object.
     * @return true if the object consumed the event, false otherwise.
     */
    boolean onTouch(MotionEvent event);
}
