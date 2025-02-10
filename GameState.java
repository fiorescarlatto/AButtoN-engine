package com.abutton.game;

/**
 * Created by Dragonz on 21/06/2015. <br>
 * Class that holds the game state, loaded to be drawn onto the screen
 */
public abstract class GameState {
    // flag to hold the loaded state
    private boolean loaded;

    // loader that allows the state to easily load all the needed resources
    private GameStateLoader loader;

    /**
     * Constructor. <br>
     * Creates a new {@link GameState}
     */
    public GameState() {
        this(null);
    }
    public GameState(GameStateLoader loader) {
        this.loaded = false;
        this.loader = loader;
    }

    /**
     * Tells whether this state has already been loaded.
     * @return boolean, true if the state is loaded, false otherwise
     */
    public boolean isLoaded() {
        return this.loaded;
    }

    /**
     * Tells whether this state is persistent (meaning it should save/read data to a file each time
     * it is loaded or unloaded from memory. <br>
     * This method should be overridden if necessary. Default implementation always returns false.
     * @return boolean, true if this state is persistent false otherwise.
     * @see GameStateLoader#save(GameState)
     * @see GameStateLoader#read(GameState)
     */
    public boolean isPersistent() {
        return false;
    }

    @SuppressWarnings("unused")
    public GameStateLoader getLoader() {
        return this.loader;
    }
    public void setLoader(GameStateLoader loader) {
        this.loader = loader;
    }

    /**
     * Called by {@link Game} every time this state is set as the current state for the game.
     * If overriding this method {@code super.onSet()} needs to be called first.
     */
    public void onSet() {}

    /**
     * Automatically called by the {@link Game} when the surface size is changed. <br>
     * This method will be called also every time a this state is set in the displaying view.
     * @see Game#setView(String)
     */
    public void sizeChanged(int width, int height) {}

    /**
     * Package only visible method used to encapsulate {@code void update()}.
     */
    void nextFrame() {
        // update all elements inside this state
        Game.getLayer().update(this);
        // user defined update function
        update();
    }

    /**
     * Automatically called by the {@link Game} when this state needs to load. <br>
     * This method will be called also every time this state is set in the displaying view.
     * @see Game#setView(String)
     */
    void load() {
        if (isLoaded()) return;

        loaded = true;
        if (loader == null) return;

        loader.load(this);

        if (isPersistent()) {
            loader.read(this);
        }
    }
    /**
     * Automatically called by the {@link Game} when this state needs to unload. <br>
     * This method will be called also every time a different state is set in the displaying view.
     * @see Game#setView(String)
     */
    void unload() {
        if (!isLoaded()) return;

        loaded = false;
        if (loader == null) return;

        if (isPersistent()) {
            loader.save(this);
        }
        loader.unload(this);
    }

    /**
     * Automatically called by the {@link Game} when this state needs to save before the
     * application is killed. <br>
     */
    void save() {
        if (isPersistent())
            loader.save(this);
    }

    protected abstract void update();
}
