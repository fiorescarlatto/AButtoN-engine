package com.abutton.game.base.animation.property;

import com.abutton.game.base.element.Rotatable;

/**
 * Created by Dragonz on 16/08/2015. <br>
 * Property manager for Rotatable objects.
 */
public class Rotate implements PropertyChanger {

    public Rotate(Object o) {
        if (!(o instanceof Rotatable))
            throw new ClassCastException("unable to apply HueShift on this object as it's not implementing Colorable.");
    }

    @Override
    public void setProperty(Object object, float propriety) {
        ((Rotatable) object).setAngle(propriety);
    }

    @Override
    public float getProperty(Object object) {
        return ((Rotatable) object).getAngle();
    }
}
