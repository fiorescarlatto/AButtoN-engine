package com.abutton.game.base.shape;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import com.abutton.game.utility.MathUtils;

/**
 * Created by Dragonz on 30/07/2015.
 * Class used to easily build generic cogs
 */
public final class Cogwheel {
    // maximum number of teeth a cogwheel can have
    public static int TEETH_LIMIT = 128;

    private Cogwheel() {}

    public static Shape build(int teeth, float teethWidth, float teethHeight, float centerSize, int color) {
        teeth       = MathUtils.constrain(teeth, 3, TEETH_LIMIT);
        centerSize  = (float) MathUtils.constrain(centerSize, 0, 1);
        teethHeight = (float) MathUtils.constrain(teethHeight, 0, 1);
        teethWidth  = (float) MathUtils.constrain(teethWidth, 0, 1);

        // calculates structure sizes
        float height = centerSize + (1-centerSize) * teethHeight;
        float width  = 360 / teeth;

        Path cogwheel = new Path();
        cogwheel.setFillType(Path.FillType.EVEN_ODD);

        // inner circle
        cogwheel.addCircle(0, 0, centerSize, Path.Direction.CW);

        // arc oval boundaries
        //noinspection SuspiciousNameCombination
        RectF lower  = new RectF(-height, -height, height, height);
        RectF higher = new RectF(-1, -1, 1, 1);

        // outer contour
        //noinspection SuspiciousNameCombination
        cogwheel.moveTo(height, 0);

        for (int i = 0; i < teeth; i++){
            cogwheel.arcTo (higher, width*i + (width/2 - width/2*teethWidth)/2, width/2*teethWidth);
            cogwheel.arcTo (lower,  width*i + width/2, width/2);
        }

        cogwheel.close();

        // creates a new paint
        Paint p = new Paint();
        p.setColor(color);

        return new Shape(cogwheel, p);
    }
}
