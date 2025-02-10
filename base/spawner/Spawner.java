package com.abutton.game.base.spawner;

import com.abutton.game.base.element.Displayable;
import com.abutton.game.base.element.Hideable;
import com.abutton.game.base.element.Moveable;
import com.abutton.game.base.element.Updateable;

/**
 * Created by Dragonz on 31/07/2015. <br>
 * Interface that defines an object that can spawn new elements.
 */
public interface Spawner extends Moveable, Displayable, Hideable, Updateable {
    /**
     * Spawns a new element. <br>
     * This element should be added into a list of particles that will be updated and displayed
     * when this spawner is updated and or displayed.
     */
    void spawn();

    /**
     * Spawns a new element at a certain position. <br>
     * This element should be added into a list of particles that will be updated and displayed
     * when this spawner is updated and or displayed.
     * @param x Float, x position of the particle that has to be spawned.
     * @param y Float, y position of the particle that has to be spawned.
     */
    void spawn(float x, float y);
}
