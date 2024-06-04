package org.davelogapps.cineconcertmanagerv2.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.davelogapps.cineconcertmanagerv2.data.JsonIO;
import org.davelogapps.cineconcertmanagerv2.model.Sequence;
import org.davelogapps.cineconcertmanagerv2.model.Video;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EditSequenceController implements Initializable {
    @FXML
    private ListView<Sequence> sequenceListView;
    @FXML
    private ListView<Video> videoListView;
    @FXML
    private Button openSequenceButton;
    @FXML
    private Button saveSequenceButton;

    private Sequence currentSequence;
    private String currentSequencePath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sequenceListView.setCellFactory(param -> new SequenceCell());
        videoListView.setCellFactory(param -> new VideoCell());
    }

    @FXML
    private void openSequence() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            currentSequencePath = selectedFile.getAbsolutePath();
            currentSequence = JsonIO.readSequenceFromJson(selectedFile.getAbsolutePath());
            if (currentSequence != null) {
                videoListView.getItems().setAll(currentSequence.getVideos());
            }
        }
    }

    @FXML
    private void saveSequence() {
        if (currentSequence != null && currentSequencePath != null) {
            JsonIO.writeSequenceToJson(currentSequence, currentSequencePath);
            System.out.println("Séquence éditée et sauvegardée sous " + currentSequencePath);
        }
    }

    private static class SequenceCell extends ListCell<Sequence> {
        private final HBox content;
        private final Label sequenceLabel;

        public SequenceCell() {
            super();
            sequenceLabel = new Label();
            content = new HBox(10, sequenceLabel);
        }

        @Override
        protected void updateItem(Sequence sequence, boolean empty) {
            super.updateItem(sequence, empty);
            if (empty || sequence == null) {
                setGraphic(null);
            } else {
                sequenceLabel.setText(sequence.getName());
                setGraphic(content);
            }
        }
    }

    private static class VideoCell extends ListCell<Video> {
        private final HBox content;
        private final CheckBox muteCheckBox;
        private final CheckBox placeholderCheckBox;
        private final Label videoLabel;

        public VideoCell() {
            super();
            videoLabel = new Label();
            muteCheckBox = new CheckBox("Mute");
            placeholderCheckBox = new CheckBox("Placeholder");

            muteCheckBox.setOnAction(event -> {
                if (getItem() != null) {
                    getItem().setMuted(muteCheckBox.isSelected());
                }
            });

            placeholderCheckBox.setOnAction(event -> {
                if (getItem() != null) {
                    getItem().setHasPromoImage(placeholderCheckBox.isSelected());
                }
            });

            content = new HBox(10, videoLabel, muteCheckBox, placeholderCheckBox);
        }

        @Override
        protected void updateItem(Video video, boolean empty) {
            super.updateItem(video, empty);
            if (empty || video == null) {
                setGraphic(null);
            } else {
                videoLabel.setText(new File(video.getPath()).getName());
                muteCheckBox.setSelected(video.isMuted());
                placeholderCheckBox.setSelected(video.isHasPromoImage());
                setGraphic(content);
            }
        }
    }
}
