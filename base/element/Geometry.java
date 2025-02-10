package com.abutton.game.base.element;

import android.graphics.RectF;
//todo: move this dependency one step away. (in API)

/**
 * Created by Dragonz on 31/07/2015. <br>
 * Interface that defines rectangular objects. <br>
 * This interface is used for objects that can be fit inside of a rectangle.
 */
public interface Geometry {
    /**
     * Retrieves the rectangle that most closely wraps this object.
     * @return {@link RectF} describing the rectangle wrapping the object.
     */
    RectF getBoundingRect();

    /**
     * Retrieves whether this object contains the specified point coordinates or not. <br>
     * Default implementation is usually just a test to see if the point is contained in the
     * bounding rectangle. (equivalent to {@code getBoundingRect().contains(x,y);})
     * @param x Float specifying the x coordinate of the point to check.
     * @param y Float specifying the y coordinate of the point to check.
     * @return Boolean: <ul>
     *     <li>true, this object contains the point with the specified coordinates.</li>
     *     <li>false, this object does not contain the point with the specified coordinates.</li>
     * </ul>
     */
    boolean contains(float x, float y);
}
