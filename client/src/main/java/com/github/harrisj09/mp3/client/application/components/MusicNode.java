package com.github.harrisj09.mp3.client.application.components;

import com.github.harrisj09.mp3.client.domain.Song;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.time.Duration;

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
    private Song song;
    private String clientPath;
    private Button playButton = new Button("Play");
    private Button addToQueue = new Button("Add");

    public MusicNode(Song song, String clientPath) {
        this.song = song;
        this.clientPath = clientPath;
    }

    public String getClientPath() {
        return clientPath;
    }

    public void setClientPath(String clientPath) {
        this.clientPath = clientPath;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getAddToQueue() {
        return addToQueue;
    }

    public Song getSong() {
        return song;
    }

}