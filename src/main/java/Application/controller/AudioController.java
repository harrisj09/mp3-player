package Application.controller;

import Application.components.MusicNode;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;

public class AudioController {
    private MusicNode currentlyPlaying;
    private Media media = null;
    private MediaPlayer mediaPlayer = null;

    // https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
    public void playSong(MusicNode node, int listSize) {
        if (node.getId() > listSize - 1) {
            JOptionPane.showMessageDialog(new JFrame(), "Out of bounds");
        }
        else {
            if (mediaPlayer != null) {
                pauseSong();
            }
            currentlyPlaying = node;
            String filePath = node.getFile().getAbsolutePath();
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
