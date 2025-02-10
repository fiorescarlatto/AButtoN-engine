package com.abutton.game.base;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.abutton.game.API.Align;
import com.abutton.game.GameElement;
import com.abutton.game.GameState;
import com.abutton.game.base.element.Colorable;

/**
 * Created by Steve on 16/09/2015.
 * Implementation of a simple two layered Bar, using two different textures one foreground and one
 * background.
 */
public class Bar extends GameElement implements Colorable{

    private float percentage;
    private Texture foreground;
    private Texture background;
    private Align alignment;

    /**
     * Constructor, creates a new bar object starting from 2 textures.
     */
    public Bar(Texture foreground, Texture background){
        this(foreground, background, 0, Align.LEFT);
    }
    public Bar(Texture foreground, Texture background, float percentage, Align alignment){
        super(0, 0, foreground.getWidth(), foreground.getHeight());

        this.foreground = foreground;
        this.background = background;
        setAlignment(alignment);
        setPercentage(percentage);

        this.background.setOrigin(0, 0);
    }

    /**
     * Sets the current alignment to the given percentage
     * @param alignment enum (ALign) that can be LEFT, CENTER or RIGHT
     */
    public void setAlignment(Align alignment) {
        this.alignment = alignment;
    }
    /**
     * Sets the current percentage to the given percentage
     * @param percentage float number between 0 and 1
     */
    public void setPercentage(float percentage) {
        if (percentage > 1) percentage = 1;
        if (percentage < 0) percentage = 0;
        this.percentage = percentage;
    }

    public void setForeground(Texture foreground) {
        this.foreground = foreground;
    }
    public void setBackground(Texture background) {
        this.background = background;
    }

    /**
     * Retrieves the current alignment of the object
     * @return Align (enum) that can be LEFT, CENTER, or RIGHT
     */
    public Align getAlignment() {
        return alignment;
    }
    /**
     * Retrieves the current percentage as a float
     * @return float number between 0 and 1
     */
    public float getPercentage() {
        return percentage;
    }

    @Override
    public int getColor() {
        return foreground.getColor();
    }

    @Override
    public void setColor(int color) {
        foreground.setColor(color);
    }

    @Override
    public void display(Canvas canvas) {
        this.background.display(canvas);
        this.foreground.display(canvas);
    }

    @Override
    public void update(GameState state) {
        RectF rect = getBoundingRect();

        this.background.setPos(rect.left, rect.top);
        this.background.setSize(this.getWidth(), this.getHeight());

        switch(alignment){
            case LEFT:
                this.foreground.setSource(0, 0, percentage, 1);
                this.foreground.setOrigin(0, 0);
                this.foreground.setPos(rect.left, rect.top);
                break;
            case RIGHT:
                this.foreground.setSource(1 - percentage, 0, 1, 1);
                this.foreground.setOrigin(1, 0);
                this.foreground.setPos(rect.right, rect.top);
                break;
            case CENTER:
                this.foreground.setSource(percentage / 2, 0, 1 - (percentage / 2), 1);
                this.foreground.setOrigin(0.5f, 0);
                this.foreground.setPos(rect.centerX(), rect.top);
                break;
        }

        this.foreground.setSize(this.getWidth() * percentage, this.getHeight());
    }
}
