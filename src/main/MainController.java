package main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class MainController extends BorderPane implements Initializable {

    @FXML
    private ListView<ItemController> SongList;

    @FXML
    private Button FileButton;

    @FXML
    private ScrollPane Scroller;

    public MainController() {

        FXMLLoader loader = new FXMLLoader();

        System.out.println(MainController.class.getResource("MainWindow.fxml"));

        loader.setLocation(MainController.class.getResource("MainWindow.fxml"));

        loader.setRoot(this);

        loader.setController(this);

        try {

            loader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SongList.minWidthProperty().bind(Scroller.widthProperty());

        SongList.maxWidthProperty().bind(Scroller.widthProperty());

        SongList.getItems().add(new ItemController(new File("music.mp3")));

    }

}
