package com.abutton.game.base;

import android.view.MotionEvent;

import com.abutton.game.Game;
import com.abutton.game.base.element.BaseElement;
import com.abutton.game.base.element.BaseElementWrapper;
import com.abutton.game.base.element.Touchable;

/**
 * Created by Dragonz on 19/08/2015. <br>
 * Class used to easily make elements that can be dragged and dropped in the screen.
 */
public class Draggable extends BaseElementWrapper implements Touchable {

    /**
     * Creates a draggable element from a given element.
     * @param element {@link BaseElement} that will be made into a draggable.
     */
    public Draggable(BaseElement element) {
        super(element);
    }

    @Override
    public boolean onTouch(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            int id = Game.getLayer().getFocusedPointerId(this);

            for (int i = 0; i < event.getPointerCount(); i++)
                if (event.getPointerId(i) == id)
                    element.setPos(event.getX(i), event.getY(i));

            return true;
        }
        return false;
    }
}
