package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends BorderPane implements Initializable {

    @FXML
    private ListView<ItemController> SongList;

    @FXML
    private Button FileButton;

    public MainController() {

        FXMLLoader loader = new FXMLLoader();

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

        SongList.getItems().add(new ItemController("music.mp3"));
    }

    public void deleteItem(ItemController item) {
        SongList.getItems().remove(item);
    }

}
