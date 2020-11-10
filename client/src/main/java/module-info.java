module mp3player {
    requires javafx.controls;
    requires javafx.media;
    requires java.net.http;
    requires mp3agic;
    opens com.github.harrisj09.mp3.client;
}