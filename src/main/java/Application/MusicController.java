package Application;

import Application.gui.components.MusicNode;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.control.ListView;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * Event listeners are handled here and this also acts as your musichandler and audioplayer
 *
 * Use this for list view
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Cell.html
 */

public class MusicController {

    private final MusicModel musicModel;
    // TODO Move this to model

    public MusicController(MusicModel musicModel) {
        this.musicModel = musicModel;
    }

    public MusicModel getMusicModel() {
        return musicModel;
    }

    public ObservableList<MusicNode> grabCenterContents() {
        createSongsList();
        return musicModel.getMusicList();
    }

    // this calls the Model to update the observable list
    protected void addSong(File file) throws IOException {
        if(!isPlayableSong(file)) {
            JOptionPane.showMessageDialog(new JFrame(), "Incorrect file type");
        } else {
            System.out.println("Correct file type");
            updateSongsFile(file);
        }
    }

    private boolean isPlayableSong(File file) {
        return fileType(file).equals(".mp3");
    }

    private String fileType(File file) {
        return file.getName().substring(file.getName().lastIndexOf("."));
    }

    // this creates a new observable list, goes through the file and generates a new list of music nodes
    // calls setMusicList (name will be changed hopefully)
    public void updateSongsFile(File file) throws IOException {
        // Add the file path to the text file
        FileWriter writer = new FileWriter(musicModel.getMp3File(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        if (isEmptyFile()) {
            bufferedWriter.write(file.getAbsolutePath());
        } else {
            bufferedWriter.write("\n" + file.getAbsolutePath());
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        createSongsList();
    }

    // Create a new list, call setMusicList in Model
    public boolean isEmptyFile() {
        return musicModel.getMp3File().length() == 0;
    }

    public void createSongsList() {
        ObservableList<MusicNode> musicList = FXCollections.observableArrayList();
        try {
            Scanner reader = new Scanner(musicModel.getMp3File());
            BufferedReader br = new BufferedReader(new FileReader(musicModel.getMp3File()));
            String filePath;
            while((filePath = br.readLine()) != null) {
                // TODO Fix this so it actually take in a node
                musicList.add(new MusicNode(new File(filePath)));
            }
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }
        musicModel.setMusicList(musicList);
    }
}