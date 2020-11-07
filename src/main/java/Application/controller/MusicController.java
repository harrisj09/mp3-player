package Application.controller;

import Application.model.MusicModel;
import Application.components.MusicNode;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

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

public class MusicController {

    private final MusicModel musicModel;
    private final AudioController audioController;
    private ObservableList<MusicNode> musicList;
    private Button playStatus;
    private String playStatusToggleText = "Play";

    public MusicController(MusicModel musicModel) {
        this.musicModel = musicModel;
        this.audioController = new AudioController();
    }

    public ObservableList<MusicNode> grabCenterContents() {
        createSongsList();
        return musicModel.getMusicList();
    }

    public HBox grabBottomContents() {
        HBox bottom = new HBox(previousButton(), playStatusToggle(), skipButton());
        return bottom;
    }

    private Button previousButton() {
        Button previous = new Button("Previous");
        EventHandler<MouseEvent> previousEvent = e -> {
            if(canGoBack()) {
                int nodeId = audioController.getCurrentlyPlaying().getId();
                audioController.playSong(musicList.get(nodeId - 1));
            }
        };
        previous.addEventFilter(MouseEvent.MOUSE_CLICKED, previousEvent);
        return previous;
    }

    private Button playStatusToggle() {
        playStatus = new Button(playStatusToggleText);
        EventHandler<MouseEvent> toggleStatus = e -> {
            if (audioController.getCurrentlyPlaying() != null) {
                if (playStatusToggleText.equals("Play")) {
                    playStatusToggleText = "Pause";
                    audioController.resumeSong();
                }
                else {
                    playStatusToggleText = "Play";
                    audioController.pauseSong();
                }
                changeToggleText(playStatusToggleText);
            }
        };
        playStatus.addEventFilter(MouseEvent.MOUSE_CLICKED, toggleStatus);
        return playStatus;
    }

    private void changeToggleText(String text) {
        playStatus.setText(text);
    }

    private Button skipButton() {
        Button skip = new Button("Skip");
        EventHandler<MouseEvent> skipEvent = e -> {
            if(canSkip()) {
                int nodeId = audioController.getCurrentlyPlaying().getId();
                audioController.playSong(musicList.get(nodeId + 1));
            }
        };
        skip.addEventFilter(MouseEvent.MOUSE_CLICKED, skipEvent);
        return skip;
    }

    private boolean canGoBack() {
        return audioController.getCurrentlyPlaying() != null && audioController.getCurrentlyPlaying().getId() > 0;
    }

    private boolean canSkip() {
        return audioController.getCurrentlyPlaying() != null && audioController.getCurrentlyPlaying().getId() < musicList.size() - 1;
    }

    public File getFile() {
        return musicModel.getMp3File();
    }

    public void createFile() throws IOException {
        musicModel.createFile();
    }

    public void createSongsList() {
        musicList = FXCollections.observableArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(musicModel.getMp3File()));
            String filePath;
            int counter = 0;
            while((filePath = br.readLine()) != null) {
                File temp = new File(filePath);
                if (temp.exists()) {
                    MusicNode node = new MusicNode(new File(filePath), counter);
                    counter++;
                    musicList.add(node);
                    EventHandler<MouseEvent> playEvent = e -> {
                        System.out.println(node.getId());
                        changeToggleText("Pause");
                        audioController.playSong(node);
                    };
                    node.getButton().addEventFilter(MouseEvent.MOUSE_CLICKED, playEvent);
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
}