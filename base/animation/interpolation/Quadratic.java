package com.abutton.game.base.animation.interpolation;

/**
 * Created by Dragonz on 29/07/2015.
 * Quadratic interpolation
 */
public class Quadratic implements Interpolator {

    private boolean reverse;

    public Quadratic() {
        this(false);
    }
    public Quadratic(boolean reverse) {
        this.reverse = reverse;
    }

    @Override
    public float getInterpolation(float input) {
        if (reverse)
            return 1-(input-1)*(input-1);
        else
            return input*input;
    }
}
