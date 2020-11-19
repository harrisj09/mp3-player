package com.github.harrisj09.mp3.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.harrisj09.mp3.client.Application.model.MusicModel;
import com.github.harrisj09.mp3.client.Application.view.MusicView;
import com.github.harrisj09.mp3.client.Application.controller.MusicController;
import com.github.harrisj09.mp3.client.service.MusicService;
import com.github.harrisj09.mp3.client.service.ServiceMusicNodeDto;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Entry point of application
 *
 * @author John Harris
 */

public class Main extends Application {

    public static void main(String[] args) {
        // this is important
        // TODO move this to musicModel
        System.out.println(new MusicService(Paths.get("")).fetchMusicList());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        MusicModel model = new MusicModel();
        MusicController controller = new MusicController(model);
        MusicView view = new MusicView(controller, primaryStage);
        Scene scene = new Scene(view.getLayout(), 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}