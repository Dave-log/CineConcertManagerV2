package org.davelogapps.cineconcertmanagerv2.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.davelogapps.cineconcertmanagerv2.Application;
import org.davelogapps.cineconcertmanagerv2.data.JsonIO;
import org.davelogapps.cineconcertmanagerv2.model.Sequence;

import java.io.File;
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
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            loadPlaySequenceScene(selectedFile);
        }
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

    private void loadPlaySequenceScene(File file) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("playSequence.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            PlaySequenceController controller = fxmlLoader.getController();
            controller.loadSequenceFromFile(file);

            Stage stage = new Stage();
            stage.setTitle("Lancer une séquence");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}