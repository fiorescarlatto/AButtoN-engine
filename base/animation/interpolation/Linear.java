package com.abutton.game.base.animation.interpolation;

/**
 * Created by Dragonz on 29/07/2015.
 * Linear Interpolation
 */
public class Linear implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        return input;
    }
}
