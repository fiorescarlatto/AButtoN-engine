package com.abutton.game.API;

import android.graphics.Paint;

/**
 * Created by Dragonz on 12/08/2015. <br>
 * Utility class to hold Align types
 */
public enum Align {
    LEFT(Paint.Align.LEFT),
    RIGHT(Paint.Align.RIGHT),
    CENTER(Paint.Align.CENTER);

    private Paint.Align alignment;

    Align(Paint.Align alignment) {
        this.alignment = alignment;
    }

    public Paint.Align get() {
        return alignment;
    }
}
