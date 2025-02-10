package com.abutton.game.base.animation;

import com.abutton.game.Game;
import com.abutton.game.GameState;
import com.abutton.game.base.animation.interpolation.Interpolation;
import com.abutton.game.base.animation.interpolation.Interpolator;
import com.abutton.game.base.animation.property.*;
import com.abutton.game.base.element.Updateable;

/**
 * Created by Dragonz on 29/07/2015.
 * Class used to animate elements
 */
public class Animation implements Updateable{

    public interface Callback {
        void onAnimationEnd(Animation animation);
        void onAnimationLoop(Animation animation);
        void onAnimationStart(Animation animation);
    }

    private Animation next;
    private Animation builder;

    private Animation.Callback callback;
    private Interpolator       interpolator;
    private PropertyChanger    propertyChanger;

    private int loop;

    private long begin;
    private long elapsed;
    private long duration;
    private long offset;

    private Object object;

    private float initialPropriety;
    private float finalPropriety;

    private boolean initialProprietySet;

    /**
     * Creates a new animation of the given object and adds it to the current Layer
     * so that it will be automatically updated each frame.
     */
    public static Animation of(Object o) {
        Animation a = new Animation(o);
        Game.getLayer().add(a);
        return a;
    }

    /**
     * Creates a new animation of the given object.
     */
    public Animation(Object object) {
        this.next = null;
        this.builder = this;

        this.callback        = null;
        this.interpolator    = Interpolation.Linear();
        this.propertyChanger = null;

        this.loop = 0;

        this.begin    = 0;
        this.elapsed  = 0;
        this.duration = 0;
        this.offset   = 0;

        this.object = object;

        this.initialPropriety = 0;
        this.finalPropriety   = 0;

        this.initialProprietySet = false;
    }
    private Animation(Animation a, boolean after) {
        // creates a new animation from the animated object.
        this(a.object);

        // copies common properties.
        this.callback     = a.callback;
        this.interpolator = a.interpolator;

        this.loop = a.loop;

        this.duration = a.duration;
        this.offset   = a.offset;

        // updates the offset if this animation has to be played after the given animation
        if (after) this.offset = a.duration + a.offset;
    }

    public boolean isLoop() {
        return (loop != 0);
    }
    public boolean isPaused() {
        return (elapsed != 0);
    }
    public boolean isStopped() {
        return (begin == 0);
    }

    public long getDuration() {
        return duration;
    }
    public long getElapsed() {
        if (isStopped())
            return duration+offset;
        else
            return (Game.getTime() - begin);
    }
    public long getOffset() {
        return offset;
    }
    public Object getObject() {
        return object;
    }

    public Animation setCallback(Animation.Callback callback) {
        builder.callback = callback;
        return this;
    }
    public Animation setDuration(long duration) {
        builder.duration = duration;
        return this;
    }
    public Animation setFinalPropriety(float finalPropriety) {
        builder.finalPropriety = finalPropriety;
        return this;
    }
    public Animation setInitialPropriety(float initialPropriety) {
        builder.initialPropriety = initialPropriety;
        builder.initialProprietySet = true;
        return this;
    }
    public Animation setInterpolator(Interpolator interpolator) {
        builder.interpolator = interpolator;
        return this;
    }
    public Animation setLoop(int loop) {
        builder.loop = loop;
        return this;
    }
    public Animation setOffset(long offset) {
        builder.offset = offset;
        return this;
    }

    public Animation colorShift() {
        return colorShift(false);
    }
    public Animation colorShift(boolean after) {
        return buildNextAnimation(new ColorShift(object), after);
    }

    public Animation fade() {
        return fade(false);
    }
    public Animation fade(boolean after) {
        return buildNextAnimation(new Fade(object), after);
    }

    public Animation hueShift() {
        return hueShift(false);
    }
    public Animation hueShift(boolean after) {
        return buildNextAnimation(new HueShift(object), after);
    }

    public Animation resizeH() {
        return resizeH(false);
    }
    public Animation resizeH(boolean after) {
        return buildNextAnimation(new ResizeH(object), after);
    }

    public Animation resizeW() {
        return resizeW(false);
    }
    public Animation resizeW(boolean after) {
        return buildNextAnimation(new ResizeW(object), after);
    }

    public Animation rotate() {
        return rotate(false);
    }
    public Animation rotate(boolean after) {
        return buildNextAnimation(new Rotate(object), after);
    }

    public Animation translateX() {
        return translateX(false);
    }
    public Animation translateX(boolean after) {
        return buildNextAnimation(new TranslateX(object), after);
    }

    public Animation translateY() {
        return translateY(false);
    }
    public Animation translateY(boolean after) {
        return buildNextAnimation(new TranslateY(object), after);
    }

    /**
     * Pauses the animation until resume(), start() or stop() are called.
     */
    public void pause() {
        if (next != null) next.pause();
        if (isPaused()) return;

        elapsed = Game.getTime() - begin;
    }
    /**
     * Resumes the current paused animation.
     */
    public void resume() {
        if (next != null) next.resume();
        if (!isPaused()) return;

        begin   = Game.getTime() - elapsed;
        elapsed = 0;
    }
    /**
     * Starts the current animation from the beginning.
     */
    public void start() {
        if (next != null) next.start();

        begin   = Game.getTime();
        elapsed = 0;

        if (callback != null)
            callback.onAnimationStart(this);
    }
    /**
     * Stops the current animation.
     */
    public void stop () {
        if (next != null) next.stop();

        begin   = 0;
        elapsed = 0;

        if (callback != null)
            callback.onAnimationEnd(this);
    }

    @Override
    public void update(GameState state) {
        update();
    }

    public void update() {
        if (next != null) next.update();
        if (isPaused() || isStopped()) return;

        if (getElapsed() >= (getDuration() + getOffset())) {
            if (isLoop()) {
                begin += duration;
                loop--;
                if (callback != null)
                    callback.onAnimationLoop(this);
            } else {
                begin   = 0;
                elapsed = 0;

                if (callback != null)
                    callback.onAnimationEnd(this);
            }
        }

        if (getElapsed() >= getOffset()) {
            float propriety = (finalPropriety - initialPropriety);
            float time = (float) (getElapsed() - getOffset()) / (float) getDuration();

            time = interpolator.getInterpolation(time);
            setProperty(initialPropriety + (propriety * time));
        }
    }

    private Animation buildNextAnimation(PropertyChanger p, boolean after) {
        if (builder.propertyChanger != null) {
            builder.next = new Animation(builder, after);
            builder = builder.next;
        }

        builder.propertyChanger = p;
        if (!builder.initialProprietySet)
            builder.initialPropriety = builder.getProperty();

        return this;
    }

    private void  setProperty(float propriety) {
        propertyChanger.setProperty(object, propriety);
    }
    private float getProperty(){
        return propertyChanger.getProperty(object);
    }
}
