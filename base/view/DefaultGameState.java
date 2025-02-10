package com.abutton.game.base.view;

import com.abutton.game.GameState;

/**
 * Created by Dragonz on 17/07/2015.
 * This state does not contain anything and ignores all
 * update requests received from the game.
 * Never use if not for debug purposes.
 */
public class DefaultGameState extends GameState {

    @Override
    public void update() {
        // nothing to do here
    }
}
