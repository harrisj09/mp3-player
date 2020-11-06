package Application;

import Application.components.MusicNode;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.scene.control.ListCell;

import java.io.IOException;

class MusicCell extends ListCell<MusicNode> {

    public MusicCell() {
        super();
    }

    @Override
    protected void updateItem(MusicNode item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) { // <== test for null item and empty parameter
            try {
                setGraphic(item.getComponent());
            } catch (InvalidDataException | IOException | UnsupportedTagException e) {
                e.printStackTrace();
            }
        }
    }
}