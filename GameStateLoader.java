package com.abutton.game;

/**
 * Created by Dragonz on 21/06/2015.
 * Class used to load game resources such as textures, paint,
 * sound, fonts and so on.
 */
public abstract class GameStateLoader {

    /**
     * Constructor. <br>
     * Creates a new {@link GameStateLoader}.
     */
    public GameStateLoader() {}

    /**
     * Called by {@link Game} every time this loader is set as the current loader for the game.
     * If overriding this method {@code super.onSet()} needs to be called first.
     */
    public void onSet() {}

    /**
     * Persistent enabled load method. <br>
     * This method is called by {@link Game} when this state is persistent. This method will be
     * called in addition to the usual {@code load(GameState state)}. <br>
     * @param gameState {@link GameState} that has to be loaded.
     * @see GameState#isPersistent()
     * @see com.abutton.game.utility.SaveFile
     */
    @SuppressWarnings("unused")
    public void read(GameState gameState) {
        // empty, needs to be overridden
    }
    /**
     * Persistent enabled save method. <br>
     * Called when it is required to save the current {@link GameState} into a {@link com.abutton.game.utility.SaveFile}. <br>
     * This method is called by {@link Game} when this state is persistent. This method will be
     * called in addition and before the usual {@code unload(GameState state)}. <br>
     * @param gameState {@link GameState} that has to be saved.
     * @see GameState#isPersistent()
     * @see com.abutton.game.utility.SaveFile
     */
    @SuppressWarnings("unused")
    public void save(GameState gameState) {
        // empty, needs to be overridden
    }

    /**
     * Automatically called by {@link Game} when the current state is ready to be loaded. <br>
     * Usually this method loads Images, Music, and other elements needed by the state.
     * @param gameState state that need to be loaded.
     */
    public abstract void load(GameState gameState);
    /**
     * Automatically called by {@link Game} when the current state is not needed anymore and all of
     * it's resources must be de-allocated. <br>
     * Usually this method empties all of the layers and sets to null all the references to loaded
     * resources allowing the garbage collector to dispose of them.
     * @param gameState state that need to be unloaded.
     */
    public abstract void unload(GameState gameState);
}
