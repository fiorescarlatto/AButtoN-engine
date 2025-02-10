package com.abutton.game;

import android.graphics.PointF;
import android.graphics.RectF;

import com.abutton.game.base.element.BaseElement;

/**
 * Created by Dragonz on 22/06/2015.
 * Interface of a game element, used by GameState and GameDisplay
 */
public abstract class GameElement implements BaseElement {

    // boolean that defines whether the element is visible
    private boolean visible;
    // rectangle that contains the element
    private RectF rect;
    // angle of rotation
    private float angle;
    // origin of the element.
    // this is tells where is the fixed point when resizing or rotating the element.
    private PointF origin;

    @SuppressWarnings("unused")
    public GameElement(BaseElement e) {
        this();

        this.setAngle(e.getAngle());
        this.setOrigin(e.getOrigin().x, e.getOrigin().y);
        this.setPos(e.getX(), e.getY());
        this.setSize(e.getWidth(), e.getHeight());
        this.setVisible(e.isVisible());
    }

    public GameElement() { this(0, 0); }
    public GameElement(float x, float y) { this(x,y,0,0); }
    public GameElement(float x, float y, float width, float height) { this(x, y, width, height, 0); }
    public GameElement(float x, float y, float width, float height, float angle) {
        this.visible = true;
        this.rect    = new RectF (x,y,x+width,y+height);
        this.origin  = new PointF(0,0);
        this.angle   = angle;
    }

    // getters for angle, origin, position, size
    public boolean isVisible() { return visible; }

    public float  getAngle()  { return this.angle; }

    public PointF getOrigin() { return new PointF(origin.x, origin.y); }

    public float getX() { return rect.left + getWidth() * origin.x; }
    public float getY() { return rect.top + getHeight() * origin.y; }

    public float getWidth()  { return rect.width(); }
    public float getHeight() { return rect.height(); }

    public RectF getBoundingRect()   { return new RectF(rect); }

    // setters for angle, origin, position, size
    public void setVisible(boolean visible) { this.visible = visible; }

    public void setAngle (float angle) { this.angle = angle; }

    public void setOrigin(float x, float y) {
        origin.x = x;
        origin.y = y;
    }

    public boolean contains(float x, float y) {
        return rect.contains(x, y);
    }

    public void setPos(float x, float y) {
        float w = getWidth();
        float h = getHeight();

        rect.left   = x - w * origin.x;
        rect.right  = x + w * (1 - origin.x);
        rect.top    = y - h * origin.y;
        rect.bottom = y + h * (1 - origin.y);
    }

    public void setSize  (float width, float height) {
        float dw = width - getWidth();
        float dh = height - getHeight();

        rect.left   -= dw * origin.x;
        rect.right  += dw * (1 - origin.x);
        rect.top    -= dh * origin.y;
        rect.bottom += dh * (1 - origin.y);
    }
}
