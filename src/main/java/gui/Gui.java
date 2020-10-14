package gui;

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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.MusicHandler;
import logic.linkedmusiclist.MusicNode;

import javax.swing.*;
import java.io.File;
import java.util.Optional;

/**
 * Main Gui, starts the process of building the UI
 *
 * @author John Harris
 */


/*
    TODO:
        - Create variables for each component in the gui so event listeners can reference them
        - Fix invocationException
        - MusicNode is a collection
 */


/*
 FIXME: 9/26/2020
    - Create variables for each component in the gui so event listeners can reference them
    - Fix invocationException
 */

public class Gui extends Application {

    private static MusicHandler musicHandler;
    private ObservableList<MusicNode> songs;

    @Override
    public void start(Stage stage) {
        initUI(stage);
    }

    public void startMp3() {
        musicHandler = new MusicHandler();
        songs = musicHandler.getMusicList();
        launch();
    }

    private void initUI(Stage stage) {
        BorderPane borderPane = new BorderPane();
        stage.setTitle("MP3 Player");
        borderPane.setTop(createTop(stage, borderPane));
        borderPane.setCenter(createCenter());
        borderPane.setBottom(createBottom());
        Scene scene = new Scene(borderPane, 600, 450);
        stage.setScene(scene);
        stage.show();
    }

    // http://fxexperience.com/2011/12/styling-fx-buttons-with-css/ use this to make a file
    private Node createTop(Stage stage, BorderPane borderPane) {
        Button add = new Button("Add Song");
        HBox top = new HBox(add);
        top.setAlignment(Pos.CENTER);
        EventHandler<MouseEvent> addHandler = e -> {
            File song = new FileChooser().showOpenDialog(stage);
            if (!getExtension(song.getName()).equals(Optional.of(".mp3"))) {
                JFrame wrongFileType = new JFrame();
                JOptionPane.showMessageDialog(wrongFileType, "Incorrect file type");
            } else {
                updateMusicList(song, borderPane);
            }
        };
        add.addEventFilter(MouseEvent.MOUSE_CLICKED, addHandler);
        return top;
    }

    //https://www.baeldung.com/java-file-extension
    private Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".")));
    }

    private void updateMusicList(File song, BorderPane borderPane) {
        musicHandler.updateSongsList(song);
        songs = musicHandler.getMusicList();
        borderPane.setCenter(createCenter());
    }

    private Node createCenter() {
        if (songs == null) {
            return new HBox(new Text("Music List is Empty :("));
        }
        // https://docs.oracle.com/javafx/2/ui_controls/list-view.htm
        ListView<MusicNode> list = new ListView<>();
        ObservableList<MusicNode> items = FXCollections.observableArrayList(songs);
        list.setItems(items);
        return list;
    }

    // Pause, play, etc
    private Node createBottom() {
        Button previous = new Button("Previous");
        ToggleButton onAndOffToggle = new ToggleButton("Play/Skip");
        Button next = new Button("Next");
        HBox bottom = new HBox(previous, onAndOffToggle, next);
        bottom.setAlignment(Pos.CENTER);
        return bottom;
    }
}