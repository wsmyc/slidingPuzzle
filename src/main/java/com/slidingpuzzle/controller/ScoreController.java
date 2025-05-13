package com.slidingpuzzle.controller;

import java.util.Map;

import com.slidingpuzzle.model.Score;

/**
 * Controller for managing game scores.
 */
public class ScoreController {
    
    private Score scoreModel;
    
    /**
     * Constructor initializes the score model.
     */
    public ScoreController() {
        scoreModel = new Score();
    }
    
    /**
     * Update the high score for a configuration.
     * @param configuration Puzzle configuration
     * @param score New score
     * @return True if the high score was updated, false otherwise
     */
    public boolean updateHighScore(String configuration, int score) {
        return scoreModel.updateHighScore(configuration, score);
    }
    
    /**
     * Get the high score for a configuration.
     * @param configuration Puzzle configuration
     * @return High score for the configuration
     */
    public int getHighScore(String configuration) {
        return scoreModel.getHighScore(configuration);
    }
    
    /**
     * Get all high scores.
     * @return Map of all high scores
     */
    public Map<String, Integer> getAllHighScores() {
        return scoreModel.getAllHighScores();
    }
}