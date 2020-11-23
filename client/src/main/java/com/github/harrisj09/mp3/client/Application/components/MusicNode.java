package com.github.harrisj09.mp3.client.Application.components;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * https://github.com/mpatric/mp3agic
 *
 * Use this to play mp3Files
 * https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java/10237397#10237397
 *
 * MusicNode contains the JavaFX node for each song
 *
 * @author John Harris
 */
public class MusicNode {
    private String serverPath;
    private String clientPath;
    private String artist;
    private String song;
    private long lengthInSeconds;
    private int id;
    private Button playButton = new Button("Play");
    private Button addToQueue = new Button("Add");

    public MusicNode(String serverPath, String title, String artist, long lengthInSeconds, int id) {
        this.serverPath = serverPath;
        this.artist = title;
        this.song = artist;
        this.lengthInSeconds = lengthInSeconds;
        this.id = id;
        clientPath = null;
    }

    public String getClientPath() {
        return clientPath;
    }

    public void setClientPath(String clientPath) {
        this.clientPath = clientPath;
    }

    public int getId() {
        return id;
    }

    public Node getComponent() {
        return new HBox(getAddToQueue(), getPlayButton(), new Text(song + " - "), new Text(artist), new Text(convertTime()));
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getAddToQueue() {
        return addToQueue;
    }

    public String getArtist() {
        return artist;
    }

    public String getSong() {
        return song;
    }

    private String convertTime() {
        long minutes = lengthInSeconds / 60;
        long seconds = lengthInSeconds - (minutes * 60);
        if(seconds < 10) {
            return minutes + ":0" + seconds;
        }
        return minutes + ":" + seconds;
    }
}