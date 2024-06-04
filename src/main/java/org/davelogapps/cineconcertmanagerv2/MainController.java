package org.davelogapps.cineconcertmanagerv2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private void createNewSequence() {
        System.out.println("Créer une nouvelle séquence");
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