package com.github.harrisj09.mp3.client.application.model;

import com.github.harrisj09.mp3.client.application.components.MusicNode;
import com.github.harrisj09.mp3.client.application.model.queue.Queue;
import com.github.harrisj09.mp3.client.domain.Song;
import com.github.harrisj09.mp3.client.service.MusicService;
import com.github.harrisj09.mp3.client.service.ServiceMusicNodeDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class MusicModel {
    /**
     * This class will http request from the server
     * so it can grab data from the server
     *
     * It also creates the folder used to store music
     * downloaded from the server.
     */

    private Queue queue = new Queue();
    private ObservableList<MusicNode> musicList = FXCollections.observableArrayList();
    private List<ServiceMusicNodeDto> musicNodeDtoList = new MusicService(Paths.get("")).fetchMusicList();

    public ObservableList<MusicNode> getMusicList() {
        return musicList;
    }

    public void createClientDirectory() {
        File file = new File("client-music-folder");
        boolean createFolder = file.mkdir();
    }

    public ObservableList<MusicNode> grabSongs() {
        musicList = musicNodeDtoList.stream().map(this::convertFromDtoToMusicNode).collect(Collectors.toCollection(FXCollections::observableArrayList));
        return musicList;
    }

    private MusicNode convertFromDtoToMusicNode(ServiceMusicNodeDto nodeDto) {
        String clientPath = findClientPath(nodeDto);
        String artist = nodeDto.getArtist();
        String name = nodeDto.getSong();
        long time = nodeDto.getLength();
        int id = nodeDto.getId();
        Song song = new Song(id, artist, name, Duration.ofSeconds(time));
        return new MusicNode(song, clientPath);
    }

    public Queue getMusicQueue() {
        return queue;
    }

    // TODO Finish this
    public String findClientPath(ServiceMusicNodeDto node) {
        // check file directory
        // songs format is always "artist - song".mp3 check if it exists
        return null;
    }

    public void downloadSong(Song song) {
        int id = song.getId();
        System.out.println("Downloading song");
        Path songPath = new MusicService(Paths.get("")).fetchMusicFile(getMusicList().get(id), getMusicList().get(id).getSong().getId());
        getMusicList().get(id).setClientPath(songPath.toAbsolutePath().toString());
    }
}