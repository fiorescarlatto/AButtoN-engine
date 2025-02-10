package com.abutton.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Dragonz on 21/06/2015.
 * This is the main surface that handles the onTouch events and draws
 * the image to the screen.
 */
public final class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    // tells whether this surface has been detached from its view.
    private boolean detached;
    // thread that runs the game instance
    private GameThread thread;

    // constructor
    public GameSurface (Context context) {
        super(context);

        setBackgroundColor(Color.TRANSPARENT);
        // adds the callback (this) to the surface holder to intercept events
        getHolder().addCallback(this);
        // make the GameSurface focusable so it can handle events
        setFocusable(true);

        // this surface is new.
        detached = false;
        // creates the game loop thread
        thread   = new GameThread();
    }

    public boolean isDetached() {
        return detached;
    }

    void start() {
        // if the thread died (when the surface was killed) it creates a new thread
        if (thread.getState() == Thread.State.TERMINATED)
            thread = new GameThread();

        // starts the thread.
        if (!thread.isAlive())
            thread.start();
    }

    void stop() {
        // tell the thread to shut down and wait for it to finish
        boolean retry = true;

        while (retry){
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException ex) {
                // the tread didn't shut down
                // try again shutting down the thread
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Game.getDisplay().onSet();
        Game.getInput()  .onSet();
        Game.getState()  .onSet();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // tells the game state that current size has changed
        Game.getState().sizeChanged(width, height);

        // start is called here to ensure the surface has already been created and resized.
        // no conditions here because if the game is already started, nothing happens
        // when calling this.
        Game.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        detached = true;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        // passes the event to the game's input manager
        Game.getInput().onTouchEvent(event);
        // returns the superclass touch event [as spec in the doc]
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // this code should never get here.
    }
}
