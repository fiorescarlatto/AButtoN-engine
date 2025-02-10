package com.abutton.game;

/**
 * Created by Dragonz on 21/06/2015.
 * The Main thread which contains the game loop. The thread must have access to
 * the surface view and holder to trigger events every game tick.
 */

public final class GameThread extends Thread {
    /* Tried to keep this class very simple */

    public GameThread()
    {
        super();
    }

    @Override
    public void run() {
        super.run();

        // loops as long as the game is running
        while (Game.isRunning()) {
            // updates the game state
            Game.update();
            // renders the game
            Game.render();
            // keeps the update rate constant
            Game.synchronize();
        }

    }
}
