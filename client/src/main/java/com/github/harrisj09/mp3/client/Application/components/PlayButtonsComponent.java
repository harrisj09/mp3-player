package com.github.harrisj09.mp3.client.Application.components;

import com.github.harrisj09.mp3.client.Application.controller.AudioController;
import com.github.harrisj09.mp3.client.Application.controller.MusicController;
import com.github.harrisj09.mp3.client.Application.model.queue.MusicQueueNode;
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
                int nodeId = audioController.getCurrentlyPlaying().getId();
                if (!songIsInLibrary(nodeId - 1)) {
                    downloadSong(nodeId - 1);
                }
                audioController.playSingleSong(musicController.getMusicList().get(nodeId - 1));
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
                    int nodeId = audioController.getCurrentlyPlaying().getId();
                    if (!songIsInLibrary(nodeId + 1)) {
                        downloadSong(nodeId + 1);
                    }
                    audioController.playSingleSong(musicController.getMusicList().get(nodeId + 1));
                }
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
                audioController.playSingleSong( musicController.getMusicList().get(counter));
            };
            EventHandler<MouseEvent> queueEvent = e -> {
                System.out.println("Added " + musicController.getMusicList().get(counter).getArtist());
                musicController.getMusicQueue().enqueue(musicController.getMusicList().get(counter));
            };
            musicController.getMusicList().get(counter).getPlayButton().addEventFilter(MouseEvent.MOUSE_CLICKED, playEvent);
            musicController.getMusicList().get(counter).getAddToQueue().addEventFilter(MouseEvent.MOUSE_CLICKED, queueEvent);
        }
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
