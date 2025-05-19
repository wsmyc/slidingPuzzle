package com.slidingpuzzle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for the Sliding Puzzle game.
 */
public class Main extends Application {

 @Override
public void start(Stage stage) throws Exception {
    // Charge game.fxml dans com/slidingpuzzle
    FXMLLoader loader = new FXMLLoader(
        Main.class.getResource("/com/slidingpuzzle/game.fxml")
    );
    Scene scene = new Scene(loader.load());
    // Charge le CSS si tu en as besoin
    scene.getStylesheets().add(
        Main.class.getResource("/com/slidingpuzzle/game.css").toExternalForm()
    );
    stage.setScene(scene);
    stage.setTitle("Sliding Puzzle");
    stage.show();
}

    /**
     * Main method to launch the application.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}