package com.abutton.game.base.element;

/**
 * Created by Dragonz on 30/07/2015. <br>
 * Interface defining the behaviour of an object that can rotate over time.
 */
public interface Rotatable {
    /**
     * Retrieves the current angle of the object.
     * @return float, current rotation angle of the object. <br>
     *         Expressed in degrees. <br>
     *         An angle of 0 degrees means that the object is horizontal.
     */
    float getAngle();

    /**
     * Sets the new rotation angle of the object. <br>
     * If the object has an {@link Origin}, it should be taken into account when rotating so that
     * the object will rotate around it's origin making it the center of rotation.
     * @param angle float, new angle of rotation. <br>
     *              Expressed in degrees. <br>
     *              An angle of 0 degrees means that the object is horizontal.
     */
    void  setAngle(float angle);
}
