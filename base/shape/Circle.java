package com.abutton.game.base.shape;

import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Dragonz on 30/06/2015.
 * Class used to easily build simple regular shapes
 */
public final class Circle {

    private Circle() {}

    public static Shape build(int color) {

        Path circle = new Path();
        circle.setFillType(Path.FillType.EVEN_ODD);
        circle.addCircle(0, 0, 1, Path.Direction.CW);

        // creates a new paint
        Paint p = new Paint();
        p.setColor(color);

        return new Shape(circle, p);
    }
}
