package logic;

import java.io.BufferedReader;

public class MusicNode {
    private BufferedReader reader;
    private String path;

    // Should be file
    public MusicNode(String path) {
        this.path = path;
        //reader = new BufferedReader();
    }
}
