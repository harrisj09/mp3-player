package com.github.harrisj09.mp3.client.Application.components;

import com.github.harrisj09.mp3.client.Application.controller.AudioController;
import com.github.harrisj09.mp3.client.Application.controller.MusicController;
import com.github.harrisj09.mp3.client.service.MusicService;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PlayButtonsComponent {

    private MusicController musicController;
    private AudioController audioController;
    private Button playStatus;
    private String playStatusToggleText = "Play";

    // todo swap musiccontroller with musicmodel for grabbing list
    public PlayButtonsComponent(MusicController musicController, AudioController audioController) {
        this.musicController = musicController;
        this.audioController = audioController;
    }

    public Button playStatusToggle() {
        playStatus = new Button(playStatusToggleText);
        EventHandler<MouseEvent> toggleStatus = e -> {
            if (audioController.getCurrentlyPlaying() != null) {
                if (playStatusToggleText.equals("Play")) {
                    playStatusToggleText = "Pause";
                    audioController.resumeSong();
                } else {
                    playStatusToggleText = "Play";
                    audioController.pauseSong();
                }
                changeToggleText(playStatusToggleText);
            }
        };
        playStatus.addEventFilter(MouseEvent.MOUSE_CLICKED, toggleStatus);
        return playStatus;
    }

    // TODO Fix this to handle non downloaded files
    public Button previousButton() {
        Button previous = new Button("Previous");
        EventHandler<MouseEvent> previousEvent = e -> {
            if(canGoBack()) {
                int nodeId = audioController.getCurrentlyPlaying().getId();
                if (!songIsInLibrary(nodeId - 1)) {
                    downloadSong(nodeId - 1);
                }
                audioController.playSong(musicController.getMusicList().get(nodeId - 1),  musicController.getMusicList().size());
            }
        };
        previous.addEventFilter(MouseEvent.MOUSE_CLICKED, previousEvent);
        return previous;
    }

    // TODO Fix this to handle non downloaded files
    public Button skipButton() {
        Button skip = new Button("Skip");
        EventHandler<MouseEvent> skipEvent = e -> {
            if(canSkip()) {
                int nodeId = audioController.getCurrentlyPlaying().getId();
                if (!songIsInLibrary(nodeId + 1)) {
                    downloadSong(nodeId + 1);
                }
                audioController.playSong(musicController.getMusicList().get(nodeId + 1), musicController.getMusicList().size());
            }
        };
        skip.addEventFilter(MouseEvent.MOUSE_CLICKED, skipEvent);
        return skip;
    }

    public void applyEventListeners() {
        for (int i = 0; i <  musicController.getMusicList().size(); i++) {
            int counter = i;
            EventHandler<MouseEvent> playEvent = e -> {
                if (!songIsInLibrary(counter)) {
                    downloadSong(counter);
                }
                audioController.playSong( musicController.getMusicList().get(counter),  musicController.getMusicList().size());
            };
            musicController.getMusicList().get(counter).getButton().addEventFilter(MouseEvent.MOUSE_CLICKED, playEvent);
        }
    }

    public String findFile() {
        return null;
    }

    public void downloadSong(int id) {
        System.out.println("Downloading song");
        Path song = new MusicService(Paths.get("")).fetchMusicFile(musicController.getMusicList().get(id), musicController.getMusicList().get(id).getId());
        musicController.getMusicList().get(id).setClientPath(song.toAbsolutePath().toString());
    }

    // TODO use this with making prev and skip work
    private boolean songIsInLibrary(int counter) {
        return musicController.getMusicList().get(counter).getClientPath() != null;
    }

    private String findFileInDirectory() {
        
        return null;
    }

    private void changeToggleText(String text) {
        playStatus.setText(text);
    }

    private boolean canGoBack() {
        return audioController.getCurrentlyPlaying() != null && audioController.getCurrentlyPlaying().getId() > 0;
    }

    private boolean canSkip() {
        return audioController.getCurrentlyPlaying() != null && audioController.getCurrentlyPlaying().getId() < musicController.getMusicList().size() - 1;
    }
}
