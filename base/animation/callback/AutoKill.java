package com.abutton.game.base.animation.callback;

import com.abutton.game.Game;
import com.abutton.game.base.animation.Animation;

/**
 * Created by Dragonz on 18/09/2015.
 * Animation callback used to make the animation automatically kill itself upon it's end.
 */
public class AutoKill implements Animation.Callback {
    @Override
    public void onAnimationEnd(Animation animation) {
        Game.getLayer().remove(animation);
    }

    @Override
    public void onAnimationLoop(Animation animation) {

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }
}
