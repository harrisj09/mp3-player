package logic;

import gui.components.SongDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.linkedmusiclist.LinkedMusicList;
import logic.linkedmusiclist.MusicNode;

import java.io.File;

public class MusicHandler {
    /**
     * Grabs folder and looks for text file containing paths to music
     */
    protected SongDisplay songDisplay;
    private File mp3File = new File("mp3list.txt");

    public ObservableList<MusicNode> getMusicList() {
        if (!mp3File.exists()) {
            return null;
        }
        LinkedMusicList musicList = generateSongsList(mp3File);
        ObservableList<MusicNode> items = FXCollections.observableArrayList();
        MusicNode curr = musicList.getHead();
        while (curr.hasNext()) {
            items.add(curr);
            curr = curr.getNext();
        }
        return items;
    }

    public LinkedMusicList generateSongsList(File mp3) {
        songDisplay = new SongDisplay();
        LinkedMusicList songsList = new LinkedMusicList();
        // go through file and create list
        return songsList;
    }

    public void updateSongsList(File file) {
        // write to txt with new file path
        // getMusicList is invoked
    }
}