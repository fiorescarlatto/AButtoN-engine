package com.abutton.game.base.view;

import com.abutton.game.GameView;

/**
 * Created by Dragonz on 31/07/2015.
 * Default game view
 */
public class DefaultGameView extends GameView {
    public DefaultGameView() {
        super(null, new DefaultGameState(), new DefaultGameDisplay(), new DefaultGameInput());
    }
}
