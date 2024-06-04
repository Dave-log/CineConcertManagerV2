package org.davelogapps.cineconcertmanagerv2.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.davelogapps.cineconcertmanagerv2.data.JsonIO;
import org.davelogapps.cineconcertmanagerv2.model.Sequence;
import org.davelogapps.cineconcertmanagerv2.model.Video;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateSequenceController implements Initializable {
    @FXML
    private TextField directoryPathField;
    @FXML
    private ListView<Video> videoListView;
    @FXML
    private Button createSequenceButton;

    private List<Video> videos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        videoListView.setCellFactory(param -> new VideoCell());
    }

    @FXML
    private void browse() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());

        if (selectedDirectory != null) {
            directoryPathField.setText(selectedDirectory.getAbsolutePath());
            loadVideosFromDirectory(selectedDirectory);
        }
    }

    private void loadVideosFromDirectory(File directory) {
        File[] videoFiles = directory.listFiles(file -> file.isFile() && file.getName().endsWith(".mp4"));
        if (videoFiles != null) {
            videos = new ArrayList<>();
            for (File videoFile : videoFiles) {
                Video video = new Video();
                video.setPath(videoFile.getAbsolutePath());
                video.setDuration(0); // Durée fictive, à déterminer plus tard
                video.setMuted(true);
                video.setHasPromoImage(true);
                videos.add(video);
            }
            videoListView.getItems().setAll(videos);
        }
    }

    @FXML
    private void createSequence() {
        if (videos != null && !videos.isEmpty()) {
            Sequence sequence = new Sequence();
            sequence.setName("New Sequence");
            sequence.setVideos(videos);
            JsonIO.writeSequenceToJson(sequence, "new_sequence.json");
            System.out.println("Séquence créée et sauvegardée sous new_sequence.json");
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
