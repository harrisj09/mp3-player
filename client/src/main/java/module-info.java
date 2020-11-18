module mp3player {
    requires javafx.controls;
    requires javafx.media;
    requires java.net.http;
    requires mp3agic;
    requires com.fasterxml.jackson.databind;
    requires static lombok;
    opens com.github.harrisj09.mp3.client;
    opens com.github.harrisj09.mp3.client.service;
}