package gui;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.MusicHandler;

import javax.swing.*;
import java.awt.geom.Rectangle2D;

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
        JButton test = new JButton("Hello");
        stage.setTitle("MP3 Player");
        borderPane.setTop(createTop());
        borderPane.setCenter(createCenter());
        borderPane.setBottom(createBottom());
/*        // Layout
        StackPane root = new StackPane();
        // Layout, width and height scene holds components and is inside of stage
        Scene scene = new Scene(root, 600, 450);

        // Presets
        stage.setScene(scene);*/
        stage.show();
    }

    // Top menu file, help etc
    private Node createTop() {
        return null;
    }

    // Shows list of music
    private Node createCenter() {
        return null;
    }

    // Pause, play, etc
    private Node createBottom() {
        return null;
    }
}