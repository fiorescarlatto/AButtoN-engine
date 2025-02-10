package com.abutton.game.base.animation.callback;

import com.abutton.game.Game;
import com.abutton.game.base.animation.Animation;

/**
 * Created by Dragonz on 18/09/2015.
 * Animation callback used to switch the current view upon the animation's end.
 */
public class SwitchView implements Animation.Callback {

    private String viewID;

    public SwitchView(String viewID) {
        this.viewID = viewID;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Game.setView(viewID);
    }

    @Override
    public void onAnimationLoop(Animation animation) {

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }
}
