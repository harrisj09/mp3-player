package com.github.harrisj09.mp3.client;

import com.github.harrisj09.mp3.client.application.model.MusicModel;
import com.github.harrisj09.mp3.client.application.view.MusicView;
import com.github.harrisj09.mp3.client.application.controller.MusicController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point of application
 *
 * @author John Harris
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MusicModel model = new MusicModel();
        MusicController controller = new MusicController(model);
        MusicView view = new MusicView(controller, primaryStage);
        Scene scene = new Scene(view.getLayout(), 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}