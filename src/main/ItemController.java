package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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
    public Button pauseButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button loop;

    private boolean loopb = false;

    public MediaPlayer mp;

    public File songFile;
    public String song;

    public String length;

    public ItemController(File songFile) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ItemController.class.getResource("SongItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.songFile = songFile;
        this.song = this.songFile.getName();

        this.mp = new MediaPlayer(new Media("file:" + this.songFile.getAbsolutePath()
                .replace(" ", "%20").replace("\\", "/")));

        nameLabel.setText(this.song);

        length = rand(0, 3) + ":" + String.valueOf(rand(0, 6)) + rand(0, 9);

        pauseButton.setOnAction(action -> {
            this.togglePlay();
        });

        loop.setOnAction(a -> loopb = !loopb);

        durationLabel.setText(length);

        deleteButton.setOnAction(action -> {
            this.mp.stop();
            App.controller.deleteItem(this);
        });

        mp.setOnEndOfMedia(() -> {
            pauseButton.setText("▶");
        });
    }

    public ItemController(String URL) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ItemController.class.getResource("SongItem.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.mp = new MediaPlayer(new Media(URL));

        this.song = this.mp.getStatus().name();

        nameLabel.setText(this.song);

        length = rand(0, 3) + ":" + String.valueOf(rand(0, 6)) + rand(0, 9);

        pauseButton.setOnAction(action -> {
            this.togglePlay();
        });

        loop.setOnAction(a -> loopb = !loopb);

        durationLabel.setText(length);

        deleteButton.setOnAction(action -> {
            this.mp.stop();
            App.controller.deleteItem(this);
        });

        mp.setOnEndOfMedia(() -> {
            pauseButton.setText("▶");
        });
    }


    public void togglePlay() {
        if (!mp.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            if (loopb) {
                this.mp.setCycleCount(999);
            }
            this.mp.play();
            pauseButton.setText("▌▌");
        } else {
            this.mp.stop();
            pauseButton.setText("▶");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    private int rand(int a, int b) {
        return ThreadLocalRandom.current().nextInt(a, b);
    }

}
