package com.github.harrisj09.mp3.client.Application.controller;

import com.github.harrisj09.mp3.client.Application.components.MusicNode;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.harrisj09.mp3.client.Application.factories.ErrorFactory;
import com.github.harrisj09.mp3.client.Application.model.MusicModel;
import com.github.harrisj09.mp3.client.Application.model.queue.MusicQueueNode;
import com.github.harrisj09.mp3.client.service.MusicService;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class AudioController {
    // https://stackoverflow.com/questions/15635746/how-to-detect-song-playing-is-completed

    private MusicNode currentlyPlaying;
    private Media media = null;
    private MediaPlayer mediaPlayer = null;
    private ErrorFactory errorFactory = new ErrorFactory();
    private MusicModel musicModel;
    private boolean inQueue = false;

    public AudioController(MusicModel musicModel) {
        this.musicModel = musicModel;
    }

    public boolean isInQueue() {
        return inQueue;
    }

    // https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
    // When the song is done check if theres anything in the queue if true play that otherwise stop
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

    public void playQueue(MusicQueueNode musicNode) {
        if (musicNode == null) {
            System.out.println("Do nothing");
        } else {
            System.out.println("Playing queue");
            MusicNode node = musicNode.getSong();
            downloadSong(node.getId());
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

    public void downloadSong(int id) {
        System.out.println("Downloading song");
        Path song = new MusicService(Paths.get("")).fetchMusicFile(musicModel.getMusicList().get(id), musicModel.getMusicList().get(id).getId());
        musicModel.getMusicList().get(id).setClientPath(song.toAbsolutePath().toString());
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
