package com.github.harrisj09.mp3.client.application.controller;

import com.github.harrisj09.mp3.client.application.components.MusicNode;
import com.github.harrisj09.mp3.client.application.components.PlayButtonsComponent;
import com.github.harrisj09.mp3.client.application.model.MusicModel;
import com.github.harrisj09.mp3.client.application.model.queue.Queue;
import com.github.harrisj09.mp3.client.domain.Song;
import com.github.harrisj09.mp3.client.service.MusicService;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MusicController {

    private final MusicModel musicModel;
    private final AudioController audioController;
    private final PlayButtonsComponent playButtonsComponent;
    private ObservableList<MusicNode> musicList;

    public MusicController(MusicModel musicModel) {
        this.musicModel = musicModel;
        musicModel.createClientDirectory();
        this.audioController = new AudioController(musicModel);
        playButtonsComponent = new PlayButtonsComponent(this, audioController);
    }

    public ObservableList<MusicNode> grabCenterContents() {
        createSongsList();
        return musicModel.getMusicList();
    }

    public Queue<MusicNode> getMusicQueue() {
        return musicModel.getMusicQueue();
    }

    public HBox grabBottomContents() {
        return new HBox(playButtonsComponent.previousButton(), playButtonsComponent.playStatusToggle(), playButtonsComponent.skipButton());
    }

    public void createSongsList() {
        musicList = musicModel.grabSongs();
    }

    public ObservableList<MusicNode> getMusicList() {
        return musicList;
    }

    public void addSongToQueue(Song song) {

        musicModel.getMusicQueue().enqueue(getMusicList().get(song.getId()));
    }

    public void playSingleSong(Song song) {
        int id = song.getId();
        if (!songIsInLibrary(id)) {
            downloadSong(id);
        }
        playSong(id, audioController);
    }

    // TODO use this with making prev and skip work
    public boolean songIsInLibrary(int counter) {
        return getMusicList().get(counter).getClientPath() != null;
    }

    public void downloadSong(int id) {
        System.out.println("Downloading song");
        Path song = new MusicService(Paths.get("")).fetchMusicFile(getMusicList().get(id), getMusicList().get(id).getSong().getId());
        getMusicList().get(id).setClientPath(song.toAbsolutePath().toString());
    }

    public void playSong(int counter, AudioController audioController) {
        audioController.playSingleSong(getMusicList().get(counter));
    }
}