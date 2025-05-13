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
    public void start(Stage primaryStage) throws Exception {
        // Load the main game view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
        Parent root = loader.load();
        
        // Set up the scene
        Scene scene = new Scene(root, 600, 700);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        
        // Configure and show the stage
        primaryStage.setTitle("Sliding Puzzle Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Main method to launch the application.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}