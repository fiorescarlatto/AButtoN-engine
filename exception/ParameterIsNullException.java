package com.abutton.game.exception;

/**
 * Created by Dragonz on 06/08/2015. <br>
 * Exception
 */
public class ParameterIsNullException extends RuntimeException {
    public ParameterIsNullException() {
        super();
    }
    public ParameterIsNullException(String message) {
        super(message);
    }
}
