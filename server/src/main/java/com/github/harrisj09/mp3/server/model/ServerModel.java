package com.github.harrisj09.mp3.server.model;

import com.github.harrisj09.mp3.server.nodes.ServerMusicNode;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

@Service
public class ServerModel {

    private HashMap<Integer, ServerMusicNode> musicMap = new HashMap<>();

    public void createMusicMap() throws InvalidDataException, IOException, UnsupportedTagException {
        File file = createFile();
        int counter = 0;
        for (final File fileEntry : Objects.requireNonNull(file.listFiles())) {
            String fileName = fileEntry.getName();
            String artist = fileName.substring(0, fileName.indexOf("-") - 1);
            String song = fileName.substring(fileName.indexOf("-") + 2);
            Mp3File mp3File = new Mp3File(fileEntry);
            musicMap.put(counter, new ServerMusicNode(fileEntry.getPath(), artist, song, mp3File.getLengthInSeconds(), counter));
            counter++;
        }
    }

    public File createFile() {
        File file = new File("server-music-folder");
        boolean createFolder = file.mkdir();
        System.out.println(file.isDirectory());
        String result = createFolder ? "\u001B[31m" + "Directory created successfully, this isn't good" + "\u001B[0m" : "\u001B[32m" + "Directory already exists" + "\u001B[0m";
        System.out.println(result);
        return file;
    }

    public HashMap<Integer, ServerMusicNode> getMusicMap() {
        return musicMap;
    }
}