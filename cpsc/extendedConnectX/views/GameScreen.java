package cpsc2150.extendedConnectX.views;

import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import cpsc2150.extendedConnectX.models.IGameBoard;

import java.io.IOException;
import java.util.Scanner;

import static cpsc2150.extendedConnectX.models.IGameBoard.*;

/*
George Jubenvill - gjubenv
Jake Barz - uppishdonkey
Haagen Williams - haagenwilliams
 */
public class GameScreen {

    public static void main(String[] args) throws IOException {

        boolean again = true;
        while (again) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("How many players?");

            // Validating numPlayers input
            int numPlayers;
            while (true) {
                numPlayers = scanner.nextInt();

                if (numPlayers < 2) {
                    System.out.println("Must be at least 2 players");
                    continue;
                }
                if (numPlayers > 10) {
                    System.out.println("Must be 10 players or fewer");
                    continue;
                }

                break;
            }

            // Adding each player symbol
            boolean unique = false;
            char[] players = new char[numPlayers];
            for (int i = 0; i < numPlayers; i++) {
                unique = false;
                while (!unique) {
                    unique = true;
                    System.out.print("Enter the character to represent player " + (i + 1) + "\n");
                    char token = scanner.next().charAt(0);

                    // Checks if symbol is already taken
                    for (int j = 0; j < i; j++) {
                        if (token == players[j]) {
                            System.out.println(token + "is already taken as a player token!");
                            unique = false;
                            break;
                        }
                    }

                    if (unique) {
                        players[i] = token;
                    }
                }
            }

            // Validating rows input
            int rows;
            while (true) {
                System.out.println("How many rows should be on the board?");
                rows = scanner.nextInt();
                if (rows >= MINIMUM && rows <= MAX_ROW) {
                    break;
                }
            }

            // Validating columns input
            int columns;
            while (true) {
                System.out.println("How many columns should be on the board?");
                columns = scanner.nextInt();
                if (columns >= MINIMUM && columns <= MAX_COL) {
                    break;
                }
            }

            // Validating numToWin input
            int numToWin;
            while (true) {
                System.out.println("How many in a row to win?");
                numToWin = scanner.nextInt();
                if (numToWin <= rows && numToWin <= columns && numToWin <= TOKENS_TO_WIN && numToWin >= MINIMUM) {
                    break;
                }
            }

            // Validating gameType input
            char gameType;
            while (true) {
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                gameType = scanner.next().charAt(0);
                if (gameType != 'f' && gameType != 'F' && gameType != 'M' && gameType != 'm') {
                    System.out.println("Please enter F or M");
                }
                else {
                    break;
                }
            }

            // Creating the correct type of board
            IGameBoard board;
            if (gameType == 'f' || gameType == 'F') {
                board = new GameBoard(rows, columns, numToWin);
            }
            else {
                board = new GameBoardMem(rows, columns, numToWin);
            }

            // First row of the board that indicates the column numbers
            StringBuilder string = new StringBuilder();
            string.append("|");
            for (int c = 0; c < columns; c++) {
                if (columns > 10) {
                    string.append(String.format("%2d", c)).append("|");
                }
                else {
                    string.append(c).append("|");
                }
            }
            string.append("\n");


            boolean end = false;
            // Loops after every players turn
            while (!end) {
                // Loops through each player for one turn
                for (int i = 0; i < numPlayers; i++) {

                    // Printing the board
                    System.out.print(string);
                    System.out.print(board);

                    // Validating the choice of column
                    int choice;
                    do {
                        System.out.print("Player " + players[i] + ", what column do you want to place your marker in?\n");
                        choice = scanner.nextInt();
                    }while (choice < 0 || choice > columns - 1 || !board.checkIfFree(choice));

                    // Adding the token and checking for a win or tie
                    board.dropToken(players[i], choice);
                    if (board.checkForWin(choice)) {
                        System.out.println("Player " + players[i] + " Won!");
                        end = true;
                        break;
                    }
                    else if (board.checkTie()) {
                        System.out.println("It's a Tie!");
                        end = true;
                        break;
                    }
                }
            }
            // Printing the final board state
            System.out.print(string);
            System.out.print(board);

            // Checking if user wants to play again
            System.out.println("Would you like to play again? Y/N");
            if (scanner.next().equalsIgnoreCase("n")) {
                again = false;
            }
        }
    }
}