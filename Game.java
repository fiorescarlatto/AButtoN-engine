package com.abutton.game;

import android.content.Context;
import android.content.res.Resources;
//todo: move this dependency one step away. (in API)

import com.abutton.game.base.view.DefaultGameDisplay;
import com.abutton.game.base.view.DefaultGameInput;
import com.abutton.game.base.view.DefaultGameState;
import com.abutton.game.base.view.DefaultGameView;
import com.abutton.game.base.view.DefaultSharedLoader;
import com.abutton.game.exception.GameConstructionException;
import com.abutton.game.exception.ParameterIsNullException;
import com.abutton.game.exception.SaveFileDecodeException;
import com.abutton.game.utility.SaveFile;
import com.abutton.game.shared.SharedData;
import com.abutton.game.shared.SharedLoader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Dragonz on 22/06/2015. <br>
 * Class that holds all of the elements needed in a game. <br><br>
 *
 * The general state of a game is defined by two boolean values: <ol>
 * <li> {@code boolean ready}   (tells whether the game has already been loaded and is ready to run,
 * or is already running)
 * <li> {@code boolean running} (tells whether the game is running or is stopped) </ol><br>
 *
 * Access to these two flags is given by: <ul>
 * <li> {@code boolean isReady()} </li>
 * <li> {@code boolean isRunning()} </li> </ul><br><br>
 *
 * The Game class also holds a {@link GameState}, {@link GameDisplay} and {@link GameInput}, which
 * together define how the game is represented on the screen and how a user interaction is taken
 * into part. These three elements are always held together in a {@link GameView}. <br><br>
 *
 * This way every {@link GameView} is mapped to a String ID that makes it easy to switch from one
 * view to another. <br>
 * {@link GameView} views can be added into the game with: <ul>
 * <li> {@code void addView(String viewID, GameView view)} </li> </ul><br>
 *
 * Regarding other classes used in Game: <ul>
 * <li>{@link GameSound} is used to manage sound effects of the game</li>
 * <li>{@link GameLayer} is used as a tool to simplify input recognition and decide which of the
 * elements on the screen was actually pressed </li>
 * <li>{@link GameRate} is used to synchronize the drawing and updating thread, trying to make time
 * run on a constant flow. </li></ul>
 */
public final class Game {
    /* Main class, it holds all of the game information */

    /* general state of the game */
    private static boolean initialized;
    private static boolean ready;
    private static boolean running;

    /* used to hold Application context */
    private static Context context;

    /* Game related classes */
    /* current state of the game */
    private static GameState   state;
    /* used to display (draw on the screen) the game's state */
    private static GameDisplay display;
    /* used to manage all the user input on the game */
    private static GameInput   input;
    /* used to load and play back sound effects of this game */
    private static GameSound   sound;
    /* physical surface that will display the game */
    private static GameSurface surface;
    /* used to interact with GameElements */
    private static GameLayer   layer;
    /* used to synchronize threads and maintain a constant frame rate */
    private static GameRate    rate;

    /* used to load shared resources */
    private static SharedLoader loader;
    /* map used to hold all the different views of the game */
    private static Map <String, GameView> viewMap;
    /* string used to keep last set state */
    private static LinkedList<String> history;
    private static String current;

    /* reserved */
    private static ViewHolder holder;

    // constructor
    public Game(Context context) {
        this(context, null);
    }

    /**
     * Constructs a new Game
     * @param context this is the application context of the application the Game runs on
     * @param loader  this is the global loader, it can be only specified upon Game creation, it is
     *                needed in order to load the SharedResources.
     */
    public Game(Context context, SharedLoader loader) {
        if (initialized)
            throw new GameConstructionException();

        // sets the general state to 'stopped and not loaded'
        Game.ready   = false;
        Game.running = false;

        Game.context = context;

        // creates the hash map to contain the different views.
        Game.viewMap = new HashMap<>();
        Game.history = new LinkedList<>();
        Game.current = "";

        // creates a new GameSound (max number of streams is 7
        // because 7 is the maximum number of anything)
        Game.sound   = new GameSound(7);
        //creates a new GameInterface to hold Interactable elements
        Game.layer   = new GameLayer();

        // creates the default state (contains and does nothing)
        Game.state   = new DefaultGameState();
        // creates the default display (it only shows FPS)
        Game.display = new DefaultGameDisplay();
        // creates the default input manager (it ignores all input)
        Game.input   = new DefaultGameInput();
        // creates the default view
        Game.addView("default", new DefaultGameView());

        // creates a default shared loader unless there is a given one
        if ((Game.loader = loader) == null)
            Game.loader = new DefaultSharedLoader();

        // creates a new frame rate manager
        Game.rate = new GameRate();
        // creates a new surface that will be used to draw onto
        Game.surface = new GameSurface(context);

        // the game has successfully been initialized
        Game.initialized = true;
        // loads the SharedData
        SharedData.load();
    }

    /**
     * Tells whether the Game has been created and initialized.
     * @return boolean, true or false.
     */
    public static boolean isInitialized() {
        return initialized;
    }
    /**
     * Tells whether the Game is ready or not, meaning that all of it's resources have already been
     * loaded into memory.
     * @return Boolean: <ul>
     *         <li>true: the game is ready to start, all resources are loaded.</li>
     *         <li>false: the game is not ready to start, resource may not be completely loaded.</li>
     * </ul>
     */
    public static boolean isReady() {
        return ready;
    }
    /**
     * Tells whether the update and rendering loop is running, meaning the {@link GameState} is
     * being displayed on the screen.
     * @return Boolean: <ul>
     *         <li>true: the game loop is executing.</li>
     *         <li>false: the game loop is stopped.</li>
     * </ul>
     */
    public static boolean isRunning() {
        return running;
    }
    /**
     * Tells whether the screen is vertical or horizontal.
     * @return Boolean: <ul>
     *         <li>true: the screen is vertical.</li>
     *         <li>false: the screen is horizontal.</li>
     * </ul>
     */
    public static boolean isVertical() {
        return (getHeight() > getWidth());
    }
    /**
     * Tells whether the screen is vertical or horizontal.
     * @return Boolean: <ul>
     *         <li>true: the screen is horizontal.</li>
     *         <li>false: the screen is vertical.</li>
     * </ul>
     */
    public static boolean isHorizontal() {
        return (getHeight() < getWidth());
    }

    public static Resources   getResources(){ return context.getResources(); }
    public static Context     getContext()  { return context; }
    public static GameSurface getSurface()  { return surface; }
    public static GameDisplay getDisplay()  { return display; }
    public static GameLayer   getLayer()    { return layer; }
    public static GameSound   getSound()    { return sound; }
    public static GameInput   getInput()    { return input; }
    public static GameState   getState()    { return state; }
    public static GameRate    getRate()     { return rate; }
    public static String      getCurrent()  { return current; }

    /**
     * Retrieves the width in pixel of the Surface on which this instance of Game is being
     * displayed. <br>
     * When the {@link GameSurface} changes its size in any way, a call to the state method
     * {@code sizeChanged(int width, int height)} is performed.
     * @return Integer containing the width of the game window (in pixels).
     */
    public static int  getWidth()  { return getSurface().getWidth(); }
    /**
     * Retrieves the height in pixel of the Surface on which this instance of Game is being
     * displayed. <br>
     * When the {@link GameSurface} changes its size in any way, a call to the state method
     * {@code sizeChanged(int width, int height)} is performed.
     * @return Integer containing the height of the game window (in pixels).
     */
    public static int  getHeight() { return getSurface().getHeight(); }
    /**
     * Retrieves the total number of frames displayed up to now. <br>
     * This number corresponds to the current frame number and can be used as a timer
     * @return Long containing the number of the current frame.
     */
    public static long getFrame()  { return getRate().getTotalFrames(); }
    /**
     * Retrieves the total time the game has been running in milliseconds. <br>
     * This timer is lazy, meaning this number corresponds to {@code getFrame() * frameDuration}<br>
     * This means the value of {@code getTime()} won't change for the entire duration of the frame.
     * if you need a more precise timer consider using {@code System.currentTimeMillis()} or
     * {@code System.currentNanoTime()}.
     * @return Long containing the time of the current frame (in milliseconds).
     */
    public static long getTime()   { return getFrame() * getRate().getFramePeriod(); }
    /**
     * Retrieves an instance to a new {@link SaveFile} that is ready to be read or to be written on.
     * This file can be opened in read or write mode calling it's methods getInput() and getOutput()
     * @param fileName String that contains the name of the file to be created or opened.
     * @return {@link SaveFile} object to the required file. (it may not exist yet, in that case
     *         it will be created as soon as a call to getOutput() is performed).
     * @throws SaveFileDecodeException
     */
    public static SaveFile getSaveFile(String fileName) throws SaveFileDecodeException {
        return new SaveFile(fileName, getContext());
    }

    /**
     * Sets the previous view as the current view, only works if there is at least one old view
     * in the history of views.
     * @see #setView(String)
     */
    public static void setView() {
        if (history.size() > 1) {
            history.pop();
            setView(history.pop());
        }
    }
    /**
     * Prepares to set the next view as the view corresponding to the given viewID. <br>
     * The required view might not be set right away. The Game object will wait for the right
     * conditions before attempting to set any view.
     * @param viewID String containing the ID of the view that has to be set.
     */
    public static void setView(String viewID) {
        // if the game is not ready it won't load the view right away.
        setView(viewID, isReady(), isReady());
    }
    /**
     * Prepares to set the next {@link GameView} as the one corresponding to the given viewID. <br>
     * The required {@link GameView} might not be set right away. <br>
     * The Game object will wait for the right conditions before attempting to set any view.
     * @param viewID String containing the ID of the view that has to be set.
     * @param load Boolean telling the game whether to load or not the next view: <ul>
     *             <li>true: the next view is going to be loaded normally.
     *             <li>false: the next view will have to be loaded manually.
     * </ul>
     * @param unload Boolean telling the game whether to unload or not the current view: <ul>
     *               <li>true: the current view will be unloaded normally.</li>
     *               <li>false: the current view will have to be unloaded manually.</li>
     * </ul>
     *               In any case all of the views will be unloaded upon destruction of the Game.
     */
    public static void setView(String viewID, boolean load, boolean unload) {
        // checks if the given view is an existing view
        if (!viewMap.containsKey(viewID)) return;

        // adds the view into the history
        history.push(viewID);

        // creates a new holder that will post the setting of the view, making sure it will be
        // set when it's safe to do so.
        holder = ViewHolder.of(viewID, load, unload);
    }

    /**
     * Adds a new view to the list of the game's views. <br>
     * Every view is mapped to a String viewID that is later used to simplify switching from one
     * view the other.
     * @param viewID String containing the ID of the view to add into the Game.
     * @param view {@link GameView} corresponding to the given viewID.
     */
    public static void addView(String viewID, GameView view) {
        if (view == null) throw new ParameterIsNullException("GameView");

        // you can't change a viewID once you set it.
        if (!viewMap.containsKey(viewID))
            viewMap.put(viewID, view);
    }

    /**
     * Forces a call to the {@link GameStateLoader} currently set, if it exists, making it load
     * the current {@link GameState}'s resources into memory.
     */
    public static void load() {
        Game.getState().load();
    }
    /**
     * Forces a call to the {@link GameStateLoader} currently set, if it exists, making it unload
     * the current {@link GameState}'s resources from memory.
     */
    public static void unload() {
        Game.getState().unload();
    }

    /**
     * Resets the GameView, this function is to be called with a new view as it's parameter
     * doing so allows the Garbage collector to easily dispose of all the current set parameters.
     * @param view a new view to be set as the current {@link GameView}.
     */
    public static void clear(GameView view) {
        if (view == null)
            throw new ParameterIsNullException();

        viewMap.put(current, view);
    }

    public static void create() {
        if (surface.isDetached()) {
            // creates a new surface to hold the graphics
            surface = new GameSurface(context);
        }
        if (!isReady() && isInitialized()) {
            // loads shared resources
            loader.load();
            // loads the GameState
            load();
            // the game is now ready
            Game.ready = true;
        }
    }
    public static void save() {
        state.save();
        SharedData.save();
    }
    public static void start() {
        if (isReady()) {
            // the game is now running
            Game.running = true;
            // tells the surface to start the rendering thread
            surface.start();
        }
    }
    public static void stop() {
        // the game has now stopped
        Game.running = false;
        // tells the surface to stop the rendering thread
        surface.stop();
    }
    public static void destroy() {
        stop();

        if (isReady()) {
            // the game is not ready anymore
            Game.ready = false;
            // unloads the GameState
            unload();
            // unloads shared resources
            loader.unload();
        }

        SharedData.save();
        Game.initialized = false; // todo: ???
    }

    static void update() {
        // Checks if the holder contains any posted call and if so calls setView.
        // This has to be done to ensure there will be no issues when switching between different
        // views.
        if (holder != null) {
            setView(holder.view, holder.load, holder.unload);
            current = holder.viewID;
            holder = null;
        }

        // tells the frame rate manager we are going to update the game state
        rate.update();
        {// updates the game state (always call this between 'update' / 'finish')
            state.nextFrame();
        }// finish updating
        rate.finish();
    }
    static void render() {
        // tells the frame rate manager we are going to render and display the game state
        rate.render();
        {// renders the game state (always call this between 'render' / 'finish')
            display.nextFrame();
        }// finish rendering
        rate.finish();
    }
    static void synchronize() {
        // checks whether we need to wait or catch up on updates.
        // if there is an excess time before the frame is over.
        if (rate.getFrameExcess() >= 0) {
            try {
                // sleeps that amount of time.
                Thread.sleep(rate.getFrameExcess());
            } catch (InterruptedException e) {
                // CAN'T. SLEEP. MUST. GO. ON!
            }
        } else {
            // this means we are late on updating.
            // tries to avoid rendering the state and updates the
            // state x many times to catch up.
            do {
                // tells the frame rate manager we are going to skip one frame
                rate.skip();
                {// updates the game state
                    state.nextFrame();
                }// finish updating
                rate.finish();
            // keeps cycling until we catch up.
            }while (rate.getFrameExcess() < 0);
        }
    }

    /* private */
    private static void setDisplay(GameDisplay display) {
        if (display == null) throw new ParameterIsNullException("GameDisplay");

        // sets the rate FPS to be the same as the one specified in the display
        rate.setMaxFPS(display.maxFPS());

        // sets the current display
        Game.display = display;
        Game.display.onSet();
    }
    private static void setInput(GameInput input) {
        if (input == null) throw new ParameterIsNullException("GameInput");

        // sets the current input
        Game.input = input;
        Game.input.onSet();
    }
    private static void setState(GameState state) {
        if (state == null) throw new ParameterIsNullException("GameState");

        // sets the current state
        Game.state = state;
        Game.state.onSet();
    }

    private static void setView (GameView view, boolean load, boolean unload) {
        // unloads all the current resources
        // (remember to empty the GameLayer in your unload)
        if (unload) unload();

        //sets the view to be the current view
        setState(view.getState());
        setDisplay(view.getDisplay());
        setInput  (view.getInput());

        if (view.getLoader() != null)
            view.getLoader().onSet();

        // loads the new resources
        if (load) load();
        // tells the state current game size
        if (load) state.sizeChanged(getWidth(), getHeight());

        if (unload) System.gc();
    }

    private static class ViewHolder {
        String   viewID;
        GameView view;
        boolean  load;
        boolean  unload;

        private ViewHolder(String viewID, boolean load, boolean unload) {
            this.viewID = viewID;
            this.view = viewMap.get(viewID);
            this.load = load;
            this.unload = unload;
        }

        public static ViewHolder of(String viewID, boolean load, boolean unload) {
            ViewHolder v = null;

            if (!isRunning()) {
                //if the game is not running it's ok to set the view right away.
                setView(viewMap.get(viewID), load, unload);
                current = viewID;
            } else {
                //otherwise the view is going to be set as soon as it's safe to do so.
                v = new ViewHolder(viewID, load, unload);
            }

            return  v;
        }
    }
}
