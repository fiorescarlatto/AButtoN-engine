package com.abutton.game.API;

import android.graphics.PorterDuff;

/**
 * Created by Dragonz on 11/08/2015. <br>
 * Support class for the implementation of blending modes.
 */
@SuppressWarnings("unused")
public enum Blending {
    ADD(PorterDuff.Mode.ADD),
    CLEAR(PorterDuff.Mode.CLEAR),
    DARKEN(PorterDuff.Mode.DARKEN),
    DST(PorterDuff.Mode.DST),
    DST_ATOP(PorterDuff.Mode.DST_ATOP),
    DST_IN(PorterDuff.Mode.DST_IN),
    DST_OUT(PorterDuff.Mode.DST_OUT),
    DST_OVER(PorterDuff.Mode.DST_OVER),
    LIGHTEN(PorterDuff.Mode.LIGHTEN),
    MULTIPLY(PorterDuff.Mode.MULTIPLY),
    OVERLAY(PorterDuff.Mode.OVERLAY),
    SCREEN(PorterDuff.Mode.SCREEN),
    SRC(PorterDuff.Mode.SRC),
    SRC_ATOP(PorterDuff.Mode.SRC_ATOP),
    SRC_IN(PorterDuff.Mode.SRC_IN),
    SRC_OUT(PorterDuff.Mode.SRC_OUT),
    SRC_OVER(PorterDuff.Mode.SRC_OVER),
    XOR(PorterDuff.Mode.XOR);

    private PorterDuff.Mode mode;

    Blending(PorterDuff.Mode mode) {
        this.mode = mode;
    }

    public PorterDuff.Mode get() {
        return mode;
    }
}
