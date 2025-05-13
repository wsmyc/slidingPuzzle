package com.slidingpuzzle.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * View class for displaying score information.
 */
public class ScoreView extends HBox {
    
    private Label movesValueLabel;
    private Label scoreValueLabel;
    private Label bestScoreValueLabel;
    
    /**
     * Constructor initializes the score view.
     */
    public ScoreView() {
        // Configure HBox
        setSpacing(50);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);
        getStyleClass().add("score-panel");
        
        // Create score components
        createMoveCounter();
        createScoreDisplay();
        createBestScoreDisplay();
    }
    
    /**
     * Create the move counter component.
     */
    private void createMoveCounter() {
        GridPane movePane = new GridPane();
        movePane.setHgap(5);
        movePane.setVgap(5);
        movePane.setAlignment(Pos.CENTER);
        
        Label movesLabel = new Label("Moves:");
        movesLabel.getStyleClass().add("score-label");
        
        movesValueLabel = new Label("0");
        movesValueLabel.getStyleClass().add("score-value");
        
        movePane.add(movesLabel, 0, 0);
        movePane.add(movesValueLabel, 0, 1);
        
        getChildren().add(movePane);
    }
    
    /**
     * Create the current score display component.
     */
    private void createScoreDisplay() {
        GridPane scorePane = new GridPane();
        scorePane.setHgap(5);
        scorePane.setVgap(5);
        scorePane.setAlignment(Pos.CENTER);
        
        Label scoreLabel = new Label("Score:");
        scoreLabel.getStyleClass().add("score-label");
        
        scoreValueLabel = new Label("0");
        scoreValueLabel.getStyleClass().add("score-value");
        
        scorePane.add(scoreLabel, 0, 0);
        scorePane.add(scoreValueLabel, 0, 1);
        
        getChildren().add(scorePane);
    }
    
    /**
     * Create the best score display component.
     */
    private void createBestScoreDisplay() {
        GridPane bestScorePane = new GridPane();
        bestScorePane.setHgap(5);
        bestScorePane.setVgap(5);
        bestScorePane.setAlignment(Pos.CENTER);
        
        Label bestScoreLabel = new Label("Best Score:");
        bestScoreLabel.getStyleClass().add("score-label");
        
        bestScoreValueLabel = new Label("0");
        bestScoreValueLabel.getStyleClass().add("score-value");
        
        bestScorePane.add(bestScoreLabel, 0, 0);
        bestScorePane.add(bestScoreValueLabel, 0, 1);
        
        getChildren().add(bestScorePane);
    }
    
    /**
     * Update the displayed moves.
     * @param moves Number of moves
     */
    public void updateMoves(int moves) {
        movesValueLabel.setText(String.valueOf(moves));
    }
    
    /**
     * Update the displayed score.
     * @param score Current score
     */
    public void updateScore(int score) {
        scoreValueLabel.setText(String.valueOf(score));
    }
    
    /**
     * Update the displayed best score.
     * @param bestScore Best score
     */
    public void updateBestScore(int bestScore) {
        bestScoreValueLabel.setText(String.valueOf(bestScore));
    }
    
    /**
     * Get the moves value label.
     * @return Label for moves value
     */
    public Label getMovesValueLabel() {
        return movesValueLabel;
    }
    
    /**
     * Get the score value label.
     * @return Label for score value
     */
    public Label getScoreValueLabel() {
        return scoreValueLabel;
    }
    
    /**
     * Get the best score value label.
     * @return Label for best score value
     */
    public Label getBestScoreValueLabel() {
        return bestScoreValueLabel;
    }
}