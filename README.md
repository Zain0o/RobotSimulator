markdown
Console-Based Robot Arena Simulation
A Java program that simulates robots moving within a console-based arena. Robots can be added, moved, and animated within the arena, displaying their movements directly in the console.

Features
Random Robot Placement and Direction: New robots are placed at random, unoccupied positions with random initial directions (North, East, South, or West).
Directional Movement and Collision Avoidance: Robots attempt to move forward in their current direction and change direction when they encounter obstacles or arena boundaries.
User Interaction via Console Menu: A simple console menu allows users to add robots, display robot information, move robots, simulate movements, create a new arena with custom dimensions, and save or load the arena state.
Save and Load Arena State: The current state of the arena can be saved to a file and loaded back, allowing the simulation to be paused and resumed.
Customizable Arena Size: Users can create a new arena with specified dimensions.
Classes and Structure
Robot: Represents an individual robot with its position, direction, and unique identifier.
RobotArena: Manages the arena grid and the collection of robots, handling their placement and movement logic.
RobotInterface: Provides the user interface, handling user input and coordinating actions within the simulation.
Direction: An enum representing the four cardinal directions, with methods for random selection and cycling through directions.
ConsoleCanvas: Handles the visual representation of the arena in the console using ASCII characters.
TextFile: Manages file input/output operations for saving and loading the arena state.
Getting Started
Prerequisites
Java Development Kit (JDK) installed (Java 8 or higher).
A console or terminal to run the program.
Installation
Clone the Repository:

bash
Copy code
git clone https://github.com/yourusername/robot-arena-simulation.git
Navigate to the Project Directory:

bash
Copy code
cd robot-arena-simulation/RobotSim
Compile the Java Files:

bash
Copy code
javac *.java
Run the Program:

bash
Copy code
java RobotInterface
Usage
Upon running the program, you will be presented with a menu of options to interact with the simulation.

Menu Options
(A)dd Robot: Adds a new robot to the arena at a random position and direction.
Get (I)nformation: Displays information about all robots in the arena.
(D)isplay arena: Shows the current state of the arena with all robots.
(M)ove robots: Moves all robots one step forward.
(S)imulate: Animates robot movements over multiple steps.
(N)ew arena: Creates a new arena, with the option to specify new dimensions.
(L)oad: Loads the arena state from a file (arena_save.txt).
(W)rite: Saves the current arena state to a file (arena_save.txt).
E(X)it: Exits the program.
Example Interaction
scss
Copy code
Enter (A)dd Robot, get (I)nformation, (D)isplay arena, (M)ove robots, (S)imulate, (N)ew arena, (L)oad, (W)rite or e(X)it > A
Robot added.

Enter (A)dd Robot, get (I)nformation, ... > D
#####32019071#####
#               #
#       R       #
#               #
#               #
#               #
#################

Enter (A)dd Robot, get (I)nformation, ... > S
[Robots move and the arena updates over several steps]
Note: Replace "32019071" with your student number or identifier as needed.

Example
Here's a brief example demonstrating how to interact with the program:

Add Robots:

Select (A)dd Robot to add one or more robots to the arena.
Display Arena:

Use (D)isplay arena to view the current state of the arena with the robots' positions.
Move Robots:

Choose (M)ove robots to move all robots one step forward.
Simulate Movements:

Select (S)imulate to animate robot movements over several steps with delays.
Get Information:

Use (I)nformation to display detailed information about each robot, including their ID, position, and direction.
Save and Load Arena State:

Use (W)rite to save the current arena state to a file.
Use (L)oad to load a previously saved arena state from a file.
Create New Arena:

Select (N)ew arena to reset the arena, optionally specifying new dimensions.
Exit Program:

Choose (X) to exit the simulation.
Program Structure
The program consists of the following classes:

Robot: Contains properties and methods related to individual robots.
RobotArena: Manages the arena and the collection of robots.
RobotInterface: Handles user input and program execution flow.
Direction: Enum for robot directions with methods for direction handling.
ConsoleCanvas: Manages the display of the arena in the console.
TextFile: Provides file read and write operations for saving/loading the arena state.
Customization
Changing Arena Dimensions:

When creating a new arena, you can specify custom width and height.
Modifying Robot Symbols:

In the ConsoleCanvas class, you can change the symbol used to represent robots (currently 'R').
