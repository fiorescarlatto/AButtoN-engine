package com.abutton.game.base.element;

/**
 * Created by Dragonz on 05/08/2015. <br>
 * Interface that defines the behaviour of a Button. <br>
 * A Button is defines as a {@link BaseElement} that is also {@link Touchable}, meaning it has a
 * {@code onTouch(MotionEvent event)} method that has to be implemented.
 */
public interface Button extends BaseElement, Touchable {
    /**
     * Checks if the {@link Button} is being pressed or not. <br>
     * {@code isPressed()} can not be always reliable when it is used inside of the
     * {@code onTouch(...)} method, and it's use in such situation is discouraged.
     * @return boolean, true if the {@link Button} is being pressed, false otherwise.
     */
    boolean isPressed();
}
