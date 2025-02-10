package com.abutton.game.utility;

/**
 * Created by Dragonz on 05/08/2015.
 * This class is used to set a Task and execute it asynchronously after a defined amount of time.
 */
public abstract class Task implements Runnable {

    private boolean canceled;

    private int loop;

    private long begin;
    private long time;

    private Thread thread;

    public Task() {
        this(0);
    }
    public Task(long time) {
        this(time, 0);
    }
    public Task(long time, int loop) {
        this.canceled = false;

        setLoop(loop);
        setTime(time);

        this.begin = System.currentTimeMillis();
        this.thread = new Thread(this);
    }

    public boolean isRunning() {
        return thread.isAlive();
    }
    public boolean isCanceled() {
        return canceled;
    }
    public boolean isLoop() {
        return (loop != 0);
    }

    public long getTime() {
        return time;
    }

    public void setLoop(int loop) {
        this.loop = loop;
    }
    public void setTime(long time) {
        this.time = time;
    }

    public void cancel() {
        canceled = true;
    }
    public void start() {
        if (!isRunning()) thread.start();
    }
    public void stop() {
        boolean retry = true;

        while (retry){
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException ex) {
                // the tread didn't shut down
                // try again shutting down the thread
            }
        }
    }

    @Override
    public void run() {

        do {
            long sleep = getTime() - (System.currentTimeMillis() - begin);

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                // wants to stay awake.
                break;
            }

            if (!isCanceled()) task();

            begin += time;

        } while (loop-- != 0);

    }

    public abstract void task();
}
