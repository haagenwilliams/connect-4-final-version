# CPSC2150 - Connect 4 - Fall 2024

Developers:

SCRIPT SQUAD

George Jubenvill - gjubenv

Jake Barz - uppishdonkey

Haagen Williams - haagenwilliams

This project should be runnable with JDK17 and Junit 4.


Basic game of Connect X. Number of tokens to connect is currently 5 as set in IGameBoard TOKENS_TO_WIN.
Board size is currently 9 x 7 but can be changed by altering MAX_ROW and MAX_COL in IGameBoard.

GameBoard along with its interface and abstract file perform operations on the board such as dropping in tokens,
checking if a column has free spaces, and checking for wins/ties. It maintains a 2D char array of tokens as the board.

BoardPosition class is a data type that holds the row, column, and token for a specific spot in the board with accessors. 

GameScreen is the driver that controls the playing of the game and interacts with the player.

--------------------------------------------------------
