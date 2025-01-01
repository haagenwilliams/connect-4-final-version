package cpsc2150.extendedConnectX.models;

import java.util.*;

/**
 * Implements IGameboard interface as a memory efficient method for playing connectX
 *
 * @corresponds board - a hashmap keeping the state of the game board
 *              rows - the number of rows in the board
 *              columns - the number of columns in the board
 *              numToWin - the number of tokens in a row to win the game
 *
 * @invariants
 * MINIMUM <= rows <= MAX_ROWS
 * MINIMUM <= columns <= MAX_COLS
 * numToWin <= rows AND numToWin <= columns AND MINIMUM <= numToWin <= TOKENS_TO_WIN
 */
public class GameBoardMem extends AbsGameBoard {

    private final Map<Character, List<BoardPosition>> board;
    private final int rows;
    private final int columns;
    private final int numToWin;

    /**
     * Constructor for GameBoardMem.
     * Initializes a new memory-efficient game board instance.
     *
     * @param rows The number of rows on the game board
     * @param columns The number of columns on the game board
     * @param numToWin The number of tokens needed to win
     *
     * @pre MINIMUM <= rows <= MAX_ROWS AND MINIMUM <= columns <= MAX_COLS AND numToWin <= rows AND numToWin <= columns AND MINIMUM <= numToWin <= TOKENS_TO_WIN
     * @post [A new memory-efficient game board instance is created]
     */
    public GameBoardMem(int rows, int columns, int numToWin) {
        this.rows = rows;
        this.columns = columns;
        this.numToWin = numToWin;
        board = new HashMap<>();
    }

    @Override
    public void dropToken(char p, int c) {
        if (!board.containsKey(p)) {
            board.put(p, new ArrayList<>());
        }

        for (int i = rows - 1; i >= 0; i--) {
            BoardPosition pos = new BoardPosition(i, c);
            if (whatsAtPos(pos) == ' ') {
                board.get(p).add(pos);
                return;
            }
        }
    }

    @Override
    public char whatsAtPos(BoardPosition pos) {
        for (Map.Entry<Character, List<BoardPosition>> entry : board.entrySet()) {
            if (entry.getValue().contains(pos)) {
                return entry.getKey();
            }
        }
        return ' '; //return space if no token is present
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        return board.containsKey(player) && board.get(player).contains(pos);
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



