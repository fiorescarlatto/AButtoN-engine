package com.abutton.game.shared;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dragonz on 17/07/2015.
 * Static class used to save global game information that is shared between
 * states and stuff.
 */
public final class SharedResources {

    private static Map<String, Object> map = new HashMap<>();

    private SharedResources() {}

    public static void put(String key, Object object) {
        map.put(key, object);
    }
    public static void remove(String key) {
        map.remove(key);
    }
    public static Object get(String key) {
        return map.get(key);
    }
}
