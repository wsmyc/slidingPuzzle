package com.slidingpuzzle.model;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Manages puzzle configurations available in the game.
 */
public class PuzzleConfiguration {
    
    // List of available puzzle configurations
    private static final List<String> CONFIGURATIONS = Arrays.asList(
        "073214568", 
        "124857063", 
        "204153876", 
        "624801753", 
        "670132584", 
        "781635240", 
        "280163547"
    );
    
    // Default configuration index
    private static final int DEFAULT_INDEX = 2; // "204153876"
    
    private final Random random = new Random();
    
    /**
     * Get the default puzzle configuration.
     * @return Default configuration string
     */
    public String getDefaultConfiguration() {
        return CONFIGURATIONS.get(DEFAULT_INDEX);
    }
    
    /**
     * Get a puzzle configuration by index.
     * @param index Index of the configuration to retrieve
     * @return Configuration string at the specified index
     */
    public String getConfiguration(int index) {
        if (index < 0 || index >= CONFIGURATIONS.size()) {
            throw new IndexOutOfBoundsException("Invalid configuration index: " + index);
        }
        return CONFIGURATIONS.get(index);
    }
    
    /**
     * Get a random puzzle configuration.
     * @return Random configuration string
     */
    public String getRandomConfiguration() {
        int randomIndex = random.nextInt(CONFIGURATIONS.size());
        return CONFIGURATIONS.get(randomIndex);
    }
    
    /**
     * Get all available configurations.
     * @return List of all configurations
     */
    public List<String> getAllConfigurations() {
        return CONFIGURATIONS;
    }
    
    /**
     * Get the number of available configurations.
     * @return Number of configurations
     */
    public int getConfigurationCount() {
        return CONFIGURATIONS.size();
    }
}