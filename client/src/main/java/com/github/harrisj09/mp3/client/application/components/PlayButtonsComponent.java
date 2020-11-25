package com.github.harrisj09.mp3.client.application.components;

import com.github.harrisj09.mp3.client.application.controller.AudioController;
import com.github.harrisj09.mp3.client.application.controller.MusicController;
import com.github.harrisj09.mp3.client.application.model.queue.MusicQueueNode;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class PlayButtonsComponent {

    public MusicController musicController;
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
            } else {
                if(musicController.getMusicQueue().getBack() != null) {
                    MusicQueueNode queueNode = musicController.getMusicQueue().getBack();
                    audioController.playQueue(musicController.getMusicQueue().getBack());
                }
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
                int nodeId = audioController.getCurrentlyPlaying().getSong().getId();
                if (!musicController.songIsInLibrary(nodeId - 1)) {
                    musicController.downloadSong(nodeId - 1);
                }
                musicController.playSong(nodeId - 1, audioController);
            }
        };
        previous.addEventFilter(MouseEvent.MOUSE_CLICKED, previousEvent);
        return previous;
    }

    // TODO Fix this to handle non downloaded files
    public Button skipButton() {
        Button skip = new Button("Skip");
        EventHandler<MouseEvent> skipEvent = e -> {
            if(audioController.isInQueue() && musicController.getMusicQueue().getBack().getNext() != null) {
                musicController.getMusicQueue().dequeue();
                audioController.pauseSong();
                MusicQueueNode queueNode = musicController.getMusicQueue().getBack();
                audioController.playQueue(queueNode);
            } else {
                if(canSkip()) {
                    int nodeId = audioController.getCurrentlyPlaying().getSong().getId();
                    if (!musicController.songIsInLibrary(nodeId + 1)) {
                        musicController.downloadSong(nodeId + 1);
                    }
                    musicController.playSong(nodeId + 1, audioController);
                }
            }
        };
        skip.addEventFilter(MouseEvent.MOUSE_CLICKED, skipEvent);
        return skip;
    }

    private String findFileInDirectory() {
        
        return null;
    }

    private void changeToggleText(String text) {
        playStatus.setText(text);
    }

    private boolean canGoBack() {
        return audioController.getCurrentlyPlaying() != null && audioController.getCurrentlyPlaying().getSong().getId() > 0;
    }

    private boolean canSkip() {
        return audioController.getCurrentlyPlaying() != null && audioController.getCurrentlyPlaying().getSong().getId() < musicController.getMusicList().size() - 1;
    }
}
