package com.abutton.game.base.audio;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by Dragonz on 30/06/2015.
 * class used to easily manage music files
 * [UNTESTED]
 */
public class MusicPlayer {

    // media player object, that lets you load and play back music
    // on android devices
    MediaPlayer player;
    Context context;

    public MusicPlayer(Context context, int resID) {
        this.context = context;
        this.player  = MediaPlayer.create(context, resID);
    }
    public MusicPlayer(Context context, String filename) {
        Uri uri = Uri.parse(filename);
        this.context = context;
        this.player = MediaPlayer.create(context, uri);
    }

    public boolean isLooping() {
        return player.isLooping();
    }
    public boolean isPlaying() {
        return player.isPlaying();
    }

    public int getDuration() {
        return player.getDuration();
    }
    public int getPosition() {
        return player.getCurrentPosition();
    }

    public boolean setMusic(int resID) {

        player.reset();

        try {
            AssetFileDescriptor fd = context.getResources().openRawResourceFd(resID);
            player.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            player.prepare();
            fd.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public boolean setMusic(String filename) {

        player.reset();

        try {
            player.setDataSource(filename);
            player.prepare();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public boolean setMusic(Uri URI) {

        player.reset();

        try {
            player.setDataSource(context, URI);
            player.prepare();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public void setLooping(boolean looping) {
        player.setLooping(looping);
    }
    public void setVolume(float leftVolume, float rightVolume) {
        player.setVolume(leftVolume, rightVolume);
    }

    public void start() {
        player.start();
    }
    public void pause() {
        player.pause();
    }
    public void stop() {
        player.stop();
    }
    public void seek(int milliSeconds) {
        player.seekTo(milliSeconds);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        player.release();
    }

}
