package main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.scene.control.Label;

import javax.xml.soap.Text;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends BorderPane implements Initializable {

    @FXML
    private ListView<ItemController> SongList;

    @FXML
    private Button FileButton;

    @FXML
    private Label RPanelNameLabel;

    @FXML
    private Label RPanelFileLabel;

    @FXML
    private TextField RPanelEventField;

    @FXML
    private Button RPanelAddButton;

    @FXML
    private ListView<?> RPanelListView;

    @FXML
    private ProgressBar RPanelProgressBar;

    @FXML
    private Label RPanelProgressLabel;

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

        FileButton.setOnAction(action -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Audio Clip");
            File file = fileChooser.showOpenDialog(App.window);
            if (file != null) {
                SongList.getItems().add(new ItemController(file));
            }
        });

        this.setOnKeyPressed(press -> {

            if (press.equals(KeyCode.SPACE)) {



            }

        });

        SongList.getSelectionModel().selectedItemProperty().addListener((obs, old, n) -> {



        });

//        SongList.getItems().add(new ItemController("music.mp3"));
    }

    public void deleteItem(ItemController item) {
        SongList.getItems().remove(item);
    }

}
