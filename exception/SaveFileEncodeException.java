package com.abutton.game.exception;

import java.io.IOException;

/**
 * Created by Dragonz on 18/09/2015.
 * This exception is thrown by SaveFile s when the encoder is unable to write the given file
 * (proceed with caution, loss of data may occur)
 */
public class SaveFileEncodeException extends IOException {
    public SaveFileEncodeException() {super();}
    public SaveFileEncodeException(String message) {super(message);}
}