package logic;

import javafx.scene.Node;
import java.io.File;

public class MusicNode {
    private File file;

    // Should be file
    public MusicNode(File file) {
        this.file = file;
        createComponent();
    }

    public Node createComponent() {
        return null;
    }

    public Node getComponent() {
        createComponent();
        return null;
    }
}
