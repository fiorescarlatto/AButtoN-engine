package com.abutton.game.base.animation.property;

import com.abutton.game.base.element.Colorable;

/**
 * Created by Dragonz on 16/08/2015. <br>
 * Property manager for Colorable objects
 */
public class ColorShift implements PropertyChanger {

    public ColorShift(Object o) {
        if (!(o instanceof Colorable))
            throw new ClassCastException("unable to apply ColorShift on this object as it's not implementing Colorable.");
    }

    @Override
    public void setProperty(Object object, float propriety) {
        ((Colorable) object).setColor((int) propriety);
    }

    @Override
    public float getProperty(Object object) {
        return ((Colorable) object).getColor();
    }
}
