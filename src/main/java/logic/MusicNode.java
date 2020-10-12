package logic;

import javafx.scene.Node;

import java.io.BufferedReader;

public class MusicNode {
    private BufferedReader reader;
    private final String path;
    private final String artistName;
    private final String songName;
    private final int id;

    // Should be file
    public MusicNode(String path, String artistName, String songName, int id) {
        this.path = path;
        this.artistName = artistName;
        this.songName = songName;
        this.id = id;
    }

    // Creates music component
    public Node createComponent() {
        /*
        Should look like this
                     Artist
        Play/Pause | Jake Nuffer        |       Laundry     |           Delete
         */
        return null;
    }
}
