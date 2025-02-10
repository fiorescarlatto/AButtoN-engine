package com.abutton.game.base.animation.interpolation;

/**
 * Created by Dragonz on 29/07/2015. <br>
 * Interpolator factory
 */
public class Interpolation {
    public static Interpolator Harmonic() {
        return new Harmonic();
    }

    public static Interpolator Harmonic(float frequency, float phase) {
        return new Harmonic(frequency, phase);
    }

    public static Interpolator Linear() {
        return new Linear();
    }

    public static Interpolator Noise() {
        return new Noise();
    }

    public static Interpolator Noise(boolean symmetric) {
        return new Noise(symmetric);
    }

    public static Interpolator Quadratic() {
        return new Quadratic();
    }

    public static Interpolator Quadratic(boolean reverse) {
        return new Quadratic(reverse);
    }

    public static Interpolator RubberBand() {
        return new RubberBand();
    }

    /**
     * Creates a rubber band interpolator with the given elasticity.
     * 0 < elasticity < 1.0
     */
    public static Interpolator RubberBand(float elasticity) {
        return new RubberBand(elasticity);
    }
}
