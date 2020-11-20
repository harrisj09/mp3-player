package com.github.harrisj09.mp3.client.Application.view;

import com.github.harrisj09.mp3.client.Application.components.MusicCell;
import com.github.harrisj09.mp3.client.Application.components.MusicNode;
import com.github.harrisj09.mp3.client.Application.controller.MusicController;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MusicView {

    /**
     * GUI is built here
     *
     * Use the controllers model to handle data
     */
    private final MusicController musicController;
    private final Stage stage;
    private BorderPane borderPane;

    public MusicView(MusicController musicController, Stage stage) {
        this.musicController = musicController;
        this.stage = stage;
        borderPane = new BorderPane();
    }

    public Parent getLayout() {
        borderPane.setCenter(createCenter());
        borderPane.setBottom(createBottom());
        return borderPane;
    }

    public HBox createTop() {
        Button add = new Button("Add Song");
        HBox top = new HBox(add);
        EventHandler<MouseEvent> addEvent = e -> {
            File song = new FileChooser().showOpenDialog(stage);
            try {
                musicController.addSong(song);
                borderPane.setCenter(updateCenter());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };
        top.setAlignment(Pos.CENTER);
        add.addEventFilter(MouseEvent.MOUSE_CLICKED, addEvent);
        return top;
    }

    public Node createCenter() {
        ListView<MusicNode> list = new ListView<>();
        ObservableList<MusicNode> songs = musicController.grabCenterContents();
        list.setItems(songs);
        list.setCellFactory(param -> new MusicCell());
        return list;
    }

    public HBox createBottom() {
        HBox bottom = musicController.grabBottomContents();
        bottom.setAlignment(Pos.CENTER);
        return bottom;
    }

    public Node updateCenter() {
        ListView<MusicNode> list = new ListView<>();
        ObservableList<MusicNode> songs = musicController.grabCenterContents();
        list.setItems(songs);
        list.setCellFactory(param -> new MusicCell());
        return list;
    }
}
