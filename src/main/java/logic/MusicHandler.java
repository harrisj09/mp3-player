package logic;

import gui.components.SongDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicHandler {

    /**
     * Grabs folder and looks for text file containing paths to music
     *
     * Grabs the LinkedMusicList
     */
    protected SongDisplay songDisplay;
    private File mp3File = new File("mp3list.txt");

    public ObservableList<MusicNode> getMusicList() throws IOException {
        if (!mp3File.exists()) {
            mp3File.createNewFile();
            return null;
        }
        if(mp3File.length() == 0) {
            return null;
        }
        return generateSongsList(mp3File);
    }

    public ObservableList<MusicNode> generateSongsList(File mp3) {
        songDisplay = new SongDisplay();
        ObservableList<MusicNode> items = FXCollections.observableArrayList();
        // go through file and create list
/*        try {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        return items;
    }

    // https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
    public void updateSongsList(File file) {
        // write to txt with new file path
        // getMusicList is invoked
    }
}