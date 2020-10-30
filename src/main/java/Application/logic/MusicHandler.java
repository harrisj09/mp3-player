package Application.logic;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import Application.gui.components.MusicNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.Scanner;

/**
 * Handles List of MusicNode's
 * by creating and updating the list
 * from the text file
 *
 * @author John Harris
 */

public class MusicHandler {

    private File mp3File = new File("mp3list.txt");

    public ObservableList<MusicNode> getMusicList() throws IOException, InvalidDataException, UnsupportedTagException {
        if (!mp3File.exists()) {
            System.out.println("Music List doesnt exist");
            mp3File.createNewFile();
            return null;
        }
        if(isEmptyFile()) {
            System.out.println("Music List is empty");
            return null;
        }
        System.out.println("Music List is not empty");
        return generateSongsList(mp3File);
    }

    private boolean isEmptyFile() {
        return mp3File.length() == 0;
    }

    public ObservableList<MusicNode> generateSongsList(File mp3) throws IOException, InvalidDataException, UnsupportedTagException {
        ObservableList<MusicNode> items = FXCollections.observableArrayList();
        try {
            Scanner reader = new Scanner(mp3File);
            BufferedReader br = new BufferedReader(new FileReader(mp3File));
            String filePath;
            while((filePath = br.readLine()) != null) {
                // TODO Fix this so it actually take in a node
                items.add(new MusicNode(new File(filePath)));
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return items;
    }

    // https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
    public void updateSongsList(File file) throws IOException {
        FileWriter writer = new FileWriter(mp3File, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        if (isEmptyFile()) {
            bufferedWriter.write(file.getAbsolutePath());
        } else {
            bufferedWriter.write("\n" + file.getAbsolutePath());
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}