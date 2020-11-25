package com.github.harrisj09.mp3.server.nodes;

public class ServerMusicNodeDto {

    /**
     * These are the Objects used which stores the objects into the hashmap
     * the key for the hashmap is the id used in this object.
     *
     * @author John Harris
     */

    private final String artist;
    private final String song;
    private final int id;
    private final long length;

    public ServerMusicNodeDto(int id, String artist, String song, long length) {
        this.id = id;
        this.artist = artist;
        this.song = song;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getSong() {
        return song;
    }

    public long getLength() {
        return length;
    }
}