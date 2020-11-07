package Application.controller;

import Application.components.MusicNode;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioController {
    private MusicNode songToPlay;
    Media media = null;
    MediaPlayer mediaPlayer = null;

    // https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
    public void playSong(MusicNode node) {
        if (mediaPlayer != null) {
            pauseSong();
        }
        String filePath = node.getFile().getAbsolutePath();
        media = new Media(new File(filePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    public void resumeSong() {

    }

    public void pauseSong() {
        mediaPlayer.pause();
    }

    public void skipSong() {

    }

    public void previousSong() {

    }

    public MusicNode getSongToPlay() {
        return songToPlay;
    }
}
