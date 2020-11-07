package Application.components;

import com.mpatric.mp3agic.*;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;

import javafx.scene.text.Text;
import javafx.stage.FileChooser;

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
    private final Mp3File mp3File;
    private long lengthInSeconds;
    private Button button = new Button("Play");
    private boolean isPlaying = false;

    /*
    TODO
        - Mp3agic doesn't read metadata use something else
     */
    public MusicNode(File file) throws InvalidDataException, IOException, UnsupportedTagException {
        this.file = file;
        mp3File = new Mp3File(file);
    }

    public File getFile() {
        return file;
    }

    public Node getComponent() throws InvalidDataException, IOException, UnsupportedTagException {
        handleMp3Type();
        return new HBox(getButton(), new Text(artist + " - "), new Text(title), new Text(convertTime()));
    }

    public Button playStatusButton() {
        Button button = new Button("Play");
        // TODO have it play the song
        EventHandler<MouseEvent> addEvent = e -> System.out.println("clicked me! " + lengthInSeconds);
        button.addEventFilter(MouseEvent.MOUSE_CLICKED, addEvent);
        return button;
    }

    public Button getButton() {
        return button;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
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
        getMetaData();
    }

    private void getMetaData() {
        // All songs should be renamed to this format artist - song
        String fileName = file.getName();
        artist = fileName.substring(0, fileName.indexOf("-"));
        title = fileName.substring(fileName.indexOf("-") + 1, fileName.lastIndexOf("."));
        lengthInSeconds = mp3File.getLengthInSeconds();
    }
}