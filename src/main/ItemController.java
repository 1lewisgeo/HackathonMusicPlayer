package main;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemController extends BorderPane implements Initializable {


    public ItemController() {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(ItemController.class.getResource("SongItem.fxml"));

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

    }

}
