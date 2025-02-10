package com.abutton.game.base.animation.property;

/**
 * Created by Dragonz on 16/08/2015. <br>
 * Interface dedicated to changing single float properties of objects allowing the creation
 * of an animator for that property.
 */
public interface PropertyChanger {
    /**
     * Retrieves a property from an object. This property must be either float or a property
     * that can be described by a float value.
     * @param object The object whose property has to be retrieved.
     *               This object has to be casted into the appropriate type before operating.
     * @return float, the property that has to be retrieved.
     * @see #setProperty(Object, float)
     */
    float getProperty(Object object);
    /**
     * Sets a property into an object. This property must be either float or a property
     * that can be described by a float value.
     * @param object The object whose property has to be set.
     *               This object has to be casted into the appropriate type before operating.
     * @param property float containing the new property to set in the object.
     *                  This property should correspond to the property retrieved in the other
     *                  method {@code getProperty(Object)}
     * @see #getProperty(Object)
     */
    void setProperty(Object object, float property);
}
