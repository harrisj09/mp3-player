package logic;

import gui.components.SongDisplay;

import java.io.File;
import java.util.ArrayList;

public class MusicHandler {
    /**
     * Grabs folder and looks for text file containing paths to music
     */
    private File mp3;
    protected SongDisplay songDisplay;

    public ArrayList<MusicNode> getMusicList() {
        File mp3 = new File("mp3list.txt");
        if (!mp3.exists()) {

        }
        return null;
    }

    public ArrayList<File> generateSongsList(File mp3) {
        songDisplay = new SongDisplay();
        ArrayList<File> songsList = new ArrayList<>();
        return songsList;
    }
}