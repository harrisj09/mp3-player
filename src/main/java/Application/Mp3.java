package Application;

import Application.gui.Gui;
import Application.gui.components.MusicNode;
import Application.logic.AudioPlayer;
import Application.logic.MusicHandler;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.collections.ObservableList;

import java.io.IOException;

public class Mp3 {
    private MusicHandler musicHandler;
    private AudioPlayer audioPlayer;
    private Gui gui;

    public Mp3() {
        this.musicHandler = new MusicHandler();
        this.audioPlayer = new AudioPlayer();
        this.gui = new Gui();
    }

    public void start() throws InvalidDataException, IOException, UnsupportedTagException {
        System.out.println("Checking from mp3: " + musicHandler.getMusicList().isEmpty());
        gui.updateSongsList(musicHandler.getMusicList());
        gui.buildGui();
    }
}