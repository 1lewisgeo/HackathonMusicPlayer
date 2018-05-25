package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class ItemController extends BorderPane implements Initializable {


    @FXML
    private Label durationLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button pauseButton;

    @FXML
    private Button deleteButton;

    public File songFile;
    public String song;
    private AudioClip songClip;

    public String length;

    public ItemController(File songFile) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ItemController.class.getResource("SongItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        length = rand(2, 5) + ":" + String.valueOf(rand(0, 9)) + rand(0, 9);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.songFile = songFile;
        this.song = this.songFile.getName();

        this.songClip = new AudioClip("file:" + this.songFile.getAbsolutePath()
                .replace(" ", "%20").replace("\\", "/"));

        nameLabel.setText(this.song);

        pauseButton.setOnAction(action -> {
            this.togglePlay();
        });

        durationLabel.setText(length);

        deleteButton.setOnAction(action -> {
            this.songClip.stop();
            App.controller.deleteItem(this);
        });
    }

    public void togglePlay() {
        if (!this.songClip.isPlaying()) {
            this.songClip.play();
            pauseButton.setText("▌▌");
        } else {
            this.songClip.stop();
            pauseButton.setText("▶");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    private int rand(int a, int b) {
        return ThreadLocalRandom.current().nextInt(a, b);
    }

}
