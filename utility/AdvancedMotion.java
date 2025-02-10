package com.abutton.game.utility;

import android.graphics.PointF;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Dragonz on 17/07/2015.
 * Class used to analyze MotionEvent objects to determine whether they are
 * particular types of input, such as Swipe, Touch and Long touch
 */

public class AdvancedMotion {

    private static final long MAX_SAMPLE_TIME = 400;

    private static AdvancedMotion lastMotion;
    private static AdvancedMotion currentMotion;

    public enum Type{
        NONE,
        UNDEFINED,

        PINCH,
        STROKE,
        SWIPE,
        TOUCH,
        TOUCH_DOUBLE,
        TOUCH_LONG
    }

    private Type type;

    private float speed;
    private float averageSpeed;
    private float direction;
    private float length;
    private float distance;
    private float rotation;
    private float zoom;

    private long begin;
    private long end;
    private long duration;

    private PointF initialPos;
    private PointF centerPos;
    private PointF finalPos;

    private boolean tracked;
    private boolean canceled;

    private AdvancedMotion() {
        type  = Type.UNDEFINED;

        speed     = 0;
        averageSpeed = 0;
        direction = 0;
        length    = 0;
        distance  = 0;
        rotation  = 0;
        zoom      = 0;

        begin    = 0;
        end      = 0;
        duration = 0;

        initialPos = new PointF(0,0);
        centerPos  = new PointF(0,0);
        finalPos   = new PointF(0,0);

        tracked  = false;
        canceled = false;
    }


    public boolean isCanceled() {
        return canceled;
    }
    public boolean isTracked() {
        return tracked;
    }
    public boolean isUndefined() {
        return (type == Type.UNDEFINED);
    }

    public Type getType() {
        return type;
    }
    public float getSpeed() {
        return speed;
    }
    public float getAverageSpeed() {
        return averageSpeed;
    }
    public float getDirection() {
        return direction;
    }
    public float getLength() {
        return length;
    }
    public float getDistance() {
        return distance;
    }
    public float getRotation() {
        return rotation;
    }
    public float getZoom() {
        return zoom;
    }

    public long getBegin() {
        return begin;
    }
    public long getEnd() {
        return end;
    }
    public long getDuration() {
        return duration;
    }

    public float getInitialX() {
        return initialPos.x;
    }
    public float getInitialY() {
        return initialPos.y;
    }
    public float getCenterX() {
        return centerPos.x;
    }
    public float getCenterY() {
        return centerPos.y;
    }
    public float getFinalX() {
        return finalPos.x;
    }
    public float getFinalY() {
        return finalPos.y;
    }

    public static AdvancedMotion getCurrentMotion() {
        if (currentMotion.isUndefined())
            return lastMotion;
        else
            return currentMotion;
    }

    public static void feed(MotionEvent e) {
        if (currentMotion == null || lastMotion == null) {
            currentMotion = new AdvancedMotion();
            lastMotion = new AdvancedMotion();
        }

        int index = e.getActionIndex();

        switch (e.getActionMasked()) {
            case MotionEvent.ACTION_MOVE:

            case MotionEvent.ACTION_POINTER_DOWN:

            case MotionEvent.ACTION_POINTER_UP:
                // ignore?

            case MotionEvent.ACTION_DOWN:
                startTracking(e);
                break;

            case MotionEvent.ACTION_UP:
                actionCompleted(e);
                break;

            case MotionEvent.ACTION_CANCEL:
                cancel();
                break;
        }

    }

    private static void startTracking(MotionEvent e)  {
        if (currentMotion.isUndefined())
        {
            currentMotion.tracked = true;
            currentMotion.begin = e.getEventTime();
            currentMotion.initialPos.x = e.getX(e.getActionIndex());
            currentMotion.initialPos.y = e.getY(e.getActionIndex());

        } else {
            if (currentMotion.type == Type.TOUCH) {
                currentMotion.type = Type.TOUCH_DOUBLE;
                flush();
            } else {
                cancel();
            }
        }
    }

    private static void actionCompleted(MotionEvent e) {
        if (currentMotion.isUndefined()) {
            if (currentMotion.isTracked()) {
                currentMotion.type = Type.TOUCH;
            } else {
                currentMotion.type = Type.TOUCH_DOUBLE;
            }
        }
    }


    private static void cancel() {
        currentMotion.canceled = true;
        flush();
    }

    private static void flush() {
        lastMotion = currentMotion;
        currentMotion = new AdvancedMotion();
    }
}
