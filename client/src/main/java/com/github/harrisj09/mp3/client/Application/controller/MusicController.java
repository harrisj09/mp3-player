package com.github.harrisj09.mp3.client.Application.controller;

import com.github.harrisj09.mp3.client.Application.components.PlayButtonsComponent;
import com.github.harrisj09.mp3.client.Application.model.MusicModel;
import com.github.harrisj09.mp3.client.Application.components.MusicNode;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;

import java.io.*;

public class MusicController {

    private final MusicModel musicModel;
    private final AudioController audioController;
    private final PlayButtonsComponent playButtonsComponent;
    private final ErrorFactory errorFactory = new ErrorFactory();
    private ObservableList<MusicNode> musicList;

    public MusicController(MusicModel musicModel) {
        this.musicModel = musicModel;
        this.audioController = new AudioController();
        musicModel.createClientDirectory();
        playButtonsComponent = new PlayButtonsComponent(this, audioController);
    }

    public ObservableList<MusicNode> grabCenterContents() {
        createSongsList();
        return musicModel.getMusicList();
    }

    public HBox grabBottomContents() {
        return new HBox(playButtonsComponent.previousButton(), playButtonsComponent.playStatusToggle(), playButtonsComponent.skipButton());
    }

    public File getFile() {
        return musicModel.getMp3File();
    }

    public void createFile() throws IOException {
        musicModel.createFile();
    }

    public void createSongsList() {
        musicList = musicModel.grabSongs();
        playButtonsComponent.applyEventListeners();
    }

    public void addSong(File file) throws IOException {
        if(!isPlayableSong(file)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            errorFactory.createAlert(alert, "Information", "Error", "Incorrect File Type");
        } else {
            updateSongsFile(file);
        }
    }
    private boolean isPlayableSong(File file) {
        return fileType(file).equals(".mp3");
    }

    private String fileType(File file) {
        return file.getName().substring(file.getName().lastIndexOf("."));
    }

    public void updateSongsFile(File file) throws IOException {
        FileWriter writer = new FileWriter(musicModel.getMp3File(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        if (musicModel.alreadyAdded(file.getAbsolutePath())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            errorFactory.createAlert(alert, "Information", "Error", "Song already in library");
        }
        else {
            if (isEmptyFile()) {
                bufferedWriter.write(file.getAbsolutePath());
            } else {
                bufferedWriter.write("\n" + file.getAbsolutePath());
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            createSongsList();
        }
    }

    public boolean isEmptyFile() {
        return musicModel.getMp3File().length() == 0;
    }

    public ObservableList<MusicNode> getMusicList() {
        return musicList;
    }
}