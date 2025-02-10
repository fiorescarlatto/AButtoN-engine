package com.abutton.game;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.WindowManager;

/**
 * Created by Dragonz on 20/06/2015.
 * Template Activity that holds the GameSurface
 */
public class GameActivity extends Activity {

    private boolean unload;

    public GameActivity() {
        this.unload = false;
    }

    /**
     * Tells whether this application is being executed for the first time or there is a
     * {@link Game} that has already been created.
     * @param savedInstanceState the state passed to the Activity upon it's creation.
     * @return <ul>
     *     <li>true: there is not an old {@link Game} instance. A new one should be created.</li>
     *     <li>false: there is an existing {@link Game} instance. It should be set as the
     *     current Game instance for this Activity.</li>
     * </ul>
     */
    protected boolean isFirstCreated(Bundle savedInstanceState){
        return (savedInstanceState == null);
    }

    /**
     * When deriving this class a call to {@code super.onCreate(...)} is required. <br>
     * Keep in mind that this function cannot be rub before a {@link Game} has been set. <br>
     * onCreate(...) will call Game.create() and set both the fullscreen flag of the activity and
     * the content view of the activity to the {@link GameSurface}.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** Called when the application is first created. */

        if (!Game.isInitialized())
            throw new RuntimeException("a Game must be initialized before launching a GameActivity.");

        // Set the FULLSCREEN flag to so that the action bar auto hides.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // tells the game to create a new surface
        Game.create();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible

        // start is only called if the game has already been loaded, to avoid trying to load
        // resources before knowing the surface size
        if (Game.isReady())
            Game.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The activity is now running normally (it is now "resumed")
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
        // this function is called before onSaveInstanceState
        unload = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
        Game.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.

        // if the system asked to save the instanceState then the game
        // shouldn't unload its resources
        if (unload)
            Game.destroy();

        // sets the unload flag to true as the system might ask to destroy
        // the application a second time.
        unload = true;
    }

    @Override
    protected void onSaveInstanceState(@Nullable Bundle outState) {
        if (outState == null) return;
        super.onSaveInstanceState(outState);

        outState.putBoolean("saved", true);
        unload = false;

        // The application might also be destroyed (if the user swipes) so it is required to
        // save every persistent stuff.
        Game.save();
    }

    @Override
    public void onBackPressed() {
        if (!Game.getInput().onBackPressed())
            super.onBackPressed();
    }
    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        Game.getInput().onKeyDown(event);
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
        Game.getInput().onKeyUp(event);
        return super.onKeyUp(keyCode, event);
    }
}
