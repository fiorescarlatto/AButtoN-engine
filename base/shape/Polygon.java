package com.abutton.game.base.shape;

import android.graphics.Paint;
import android.graphics.Path;

import com.abutton.game.utility.MathUtils;

/**
 * Created by Dragonz on 30/06/2015.
 * Class used to easily build simple regular shapes
 */
public final class Polygon {
    // maximum number of sides that a polygon can have
    public static int SIDE_LIMIT = 128;

    private Polygon() {}

    public static Shape build(int sides, int color) {
        sides = MathUtils.constrain(sides, 3, SIDE_LIMIT);

        float angle = (float) (Math.PI * 2) / (sides);

        Path polygon = new Path();
        polygon.setFillType(Path.FillType.EVEN_ODD);
        polygon.moveTo(0, 1);

        for (int i = 1; i < sides; i++){
            polygon.lineTo(
                    (float) Math.sin(angle * i),
                    (float) Math.cos(angle * i)
            );
        }

        polygon.close();

        // creates a new paint
        Paint p = new Paint();
        p.setColor(color);

        return new Shape(polygon, p);
    }
}
