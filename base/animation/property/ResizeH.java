package com.abutton.game.base.animation.property;

import com.abutton.game.base.element.Resizeable;

/**
 * Created by Dragonz on 16/08/2015. <br>
 * Property manager for Resizeable objects.
 */
public class ResizeH implements PropertyChanger {

    public ResizeH(Object o) {
        if (!(o instanceof Resizeable))
            throw new ClassCastException("unable to apply ResizeH on this object as it's not implementing Resizeable.");
    }

    @Override
    public void setProperty(Object object, float propriety) {
        ((Resizeable) object).setSize(((Resizeable) object).getWidth(), propriety);
    }

    @Override
    public float getProperty(Object object) {
        return ((Resizeable) object).getHeight();
    }
}
