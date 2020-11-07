package Application.controller;

import Application.model.MusicModel;
import Application.components.MusicNode;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.*;

/**
 * Event listeners are handled here and this also acts as your musichandler and audioplayer
 *
 * Use this for list view
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Cell.html
 *
 * Use this for handling selected item?
 * https://docs.oracle.com/javafx/2/ui_controls/list-view.htm
 * getSelectionModel().getSelectedIndex() – Returns the index of the currently selected items in a single-selection mode
 * getSelectionModel().getSelectedItem() – Returns the currently selected item
 * getFocusModel().getFocusedIndex() – Returns the index of the currently focused item
 * getFocusModel().getFocusedItem() – Returns the currently focused item
 */


/*
TODO
    - Have it so model is just directly called in controller (dont do musicController.getModel)
    - adding songs turns the cells in hashmaps (it has to do with the updating songs method not using cells)
 */
public class MusicController {

    private final MusicModel musicModel;
    private final AudioController audioController;

    public MusicController(MusicModel musicModel) {
        this.musicModel = musicModel;
        this.audioController = new AudioController();
    }

    public ObservableList<MusicNode> grabCenterContents() {
        createSongsList();
        return musicModel.getMusicList();
    }

    public File getFile() {
        return musicModel.getMp3File();
    }

    public void createFile() throws IOException {
        musicModel.createFile();
    }

    public void createSongsList() {
        ObservableList<MusicNode> musicList = FXCollections.observableArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(musicModel.getMp3File()));
            String filePath;
            while((filePath = br.readLine()) != null) {
                // TODO Check if the path actually exists
                File temp = new File(filePath);
                if (temp.exists()) {
                    MusicNode node = new MusicNode(new File(filePath));
                    musicList.add(node);
                    EventHandler<MouseEvent> addEvent = e -> {
                        audioController.playSong(node);
                    };
                    node.getButton().addEventFilter(MouseEvent.MOUSE_CLICKED, addEvent);
                }
                else {
                    // delete the line from the file
                    System.out.println("doesnt exist");
                }
            }
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }
        musicModel.setMusicList(musicList);
    }

    // this calls the Model to update the observable list
    public void addSong(File file) throws IOException {
        if(!isPlayableSong(file)) {
            JOptionPane.showMessageDialog(new JFrame(), "Incorrect file type");
        } else {
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
        if (alreadyAdded(file.getAbsolutePath())) {
            JOptionPane.showMessageDialog(new JFrame(), "Song already in library");
        }
        else {
            if (isEmptyFile()) {
                bufferedWriter.write(file.getAbsolutePath());
            } else {
                bufferedWriter.write("\n" + file.getAbsolutePath());
            }
            bufferedWriter.flush();
            bufferedWriter.close();
            createSongsList();
        }
    }

    private boolean alreadyAdded(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(musicModel.getMp3File()));
        String filePath;
        while((filePath = br.readLine()) != null) {
            if(file.equals(filePath)) {
                return true;
            }
        }
        return false;
    }

    // Create a new list, call setMusicList in Model
    public boolean isEmptyFile() {
        return musicModel.getMp3File().length() == 0;
    }

    protected void getSong() {

    }
}