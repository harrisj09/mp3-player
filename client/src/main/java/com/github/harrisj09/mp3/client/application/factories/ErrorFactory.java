package com.github.harrisj09.mp3.client.application.factories;

import javafx.scene.control.Alert;

public class ErrorFactory {
    public void createAlert(Alert alert, String title, String headerText, String contentText) {
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
