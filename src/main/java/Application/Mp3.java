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

    public Mp3(MusicHandler musicHandler, AudioPlayer audioPlayer, Gui gui) {
        this.musicHandler = musicHandler;
        this.audioPlayer = audioPlayer;
        this.gui = gui;
    }

    public void start() throws InvalidDataException, IOException, UnsupportedTagException {
        ObservableList<MusicNode> songs = musicHandler.getMusicList();
        // add an arg for music list and pass in songs
        gui.buildGui();
    }
}
