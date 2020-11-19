package com.github.harrisj09.mp3.client.Application.components;

import com.mpatric.mp3agic.*;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

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
    private Button button = new Button("Play");

    public MusicNode(String serverPath, String title, String artist, long lengthInSeconds, int id) {
        this.clientPath = clientPath;
        this.serverPath = serverPath;
        this.artist = title;
        this.song = artist;
        this.lengthInSeconds = lengthInSeconds;
        this.id = id;
        clientPath = null;
    }

    public String getServerPath() {
        return serverPath;
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

    public Node getComponent() throws InvalidDataException, IOException, UnsupportedTagException {
        return new HBox(getButton(), new Text(song + " - "), new Text(artist), new Text(convertTime()));
    }

    public Button playStatusButton() {
        Button button = new Button("Play");
        EventHandler<MouseEvent> addEvent = e -> System.out.println("clicked me! " + lengthInSeconds);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, addEvent);
        return button;
    }

    public Button getButton() {
        return button;
    }

    public String getArtist() {
        return artist;
    }

    public String getSong() {
        return song;
    }

    public String getLengthInSeconds() {
        return convertTime();
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