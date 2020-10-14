package logic.linkedmusiclist;

import javafx.scene.Node;

import java.io.BufferedReader;

public class MusicNode {
    private BufferedReader reader;
    private final String path;
    private final String artistName;
    private final String songName;
    private final int id;
    private MusicNode next;

    // Should be file
    public MusicNode(String path, String artistName, String songName, int id) {
        this.path = path;
        this.artistName = artistName;
        this.songName = songName;
        this.id = id;
        next = null;
        // use constructor args
        createComponent();
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

    public boolean hasNext() {
        return next == null;
    }

    public MusicNode getNext() {
        return next;
    }

    public void setNext(MusicNode newNext) {
        next = newNext;
    }
}
