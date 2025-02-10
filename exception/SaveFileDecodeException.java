package com.abutton.game.exception;

import java.io.IOException;

/**
 * Created by Dragonz on 18/09/2015.
 * This exception is thrown by SaveFile s when the decoder is unable to decode the given file
 * (proceed with caution, loss of data may occur)
 */
public class SaveFileDecodeException extends IOException {
    public SaveFileDecodeException() {super();}
    public SaveFileDecodeException(String message) {super(message);}
}
