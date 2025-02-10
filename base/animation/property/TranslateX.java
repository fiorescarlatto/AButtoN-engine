package com.abutton.game.base.animation.property;

import com.abutton.game.base.element.Moveable;

/**
 * Created by Dragonz on 16/08/2015. <br>
 * Property manager for Moveable objects.
 */
public class TranslateX implements PropertyChanger {

    public TranslateX(Object o) {
        if (!(o instanceof Moveable))
            throw new ClassCastException("unable to apply TranslateX on this object as it's not implementing Moveable.");
    }

    @Override
    public void setProperty(Object object, float propriety) {
        ((Moveable) object).setPos(propriety, ((Moveable) object).getY());
    }

    @Override
    public float getProperty(Object object) {
        return ((Moveable) object).getX();
    }
}
