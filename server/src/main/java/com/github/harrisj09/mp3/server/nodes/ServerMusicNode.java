package com.github.harrisj09.mp3.server.nodes;

import java.io.File;

public class ServerMusicNode {

    /**
     * These are the Objects used which stores the objects into the hashmap
     * the key for the hashmap is the id used in this object.
     *
     * @author John Harris
     */

    private final String path;
    private final String artist;
    private final String song;
    private final int id;
    private final long length;

    public ServerMusicNode(String path, String artist, String song, long length, int id) {
        this.path = path;
        this.artist = artist;
        this.song = song;
        this.length = length;
        this.id = id;
    }

    public File sendFileToClient() {
        return new File(path);
    }

    public String getPath() {
        return path;
    }

    public String getArtist() {
        return artist;
    }

    public String getSong() {
        return song;
    }

    public int getId() {
        return id;
    }

    public long getLength() {
        return length;
    }
}