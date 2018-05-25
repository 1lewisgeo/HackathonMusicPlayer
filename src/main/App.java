package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

    public static Stage window;

    public static Scene scene;

    public static MainController controller;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) throws Exception {

        App.window = stage;

        App.controller = new MainController();

        App.scene = new Scene(App.controller);

        App.window.show();

    }
}
