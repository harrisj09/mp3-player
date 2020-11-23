package com.github.harrisj09.mp3.client.Application.components;

import javafx.scene.control.ListCell;

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