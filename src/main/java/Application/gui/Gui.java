package Application.gui;

import Application.gui.components.MusicNode;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import Application.logic.MusicHandler;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Main Gui, starts the process of building the UI
 *
 * @author John Harris
 */
public class Gui extends Application {

    private static MusicHandler musicHandler = new MusicHandler();
    private ObservableList<MusicNode> songs =  null;

    public void buildGui() {
        launch();
    }

    public void updateSongsList(ObservableList<MusicNode> songs) {
        System.out.println("Is Songs Empty?: " + songs.isEmpty());
        this.songs = songs;
    }

    @Override
    public void start(Stage stage) {
        buildStage(stage);
    }

    /**
     * Beginning stage for application, creates base layout (BorderPane)
     * and sets up stage
     *
     * @param stage - Object in javafx to hold everything
     */
    private void buildStage(Stage stage) {
        stage.setTitle("MP3 Player");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(createTop(stage, borderPane));
        borderPane.setCenter(createCenter());
        borderPane.setBottom(createBottom());
        Scene scene = new Scene(borderPane, 600, 450);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * The following methods create the BorderPane Layouts
     * Also apply event listeners.
     *
     * Event listener opens explorer, calls handleFile.
     */
    private Node createTop(Stage stage, BorderPane borderPane) {
        Button add = new Button("Add Song");
        HBox top = new HBox(add);
        EventHandler<MouseEvent> eventHandler = e -> {
            File song = new FileChooser().showOpenDialog(stage);
            handleFile(song, borderPane);
        };
        top.setAlignment(Pos.CENTER);
        add.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        return top;
    }

    private Node createCenter() {
        // FIXME This is always passes as soon as the program starts
        if (songs == null) {
            return new HBox(new Text("Music List is Empty :("));
        }
        // https://docs.oracle.com/javafx/2/ui_controls/list-view.htm
        ListView<MusicNode> list = new ListView<>();
        ObservableList<MusicNode> items = FXCollections.observableArrayList(songs);
        list.setItems(items);
        return list;
    }

    private Node createBottom() {
        Button previous = new Button("Previous");
        ToggleButton playStatus = new ToggleButton("Play/Skip");
        Button next = new Button("Next");
        HBox bottom = new HBox(previous, playStatus, next);
        bottom.setAlignment(Pos.CENTER);
        return bottom;
    }

    /**
     * Checks file extension to see if its valid.
     *
     * @param song - File path given
     * @param borderPane - Layout
     */
    private void handleFile(File song, BorderPane borderPane) {
        String songName = song.getName().substring(song.getName().lastIndexOf("."));
        if (!songName.equals(".mp3")) {
            JOptionPane.showMessageDialog(new JFrame(), "Incorrect file type");
        } else {
            try {
                updateMusicList(song, borderPane);
            } catch (IOException | InvalidDataException | UnsupportedTagException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * Called by handleFile, calls musicMusicHandler to update songs list
     * and rewrites the center in the borderPane
     *
     * @param song - Application.Mp3 File given from handleFile
     * @param borderPane - Layout
     * @throws IOException - Invalid file
     */
    private void updateMusicList(File song, BorderPane borderPane) throws IOException, InvalidDataException, UnsupportedTagException {
        musicHandler.updateSongsList(song);
        songs = musicHandler.getMusicList();
        borderPane.setCenter(createCenter());
    }
}