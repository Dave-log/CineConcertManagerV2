package org.davelogapps.cineconcertmanagerv2.controller;

import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.davelogapps.cineconcertmanagerv2.data.JsonIO;
import org.davelogapps.cineconcertmanagerv2.model.Sequence;
import org.davelogapps.cineconcertmanagerv2.model.Video;
import java.io.File;
import java.util.List;

public class PlaySequenceController {
    @FXML
    private MediaView mediaView;

    private MediaPlayer mediaPlayer;
    private List<Video> videos;
    private int currentVideoIndex;

    public void loadSequence(Sequence sequence) {
        if (sequence != null) {
            videos = sequence.getVideos();
            currentVideoIndex = 0;
            playVideo(videos.get(currentVideoIndex).getPath());
        }
    }

    public void loadSequenceFromFile(File file) {
        Sequence sequence = JsonIO.readSequenceFromJson(file.getAbsolutePath());
        loadSequence(sequence);
    }

    private void playVideo(String videoPath) {
        Media media = new Media(new File(videoPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnEndOfMedia(() -> {
            currentVideoIndex++;
            if (currentVideoIndex < videos.size()) {
                playVideo(videos.get(currentVideoIndex).getPath()); // Jouer la vidéo suivante
            } else {
                mediaPlayer.dispose(); // Libérer les ressources lorsque toutes les vidéos ont été lues
            }
        });

        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }

    private void playNextVideo() {
        currentVideoIndex++;
        playVideo(videos.get(currentVideoIndex).getPath());
    }

    @FXML
    private void play() {
        mediaPlayer.play();
    }

    @FXML
    private void pause() {
        mediaPlayer.pause();
    }

    @FXML
    private void stop() {
        mediaPlayer.stop();
    }
}
