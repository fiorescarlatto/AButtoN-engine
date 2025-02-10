package com.abutton.game.base.element;

import com.abutton.game.event.CollisionEvent;

/**
 * Created by Dragonz on 31/07/2015.
 * Interface that allows elements to be reactive to collisions (???)
 */
public interface Collideable extends Geometry {
    void onCollision(CollisionEvent event);
    //todo FINISH THE COLLISION MANAGER DAMNIT!!
}
