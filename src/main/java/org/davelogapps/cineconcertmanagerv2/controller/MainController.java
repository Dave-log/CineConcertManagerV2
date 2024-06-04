package org.davelogapps.cineconcertmanagerv2.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.davelogapps.cineconcertmanagerv2.Application;

import java.io.IOException;

public class MainController {
    @FXML
    private void createNewSequence() {
        loadScene("createSequence.fxml", "Créer une séquence");
    }

    @FXML
    private void editSequence() {
        loadScene("editSequence.fxml", "Éditer une séquence");
    }

    @FXML
    private void launchSequence() {
        System.out.println("Lancer une séquence");
    }

    private void loadScene(String fxmlFile, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}