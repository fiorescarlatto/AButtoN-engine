package com.abutton.game.base.element;

/**
 * Created by Dragonz on 30/07/2015. <br>
 * Interface that defines an Element. <br>
 * An element is defined as a {@link Geometry} with an {@link Origin} that can move ({@link Moveable}), resize
 * ({@link Resizeable}) and rotate ({@link Rotatable}), relative to the Origin.
 */
public interface Element extends Geometry, Moveable, Resizeable, Rotatable, Origin {
}
