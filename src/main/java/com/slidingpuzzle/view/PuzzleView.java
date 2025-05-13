package com.slidingpuzzle.view;

import com.slidingpuzzle.model.Puzzle;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * View class for rendering the puzzle grid.
 */
public class PuzzleView extends StackPane {
    
    private GridPane puzzleGrid;
    private final int TILE_SIZE = 100;
    private final int GAP = 5;
    
    /**
     * Constructor initializes the puzzle view.
     */
    public PuzzleView() {
        // Create and configure the grid
        puzzleGrid = new GridPane();
        puzzleGrid.setAlignment(Pos.CENTER);
        puzzleGrid.setHgap(GAP);
        puzzleGrid.setVgap(GAP);
        puzzleGrid.getStyleClass().add("puzzle-grid");
        
        // Add grid to this pane
        getChildren().add(puzzleGrid);
        
        // Set minimum size
        setPrefSize((TILE_SIZE + GAP) * 3, (TILE_SIZE + GAP) * 3);
        setMinSize((TILE_SIZE + GAP) * 3, (TILE_SIZE + GAP) * 3);
    }
    
    /**
     * Update the puzzle view with new board state.
     * @param puzzle The puzzle model
     */
    public void update(Puzzle puzzle) {
        // Clear existing tiles
        puzzleGrid.getChildren().clear();
        
        int size = Puzzle.getSize();
        int[][] board = puzzle.getBoard();
        
        // Create tiles based on current board state
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final int value = board[i][j];
                final int row = i;
                final int col = j;
                
                // Create tile with the value
                Pane tile = createTile(value);
                
                // Add the tile to the grid
                puzzleGrid.add(tile, j, i);
            }
        }
    }
    
    /**
     * Create a puzzle tile with the given value.
     * @param value The value to display on the tile (0 for empty space)
     * @return A pane representing the tile
     */
    private Pane createTile(int value) {
        StackPane tile = new StackPane();
        tile.setPrefSize(TILE_SIZE, TILE_SIZE);
        
        if (value > 0) {
            // Style for regular tiles
            tile.getStyleClass().add("puzzle-tile");
            
            // Add number label
            Label numberLabel = new Label(String.valueOf(value));
            numberLabel.getStyleClass().add("tile-number");
            tile.getChildren().add(numberLabel);
        } else {
            // Style for empty tile
            tile.getStyleClass().add("empty-tile");
        }
        
        return tile;
    }
    
    /**
     * Animate a tile move.
     * @param fromRow Source row
     * @param fromCol Source column
     * @param toRow Destination row
     * @param toCol Destination column
     */
    public void animateTileMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Calculate pixel coordinates for animation
        double startX = fromCol * (TILE_SIZE + GAP);
        double startY = fromRow * (TILE_SIZE + GAP);
        double endX = toCol * (TILE_SIZE + GAP);
        double endY = toRow * (TILE_SIZE + GAP);
        
        // Get the tile node
        Pane tile = findTileAt(fromRow, fromCol);
        if (tile == null) return;
        
        // Create and configure the animation
        TranslateTransition transition = new TranslateTransition(Duration.millis(150), tile);
        transition.setFromX(0);
        transition.setFromY(0);
        transition.setToX(endX - startX);
        transition.setToY(endY - startY);
        transition.setCycleCount(1);
        
        // Play the animation
        transition.play();
    }
    
    /**
     * Find a tile node at specified grid position.
     * @param row Row index
     * @param col Column index
     * @return The tile pane at the specified position, or null if not found
     */
    private Pane findTileAt(int row, int col) {
        for (javafx.scene.Node node : puzzleGrid.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return (Pane) node;
            }
        }
        return null;
    }
    
    /**
     * Get the puzzle grid.
     * @return The GridPane containing puzzle tiles
     */
    public GridPane getPuzzleGrid() {
        return puzzleGrid;
    }
}