package com.abutton.game.base.animation.property;

import com.abutton.game.base.element.Resizeable;

/**
 * Created by Dragonz on 16/08/2015.<br>
 * Property manager for Resizeable objects
 */
public class ResizeW implements PropertyChanger {

    public ResizeW(Object o) {
        if (!(o instanceof Resizeable))
            throw new ClassCastException("unable to apply ResizeW on this object as it's not implementing Resizeable.");
    }

    @Override
    public void setProperty(Object object, float propriety) {
        ((Resizeable) object).setSize(propriety, ((Resizeable) object).getHeight());
    }

    @Override
    public float getProperty(Object object) {
        return ((Resizeable) object).getWidth();
    }
}
