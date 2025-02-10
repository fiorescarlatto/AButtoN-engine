package com.abutton.game.base.particle;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.abutton.game.GameElement;
import com.abutton.game.GameState;

/**
 * Created by Dragonz on 30/06/2015.
 * this element represent a ring on the screen
 * usually it's displayed where the user touches the screen.
 */
public class Ring extends GameElement implements Particle {

    private Paint paint;

    private float minSize;
    private float maxSize;

    private long begin;
    private long duration;

    public Ring(float x, float y, float minSize, float maxSize,
                long duration, Paint paint) {
        super(x,y);
        setOrigin(0.5f, 0.5f);
        setSize(minSize, minSize);

        this.paint = paint;

        this.maxSize = maxSize/2.0f;
        this.minSize = minSize/2.0f;

        this.begin = System.currentTimeMillis();
        this.duration = duration;
    }

    @Override
    public boolean hasFinished() {
        return (System.currentTimeMillis() > begin + duration);
    }

    @Override
    public void display(Canvas canvas) {
        float period = ((float)(System.currentTimeMillis() - begin) / duration);

        paint.setAlpha((int) ((1 - period) * (1 - period) * 255));

        canvas.drawCircle(getX(), getY(), minSize + period * (maxSize - minSize), paint);
    }

    @Override
    public void update(GameState state) {
        // nothing to do here
    }
}
