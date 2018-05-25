package main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

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

    @FXML
    private Slider volumeSlider;

    @FXML
    private Button MasterPause;

    @FXML
    private Button URLButton;


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
    public void initialize(URL urlD, ResourceBundle resourceBundle) {

        FileButton.setOnAction(action -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Audio Clip");
            File file = fileChooser.showOpenDialog(App.window);
            if (file != null) {
                SongList.getItems().add(new ItemController(file));
            }
        });

        URLButton.setOnAction(action -> {

            Dialog<String>d= new Dialog<>();

            TextInputDialog tid = new TextInputDialog();

            tid.setContentText("inputurl");

            String url = tid.showAndWait().orElse("");

            if (url != null) {
                SongList.getItems().add(new ItemController(url));
            }
        });

        this.setOnKeyPressed(press -> {
            if (press.getCode().equals(KeyCode.SPACE)) {
                SongList.getSelectionModel().getSelectedItem().togglePlay();
            }
        });

        SongList.getSelectionModel().selectedItemProperty().addListener((obs, old, n) -> {
            if (n != null) {
                RPanelNameLabel.setText(n.song);
                if (n.songFile != null) {
                    RPanelFileLabel.setText(n.songFile.getAbsolutePath());
                }
                RPanelProgressLabel.setText(n.length);
                volumeSlider.setValue(n.mp.getVolume() * 100);
            } else {
                RPanelNameLabel.setText("");
                RPanelFileLabel.setText("");
                RPanelProgressLabel.setText("0:00");
            }
        });

        this.volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (SongList.getSelectionModel().getSelectedItem() != null) {
                    SongList.getSelectionModel().getSelectedItem().mp.setVolume(newValue.doubleValue() / 100);
                }
            }
        });

        MasterPause.setOnAction(a -> {

            SongList.getItems().forEach(item -> {
                item.mp.stop();
                item.pauseButton.setText("▶");
            });

        });
    }

    public void deleteItem(ItemController item) {
        SongList.getItems().remove(item);
    }

}
