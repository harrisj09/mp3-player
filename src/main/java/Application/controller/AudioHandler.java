package Application.controller;

import Application.components.MusicNode;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioHandler {
    // https://www.geeksforgeeks.org/play-audio-file-using-java/

    // to store current position
    Long currentFrame;
    Clip clip;
    // current status of clip
    String statusOfSong;
    AudioInputStream songInputStream;
    MusicNode songToPlay;

    // TODO fix javax.sound.sampled.UnsupportedAudioFileException
    public void playSong(MusicNode node) throws IOException, UnsupportedAudioFileException {
        String filePath = node.getFile().getAbsolutePath();
/*        System.out.println(filePath);
        songInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        System.out.println("clicked me: " + node.getArtist());*/
        Media hit = new Media(new File(filePath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
    }

    public void resumeSong() {

    }

    public void pauseSong() {

    }
}
