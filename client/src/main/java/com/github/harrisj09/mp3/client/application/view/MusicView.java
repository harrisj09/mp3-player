package com.github.harrisj09.mp3.client.application.view;

import com.github.harrisj09.mp3.client.application.components.MusicCell;
import com.github.harrisj09.mp3.client.application.components.MusicNode;
import com.github.harrisj09.mp3.client.application.controller.MusicController;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MusicView {

    /**
     * GUI is built here
     *
     * Use the controllers model to handle data
     */
    private final MusicController musicController;
    private final Stage stage;
    private BorderPane borderPane;
    private Node top;
    private Node center;
    private Node bottom;

    public MusicView(MusicController musicController, Stage stage) {
        this.musicController = musicController;
        this.stage = stage;
        borderPane = new BorderPane();
    }

    public Parent getLayout() {
        createPaneLayouts();
        setupStyleClasses();
        borderPane.setTop(top);
        borderPane.setCenter(center);
        borderPane.setBottom(bottom);
        return borderPane;
    }

    private void createPaneLayouts() {
        top = createTop();
        center = createCenter();
        bottom = createBottom();
    }

    public void setupStyleClasses() {
        borderPane.getStyleClass().add("pane");
        top.getStyleClass().add("top");
        center.getStyleClass().add("center");
        bottom.getStyleClass().add("bottom");
    }

    public Node createTop() {
        // Volume
        Slider slider = new Slider(0, 1, 0.5);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(0.25f);
        slider.setBlockIncrement(0.1f);
        // Currently Playing
        Text currentlyPlaying = new Text("Currently Playing: Artist - Name");
        return new HBox(currentlyPlaying, slider);
    }

    public Node createCenter() {
        ListView<MusicNode> list = new ListView<>();
        ObservableList<MusicNode> songs = musicController.grabCenterContents();
        list.setItems(songs);
        list.setCellFactory(param -> new MusicCell(musicController));
        return list;
    }

    public HBox createBottom() {
        HBox bottom = musicController.grabBottomContents();
        bottom.setAlignment(Pos.CENTER);
        return bottom;
    }
}
