# Console-Based Robot Arena Simulation

A Java program that simulates robots moving within a console-based arena. Robots can be added, moved, and animated within the arena, displaying their movements directly in the console.

## Features

- **Random Robot Placement and Direction**: 
  - New robots are placed at random, unoccupied positions with random initial directions (North, East, South, or West).

- **Directional Movement and Collision Avoidance**: 
  - Robots attempt to move forward in their current direction and change direction when they encounter obstacles or arena boundaries.

- **User Interaction via Console Menu**: 
  - A simple console menu allows users to add robots, display robot information, move robots, simulate movements, create a new arena with custom dimensions, and save or load the arena state.

- **Save and Load Arena State**: 
  - The current state of the arena can be saved to a file and loaded back, allowing the simulation to be paused and resumed.

- **Customizable Arena Size**: 
  - Users can create a new arena with specified dimensions.

## Classes and Structure

- **`Robot`**: 
  - Represents an individual robot with its position, direction, and unique identifier.

- **`RobotArena`**: 
  - Manages the arena grid and the collection of robots, handling their placement and movement logic.

- **`RobotInterface`**: 
  - Provides the user interface, handling user input and coordinating actions within the simulation.

- **`Direction`**: 
  - An enum representing the four cardinal directions, with methods for random selection and cycling through directions.

- **`ConsoleCanvas`**: 
  - Handles the visual representation of the arena in the console using ASCII characters.

- **`TextFile`**: 
  - Manages file input/output operations for saving and loading the arena state.

## Usage

Upon running the program, you will be presented with a menu of options to interact with the simulation.

### Menu Options

- **(A)dd Robot**: 
  - Adds a new robot to the arena at a random position and direction.

- **Get (I)nformation**: 
  - Displays information about all robots in the arena.

- **(D)isplay Arena**: 
  - Shows the current state of the arena with all robots.

- **(M)ove Robots**: 
  - Moves all robots one step forward.

- **(S)imulate**: 
  - Animates robot movements over multiple steps.

- **(N)ew Arena**: 
  - Creates a new arena, with the option to specify new dimensions.

- **(L)oad**: 
  - Loads the arena state from a file (`arena_save.txt`).

- **(W)rite**: 
  - Saves the current arena state to a file (`arena_save.txt`).

- **E(X)it**: 
  - Exits the program.

## Customization

- **Changing Arena Dimensions**:
  - When creating a new arena, you can specify custom width and height.

- **Modifying Robot Symbols**:
  - In the `ConsoleCanvas` class, you can change the symbol used to represent robots (currently `'R'`).

