package com.slidingpuzzle.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages high scores for different puzzle configurations.
 */
public class Score implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // Map that stores high scores for each configuration
    private Map<String, Integer> highScores;
    
    // File to store scores
    private static final String SCORE_FILE = "sliding_puzzle_scores.dat";
    
    /**
     * Constructor initializes the high scores map and loads existing scores.
     */
    public Score() {
        highScores = new HashMap<>();
        loadScores();
    }
    
    /**
     * Update the high score for a configuration if the new score is higher.
     * @param configuration Puzzle configuration
     * @param score New score
     * @return True if the score was updated, false otherwise
     */
    public boolean updateHighScore(String configuration, int score) {
        Integer currentHighScore = highScores.get(configuration);
        
        if (currentHighScore == null || score > currentHighScore) {
            highScores.put(configuration, score);
            saveScores();
            return true;
        }
        
        return false;
    }
    
    /**
     * Get the high score for a specific configuration.
     * @param configuration Puzzle configuration
     * @return High score for the configuration, or 0 if no high score exists
     */
    public int getHighScore(String configuration) {
        return highScores.getOrDefault(configuration, 0);
    }
    
    /**
     * Save scores to a file.
     */
    private void saveScores() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SCORE_FILE))) {
            out.writeObject(highScores);
        } catch (IOException e) {
            System.err.println("Error saving scores: " + e.getMessage());
        }
    }
    
    /**
     * Load scores from a file.
     */
    @SuppressWarnings("unchecked")
    private void loadScores() {
        File file = new File(SCORE_FILE);
        
        if (!file.exists()) {
            return;
        }
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            highScores = (Map<String, Integer>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading scores: " + e.getMessage());
            // If there's an error loading, start with a fresh map
            highScores = new HashMap<>();
        }
    }
    
    /**
     * Get all high scores.
     * @return Map of high scores for all configurations
     */
    public Map<String, Integer> getAllHighScores() {
        return new HashMap<>(highScores);
    }
}