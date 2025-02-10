package com.abutton.game.base.element;

/**
 * Created by Dragonz on 30/07/2015. <br>
 * Interface defining the behaviour of an object that can change its opacity over time.
 */
public interface Transparent {
    /**
     * Retrieves the current alpha component of an object
     * @return Integer between 0 and 255 describing the amount of 'opacity' of the object. <br>
     *         Example: <ul>
     *             <li>An alpha of 255 means the object is completely visible.</li>
     *             <li>An alpha of 0 means the object is completely transparent.</li>
     *         </ul>
     */
    int  getAlpha();
    /**
     * Sets the new alpha component of the object
     * @param alpha Integer value between 0 and 255 describing the amount of 'opacity' of the
     *              object, 255 meaning that the object is fully visible. <br>
     *              If alpha is lower than 0 or bigger than 255 it will be set to 0 or 255
     *              accordingly.
     */
    void setAlpha(int alpha);
}
