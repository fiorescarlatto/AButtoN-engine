package com.abutton.game.base.spawner;

import android.graphics.Canvas;

import com.abutton.game.GameState;
import com.abutton.game.base.particle.Particle;

import java.util.LinkedList;

/**
 * Created by Dragonz on 30/06/2015.
 * A class that generates new GameElement.Particle objects and automatically
 * manages the existing ones.
 */
public abstract class ParticleSpawner implements Spawner {

    // visibility flag
    private boolean visible;

    // spawner position
    private float x;
    private float y;

    // list of active particles
    private LinkedList<Particle>  particles;

    /**
     * Constructor. <br>
     * Creates a new particle spawner positioned in (0,0).
     */
    public ParticleSpawner() {
        this(0,0);
    }
    /**
     * Constructor. <br>
     * Creates a new particle spawner.
     * @param x float, horizontal position of the default spawning point.
     * @param y float, vertical position of the default spawning point.
     */
    public ParticleSpawner(float x, float y) {
        this.particles = new LinkedList<>();
        this.visible = true;

        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setPos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public synchronized void display(Canvas canvas) {
        if (!isVisible()) return;

        for (Particle particle : particles)
            particle.display(canvas);
    }

    @Override
    public synchronized void update(GameState state) {
        LinkedList<Particle> particles = new LinkedList<>();

        // checks for finished elements and removes them
        for (Particle particle : this.particles){
            particle.update(state);
            if (!particle.hasFinished())
                particles.add(particle);
        }

        this.particles = particles;
    }

    @Override
    public void spawn() {
        spawn(x, y);
    }

    /**
     * Adds a particle in the display list. <br>
     * The particle might not be added right away in order to avoid generating a
     * {@link java.util.ConcurrentModificationException}.
     * @param particle particle to add in the display list of this {@link Spawner}.
     */
    protected synchronized void add(Particle particle) {
        this.addFirst(particle);
    }
    protected synchronized void addFirst(Particle particle) {
        this.particles.addFirst(particle);
    }
    protected synchronized void addLast(Particle particle) {
        this.particles.addLast(particle);
    }

}
