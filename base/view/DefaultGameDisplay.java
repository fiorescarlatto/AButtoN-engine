package com.abutton.game.base.view;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.abutton.game.GameDisplay;
import com.abutton.game.GameRate;

/**
 * Created by Dragonz on 23/06/2015.
 * This game display only draws a black screen with the frame per second
 * information. Never use if not for debug purposes
 */
public class DefaultGameDisplay extends GameDisplay {
    /* Default class, use only for debug */

    @Override
    protected void draw(Canvas canvas) {
        // nothing to do here.
    }

    /**
     * Draws a standard debug fps counter on the given canvas.
     * @param canvas Canvas to draw the counter on.
     * @param rate GameRate that contains the frame rate information to display.
     * @param textSize size of the text (float).
     * @param color color of the text (int).
     */
    public static void FPSCounter(Canvas canvas, GameRate rate, float textSize, int color) {
        float line = textSize+textSize/2.0f;

        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setTextSize(textSize);
        p.setColor(color);

        canvas.drawText("UPS: " + rate.getUPS(), 50, line += textSize, p);
        canvas.drawText("FPS: " + rate.getFPS(), 50, line += textSize, p);
        canvas.drawText("Total:   " + rate.getTotalFrames(), 50, line += textSize, p);
        canvas.drawText("Skipped: " + rate.getSkippedFrames(), 50, line += textSize, p);
        canvas.drawText("Update time: " + rate.getUpdateTime(), 50, line += textSize, p);
        canvas.drawText("Render time: " + rate.getRenderTime(), 50, line += textSize, p);
        canvas.drawText("Frame  time: " + rate.getFrameTime(), 50, line + textSize, p);
    }
}
