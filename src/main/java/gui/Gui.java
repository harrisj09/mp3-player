package gui;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.MusicHandler;
import logic.MusicNode;

import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Main Gui, starts the process of building the UI
 *
 * @author John Harris
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
        borderPane.setCenter(createCenter());
        borderPane.setBottom(createBottom());
        Scene scene = new Scene(borderPane, 600, 450);
        stage.setScene(scene);
        stage.show();
    }

    // http://fxexperience.com/2011/12/styling-fx-buttons-with-css/ use this to make a file
    // Top menu file, help etc
    private Node createTop() {
        HBox top = new HBox(new Button("Add Song"), new Button("Delete Song"));
        top.setAlignment(Pos.CENTER);
        return top;
    }

    // Shows list of music
    private Node createCenter() {
        // Grab the data from the MusicHandler and use a loop to create all of them in a holder
        ArrayList<MusicNode> songs = new MusicHandler().getMusicList();

        for (int i = 0; i < songs.size(); i++) {

        }
        return null;
    }

    // Pause, play, etc
    private Node createBottom() {
        HBox bottom = new HBox(new Button("Previous"), new ToggleButton("Play/Skip"), new Button("Next"));
        bottom.setAlignment(Pos.CENTER);
        return bottom;
    }
}