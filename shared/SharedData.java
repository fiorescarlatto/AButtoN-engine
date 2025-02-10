package com.abutton.game.shared;

import com.abutton.game.Game;
import com.abutton.game.exception.SaveFileDecodeException;
import com.abutton.game.exception.SaveFileEncodeException;
import com.abutton.game.utility.SaveFile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Dragonz on 17/07/2015.
 * Static class used to save global game information that is shared between
 * states and stuff.
 */
public final class SharedData implements Serializable{

    private static Map<String, Serializable> map;

    private SharedData() {}

    /**
     * Loads the SharedData object from the Game files.
     */
    public static void load() {
        try {
            SaveFile s = new SaveFile("SharedData", Game.getContext());
            map = s.getContents();
        }
        catch (SaveFileDecodeException e) {
            map = new HashMap<>();
        }
    }

    /**
     * Saves the SharedData object to the Game files.
     */
    public static void save() {
        try {
            SaveFile s = new SaveFile("SharedData", Game.getContext(), map);
            s.save();
        }
        catch (SaveFileEncodeException e) {
            // nothing to do here
        }
    }

    /**
     * Adds a pair of key - object to the map and retrieves the Serializable object that was
     * linked to that key before.
     * @param key String, key that maps the object
     * @param object Serializable, object that has to be mapped to the given key.
     * @return Serializable that was mapped to the given key or null of there was no such mapping.
     */
    public static Serializable put(String key, Serializable object) {
        return map.put(key, object);
    }

    /**
     * Removes the key from the map and retrieves the Serializable object that was linked to
     * that key.
     * @param key String, key that maps the object
     * @return Serializable that was mapped to the given key or null of there is no such mapping.
     */
    public static Serializable remove(String key) {
        return map.remove(key);
    }

    /**
     * Returns the Serializable object that has been previously mapped to the given key.
     * @param key String, key tht maps the object
     * @return Serializable that was mapped to the given key or null of there is no such mapping.
     */
    public static Serializable get(String key) {
        return map.get(key);
    }

    /**
     * Returns the whole set of {@code String} that are every single key that is mapped inside
     * the SharedData object.
     */
    public static Set<String> keys() {
        return map.keySet();
    }

    /**
     * Retrieves the whole set of {@code Entry<String, Serializable>} that is contained inside
     * the SharedData.
     * @return a set of the mappings.
     */
    public static Set<Map.Entry<String, Serializable>> entries() {
        return map.entrySet();
    }
}
