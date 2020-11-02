package Application;

import Application.components.MusicNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
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

    public void createFile() throws IOException {
        mp3File.createNewFile();
    }

    public void setMusicList(ObservableList<MusicNode> musicList) {
        this.musicList = musicList;
    }
}
