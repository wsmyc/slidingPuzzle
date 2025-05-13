package com.slidingpuzzle.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents the game puzzle and its logic.
 */
public class Puzzle {
    
    // Size of the puzzle grid (3x3)
    private static final int SIZE = 3;
    
    // Current state of the puzzle represented as a 2D array
    private int[][] board;
    
    // Solution state of the puzzle
    private final int[][] solution;
    
    // Position of the empty space (0)
    private int emptyRow;
    private int emptyCol;
    
    // Number of moves made
    private int moveCount;
    
    // Maximum number of moves allowed
    private int maxMoves;
    
    // Current score
    private int score;
    
    // Best score achieved
    private int bestScore;
    
    /**
     * Constructor to initialize the puzzle with a specific configuration.
     * @param configuration String representation of the puzzle (e.g., "204153876")
     */
    public Puzzle(String configuration) {
        board = new int[SIZE][SIZE];
        solution = new int[SIZE][SIZE];
        
        // Parse the configuration string
        parseConfiguration(configuration);
        
        // Initialize the solution (1,2,3,4,5,6,7,8,0) - where 0 is the empty space
        initializeSolution();
        
        // Initialize game stats
        moveCount = 0;
        maxMoves = 100; // Default max moves
        score = 0;
        bestScore = 0;
    }
    
    /**
     * Parse the configuration string into the board.
     * @param configuration String representation of the puzzle (e.g., "204153876")
     */
    private void parseConfiguration(String configuration) {
        if (configuration.length() != SIZE * SIZE) {
            throw new IllegalArgumentException("Invalid puzzle configuration. Must be " + (SIZE * SIZE) + " characters.");
        }
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int index = i * SIZE + j;
                int value = Character.getNumericValue(configuration.charAt(index));
                board[i][j] = value;
                
                // Track the empty space
                if (value == 0) {
                    emptyRow = i;
                    emptyCol = j;
                }
            }
        }
    }
    
    /**
     * Initialize the solution board.
     */
    private void initializeSolution() {
        int value = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                solution[i][j] = value % (SIZE * SIZE);
                value++;
            }
        }
    }
    
    /**
     * Attempt to move a tile to the empty space.
     * @param row Row index of the tile to move
     * @param col Column index of the tile to move
     * @return True if the move was successful, false otherwise
     */
    public boolean moveTile(int row, int col) {
        // Check if the selected tile is adjacent to the empty space
        if (!isAdjacent(row, col, emptyRow, emptyCol)) {
            return false;
        }
        
        // Swap the selected tile with the empty space
        board[emptyRow][emptyCol] = board[row][col];
        board[row][col] = 0;
        
        // Update the empty space position
        emptyRow = row;
        emptyCol = col;
        
        // Increment move count and update score
        moveCount++;
        score += 10;
        
        // Update best score if current score is higher
        if (score > bestScore) {
            bestScore = score;
        }
        
        return true;
    }
    
    /**
     * Check if two positions are adjacent.
     */
    private boolean isAdjacent(int row1, int col1, int row2, int col2) {
        return (Math.abs(row1 - row2) + Math.abs(col1 - col2)) == 1;
    }
    
    /**
     * Check if the puzzle is solved.
     * @return True if the puzzle is in the solution state, false otherwise
     */
    public boolean isSolved() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != solution[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Shuffle the puzzle by making random valid moves.
     * @param numMoves Number of random moves to make
     */
    public void shuffle(int numMoves) {
        // Reset game stats
        moveCount = 0;
        score = 0;
        
        // Perform random valid moves
        for (int i = 0; i < numMoves; i++) {
            List<int[]> validMoves = getValidMoves();
            if (!validMoves.isEmpty()) {
                int[] move = validMoves.get((int) (Math.random() * validMoves.size()));
                moveTile(move[0], move[1]);
            }
        }
        
        // Reset game stats after shuffling
        moveCount = 0;
        score = 0;
    }
    
    /**
     * Get all valid moves from the current state.
     * @return List of valid moves as [row, col] pairs
     */
    private List<int[]> getValidMoves() {
        List<int[]> validMoves = new ArrayList<>();
        
        // Check all four directions around the empty space
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right
        
        for (int[] dir : directions) {
            int newRow = emptyRow + dir[0];
            int newCol = emptyCol + dir[1];
            
            if (newRow >= 0 && newRow < SIZE && newCol >= 0 && newCol < SIZE) {
                validMoves.add(new int[]{newRow, newCol});
            }
        }
        
        return validMoves;
    }
    
    /**
     * Reset the puzzle to its original configuration.
     * @param configuration String representation of the puzzle
     */
    public void reset(String configuration) {
        parseConfiguration(configuration);
        moveCount = 0;
        score = 0;
    }
    
    // Getters and setters
    
    public int[][] getBoard() {
        return board;
    }
    
    public int getEmptyRow() {
        return emptyRow;
    }
    
    public int getEmptyCol() {
        return emptyCol;
    }
    
    public int getMoveCount() {
        return moveCount;
    }
    
    public int getMaxMoves() {
        return maxMoves;
    }
    
    public void setMaxMoves(int maxMoves) {
        this.maxMoves = maxMoves;
    }
    
    public int getScore() {
        return score;
    }
    
    public int getBestScore() {
        return bestScore;
    }
    
    public static int getSize() {
        return SIZE;
    }
    
    /**
     * Convert the current board state to a string.
     * @return String representation of the board
     */
    public String getBoardAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }
}