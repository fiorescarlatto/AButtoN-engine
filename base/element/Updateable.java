package com.abutton.game.base.element;

import com.abutton.game.GameState;

/**
 * Created by Dragonz on 30/07/2015. <br>
 * Defines ann object that can update its state by itself.
 */
public interface Updateable {
    /**
     * Updates the object inner state to it's next state.
     * Called when the object has to be updated.
     * @param state {@link GameState} describing the current global state of all of the objects,
     *              that can be displayed and or updated, and which could be in any way related to
     *              how this object is going to update itself.
     */
    void update(GameState state);
}
