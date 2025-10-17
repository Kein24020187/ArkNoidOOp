package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.GameWindow;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        int w = GameManager.windowWith;
        int h = GameManager.windowHeight;
        GameWindow root = new GameWindow(w, h);
        Scene scene = new Scene(root, w, h);
        stage.setTitle("Arkanoid - JavaFX");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
