package com.abutton.game.base.element;

import com.abutton.game.API.Blending;

/**
 * Created by Dragonz on 31/07/2015. <br>
 * Interface representing an object that can change blending mode. <br>
 * This allows the object to be multiplied, added, subtracted, screened... when drawn onto the
 * screen.
 */
public interface Blendable {
    /**
     * Sets the new blending mode.
     * @param mode this parameter defines which mode has to be used to blend the object. <br>
     *             it can be (from the enum {@link Blending}): <ul>
     *             <li>ADD</li>
     *             <li>CLEAR</li>
     *             <li>DARKEN</li>
     *             <li>DST</li>
     *             <li>DST_ATOP</li>
     *             <li>DST_IN</li>
     *             <li>DST_OUT</li>
     *             <li>DST_OVER</li>
     *             <li>LIGHTEN</li>
     *             <li>MULTIPLY</li>
     *             <li>OVERLAY</li>
     *             <li>SCREEN</li>
     *             <li>SRC</li>
     *             <li>SRC_ATOP</li>
     *             <li>SRC_IN</li>
     *             <li>SRC_OUT</li>
     *             <li>SRC_OVER</li>
     *             <li>XOR</li>
     *             </ul>
     * @see #getBlendMode()
     */
    void setBlendMode(Blending mode);

    /**
     * Retrieves the current blend mode
     * @return {@link Blending} that describes current blending mode.
     * @see #setBlendMode(Blending)
     */
    Blending getBlendMode();
}
