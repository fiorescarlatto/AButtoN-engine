package com.abutton.game.base.shape;

import android.graphics.Paint;
import android.graphics.Path;

import com.abutton.game.utility.MathUtils;

/**
 * Created by Dragonz on 30/06/2015.
 * Class used to easily build generic stars
 */
public final class Star {
    // maximum number of spikes a star can have
    public static int SPIKE_LIMIT = 128;

    private Star() {}

    public static Shape build(int spikes, float centerSize, int color) {
        spikes     = MathUtils.constrain(spikes, 3, SPIKE_LIMIT);
        centerSize = (float) MathUtils.constrain(centerSize, 0, 1);

        float angle = (float) (Math.PI) / (spikes);

        Path star = new Path();
        star.setFillType(Path.FillType.EVEN_ODD);
        star.moveTo(0, 1);

        for (int i = 0; i < spikes; i++){
            star.lineTo(
                    centerSize * (float) Math.sin(angle * (i * 2 + 1)),
                    centerSize * (float) Math.cos(angle * (i * 2 + 1))
            );
            star.lineTo(
                    (float) Math.sin(angle * (i+1) * 2),
                    (float) Math.cos(angle * (i+1) * 2)
            );
        }

        star.close();

        // creates a new paint
        Paint p = new Paint();
        p.setColor(color);

        return new Shape(star, p);
    }
}
