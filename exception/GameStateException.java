package com.abutton.game.exception;

/**
 * Created by Dragonz on 29/06/2015.
 * exception that will be thrown if the display / input / loader are incompatible with the state
 */
public class GameStateException extends IllegalArgumentException {
    public GameStateException() {
        super();
    }
    public GameStateException(String message) {
        super(message);
    }
}
