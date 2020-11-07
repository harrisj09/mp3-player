package Application.controller;

import Application.components.MusicNode;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioController {
    private MusicNode currentlyPlaying;
    private Media media = null;
    private MediaPlayer mediaPlayer = null;

    // https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
    public void playSong(MusicNode node) {
        if (mediaPlayer != null) {
            pauseSong();
        }
        currentlyPlaying = node;
        String filePath = node.getFile().getAbsolutePath();
        media = new Media(new File(filePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
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
