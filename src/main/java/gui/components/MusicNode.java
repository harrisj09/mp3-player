package gui.components;

import com.mpatric.mp3agic.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;

import javafx.scene.text.Text;
import logic.MusicHandler;

import javax.print.attribute.standard.Media;

/**
 * https://github.com/mpatric/mp3agic
 *
 * Use this to play mp3Files
 * https://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java/10237397#10237397
 *
 * MusicNode contains the JavaFX node for each song
 *
 * @author John Harris
 */
public class MusicNode {
    private File file;
    private String title;
    private String artist;
    private long lengthInSeconds;
    private Mp3File mp3File;
    private boolean isPlaying = false;
    private MusicHandler musicHandler;

    /*
    Make it so I dont need all of these exceptions
     */
    public MusicNode(File file) throws InvalidDataException, IOException, UnsupportedTagException {
        this.file = file;
        mp3File = new Mp3File(file);
    }

    public Node getComponent() throws InvalidDataException, IOException, UnsupportedTagException {
        handleMp3Type();
        return new HBox(new Button("Play/Pause"), new Text(title), new Text(artist), new Text(convertTime()));
    }

    private String convertTime() {
        long minutes = lengthInSeconds / 60;
        long seconds = lengthInSeconds - (minutes * 60);
        if(seconds < 10) {
            return minutes + ":0" + seconds;
        }
        return minutes + ":" + seconds;
    }

    private void handleMp3Type() throws InvalidDataException, IOException, UnsupportedTagException {
        // I pray to god nothing has a customId
        Mp3File mp3File = new Mp3File(file);
        if(mp3File.hasId3v1Tag()) {
            ID3v1 id3v1Tag = mp3File.getId3v1Tag();
            getMetaData(id3v1Tag);
        }
        if (mp3File.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3File.getId3v2Tag();
            getMetaData(id3v2Tag);
        }
    }

    private void getMetaData(ID3v1 idTag) {
        title = idTag.getTitle();
        artist = idTag.getAlbum();
        lengthInSeconds = mp3File.getLengthInSeconds();
    }
}
