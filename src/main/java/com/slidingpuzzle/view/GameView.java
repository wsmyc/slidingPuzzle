package com.slidingpuzzle.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Main view class for the game screen.
 */
public class GameView extends BorderPane {
    
    private PuzzleView puzzleView;
    private ScoreView scoreView;
    
    private Label timerLabel;
    private ComboBox<String> configComboBox;
    private Button rearrangeButton;
    private Button shuffleButton;
    
    /**
     * Constructor initializes the game view components.
     */
    public GameView() {
        // Set padding and styling
        setPadding(new Insets(20));
        getStyleClass().add("game-container");
        
        // Create the puzzle view
        puzzleView = new PuzzleView();
        
        // Create the score view
        scoreView = new ScoreView();
        
        // Create timer and controls
        createControls();
        
        // Set up layout
        setCenter(puzzleView);
        setTop(createHeaderArea());
        setBottom(createControlArea());
    }
    
    /**
     * Create header area with score information.
     * @return VBox containing the header components
     */
    private VBox createHeaderArea() {
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(0, 0, 20, 0));
        
        Label titleLabel = new Label("Sliding Puzzle Game");
        titleLabel.getStyleClass().add("game-title");
        
        header.getChildren().addAll(titleLabel, scoreView);
        
        return header;
    }
    
    /**
     * Create control area with buttons and configuration options.
     * @return HBox containing the control components
     */
    private VBox createControlArea() {
        VBox controlArea = new VBox(15);
        controlArea.setAlignment(Pos.CENTER);
        controlArea.setPadding(new Insets(20, 0, 0, 0));
        
        HBox timerBox = new HBox(10);
        timerBox.setAlignment(Pos.CENTER);
        Label timerTitleLabel = new Label("Time:");
        timerTitleLabel.getStyleClass().add("timer-title");
        timerBox.getChildren().addAll(timerTitleLabel, timerLabel);
        
        HBox configBox = new HBox(10);
        configBox.setAlignment(Pos.CENTER);
        Label configLabel = new Label("Puzzle Configuration:");
        configBox.getChildren().addAll(configLabel, configComboBox);
        
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(rearrangeButton, shuffleButton);
        
        controlArea.getChildren().addAll(timerBox, configBox, buttonBox);
        
        return controlArea;
    }
    
    /**
     * Create the control components.
     */
    private void createControls() {
        // Timer label
        timerLabel = new Label("00:00");
        timerLabel.getStyleClass().add("timer-label");
        
        // Configuration combo box
        configComboBox = new ComboBox<>();
        configComboBox.setPrefWidth(200);
        
        // Buttons
        rearrangeButton = new Button("Rearrange");
        rearrangeButton.getStyleClass().add("game-button");
        
        shuffleButton = new Button("Shuffle");
        shuffleButton.getStyleClass().add("game-button");
    }
    
    // Getters for components
    
    public PuzzleView getPuzzleView() {
        return puzzleView;
    }
    
    public ScoreView getScoreView() {
        return scoreView;
    }
    
    public Label getTimerLabel() {
        return timerLabel;
    }
    
    public ComboBox<String> getConfigComboBox() {
        return configComboBox;
    }
    
    public Button getRearrangeButton() {
        return rearrangeButton;
    }
    
    public Button getShuffleButton() {
        return shuffleButton;
    }
}