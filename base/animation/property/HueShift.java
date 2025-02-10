package com.abutton.game.base.animation.property;

import com.abutton.game.base.element.Colorable;
import com.abutton.game.utility.MathUtils;

/**
 * Created by Dragonz on 16/08/2015. <br>
 * Property manager for Colorable objects
 */
public class HueShift implements PropertyChanger {

    public HueShift(Object o) {
        if (!(o instanceof Colorable))
            throw new ClassCastException("unable to apply HueShift on this object as it's not implementing Colorable.");
    }

    @Override
    public void setProperty(Object object, float propriety) {
        float[] hsv = MathUtils.getHSV(((Colorable) object).getColor());
        ((Colorable) object).setColor(MathUtils.hsv(propriety, hsv[1], hsv[2]));
    }

    @Override
    public float getProperty(Object object) {
        float[] hsv = MathUtils.getHSV(((Colorable) object).getColor());
        return hsv[0];
    }
}
