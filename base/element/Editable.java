package com.abutton.game.base.element;

import android.graphics.Typeface;

import com.abutton.game.API.Align;

/**
 * Created by Dragonz on 05/08/2015. <br>
 * Interface defining an object that has a text that can be edited.
 */
public interface Editable {
    /**
     * Retrieves the current text held by the object.
     * @return a String containing the text. <br>
     *         an empty String is returned if the object holds no text.
     */
    String getText();
    /**
     * Sets the text of the object to the new given text.
     * @param text String containing the new text to set.
     */
    void setText(String text);

    /**
     * Retrieves the current color of the text contained in the {@link Editable} object. <br>
     * Color is expressed in AARRGGBB.
     * @return int, color of the text.
     */
    int getTextColor();
    /**
     * Sets the text color of the text contained in the {@link Editable} object. <br>
     * Color is expressed in AARRGGBB.
     * @param color int, color of the text.
     */
    void setTextColor(int color);

    /**
     * Retrieves the size of the text contained in the {@link Editable} object. <br>
     * @return float, size of the text.
     */
    float getTextSize();
    /**
     * Sets the size of the text contained in the {@link Editable} object. <br>
     * @param size float, size of the text.
     */
    void setTextSize(float size);

    /**
     * Retrieves the alignment of the text contained in the {@link Editable} object. <br>
     * @return {@link Align} that describes text's alignment. <br>
     *         Text alignment can be either: LEFT, CENTER or RIGHT.
     */
    Align getTextAlign();
    /**
     * Sets the alignment of the text contained in the {@link Editable} object. <br>
     * @param alignment {@link Align} that describes text's alignment. <br>
     *                  Text alignment can be either: LEFT, CENTER or RIGHT.
     */
    void setTextAlign(Align alignment);

    /**
     * Sets the {@link Typeface} of the text contained in the object.
     * @param typeface {@link Typeface}.
     */
//    void setTypeface(Typeface typeface);

    /**
     * Sets the style of the text contained in this object.
     * @param bold boolean, tells whether the text should be drawn in bold.
     * @param underline boolean, tells whether the text should be underlined.
     * @param erase boolean, tells whether the text should be drawn with a line over it.
     */
//    void setTextFlags(boolean bold, boolean underline, boolean erase);

}
