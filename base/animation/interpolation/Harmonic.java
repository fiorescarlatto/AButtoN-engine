package com.abutton.game.base.animation.interpolation;

/**
 * Created by Dragonz on 29/07/2015.
 * Harmonic Interpolation (a full wave)
 */
public class Harmonic implements Interpolator {

    private float frequency;
    private float phase;

    public Harmonic() {
        this(2, 0);
    }
    public Harmonic(float frequency, float phase){
        this.frequency = frequency;
        this.phase = phase;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) Math.sin(frequency * Math.PI * input + phase);
    }
}
