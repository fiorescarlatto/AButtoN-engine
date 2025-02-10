package com.abutton.game.base.button;

import android.view.MotionEvent;

import com.abutton.game.Game;
import com.abutton.game.base.element.BaseElement;
import com.abutton.game.base.element.BaseElementWrapper;

/**
 * Created by Dragonz on 12/08/2015. <br>
 * Generic implementation of a button.
 */
public class Button extends BaseElementWrapper implements com.abutton.game.base.element.Button {

    private static int globalID;
    private int ID;

    private boolean pressed;

    /**
     * Default constructor. <br>
     * Creates a button from the given {@link BaseElement}. <br>
     * Every button receives an unique ID that increases by one every time a new one is created.
     * @param element {@link BaseElement} that should be made into a button.
     */
    public Button(BaseElement element){
        super(element);

        this.pressed = false;
        this.ID = globalID++;
    }
    /**
     * Constructor. <br>
     * Creates a button from the given {@link BaseElement}. <br>
     * Every button receives an unique ID that increases by one every time a new one is created. <br>
     * This constructor allows to change the starting ID of a {@link Button}.
     * @param element {@link BaseElement} that should be made into a button.
     * @param initialID Integer, initial ID that should be set for the first {@link Button}.
     *                  (this one that is currently being created).
     */
    public Button(BaseElement element, int initialID) {
        super(element);

        this.pressed = false;

        globalID = initialID;
        this.ID = globalID++;
    }

    /**
     * Retrieves the ID of this {@link Button}. <br>
     * Every {@link Button} has a different and unique integer ID.
     * @return Integer ID of this {@link Button}.
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Checks if the {@link Button} is being pressed or not. <br>
     * {@code isPressed()} can not be always reliable when it is used inside of the
     * {@code onTouch(...)} method, and it's use in such situation is discouraged.
     * @return boolean, true if the {@link Button} is being pressed, false otherwise.
     */
    public boolean isPressed() {
        return pressed;
    }

    /**
     * Retrieves the {@link BaseElement} contained in this {@link Button}.
     * @return {@link BaseElement} that was used in the constructor to create this button.
     */
    public BaseElement getElement() {
        return element;
    }

    /**
     * Copies the proprieties of a given button: angle, origin, position, size and visibility.
     * @param button {@link Button} which proprieties have to be copied.
     */
    public void as(Button button) {
        this.setAngle  (button.getAngle());
        this.setOrigin (button.getOrigin().x, button.getOrigin().y);
        this.setPos    (button.getX(), button.getY());
        this.setSize   (button.getWidth(), button.getHeight());
        this.setVisible(button.isVisible());
    }

    /**
     * Defines the behaviour this {@link Button} shows when it is touched. <br>
     * When Overriding this method {@code super.onTouch(...)} should be called last, this is to
     * ensure the proper update of the "isPressed" flag
     * @param event MotionEvent describing the touch event happening upon the object.
     * @see #isPressed()
     */
    @Override
    public boolean onTouch(MotionEvent event) {

        int id = Game.getLayer().getFocusedPointerId(this);
        int index;

        if (id == -1) {
            pressed = false;
        } else {

            for (index = 0; index < event.getPointerCount(); index++)
                if (event.getPointerId(index) == id) break;

            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    pressed = false;
                    break;
                default:
                    pressed = contains(event.getX(index), event.getY(index));
            }

            return true;
        }
        return false;
    }
}
