package com.github.harrisj09.mp3.client.Application.controller;

import com.github.harrisj09.mp3.client.Application.components.MusicNode;
import com.github.harrisj09.mp3.client.Application.components.PlayButtonsComponent;
import com.github.harrisj09.mp3.client.Application.model.MusicModel;
import com.github.harrisj09.mp3.client.Application.model.queue.MusicQueue;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;

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

    public MusicQueue getMusicQueue() {
        return musicModel.getMusicQueue();
    }

    public HBox grabBottomContents() {
        return new HBox(playButtonsComponent.previousButton(), playButtonsComponent.playStatusToggle(), playButtonsComponent.skipButton());
    }

    public void createSongsList() {
        musicList = musicModel.grabSongs();
        playButtonsComponent.applyEventListeners();
    }

    public ObservableList<MusicNode> getMusicList() {
        return musicList;
    }
}