package Application;

import Application.components.MusicCell;
import Application.components.MusicNode;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MusicView {
    /**
     * GUI is built here
     *
     * Use the controllers model to handle data
     */
    private final MusicController musicController;
    private final Stage stage;
    private BorderPane borderPane;

    public MusicView(MusicController musicController, Stage stage) {
        this.musicController = musicController;
        this.stage = stage;
        borderPane = new BorderPane();
    }

    public Parent getLayout() throws IOException {
        borderPane.setTop(createTop());
        borderPane.setCenter(createCenter());
        borderPane.setBottom(createBottom());
        return borderPane;
    }

    public HBox createTop() {
        Button add = new Button("Add Song");
        HBox top = new HBox(add);
        EventHandler<MouseEvent> eventHandler = e -> {
            File song = new FileChooser().showOpenDialog(stage);
            try {
                musicController.addSong(song);
                // TODO call a method (not createCenter) that applies the updated listview
                borderPane.setCenter(updateCenter(song));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        };
        top.setAlignment(Pos.CENTER);
        add.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        return top;
    }

    // TODO make it so the file is created immediately (kinda like flush)
    public Node createCenter() throws IOException {
        // If file doesn't exist
        if (!musicController.getMusicModel().getMp3File().exists()) {
            musicController.getMusicModel().createFile();
            return new HBox(new Text("Music List is Empty :("));
        }
        // If file is empty
        if(musicController.isEmptyFile()) {
            return new HBox(new Text("Music List is Empty :("));
        }
        // Theres a method called setCellFactory for ListView
        ListView<MusicNode> list = new ListView<>();
        ObservableList<MusicNode> songs = musicController.grabCenterContents();
        list.setItems(songs);
        list.setCellFactory(param -> new MusicCell());
        return list;
    }

    public HBox createBottom() {
        Button previous = new Button("Previous");
        ToggleButton playStatus = new ToggleButton("Play/Skip");
        Button next = new Button("Next");
        HBox bottom = new HBox(previous, playStatus, next);
        bottom.setAlignment(Pos.CENTER);
        return bottom;
    }

    // Called after event listener is clicked
    public Node updateCenter(File file) {
        ListView<MusicNode> list = new ListView<>();
        ObservableList<MusicNode> songs = musicController.grabCenterContents();
        list.setItems(songs);
        list.setCellFactory(param -> new MusicCell());
        return list;
    }
}
