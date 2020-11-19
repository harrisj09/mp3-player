package com.github.harrisj09.mp3.client.Application.controller;

import com.github.harrisj09.mp3.client.Application.components.MusicNode;

import java.io.File;

import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.StageStyle;


public class AudioController {
    private MusicNode currentlyPlaying;
    private Media media = null;
    private MediaPlayer mediaPlayer = null;
    private ErrorFactory errorFactory = new ErrorFactory();

    // https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
    public void playSong(MusicNode node, int listSize) {
        if (node.getId() > listSize - 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            errorFactory.createAlert(alert, "Information", "Error", "Out of bounds");
        }
        else {
            if (mediaPlayer != null) {
                pauseSong();
            }
            currentlyPlaying = node;
            String filePath = node.getClientPath();
            media = new Media(new File(filePath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
    }

    public void resumeSong() {
        mediaPlayer.play();
    }

    public void pauseSong() {
        mediaPlayer.pause();
    }

    public MusicNode getCurrentlyPlaying() {
        return currentlyPlaying;
    }
}
