package com.github.harrisj09.mp3.client.Application.model;

import com.github.harrisj09.mp3.client.Application.components.MusicNode;
import com.github.harrisj09.mp3.client.service.MusicService;
import com.github.harrisj09.mp3.client.service.ServiceMusicNodeDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class MusicModel {
    /**
     * This class will http request from the server
     * so it can grab data from the server
     *
     * It also creates the folder used to store music
     * downloaded from the server.
     */

    private ObservableList<MusicNode> musicList = FXCollections.observableArrayList();
    private File mp3File = new File("mp3List.txt");
    private List<ServiceMusicNodeDto> musicNodeDtoList = new MusicService(Paths.get("")).fetchMusicList();
    private File file;

    public ObservableList<MusicNode> getMusicList() {
        return musicList;
    }

    public File getMp3File() {
        return mp3File;
    }

    public ObservableList<MusicNode> grabSongs() {
        musicList = FXCollections.observableArrayList();
        int counter = 0;
        while(counter < musicNodeDtoList.size()) {
            String clientPath = findClientPath(musicNodeDtoList.get(counter));
            String serverPath = musicNodeDtoList.get(counter).getPath();
            String artist = musicNodeDtoList.get(counter).getArtist();
            String song = musicNodeDtoList.get(counter).getSong();
            long time = musicNodeDtoList.get(counter).getLength();
            int id = musicNodeDtoList.get(counter).getId();
            MusicNode node = new MusicNode(serverPath, artist, song, time, id);
            musicList.add(node);
            counter++;
        }
        return musicList;
    }

    public String findClientPath(ServiceMusicNodeDto node) {
        // check file directory
        // songs format is always "artist - song".mp3 check if it exists
        return null;
    }

    // TODO Delete this
    public void createFile() throws IOException {
        mp3File.createNewFile();
    }

    public void createClientDirectory() {
        file = new File("client-music-folder");
        boolean createFolder = file.mkdir();
        System.out.println(file.isDirectory());
    }

    public boolean alreadyAdded(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(getMp3File()));
        String filePath;
        while((filePath = br.readLine()) != null) {
            if(file.equals(filePath)) {
                return true;
            }
        }
        return false;
    }
}