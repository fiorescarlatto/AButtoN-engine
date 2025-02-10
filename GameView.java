package com.abutton.game;

import com.abutton.game.base.view.DefaultGameDisplay;
import com.abutton.game.base.view.DefaultGameInput;
import com.abutton.game.base.view.DefaultGameState;

/**
 * Created by Dragonz on 17/07/2015.
 * Class that contains one instance of a State, Display and Input
 * to easily set a completely new content in a Game
 */
public class GameView {

    private GameStateLoader loader;
    private GameState   state;
    private GameDisplay display;
    private GameInput   input;

    /**
     * Creates a new {@link GameView} object. <br>
     * This method should be called from a derived class, to give an easier access to views. <br>
     * All of the parameters of this method are nullable, meaning that if it's null, the default
     * version of that Object will be created and set in the view instead.
     * @param loader {@link GameStateLoader}, used to load the GameState.
     * @param state {@link GameState}, that holds all of the view's data and information.
     * @param display {@link GameDisplay} that is able to correctly show on the screen the GameState.
     * @param input {@link GameInput} that receives and dispatches touch and input events.
     */
    public GameView (GameStateLoader loader, GameState state, GameDisplay display, GameInput input) {
        if (state == null)   state   = new DefaultGameState();
        if (display == null) display = new DefaultGameDisplay();
        if (input == null)   input   = new DefaultGameInput();

        this.loader  = loader;
        this.state   = state;
        this.display = display;
        this.input   = input;

        state.setLoader(loader);
    }

    public GameStateLoader getLoader()  { return loader; }
    public GameState   getState()   { return state; }
    public GameDisplay getDisplay() { return display; }
    public GameInput   getInput()   { return input; }
}
