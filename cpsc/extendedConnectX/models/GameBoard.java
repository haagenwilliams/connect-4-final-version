/*
George Jubenvill - gjubenv
Jake Barz - uppishdonkey
Haagen Williams - haagenwilliams
 */
package cpsc2150.extendedConnectX.models;

/**
 * Implements IGameboard interface as a fast method for playing connectX
 *
 * @corresponds board - a 2D char array that stores the state of the game board
 *              rows - the number of rows in the board
 *              columns - the number of columns in the board
 *              numToWin - the number of tokens in a row to win the game
 *
 * @invariants
 * MINIMUM <= rows <= MAX_ROWS
 * MINIMUM <= columns <= MAX_COLS
 * numToWin <= rows AND numToWin <= columns AND MINIMUM <= numToWin <= TOKENS_TO_WIN
 */
public class GameBoard extends AbsGameBoard
{
    private final char[][] board;
    private final int rows;
    private final int columns;
    private final int numToWin;

    /**
     * Constructor for GameBoard.
     * Initializes a new game board instance.
     *
     * @param rows The number of rows on the game board
     * @param columns The number of columns on the game board
     * @param numToWin The number of tokens needed to win
     *
     * @pre MINIMUM <= rows <= MAX_ROWS AND MINIMUM <= columns <= MAX_COLS AND numToWin <= rows AND numToWin <= columns AND MINIMUM <= numToWin <= TOKENS_TO_WIN
     * @post a new instance of GameBoard is created with an empty board AND this.rows = rows AND
     *      this.columns = columns AND this.numToWin = numToWin
     */
    public GameBoard(int rows, int columns, int numToWin) {

        this.rows = rows;
        this.columns = columns;
        this.numToWin = numToWin;

        board = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void dropToken(char p, int c) {

        for (int i = rows - 1; i >= 0; i--) {

            if (board[i][c] == ' ') {
                board[i][c] = p;
                break;
            }
        }
    }

    public char whatsAtPos(BoardPosition pos) {

        return board[pos.getRow()][pos.getColumn()];
    }

    public int getNumToWin() {
        return numToWin;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}