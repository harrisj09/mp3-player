package com.github.harrisj09.mp3.client.Application.model;

import com.github.harrisj09.mp3.client.Application.components.MusicNode;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MusicModel {
    /**
     * Store list of musicnodes
     * store currentlyplaying
     * store metadata
     */

    private ObservableList<MusicNode> musicList = FXCollections.observableArrayList();
    private File mp3File = new File("mp3List.txt");

    public ObservableList<MusicNode> getMusicList() {
        return musicList;
    }

    public File getMp3File() {
        return mp3File;
    }

    public ObservableList<MusicNode> grabSongs() {
        musicList = FXCollections.observableArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(getMp3File()));
            String filePath;
            int counter = 0;
            while((filePath = br.readLine()) != null) {
                File temp = new File(filePath);
                if (temp.exists()) {
                    MusicNode node = new MusicNode(new File(filePath), counter);
                    counter++;
                    musicList.add(node);
                }
            }
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }
        return musicList;
    }

    public void createFile() throws IOException {
        mp3File.createNewFile();
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
