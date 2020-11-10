import Application.model.MusicModel;
import Application.view.MusicView;
import Application.controller.MusicController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Entry point of application
 *
 * @author John Harris
 */

    /*
    Follow these

    - https://stackoverflow.com/questions/36868391/using-javafx-controller-without-fxml/36873768#36873768
    - https://stackoverflow.com/questions/64614571/javafx-application-keeps-saying-observablelist-is-empty-even-though-its-not
     */

public class Main extends Application {

    /*
    Request Songs
    - click play
    - send ID to server
    - server sends back audio
     */
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        // TODO fix this for file processing https://www.baeldung.com/java-9-http-client
        HttpRequest build = HttpRequest.newBuilder().GET().uri(new URI("http://localhost:8080/songs")).build();
        HttpResponse<String> send = HttpClient.newBuilder()
                .build()
                .send(build, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.body());
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