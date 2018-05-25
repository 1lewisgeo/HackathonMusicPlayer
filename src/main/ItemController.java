package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemController extends BorderPane implements Initializable {


    @FXML
    private Label time;

    @FXML
    private Label name;

    private File song;

    public ItemController(File song) {

        this.song = song;

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(ItemController.class.getResource("SongItem.fxml"));

        loader.setRoot(this);

        loader.setController(this);

        try {

            loader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        name.setText(song.getName());

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

}
