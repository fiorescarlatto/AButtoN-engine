package com.abutton.game.base.animation.interpolation;

/**
 * Created by Dragonz on 29/07/2015.
 * Noise interpolation
 */
public class Noise implements Interpolator {

    private boolean symmetric;

    public Noise(){
        this(true);
    }
    public Noise(boolean symmetric){
        this.symmetric = symmetric;
    }

    @Override
    public float getInterpolation(float input) {
        if (symmetric)
            return (float) Math.random() - 0.5f;
        else
            return (float) Math.random();
    }
}
