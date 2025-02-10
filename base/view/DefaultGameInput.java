package com.abutton.game.base.view;

import android.view.MotionEvent;

import com.abutton.game.GameInput;

/**
 * Created by Dragonz on 23/06/2015.
 * This class ignores all input received from the game.
 * Never use if not for debug purposes.
 */
public class DefaultGameInput extends GameInput {
    /* Default class, use only for debug */

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onTouch(MotionEvent event) {
        // ignores touch events
    }
}
