package com.github.harrisj09.mp3.client.application.controller;

import com.github.harrisj09.mp3.client.application.components.MusicNode;
import com.github.harrisj09.mp3.client.application.model.MusicModel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;


public class AudioController {
    private MusicNode currentlyPlaying;
    private Media media = null;
    private MediaPlayer mediaPlayer = null;
    private MusicModel musicModel;
    private boolean inQueue = false;

    public AudioController(MusicModel musicModel) {
        this.musicModel = musicModel;
    }

    public boolean isInQueue() {
        return inQueue;
    }

    public void playSingleSong(MusicNode node) {
        if (mediaPlayer != null) {
            pauseSong();
        }
        currentlyPlaying = node;
        String filePath = node.getClientPath();
        media = new Media(new File(filePath).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        inQueue = false;
        mediaPlayer.setOnEndOfMedia(() -> {
            if (musicModel.getMusicQueue().getBack() != null) {
                playQueue(musicModel.getMusicQueue().getBack());
            }
        });
    }

    public void playQueue(MusicNode node) {
        if (node == null) {
            System.out.println("Do nothing");
        } else {
            System.out.println("Playing queue");
            musicModel.downloadSong(node.getSong());
            currentlyPlaying = node;
            String filePath = node.getClientPath();
            media = new Media(new File(filePath).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
            inQueue = true;
            mediaPlayer.setOnEndOfMedia(() -> {
                musicModel.getMusicQueue().dequeue();
                playQueue(musicModel.getMusicQueue().getBack());
            });
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
