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

    public LinkedMusicNode getMusicList() {
        File mp3File = new File("mp3list.txt");
        if (!mp3File.exists()) {
            return null;
        }
        return generateSongsList(mp3File);
    }

    public LinkedMusicNode generateSongsList(File mp3) {
        songDisplay = new SongDisplay();
        LinkedMusicNode songsList = new LinkedMusicNode();
        return songsList;
    }
}