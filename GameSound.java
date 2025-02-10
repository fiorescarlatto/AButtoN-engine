package com.abutton.game;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.Map;

//todo LISTE PER GESTIRE LA PAUSA E IL RESUME DEI "TIPI" DI SUONO

/**
 * Created by Dragonz on 26/06/2015. <br>
 * Class used to load and play back different sound effects or background music. <br>
 * This class is able to give plenty of control over the sound streams, but is unable to show
 * the position of a single track.
 * If you need that use a {@link com.abutton.game.base.audio.MusicPlayer}.
 */
public class GameSound implements SoundPool.OnLoadCompleteListener {
    /* This object masks a SoundPool, making it easier to load and play
     * back sound effects. */

    // flag that specify whether the SoundPool finished loading
    // you should always wait for isLoadComplete() to return true
    private boolean loadComplete;

    // sound pool used to load and play back the sounds.
    private SoundPool pool;

    // map to handle ID to SoundID requests and make sound handling easier
    private Map<Integer, Integer> soundMap;

    /**
     * Constructor. <br>
     * Creates a new {@link GameSound} object.
     * @param maxStreams the maximum number of audio stream this object will play at the same time,
     *                   meaning those that exceed this number will be automatically stopped.
     */
    @SuppressWarnings("all")
    public GameSound(int maxStreams) {
        // i have to use this deprecated constructor in order to avoid
        // compatibility issues, Sound.Builder is only supported from API 22
        this.pool = new SoundPool(maxStreams, AudioManager.STREAM_MUSIC, 0);
        // sets the on load complete listener to be this class
        pool.setOnLoadCompleteListener(this);

        this.soundMap = new HashMap<>();
        this.loadComplete = false;
    }

    // returns whether this object finished loading or not.
    public boolean isLoadComplete() {
        return this.loadComplete;
    }

    public SoundPool getPool() {
        return this.pool;
    }
    public int getSoundID(int resID) {
        if (soundMap.containsKey(resID))
            return soundMap.get(resID);
        else
            throw new RuntimeException("resource is not loaded");
    }

    public void play(int resID) {
        play(resID, 1.0f, 1.0f, 0, 0, 1.0f);
    }
    public void play(int resID, int priority) {
        play(resID, 1.0f, 1.0f, priority, 0, 1.0f);
    }
    public void play(int resID, int loop, float rate) {
        play(resID, 1.0f, 1.0f, 0, loop, rate);
    }
    public void play(int resID, int priority, int loop, float rate) {
        play(resID, 1.0f, 1.0f, priority, loop, rate);
    }
    public void play(int resID, float leftVolume, float rightVolume, int loop, float rate) {
        play(resID, leftVolume, rightVolume, 0, loop, rate);
    }

    /**
     * Play a sound from a previously loaded resID.
     *
     * Play the sound specified by the resID.
     * Note that calling play() may cause another sound to stop playing if the maximum number of
     * active streams is exceeded. A loop value of -1 means loop forever, a value of 0 means don't
     * loop, other values indicate the number of repeats, e.g. a value of 1 plays the audio twice.
     * The playback rate allows the application to vary the playback rate (pitch) of the sound.
     * A value of 1.0 means play back at the original frequency.
     * A value of 2.0 means play back twice as fast, and a value of 0.5 means playback at half speed.
     *
     * @param resID the resID of a resource previously loaded with load()
     * @param leftVolume left volume value (range = 0.0 to 1.0)
     * @param rightVolume right volume value (range = 0.0 to 1.0)
     * @param priority stream priority (0 = lowest priority)
     * @param loop loop mode (0 = no loop, -1 = loop forever)
     * @param rate playback rate (1.0 = normal playback, range 0.5 to 2.0)
     */
    public void play(int resID, float leftVolume, float rightVolume, int priority, int loop, float rate) {
        if (soundMap.containsKey(resID))
            getPool().play(soundMap.get(resID), leftVolume, rightVolume, priority, loop, rate);
    }

    public void pause() {
        getPool().autoPause();
    }
    public void resume() {
        getPool().autoResume();
    }

    public int load(String path, int maskID) {
        // if this file has already been loaded
        if (soundMap.containsKey(maskID))
            return 0;

        // sets the loaded flag to false
        loadComplete = false;
        // priority is ignored and set to 1 as specified in the documentation
        int soundID = getPool().load(path, 1);

        // maps the soundID to the maskID
        if (maskID == 0) maskID = soundID;
        soundMap.put(maskID, soundID);

        // returns the soundID
        return soundID;
    }
    public int load(Context context, int resID) {
        // if this resource has already been loaded
        if (soundMap.containsKey(resID))
            return 0;

        // sets the loaded flag to false
        loadComplete = false;
        // priority is ignored and set to 1 as specified in the documentation
        int soundID = getPool().load(context, resID, 1);

        // maps the soundID to the resourceID
        soundMap.put(resID, soundID);

        // returns the soundID
        return soundID;
    }

    public void unload(int resID) {
        if (soundMap.containsKey(resID)) {
            getPool().unload(soundMap.get(resID));
            soundMap.remove(resID);
        }
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        this.loadComplete = true;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        pool.release();
    }
}
