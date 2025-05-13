package com.slidingpuzzle.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.slidingpuzzle.model.Puzzle;
import com.slidingpuzzle.model.PuzzleConfiguration;
import com.slidingpuzzle.model.Score;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Controller class for the main game view.
 */
public class GameController implements Initializable {
    
    @FXML private GridPane puzzleGrid;
    @FXML private Label movesLabel;
    @FXML private Label scoreLabel;
    @FXML private Label bestScoreLabel;
    @FXML private Label timerLabel;
    @FXML private Button rearrangeButton;
    @FXML private Button shuffleButton;
    @FXML private ComboBox<String> configComboBox;
    
    // Models
    private Puzzle puzzle;
    private PuzzleConfiguration puzzleConfig;
    private Score scoreManager;
    
    // Game state properties
    private IntegerProperty moves = new SimpleIntegerProperty(0);
    private IntegerProperty score = new SimpleIntegerProperty(0);
    private IntegerProperty bestScore = new SimpleIntegerProperty(0);
    private StringProperty timeElapsed = new SimpleStringProperty("00:00");
    
    // Timer for tracking game time
    private Timer gameTimer;
    private int seconds = 0;
    
    // Selected configuration
    private String currentConfig;
    
    // Animation for tile movement
    private Timeline animation;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize models
        puzzleConfig = new PuzzleConfiguration();
        scoreManager = new Score();
        
        // Set up default puzzle
        currentConfig = puzzleConfig.getDefaultConfiguration();
        puzzle = new Puzzle(currentConfig);
        
        // Set up UI bindings
        movesLabel.textProperty().bind(moves.asString());
        scoreLabel.textProperty().bind(score.asString());
        bestScoreLabel.textProperty().bind(bestScore.asString());
        timerLabel.textProperty().bind(timeElapsed);
        
        // Set up puzzle configurations in combo box
        setupConfigComboBox();
        
        // Create puzzle tiles
        createPuzzleTiles();
        
        // Start the game timer
        startTimer();
        
        // Set up animation timeline
        animation = new Timeline();
        animation.setCycleCount(1);
    }
    
    /**
     * Set up the puzzle configuration combo box.
     */
    private void setupConfigComboBox() {
        // Add all available configurations
        for (int i = 0; i < puzzleConfig.getConfigurationCount(); i++) {
            String config = puzzleConfig.getConfiguration(i);
            configComboBox.getItems().add("Config " + (i + 1) + ": " + config);
        }
        
        // Select default configuration
        configComboBox.getSelectionModel().select(2); // "204153876"
        
        // Add listener for selection changes
        configComboBox.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                changeConfiguration(newVal.intValue());
            }
        });
    }
    
    /**
     * Create the puzzle tiles in the grid.
     */
    private void createPuzzleTiles() {
        puzzleGrid.getChildren().clear();
        
        int size = Puzzle.getSize();
        int[][] board = puzzle.getBoard();
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final int row = i;
                final int col = j;
                
                // Create tile
                Pane tile = createTile(board[i][j]);
                
                // Add click handler
                tile.setOnMouseClicked(e -> handleTileClick(row, col));
                
                // Add to grid
                puzzleGrid.add(tile, j, i);
            }
        }
        
        // Update properties
        moves.set(puzzle.getMoveCount());
        score.set(puzzle.getScore());
        bestScore.set(scoreManager.getHighScore(currentConfig));
    }
    
    /**
     * Create a single puzzle tile.
     * @param value Tile value (0 for empty space)
     * @return Pane representing the tile
     */
    private Pane createTile(int value) {
        Pane tile = new Pane();
        tile.setPrefSize(100, 100);
        
        if (value > 0) {
            tile.getStyleClass().add("tile");
            
            Label label = new Label(String.valueOf(value));
            label.getStyleClass().add("tile-text");
            
            tile.getChildren().add(label);
            label.layoutXProperty().bind(tile.widthProperty().subtract(label.widthProperty()).divide(2));
            label.layoutYProperty().bind(tile.heightProperty().subtract(label.heightProperty()).divide(2));
        } else {
            tile.getStyleClass().add("empty-tile");
        }
        
        return tile;
    }
    
    /**
     * Handle a tile click event.
     * @param row Row of the clicked tile
     * @param col Column of the clicked tile
     */
    private void handleTileClick(int row, int col) {
        // Attempt to move the tile
        boolean moved = puzzle.moveTile(row, col);
        
        if (moved) {
            // Update UI
            updateUI();
            
            // Check if puzzle is solved
            if (puzzle.isSolved()) {
                handlePuzzleSolved();
            }
        }
    }
    
    /**
     * Update the UI after a move.
     */
    private void updateUI() {
        // Refresh puzzle grid
        createPuzzleTiles();
        
        // Update game state properties
        moves.set(puzzle.getMoveCount());
        score.set(puzzle.getScore());
        
        // Update best score if necessary
        if (puzzle.getScore() > scoreManager.getHighScore(currentConfig)) {
            scoreManager.updateHighScore(currentConfig, puzzle.getScore());
            bestScore.set(puzzle.getScore());
        }
    }
    
    /**
     * Handle puzzle solved event.
     */
    private void handlePuzzleSolved() {
        // Stop the timer
        stopTimer();
        
        // Show winning message
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText("Puzzle Solved!");
        alert.setContentText("You solved the puzzle in " + puzzle.getMoveCount() + " moves with a score of " + 
                            puzzle.getScore() + " and time of " + timeElapsed.get() + "!");
        alert.showAndWait();
    }
    
    /**
     * Handle rearrange button click.
     */
    @FXML
    private void handleRearrangeClick() {
        stopTimer();
        puzzle.reset(currentConfig);
        seconds = 0;
        updateUI();
        startTimer();
    }
    
    /**
     * Handle shuffle button click.
     */
    @FXML
    private void handleShuffleClick() {
        stopTimer();
        puzzle.shuffle(50); // Shuffle with 50 random moves
        seconds = 0;
        updateUI();
        startTimer();
    }
    
    /**
     * Change the puzzle configuration.
     * @param index Index of the selected configuration
     */
    private void changeConfiguration(int index) {
        stopTimer();
        currentConfig = puzzleConfig.getConfiguration(index);
        puzzle = new Puzzle(currentConfig);
        seconds = 0;
        bestScore.set(scoreManager.getHighScore(currentConfig));
        updateUI();
        startTimer();
    }
    
    /**
     * Start the game timer.
     */
    private void startTimer() {
        if (gameTimer != null) {
            gameTimer.cancel();
        }
        
        gameTimer = new Timer(true);
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seconds++;
                
                int minutes = seconds / 60;
                int secs = seconds % 60;
                
                Platform.runLater(() -> {
                    timeElapsed.set(String.format("%02d:%02d", minutes, secs));
                });
            }
        }, 1000, 1000);
    }
    
    /**
     * Stop the game timer.
     */
    private void stopTimer() {
        if (gameTimer != null) {
            gameTimer.cancel();
            gameTimer = null;
        }
    }
}