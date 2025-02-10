package com.abutton.game.base.element;

import android.view.MotionEvent;

/**
 * Created by Dragonz on 16/09/2015.
 */
public interface Interactable extends Geometry{
    void onPress(MotionEvent event);

    void onTouch(MotionEvent event);

    void onDoubleTouch(MotionEvent event);

    void onLongTouch(MotionEvent event, long duration);

    void onSwipe(MotionEvent event, float velocityX, float velocityY);

    void onDrag(MotionEvent event, float distanceX, float distanceY);

    void onDrop(MotionEvent event, Object o);

    void onPinch(MotionEvent event, float centerX, float centerY, float zoom);
}
