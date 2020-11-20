package com.github.harrisj09.mp3.client.Application.components;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class MusicCell extends ListCell<MusicNode> {

    /*
    TODO
        Store the cellfactory in this class

        credit for the cell factory
        https://docs.oracle.com/javafx/2/ui_controls/list-view.htm
     */

    public MusicCell() {
        super();
    }

    @Override
    protected void updateItem(MusicNode item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            setGraphic(item.getComponent());
        }
    }
}