package com.abutton.game.base.animation.property;

import com.abutton.game.base.element.Transparent;

/**
 * Created by Dragonz on 16/08/2015.
 * Property manager for Transparent objects.
 */
public class Fade implements PropertyChanger {

    public Fade(Object o) {
        if (!(o instanceof Transparent))
            throw new ClassCastException("unable to apply Fade on this object as it's not implementing Transparent.");
    }

    @Override
    public void setProperty(Object object, float propriety) {
        ((Transparent) object).setAlpha((int) propriety);
    }

    @Override
    public float getProperty(Object object) {
        return ((Transparent) object).getAlpha();
    }
}
