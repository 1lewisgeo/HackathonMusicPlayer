package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemController extends BorderPane implements Initializable {


    @FXML
    private Label durationLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button pauseButton;

    @FXML
    private Button deleteButton;

    private String song;
    private AudioClip songClip;

    public ItemController(String song) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ItemController.class.getResource("SongItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.song = song;
        final URL resource = getClass().getResource(this.song);
        this.songClip = new AudioClip(resource.toExternalForm());

        nameLabel.setText(this.song);

        pauseButton.setOnAction(action -> {
            if (!this.songClip.isPlaying()) {
                this.songClip.play();
            } else {
                this.songClip.stop();
            }
        });

        deleteButton.setOnAction(action -> {
            this.songClip.stop();
            App.controller.deleteItem(this);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

}
