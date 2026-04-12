# Sliding Puzzle Game

A modern, interactive 3x3 sliding puzzle game built with JavaFX. Features multiple puzzle configurations, score tracking, timer functionality, and persistent high scores.

![JavaFX](https://img.shields.io/badge/JavaFX-17-blue)
![Java](https://img.shields.io/badge/Java-17-orange)
![Maven](https://img.shields.io/badge/Maven-3.8-red)
![License](https://img.shields.io/badge/License-MIT-green)

## Overview

This is a classic sliding puzzle (8-puzzle) implementation where players rearrange numbered tiles on a 3x3 grid to achieve the correct order. The game features a clean JavaFX interface, multiple starting configurations, move counting, scoring system, and persistent high score storage.

## Features

### Core Gameplay
- **3x3 Grid Puzzle**: Classic 8-puzzle with numbered tiles 1-8 and one empty space
- **Click-to-Move**: Click any tile adjacent to the empty space to slide it
- **Multiple Configurations**: 7 different starting configurations with varying difficulty
- **Shuffle Mode**: Randomize the puzzle with configurable number of moves
- **Reset Function**: Return to original configuration instantly

### Scoring & Statistics
- **Move Counter**: Tracks every valid tile movement
- **Score System**: Earn 10 points per valid move
- **Timer**: Tracks elapsed time in MM:SS format
- **High Scores**: Persistent storage of best scores per configuration
- **Best Score Display**: Shows your personal best for current configuration

### User Interface
- **Modern JavaFX UI**: Clean, responsive design with CSS styling
- **Configuration Selector**: Dropdown to choose different puzzle layouts
- **Real-time Updates**: Live display of moves, score, and time
- **Win Detection**: Celebration dialog when puzzle is solved

## Architecture

### Project Structure

```
slidingPuzzle/
├── src/main/java/com/slidingpuzzle/
│   ├── Main.java                    # Application entry point
│   ├── controller/
│   │   ├── GameController.java      # Main game logic & UI bindings
│   │   └── ScoreController.java     # Score display controller
│   ├── model/
│   │   ├── Puzzle.java              # Core puzzle logic & state
│   │   ├── PuzzleConfiguration.java # Available puzzle setups
│   │   └── Score.java               # High score persistence
│   └── view/
│       ├── GameView.java            # Game board renderer
│       ├── PuzzleView.java          # Individual tile view
│       └── ScoreView.java           # Score display view
├── src/main/resources/com/slidingpuzzle/
│   ├── game.fxml                    # JavaFX layout definition
│   └── game.css                     # Styling & themes
├── pom.xml                          # Maven configuration
└── sliding_puzzle_scores.dat        # Serialized high scores (auto-generated)
```

### Design Pattern: MVC (Model-View-Controller)

| Component | Responsibility |
|-----------|--------------|
| **Model** | `Puzzle`, `Score`, `PuzzleConfiguration` - Business logic & data |
| **View** | `GameView`, `PuzzleView`, `ScoreView`, FXML/CSS - UI presentation |
| **Controller** | `GameController`, `ScoreController` - User input & coordination |

### Key Classes

#### `Puzzle.java`
Core game logic implementing the 3x3 sliding puzzle:
- Board state management (2D array)
- Move validation (adjacency checking)
- Solution state detection
- Shuffle algorithm (random valid moves)
- Score calculation

#### `GameController.java`
Main controller handling:
- FXML UI bindings (JavaFX properties)
- Timer management (java.util.Timer)
- Configuration switching
- Win condition handling
- Score persistence calls

#### `Score.java`
High score management:
- Serialization to `sliding_puzzle_scores.dat`
- Per-configuration score tracking
- Persistent storage across sessions

## Installation

### Prerequisites
- **Java 17** or later
- **Maven 3.8** or later
- JavaFX 17 (handled by Maven dependencies)

### Clone & Build

```bash
# Clone the repository
git clone https://github.com/wsmyc/slidingPuzzle.git
cd slidingPuzzle

# Build with Maven
mvn clean compile

# Package as executable JAR
mvn clean package

# Run the application
java -jar target/sliding-puzzle-1.0-SNAPSHOT-jar-with-dependencies.jar
```

### Or run directly with Maven:

```bash
mvn clean javafx:run
```

## How to Play

1. **Start the Game**: Launch the application to see the default puzzle configuration
2. **Move Tiles**: Click any tile adjacent to the empty (black) space to slide it
3. **Goal**: Arrange tiles in order: 1-2-3 on top row, 4-5-6 middle, 7-8 bottom (empty at bottom-right)
4. **Track Progress**: Watch your moves, score, and time in the top panel
5. **Try Configurations**: Use the dropdown to select different starting layouts
6. **Shuffle**: Click "Shuffle" to randomize the current configuration
7. **Reset**: Click "Rearrange" to return to the original layout

### Winning
When you solve the puzzle, a congratulatory dialog appears showing:
- Total moves made
- Final score
- Time elapsed

## Puzzle Configurations

The game includes 7 pre-defined configurations:

| # | Configuration | Description |
|---|---------------|-------------|
| 1 | `073214568` | Corner-empty variant |
| 2 | `124857063` | Center-focused layout |
| 3 | `204153876` | **Default** - Classic setup |
| 4 | `624801753` | Diagonal challenge |
| 5 | `670132584` | Edge-heavy pattern |
| 6 | `781635240` | Scattered arrangement |
| 7 | `280163547` | Mixed difficulty |

Each configuration maintains its own high score, encouraging mastery of all layouts.

## Scoring System

- **Base Points**: +10 points per valid move
- **High Score**: Best score per configuration is saved
- **No Penalty**: Invalid moves (non-adjacent clicks) don't deduct points

## Technical Details

### JavaFX Integration
- **FXML**: Declarative UI layout (`game.fxml`)
- **CSS Styling**: Modern dark theme with gradient tiles (`game.css`)
- **Properties**: Reactive binding for moves, score, and time updates
- **Animation**: Timeline support for smooth tile transitions

### Persistence
- **Serialization**: Java Object Serialization for high scores
- **Storage**: Local file `sliding_puzzle_scores.dat`
- **Format**: HashMap storing `<Configuration, Score>` pairs

### Timer Implementation
- **java.util.Timer**: Background thread for time tracking
- **Platform.runLater()**: JavaFX thread safety for UI updates
- **Auto-start**: Timer begins on game start/configuration change

## Customization

### Adding New Configurations
Edit `PuzzleConfiguration.java`:

```java
private static final List<String> CONFIGURATIONS = Arrays.asList(
    "073214568",
    "124857063",
    "204153876",
    // Add your new 9-digit configuration here
    "123456780"  // Example: solved state
);
```

### Styling
Modify `game.css` to change:
- Tile colors (`.tile`, `.empty-tile`)
- Font sizes (`.tile-text`)
- Grid appearance (`.grid-pane`)
- Background themes

## Roadmap

- [ ] **4x4 Mode**: 15-puzzle difficulty option
- [ ] **Image Mode**: Use custom images instead of numbers
- [ ] **Solver Integration**: AI solver demonstration
- [ ] **Sound Effects**: Audio feedback for moves and wins
- [ ] **Animation**: Smooth tile sliding transitions
- [ ] **Leaderboard**: Global high scores via online service

## Dependencies

| Library | Version | Purpose |
|---------|---------|---------|
| JavaFX Controls | 17.0.20 | Core UI components |
| JavaFX FXML | 17.0.20 | Layout management |
| JUnit Jupiter | 5.9.2 | Unit testing |

## License

MIT License - Free for educational and personal use.

## Author

Created as a JavaFX learning project demonstrating MVC architecture, serialization, and desktop GUI development.

---

**Enjoy solving!** 🧩
