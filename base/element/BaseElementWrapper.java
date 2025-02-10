package com.abutton.game.base.element;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import com.abutton.game.GameState;
import com.abutton.game.exception.ParameterIsNullException;

/**
 * Created by Dragonz on 19/08/2015. <br>
 * Wrapper class for BaseElement, used in other classes to simplify the coding process
 * and avoid rewriting every time a wrapper for each method of BaseElement.
 */
public class BaseElementWrapper implements BaseElement {

    protected BaseElement element;

    public BaseElementWrapper(BaseElement element) {
        if (element == null)
            throw new ParameterIsNullException();
        this.element = element;
    }

    @Override
    public float getX() {
        return element.getX();
    }
    @Override
    public float getY() {
        return element.getY();
    }
    @Override
    public void setPos(float x, float y) {
        element.setPos(x, y);
    }

    @Override
    public PointF getOrigin() {
        return element.getOrigin();
    }
    @Override
    public void setOrigin(float x, float y) {
        element.setOrigin(x, y);
    }

    @Override
    public float getWidth() {
        return element.getWidth();
    }
    @Override
    public float getHeight() {
        return element.getHeight();
    }
    @Override
    public void setSize(float width, float height) {
        element.setSize(width, height);
    }

    @Override
    public float getAngle() {
        return element.getAngle();
    }
    @Override
    public void setAngle(float angle) {
        element.setAngle(angle);
    }

    @Override
    public boolean isVisible() {
        return element.isVisible();
    }
    @Override
    public void setVisible(boolean visible) {
        element.setVisible(visible);
    }

    @Override
    public RectF getBoundingRect() {
        return element.getBoundingRect();
    }
    @Override
    public boolean contains(float x, float y) {
        return element.contains(x, y);
    }

    @Override
    public void display(Canvas canvas) {
        element.display(canvas);
    }

    @Override
    public void update(GameState state) {
        element.update(state);
    }
}
