package com.abutton.game;

/**
 * Created by Dragonz on 21/06/2015.
 * This class is used in the GameThread in order to measure frame statistics
 * such as frame time, frames per second and so on.
 */

public final class GameRate {
    /* Not going to explain this class, i'm too lazy to do that.
     * Use it to retrieve all the frame statistics you need.
     *
     * Never touch 'update()', 'render()', and 'skip()' unless
     * you want your game to lose synchronization */

    // boolean that tells whether the GameThread is rendering or updating
    private boolean isRendering;
    private boolean skipFrame;

    private long framePeriod;

    // time holders
    private long begin;
    private long updateTime;
    private long renderTime;
    private long collectedTime;

    // total number of Updates / Renders
    private long totalUpdates;
    private long totalRenders;

    // collector that holds the number of frames in 1 second
    private long updateCollector;
    private long renderCollector;
    private long updatesCollected;
    private long rendersCollected;

    // constructor
    public GameRate() { this(30); }
    public GameRate(int maxFPS)
    {
        this.isRendering = false;
        this.skipFrame = false;

        this.begin = 0L;
        this.updateTime = 0L;
        this.renderTime = 0L;
        this.collectedTime = System.currentTimeMillis();

        this.totalUpdates = 0L;
        this.totalRenders = 0L;

        this.updateCollector = 0L;
        this.renderCollector = 0L;
        this.updatesCollected = 0L;
        this.rendersCollected = 0L;

        this.framePeriod = 1000/maxFPS + 1;
    }

    public long getTotalUpdates() { return this.totalUpdates; }
    public long getTotalRenders() { return this.totalRenders; }
    public long getTotalFrames()  { return getTotalUpdates(); }
    public long getSkippedFrames(){
        return getTotalUpdates() - getTotalRenders() - (isRendering ? 1 : 0);
    }

    public long getRenderTime() { return this.renderTime; }
    public long getUpdateTime() { return this.updateTime; }
    public long getFrameTime()  { return getUpdateTime() + getRenderTime(); }

    public long getFramePeriod() { return this.framePeriod; }
    public long getFrameExcess() { return getFramePeriod() - getFrameTime(); }

    public long getUPS() { return this.updatesCollected; }
    public long getFPS() { return this.rendersCollected; }

    public void setMaxFPS(int maxFPS) { this.framePeriod = 1000/maxFPS + 1; }

    public void update()
    {
        this.isRendering = false;
        this.skipFrame = false;
        begin = System.currentTimeMillis();
    }

    public void render()
    {
        this.isRendering = true;
        this.skipFrame = false;
        begin = System.currentTimeMillis();
    }

    public void skip()
    {
        this.isRendering = false;
        this.skipFrame = true;
        begin = System.currentTimeMillis();
    }

    public void finish()
    {
        long now = System.currentTimeMillis();

        if (!skipFrame)
        {
            if (isRendering) {
                finishRender(now);
            } else {
                finishUpdate(now);
            }
        } else {
            finishUpdate(now);
            renderTime -= getFramePeriod();
        }

        if ((now - collectedTime) > 1000)
        {
            collectedTime += 1000;
            flushCollectors();
        }

    }

    private void finishUpdate(long time)
    {
        updateTime = time - begin;
        updateCollector++;
        totalUpdates++;
    }

    private void finishRender(long time)
    {
        renderTime = time - begin;
        renderCollector++;
        totalRenders++;
    }

    private void flushCollectors()
    {
        updatesCollected = updateCollector;
        rendersCollected = renderCollector;
        updateCollector = 0L;
        renderCollector = 0L;
    }
}

