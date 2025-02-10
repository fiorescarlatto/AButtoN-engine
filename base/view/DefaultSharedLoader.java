package com.abutton.game.base.view;

import com.abutton.game.shared.SharedLoader;

/**
 * Created by Dragonz on 23/07/2015.
 * Debug class used to mock a SharedLoader
 * This SharedLoader does not load anything, do not use if not for
 * debug purposes.
 */
public class DefaultSharedLoader extends SharedLoader {

    @Override
    public void load() {
        // nothing to do here
    }

    @Override
    public void unload() {
        // nothing to do here
    }
}
