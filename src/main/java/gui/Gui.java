package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
        borderPane.setTop(createTop());
        //borderPane.setCenter(createCenter());
        borderPane.setBottom(createBottom());
        Scene scene = new Scene(borderPane, 600, 450);
        stage.setScene(scene);
        stage.show();
    }

    // http://fxexperience.com/2011/12/styling-fx-buttons-with-css/ use this to make a file
    private Node createTop() {
        HBox top = new HBox(new Button("Add Song"), new Button("Delete Song"));
        top.setAlignment(Pos.CENTER);
        return top;
    }

    private Node createCenter() {
        ScrollPane scrollPane = new ScrollPane();
        MusicHandler musicHandler = new MusicHandler();
        ArrayList<MusicNode> songs = musicHandler.getMusicList();
        for (int i = 0; i < songs.size(); i++) {
            // create components inside scroll pane HBox
        }
        return scrollPane;
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