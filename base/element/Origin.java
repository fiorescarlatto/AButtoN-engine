package com.abutton.game.base.element;

import android.graphics.PointF;
//todo: move this dependency one step away. (in API)

/**
 * Created by Dragonz on 31/07/2015. <br>
 * Interface that defines an object that has an origin point. <br>
 * An origin is used when the object is transformed. All possible transformation: translating,
 * scaling and rotating are performed taking into account the object's origin. <br>
 */
public interface Origin {
    /**
     * Retrieves the object's origin point
     * @return {@link PointF} containing X and Y proportional coordinates of this object's origin
     * <br>    Proportional means that these coordinates are relative to the object's size. <br>
     *         For example if the Origin is the center of the object, it's coordinates will be
     *         {@code [0.5, 0.5]}
     */
    PointF getOrigin();
    /**
     * Changes the object's origin to the new x,y proportional coordinates. <br>
     * Proportional means that these coordinates are relative to the object's size. <br>
     * For example if the Origin is the center of the object, it's coordinates will be
     * {@code [0.5, 0.5]}
     *
     * @param x the new proportional x coordinate for the origin.
     * @param y the new proportional y coordinate for the origin.
     */
    void   setOrigin(float x, float y);
}
