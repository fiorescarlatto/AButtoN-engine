package com.abutton.game.base.button;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.abutton.game.API.Align;
import com.abutton.game.base.element.BaseElement;
import com.abutton.game.base.element.Editable;

/**
 * Created by Dragonz on 12/08/2015. <br>
 * Base implementation of a {@link Button} with text.
 */
public class TextButton extends Button implements Editable{

    private String text;
    private Rect   textBounds;
    private Align  alignment;

    private Paint  paint;

    /**
     * Default constructor. <br>
     * Creates a text button from the given {@link BaseElement}. <br>
     * Every button receives an unique ID that increases by one every time a new one is created.
     * @param text String, text this button should display.
     * @param element {@link BaseElement} that should be made into a button.
     */
    public TextButton(String text, BaseElement element) {
        super(element);
        this.text  = text;
        this.textBounds = new Rect();
        this.alignment = Align.CENTER;

        this.paint = new Paint();

        this.setTextAlign(this.alignment);
    }
    /**
     * Constructor. <br>
     * Creates a text button from the given {@link BaseElement}. <br>
     * Every button receives an unique ID that increases by one every time a new one is created. <br>
     * This constructor allows to change the starting ID of an {@link ImageButton}.
     * @param text String, text this button should display.
     * @param element {@link BaseElement} that should be made into a button.
     * @param initialID Integer, initial ID that should be set for the first {@link Button}.
     *                  (this one that is currently being created).
     */
    public TextButton(String text, BaseElement element, int initialID) {
        super(element, initialID);
        this.text  = text;
        this.textBounds = new Rect();
        this.alignment = Align.CENTER;

        this.paint = new Paint();

        this.setTextAlign(this.alignment);
    }

    /**
     * Copies the proprieties of a given button: angle, origin, position, size and visibility.
     * @param button {@link ImageButton} which proprieties have to be copied.
     */
    public void as(TextButton button) {
        super.as(button);
        this.setTextAlign(button.getTextAlign());
        this.setText(button.getText());
        this.setTextColor(button.getTextColor());
        this.setTextSize(button.getTextSize());
    }

    @Override
    public String getText() {
        return text;
    }
    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int getTextColor() {
        return paint.getColor();
    }
    @Override
    public void setTextColor(int color) {
        paint.setColor(color);
    }

    @Override
    public float getTextSize() {
        return paint.getTextSize();
    }
    @Override
    public void setTextSize(float size) {
        paint.setTextSize(size);
    }

    @Override
    public Align getTextAlign() {
        return alignment;
    }
    @Override
    public void setTextAlign(Align alignment) {
        this.alignment = alignment;
        paint.setTextAlign(alignment.get());
    }

    @Override
    public void display(Canvas canvas) {
        if (!isVisible()) return;

        super.display(canvas);

        RectF bRect = getBoundingRect();
        paint.getTextBounds(text, 0, text.length(), textBounds);

        switch (getTextAlign()) {
            case CENTER:
                canvas.drawText(text, bRect.centerX(), bRect.centerY() + textBounds.height()/2, paint);
                break;
            case LEFT:
                canvas.drawText(text, 2 + bRect.left + textBounds.width()/2, bRect.centerY() + textBounds.height()/2, paint);
                break;
            case RIGHT:
                canvas.drawText(text,-2 + bRect.right - textBounds.width()/2, bRect.centerY() + textBounds.height()/2, paint);
                break;
        }
    }
}
