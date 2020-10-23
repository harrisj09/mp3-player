package logic;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import gui.components.MusicNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class MusicHandler {

    /**
     * Grabs folder and looks for text file containing paths to music
     *
     * Grabs the LinkedMusicList
     */
    private File mp3File = new File("mp3list.txt");

    public ObservableList<MusicNode> getMusicList() throws IOException, InvalidDataException, UnsupportedTagException {
        if (!mp3File.exists()) {
            mp3File.createNewFile();
            return null;
        }
        if(mp3File.length() == 0) {
            return null;
        }
        return generateSongsList(mp3File);
    }

    public ObservableList<MusicNode> generateSongsList(File mp3) throws IOException, InvalidDataException, UnsupportedTagException {
        ObservableList<MusicNode> items = FXCollections.observableArrayList();
        FileReader fr = new FileReader(mp3File);
        // go through file and create list
/*        try {
            Scanner s = new Scanner(mp3);
            while() {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        return items;
    }

    // https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
    public void updateSongsList(File file) throws IOException {
        FileWriter writer = new FileWriter(mp3File);
        // write to txt with new file path
        // getMusicList is invoked
        // make sure you flush
        System.out.println("Added" + file.getAbsolutePath());
        writer.write(file.getName());
        writer.flush();
        writer.close();
    }

    // recursive
    public void removeSong(String path) {
    }
}