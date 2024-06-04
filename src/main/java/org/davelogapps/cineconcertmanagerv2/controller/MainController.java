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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("createSequence.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = new Stage();
            stage.setTitle("Créer une nouvelle séquence");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editSequence() {
        System.out.println("Éditer une séquence");
    }

    @FXML
    private void launchSequence() {
        System.out.println("Lancer une séquence");
    }
}