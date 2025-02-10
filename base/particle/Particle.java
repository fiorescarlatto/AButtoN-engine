package com.abutton.game.base.particle;

import com.abutton.game.base.element.Displayable;
import com.abutton.game.base.element.Updateable;

/**
 * Created by Dragonz on 30/07/2015. <br>
 * Interface that defines the behaviour of a simple particle
 */
public interface Particle extends Displayable, Updateable {
    /**
     * Retrieves whether the particle has finished it's life cycle, meaning it's ready to be
     * destroyed.
     * @return boolean, true if the particle has finished it's life cycle, false otherwise.
     */
    boolean hasFinished();
}
