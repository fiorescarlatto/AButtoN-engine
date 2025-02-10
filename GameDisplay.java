package com.abutton.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Dragonz on 21/06/2015. <br>
 * Class that contains all the methods used to display the game state
 */
public abstract class GameDisplay {
    // color to use to fill the screen each time
    private int clearColor;

    // Display only attributes
    // surface holder that can access the physical surface
    private SurfaceHolder holder;

    /**
     * Constructor. <br>
     * Creates a new {@link GameDisplay}.
     */
    public GameDisplay() {
        this(0xFF000000);
    }
    public GameDisplay(int clearColor) {
        // sets the current displays' clear color.
        this.clearColor = clearColor;

        if (Game.isInitialized())
            this.holder = Game.getSurface().getHolder();
    }

    /**
     * Retrieves the value of the maximum number of frames per second this {@link GameDisplay}
     * will draw. <br>
     * By default this will always return 30. Override this method to set a different frame
     * rate for this {@link GameDisplay}.
     * @return int, maximum number of frames per seconds this display will draw (default = 30).
     */
    public int maxFPS() {
        return 30;
    }

    /**
     * Called by {@link Game} every time this display is set as the current display for the game.
     * If overriding this method {@code super.onSet()} needs to be called first.
     */
    public void onSet() {
        this.holder = Game.getSurface().getHolder();
    }

    /**
     * Returns the default background color of this {@link GameDisplay}.
     * @return int, color of the background.
     */
    @SuppressWarnings("unused")
    public int getClearColor() {
        return clearColor;
    }
    /**
     * Sets the default background color for this {@link GameDisplay}.
     * @param color color to set the default background to.
     */
    @SuppressWarnings("unused")
    public void setClearColor(int color) {
        this.clearColor = color;
    }

    /**
     * Package only visible method used to encapsulate {@code void draw(Canvas canvas)}.
     * This method automatically loads the current canvas and prepares it to be drawn onto.
     */
    void nextFrame()
    {
        // gets the canvas from the SurfaceHolder.
        Canvas canvas;
        if ((canvas = holder.lockCanvas()) != null)
        {
            // resets the matrix to the identity matrix
            canvas.setMatrix(null);

            // clears the canvas.
            clear(canvas);

            // draws all of the Displayable elements of this Display
            Game.getLayer().draw(canvas);
            // user defined draw function.
            draw(canvas);

            // displays the drawn canvas on the screen.
            holder.unlockCanvasAndPost(canvas);
        }
    }

    /**
     * Sets the whole canvas to the default color.
     * @param canvas {@link Canvas} to clear.
     * @see #setClearColor(int)
     * @see #getClearColor()
     */
    private void clear(Canvas canvas) {
        canvas.drawColor(clearColor);
    }

    /**
     * Automatically called by the game loop when asking to draw the current frame.
     * @param canvas Canvas ready to be drawn onto.
     */
    protected abstract void draw(Canvas canvas);
}
