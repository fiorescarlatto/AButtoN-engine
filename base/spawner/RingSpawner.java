package com.abutton.game.base.spawner;

import android.graphics.Paint;

import com.abutton.game.base.particle.Ring;

/**
 * Created by Dragonz on 13/08/2015. <br>
 * Simple spawner for {@link Ring}.
 */
public class RingSpawner extends ParticleSpawner {

    private Paint p;

    private long duration;
    private float minSize;
    private float maxSize;

    public RingSpawner(float minSize, float maxSize, long duration, int color, float strokeSize) {
        super();

        this.duration = duration;
        this.minSize  = minSize;
        this.maxSize  = maxSize;

        p = new Paint();
        p.setColor(color);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(strokeSize);
    }

    @Override
    public void spawn(float x, float y) {
        add     (new Ring(x, y, minSize, maxSize, duration, p));
        addLast (new Ring(x, y, minSize - 10, maxSize - 10, duration, p));
    }
}
