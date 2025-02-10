package com.abutton.game.utility;

import android.graphics.Color;

/**
 * Created by Dragonz on 30/07/2015.
 * Utility functions
 */
@SuppressWarnings("unused")
public final class MathUtils {

    private MathUtils() {}

    public static int constrain(int value, int min, int max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }
    public static double constrain(double value, double min, double max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    public static int hsv(float hue, float saturation, float value) {
        float[] hsv = {hue, saturation, value};
        return Color.HSVToColor(hsv);
    }

    public static float[] getHSV(int color){
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        return hsv;
    }

    public static float getHue(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        return hsv[0];
    }
    public static float getSaturation(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        return hsv[1];
    }
    public static float getValue(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        return hsv[3];
    }
}
