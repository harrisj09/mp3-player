package Application;

import Application.gui.components.MusicNode;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
                // so something like borderPane.setCenter(method());
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
        if (!musicController.getMusicModel().getMp3File().exists()) {
            musicController.getMusicModel().createFile();
            System.out.println("File doesnt exist");
            return new HBox(new Text("Music List is Empty :("));
        }
        if(musicController.isEmptyFile()) {
            System.out.println("File is empty");
            return new HBox(new Text("Music List is Empty :("));
        }
        // Theres a method called setCellFactory for ListView
        ListView<MusicNode> list = new ListView<>();
        ObservableList<MusicNode> songs = musicController.grabCenterContents();
        list.setItems(songs);
        return list;
    }

    public HBox createBottom() {
        return null;
    }
}
