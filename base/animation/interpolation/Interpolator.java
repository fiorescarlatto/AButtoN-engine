package com.abutton.game.base.animation.interpolation;

/**
 * Created by Dragonz on 29/07/2015. <br>
 * Interface that defines interpolation based on time.
 */
public interface Interpolator {
    /**
     * Retrieves the interpolation value, usually a value between 0 and 1, but it can exceed this
     * value, making the animation overshoot the target.
     * @param input float value that raises linearly and is <b>always</b> between 0 and 1. <br>
     *              A value of 0 corresponds to the begin of the interpolated event while a value
     *              of 1 corresponds to the end of the event.
     * @return float value representing the interpolation value for the event. This value can
     *         exceed the limits of 0 and 1 making the animation overshoot the target.
     */
    float getInterpolation(float input);
}
