package com.abutton.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.abutton.game.base.element.Displayable;
import com.abutton.game.base.element.Touchable;
import com.abutton.game.base.element.Updateable;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Dragonz on 16/08/2015. <br>
 * Utility class that encapsulates a List and only shows useful methods
 * and is secure to use with multi threads.
 */
public final class GameLayer {

    private int maxInput;
    private LinkedList[] active;
    private LinkedList<Object> layer;

    public GameLayer() {
        this(15);
    }
    public GameLayer(int maxInput){
        this.maxInput = maxInput;

        this.layer  = new LinkedList<>();
        this.active = new LinkedList[maxInput];
    }

    /**
     * Returns if this {@code Layer} contains no elements. This implementation
     * tests, whether {@code size} returns 0.
     * @return {@code true} if this {@code Layer} has no elements, {@code false}
     *         otherwise.
     * @see #size
     */
    @SuppressWarnings("unused")
    public synchronized boolean isEmpty() {
        return (size() == 0);
    }

    /**
     * The number of elements in this {@code Layer}.
     * @return the number of elements in this {@code Layer}.
     */
    public synchronized int size() {
        return layer.size();
    }

    /**
     * Searches this {@code Layer} for the specified object.
     * @param o the object to search for.
     * @return {@code true} if {@code object} is an element of this
     *         {@code Layer}, {@code false} otherwise
     */
    public synchronized boolean contains(Object o) {
        return layer.contains(o);
    }

    /**
     * Adds the specified object at the end of this {@code Layer}.
     * @param o the object to add.
     */
    public synchronized GameLayer add(Object o) {
        if (o instanceof Displayable || o instanceof Touchable || o instanceof Updateable)
            layer.add(o);
        return this;
    }

    /**
     * Removes the first occurrence of the specified object in this {@code Layer}.
     * @param o the object to remove.
     */
    public synchronized GameLayer remove(Object o) {
        layer.remove(o);
        return this;
    }

    /**
     * Removes all elements from this {@code Layer}, leaving it empty.
     * @see #isEmpty
     * @see #size
     */
    public synchronized void clear() {
        layer.clear();
        for (int i = 0; i < maxInput; i++)
            active[i] = null;
    }

    /**
     * Retrieves any {@link Touchable} object positioned at that location in the {Layer}.
     * @param x float, x position of the object
     * @param y float, y position of the object
     * @return {@link LinkedList<Touchable>}, an empty list if there aren't any object positioned
     *         at that location.
     */
    public synchronized LinkedList<Touchable> get(float x, float y) {
        LinkedList<Touchable> focused = new LinkedList<>();

        for (Object t : layer)
            if (t instanceof Touchable && ((Touchable) t).contains(x, y))
                focused.add((Touchable)t);

        return focused;
    }

    /**
     * The Displayable positioned at that 'Z' location (index) in the {@code Layer}.
     * @param z int, position of the {@code Object} to retrieve.
     * @return {@link Object} positioned at the given Z coordinate.
     */
    public synchronized Object get(int z) {
        return layer.get(z);
    }

    /**
     * Retrieves the 'Z' position of the given {@link Object} (index) in the {@code Layer}.
     * @param o The object which position is to retrieve.
     * @return int containing the Z position of the object, -1 if the object is not found.
     */
    public synchronized int getZ(Object o) {
        int location = 0;

        for (Object object : layer)
            if (object != o) location++;
            else return location;

        return -1;
    }

    /**
     * Retrieves if the given object is currently focused and by which pointerID
     * @param touchable Touchable which focus has to be tested.
     * @return int, id of pointer focused on the touchable or -1 if no pointer is focused on the
     *         given Touchable.
     */
    public int getFocusedPointerId(Touchable touchable) {
        for (int id = 0; id < maxInput; id++) {
            if (active[id] != null && active[id].contains(touchable))
                return id;
        }
        return -1;
    }

    /**
     * Automatically interacts with any Touchable objects contained in this {@code Layer}.
     * @param event {@link MotionEvent} describing the received input event.
     */
    public void interact(MotionEvent event) {
        int index = event.getActionIndex();
        int id = event.getPointerId(index);

        // ignores pointer exceeding the maximum pointers limit.
        if (maxInput <= id) return;

        // sends an update to all of the focused Touchable on motion events.
        if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            for (int i = 0; i < event.getPointerCount() && i < maxInput; i++) {
                id = event.getPointerId(i);
                // sends the onTouch event to the focused Touchable.
                if (active[id] != null)
                    for (Object t : active[id]) if (((Touchable) t).onTouch(event)) break;
            }
            return;
        }

        // locks the focus for the active pointer incoming input stream on the pressed Touchable.
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN ||
                event.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN)
        {
            active[id] = get(event.getX(index), event.getY(index));
        }

        // if user didn't press an empty area sends an onTouch event.
        if (active[id] != null)
            for (Object t : active[id]) if (((Touchable) t).onTouch(event)) break;

        // release the focus for the active pointer.
        if (event.getActionMasked() == MotionEvent.ACTION_UP ||
                event.getActionMasked() == MotionEvent.ACTION_POINTER_UP)
        {
            active[id] = null;
        }
    }

    /**
     * Draws all of the {@link Displayable} elements contained in this {@code Layer} onto the
     * specified Canvas.
     * @param canvas canvas on which to draw all these elements.
     */
    public synchronized void draw(Canvas canvas) {
        Iterator<Object> i = layer.descendingIterator();
        Object d;

        while (i.hasNext()) {
            d = i.next();
            if (d instanceof Displayable)
                ((Displayable) d).display(canvas);
        }
    }

    /**
     * Updates all of the {@link Updateable} elements contained in this {@code Layer} with the
     * specified state.
     * @param state state to use to update these elements.
     */
    public synchronized void update(GameState state) {
        for (Object u : layer)
            if (u instanceof Updateable)
                ((Updateable) u).update(state);
    }

    @SuppressWarnings("unchecked")
    public synchronized LinkedList filter(Class filter) {
        LinkedList<Object> list = new LinkedList<>();

        for (Object o : layer)
            if (filter.isAssignableFrom(o.getClass())) list.push(o);

        return list;
    }
}