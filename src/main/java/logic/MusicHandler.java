package logic;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import gui.components.MusicNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
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
    private AudioPlayer audioPlayer;

    public MusicHandler() {
        audioPlayer = new AudioPlayer(this);
    }

    public ObservableList<MusicNode> getMusicList() throws IOException, InvalidDataException, UnsupportedTagException {
        System.out.println("At getMusicList");
        if (!mp3File.exists()) {
            System.out.println("Doesnt exist");
            mp3File.createNewFile();
            return null;
        }
        if(isEmptyFile()) {
            System.out.println("Its empty");
            return null;
        }
        System.out.println("Not empty");
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
            String st;
            while((st = br.readLine()) != null) {
                System.out.println(st);
                items.add(new MusicNode(new File(st)));
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
        System.out.println("Added" + file.getAbsolutePath());
        if (isEmptyFile()) {
            bufferedWriter.write(file.getAbsolutePath());
        } else {
            bufferedWriter.write("\n" + file.getAbsolutePath());
        }
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}