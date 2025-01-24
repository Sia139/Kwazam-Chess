#Kwazam Chess
##Overview
A Java-based chess game with a graphical interface built using Java Swing. Players can move pieces, capture opponents, and win by capturing the "Sau" (King). Features include board flipping, piece transformation, and save/load functionality.

##Features
GUI: Visual chessboard with drag-and-drop piece movement.

Piece Movement: Unique rules for each piece (e.g., Biz moves in L-shapes, Ram moves forward).

Win Condition: Capture the opponent's Sau to win.

Board Flipping: Flip the board to view from the opponent's perspective.

Piece Transformation: Xor and Tor transform every 4 turns.

Save/Load: Save the game state and load it later.

Check Detection: Notifies players when their Sau is in check.

##How to Run
Prerequisites: Install JDK.

Compile: Run javac *.java 

Execute: Run java Main

##Controls
Mouse: Click to select, drag to move, and release to place pieces.

Menu: New Game, Save, Load, and Exit options.

##Project Structure
controller: Handles game logic and input.

model: Manages game state and pieces.

view: Handles GUI and rendering.

utils: Global constants and utilities.
