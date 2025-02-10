package com.abutton.game.utility;

import android.content.Context;

import com.abutton.game.Game;
import com.abutton.game.exception.SaveFileDecodeException;
import com.abutton.game.exception.SaveFileEncodeException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Dragonz on 05/08/2015. <br>
 * This class is used to manage save files, which are files specialized in saving states
 */
public class SaveFile {

    /*context of the file (this application)*/
    private Context context;
    private String  fileName;

    /*map containing all of the file data*/
    private Map<String, Serializable> contents;

    public SaveFile(String fileName) throws SaveFileDecodeException {
        this(fileName, Game.getContext());
    }
    public SaveFile(String fileName, Context context) throws SaveFileDecodeException {
        this.fileName = fileName;
        this.context  = context;

        if (exists())
            load();
        else
            this.contents = new HashMap<>();
    }

    public SaveFile(String fileName, Context context, Map<String, Serializable> contents) {
        this.fileName = fileName;
        this.context  = context;
        this.contents = contents;
    }

    /**
     * Retrieves the string identifier for this file.
     * @return String, current file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Tells whether this file exists (it has already been created).
     * @return boolean, true if this file exists, false otherwise.
     */
    public boolean exists() {
        for (String name : context.fileList())
            if (name.equals(fileName)) return true;
        return false;
    }

    /**
     * Retrieves the contents of this SaveFile
     * @return {@code Map<String, Serializable>} that maps all of the objects of this file.
     */
    public Map<String, Serializable> getContents() {
        return contents;
    }

    /**
     * Sets the contents of this file to the given contents.
     * The newly set contents do not instantly get saved on the device memory. A call to save()
     * is required.
     * @param contents {@code Map<String, Serializable>} that maps all of the objects of this file.
     * @see #save()
     */
    public void setContents(Map<String, Serializable> contents) {
        this.contents = contents;
    }


    /**
     * Adds a pair of key - object to the map and retrieves the Serializable object that was
     * linked to that key before.
     * @param key String, key that maps the object
     * @param object Serializable, object that has to be mapped to the given key.
     * @return Serializable that was mapped to the given key or null of there was no such mapping.
     */
    public Serializable put(String key, Serializable object) {
        return contents.put(key, object);
    }

    /**
     * Removes the key from the map and retrieves the Serializable object that was linked to
     * that key.
     * @param key String, key that maps the object
     * @return Serializable that was mapped to the given key or null of there is no such mapping.
     */
    public Serializable remove(String key) {
        return contents.remove(key);
    }

    /**
     * Returns the Serializable object that has been previously mapped to the given key.
     * @param key String, key tht maps the object
     * @return Serializable that was mapped to the given key or null of there is no such mapping.
     */
    public Serializable get(String key) {
        return contents.get(key);
    }

    /**
     * Returns the whole set of {@code String} that are every single key that is mapped inside
     * of the SaveFile.
     */
    public Set<String> keys() {
        return contents.keySet();
    }

    /**
     * Retrieves the whole set of {@code Entry<String, Serializable>} that is contained inside
     * of the SaveFile.
     * @return a set of the mappings.
     */
    public Set<Map.Entry<String, Serializable>> entries() {
        return contents.entrySet();
    }

    /**
     * Saves the current SaveFile as it is.
     * @throws SaveFileEncodeException
     */
    public void save() throws SaveFileEncodeException {

        FileOutputStream file;
        ObjectOutputStream output;

        try {
            file   = getOutputStream(0);
            output = new ObjectOutputStream(file);

            output.writeObject(contents);

            output.close();
            file.close();
        }
        catch (Exception e) {
            throw new SaveFileEncodeException(e.getMessage());
        }

    }

    /**
     * Loads the current file into memory
     * This method is called by the object upon creation.
     * @throws SaveFileDecodeException
     */
    protected void load() throws SaveFileDecodeException {

        FileInputStream file;
        ObjectInputStream input;

        try {
            file  = getInputStream();
            input = new ObjectInputStream(file);

            contents = (HashMap<String, Serializable>) input.readObject();

            input.close();
            file.close();
        }
        catch (Exception e) {
            throw new SaveFileDecodeException(e.getMessage());
        }
    }

    protected FileInputStream getInputStream() throws FileNotFoundException {
        return context.openFileInput(fileName);
    }
    protected FileOutputStream getOutputStream(int mode) throws FileNotFoundException {
        return context.openFileOutput(fileName, mode);
    }
}
