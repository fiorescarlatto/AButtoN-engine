package com.abutton.game.base.animation.interpolation;

/**
 * Created by Dragonz on 02/09/2015.
 * Interpolator for an elastic animation.
 */
public class RubberBand implements Interpolator {

    private float elasticity;

    public RubberBand(){
        this(0.20f);
    }
    public RubberBand(float elasticity) {
        this.elasticity = elasticity;
    }

    @Override
    public float getInterpolation(float input) {
        float frequency = (float) (Math.PI / 2 + Math.PI / 2 * elasticity);
        return (float) ((1/Math.sin(frequency)) * Math.sin((frequency)*input));
    }
}
