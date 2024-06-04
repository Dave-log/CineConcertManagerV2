package org.davelogapps.cineconcertmanagerv2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.davelogapps.cineconcertmanagerv2.data.JsonIO;
import org.davelogapps.cineconcertmanagerv2.model.Sequence;
import org.davelogapps.cineconcertmanagerv2.model.Video;

import java.io.IOException;
import java.util.Arrays;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setTitle("Ciné-Concert Manager 2.0");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}