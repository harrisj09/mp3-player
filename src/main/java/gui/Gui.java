package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.MusicHandler;
import logic.MusicNode;
import java.util.ArrayList;

/**
 * Main Gui, starts the process of building the UI
 *
 * @author John Harris
 */


/*
    TODO:
        - Create variables for each component in the gui so event listeners can reference them
        - Fix invocationException
        - Add open file explorer when add song is pressed
 */


/*
 FIXME: 9/26/2020
    - Create variables for each component in the gui so event listeners can reference them
    - Fix invocationException
 */

public class Gui extends Application {

    private static MusicHandler musicHandler;

    @Override
    public void start(Stage stage) {
        initUI(stage);
    }

    public void startMp3() {
        musicHandler = new MusicHandler();
        launch();
    }

    /*
    Important reads
     Scrollbar info - https://stackoverflow.com/questions/30971407/javafx-is-it-possible-to-have-a-scroll-bar-in-vbox
     Layouts - https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/BorderPane.html?is-external=true
     Nodes - http://tutorials.jenkov.com/javafx/node.html
     */
    private void initUI(Stage stage) {
        BorderPane borderPane = new BorderPane();
        stage.setTitle("MP3 Player");
        borderPane.setTop(createTop(stage));
        borderPane.setCenter(createCenter());
        borderPane.setBottom(createBottom());
        Scene scene = new Scene(borderPane, 600, 450);
        stage.setScene(scene);
        stage.show();
    }

    // http://fxexperience.com/2011/12/styling-fx-buttons-with-css/ use this to make a file
    private Node createTop(Stage stage) {
        Button add = new Button("Add Song");
        Button delete = new Button("Delete Song");
        HBox top = new HBox(add, delete);
        top.setAlignment(Pos.CENTER);
        EventHandler<MouseEvent> addHandler = e -> {
            addSong(stage);
        };
        //add.addEventFilter(MouseEvent.MOUSE_CLICKED, this::addSong);
        add.addEventFilter(MouseEvent.MOUSE_CLICKED, addHandler);
        return top;
    }

    private void addSong(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.showOpenDialog(stage);
        System.out.println("Add");
    }

    private Node createCenter() {
        HBox center;
        MusicHandler musicHandler = new MusicHandler();
        ArrayList<MusicNode> songs = musicHandler.getMusicList();
        if (songs == null) {
            Text text = new Text("Music List is Empty :(");
            center = new HBox(text);
        } else {
            ScrollPane scrollPane = new ScrollPane();
            center = new HBox(scrollPane);
            for (int i = 0; i < songs.size(); i++) {
                // create components inside scroll pane HBox
                // use this
                HBox musicComponent = new HBox(new Button("Play"), new Text("Artist"), new Text("Song"), new Button("Delete"));
            }
        }
        return center;
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