package com.github.harrisj09.mp3.client.application.components;

import com.github.harrisj09.mp3.client.application.controller.MusicController;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MusicCell extends ListCell<MusicNode> {
    private MusicController musicController;

    /*
    TODO
        Store the cellfactory in this class

        credit for the cell factory
        https://docs.oracle.com/javafx/2/ui_controls/list-view.htm
     */

    public MusicCell(MusicController musicController) {
        super();
        this.musicController = musicController;
    }

    @Override
    protected void updateItem(MusicNode item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            setGraphic(getComponent(item));
        }
    }

    private String convertTime(MusicNode item) {
        return String.format("%02d:%02d", item.getSong().getLength().toMinutesPart(), item.getSong().getLength().toSecondsPart());
    }

    public Node getComponent(MusicNode item) {
        Button playButton = new Button("Play");
        playButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> musicController.playSingleSong(item.getSong()));
        Button addToQueue = new Button("Add");
        addToQueue.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> musicController.addSongToQueue(item.getSong()));
        return new HBox(addToQueue, playButton, new Text(item.getSong().getName() + " - "), new Text(item.getSong().getArtist()), new Text(convertTime(item)));
    }
}