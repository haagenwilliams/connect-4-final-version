package cpsc2150.extendedConnectX.models;

/**
 * IGameBoard interface for game board operations checkIfFree, dropToken, checkForWin,
 * checkTie, checkHorizWin, checkVertWin, checkDiagWin, whatsAtPos, isPlayerAtPos. Game board
 * is used to manage, update, and check the status of the connect 5 game.
 *
 * @initialization_ensures: board is empty
 *
 * @defines: board: the 2D array representing the game board
 *
 * @constraints: none
 */
public interface IGameBoard {

    public static final int MAX_ROW = 100;
    public static final int MAX_COL = 100;
    public static final int TOKENS_TO_WIN = 25;
    public static final int MINIMUM = 3;


    /**
     * Checks if the specified column has available space for a token, accepts one param.
     * @param c the column number to check (0 ≤ c < number of columns)
     *
     * @return true if the column c can accept another token, false if the column is full
     *
     * @pre 0 ≤ c < MAX_COL
     *
     * @post checkIfFree = true IFF column c has available space, false OW
     */
    default public boolean checkIfFree(int c) {

        char token = whatsAtPos(new BoardPosition(0, c));

        return token == ' ';
    }

    /**
     * Drops a player's token into the specified column, accepts two params.
     * @param p the player's token (either 'X' or 'O')
     * @param c the column number where the token will be dropped (0 ≤ c < number of columns)
     *
     * @pre p ∈ {'X', 'O'} AND 0 ≤ c < number of columns
     *
     * @post the player's token is placed in the lowest available row in column c
     */
    public void dropToken(char p, int c);

    /**
     * Checks if the last token placed in the specified column resulted in a win, accepts one param
     * @param c the column where the last token was placed (0 ≤ c < number of columns)
     *
     * @return true if the last token placed in column c resulted in a win; otherwise, false
     *
     * @pre 0 ≤ c < number of columns
     *
     * @post checkForWin = true IFF the last token placed in column c resulted in a win, false OW
     */
    default public boolean checkForWin(int c) {

        BoardPosition pos = new BoardPosition(0, 0);
        char token = ' ';

        for (int i = 0; i < getRows(); ++i) {
            pos = new BoardPosition(i, c);
            if (whatsAtPos(pos) != ' ') {
                token = whatsAtPos(pos);
                break;
            }
        }

        if (token == ' ') {
            return false;
        }

        if (checkHorizWin(pos, token)) {
            return true;
        }

        if (checkVertWin(pos, token)) {
            return true;
        }

        if (checkDiagWin(pos, token)) {
            return true;
        }

        return false;
    }

    /**
     * Checks if the game has ended in a tie.
     *
     * @return true if the game is tied (i.e., no available positions), otherwise false
     *
     * @post checkTie = true IFF no available spaces remain, false OW
     *
     * @pre none
     */
    default public boolean checkTie() {

        for (int i = 0; i < getColumns(); ++i) {
            if (whatsAtPos(new BoardPosition(0, i)) == ' ') {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the last token placed at the given position resulted in a horizontal win, accepts two params
     * @param pos the position of the last token placed
     * @param p   the player's token (either 'X' or 'O')
     *
     * @return true if the last token placed at position pos resulted in 5 in a row horizontally, otherwise false
     *
     * @pre pos is a valid BoardPosition AND p ∈ {'X', 'O'}
     *
     * @post checkHorizWin = true IFF there are 5 in a row horizontally, false OW
     */
    default public boolean checkHorizWin(BoardPosition pos, char p) {

        int r = pos.getRow();
        int c = pos.getColumn();
        int count = 1;

        for (int i = c - 1; i >= c - getNumToWin() - 1; --i) {

            if (i < 0) {
                break;
            }

            BoardPosition pos2 = new BoardPosition(r, i);
            if (whatsAtPos(pos2) == p) {
                count++;
            }
            else {
                break;
            }

            if (count == getNumToWin()) {
                return true;
            }
        }

        for (int i = c + 1; i <= r + getNumToWin() - 1; ++i) {

            if (i > getColumns() - 1) {
                break;
            }

            BoardPosition pos2 = new BoardPosition(r, i);
            if (whatsAtPos(pos2) == p) {
                count++;
            }
            else {
                break;
            }

            if (count == getNumToWin()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the last token placed at the given position resulted in a vertical win, accepts two params.
     * @param pos the position of the last token placed
     * @param p the player's token (either 'X' or 'O')
     *
     * @return true if the last token placed at position pos resulted in 5 in a row vertically, otherwise false
     *
     * @pre pos is a valid BoardPosition AND p ∈ {'X', 'O'}
     *
     * @post checkVertWin = true IFF there are 5 in a row vertically, false OW
     */
    default public boolean checkVertWin(BoardPosition pos, char p) {

        int r = pos.getRow();
        int c = pos.getColumn();
        int count = 1;

        for (int i = r - 1; i >= r - getNumToWin() - 1; --i) {

            if (i < 0) {
                break;
            }

            BoardPosition pos2 = new BoardPosition(i, c);
            if (whatsAtPos(pos2) == p) {
                count++;
            }
            else {
                break;
            }

            if (count == getNumToWin()) {
                return true;
            }
        }

        for (int i = r + 1; i <= r + getNumToWin() - 1; ++i) {

            if (i > getRows() - 1) {
                break;
            }

            BoardPosition pos2 = new BoardPosition(i, c);
            if (whatsAtPos(pos2) == p) {
                count++;
            }
            else {
                break;
            }

            if (count == getNumToWin()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the last token placed at the given position resulted in a diagonal win, accepts two params.
     * @param pos the position of the last token placed
     * @param p the player's token (either 'X' or 'O')
     *
     * @return true if the last token placed at position pos resulted in 5 in a row diagonally, otherwise false
     *
     * @pre pos is a valid BoardPosition AND p ∈ {'X', 'O'}
     *
     * @post checkDiagWin = true IFF there are 5 in a row diagonally, false OW
     */
    default public boolean checkDiagWin(BoardPosition pos, char p) {

        int r = pos.getRow();
        int c = pos.getColumn();
        int row = r - 1;
        int count = 1;

        for (int i = c - 1; i >= c - getNumToWin() - 1; --i) {

            if (i < 0 || row < 0) {
                break;
            }

            BoardPosition pos2 = new BoardPosition(row, i);
            if (whatsAtPos(pos2) == p) {
                count++;
            }
            else {
                break;
            }

            if (count == getNumToWin()) {
                return true;
            }
            row--;
        }

        row = r + 1;
        for (int i = c + 1; i <= r + getNumToWin() - 1; ++i) {

            if (i > getColumns() - 1 || row > getRows() - 1) {
                break;
            }

            BoardPosition pos2 = new BoardPosition(row, i);
            if (whatsAtPos(pos2) == p) {
                count++;
            }
            else {
                break;
            }

            if (count == getNumToWin()) {
                return true;
            }
            row++;
        }

        count = 1;
        row = r + 1;
        for (int i = c - 1; i >= c - getNumToWin() - 1; --i) {

            if (i < 0 || row > getRows() - 1) {
                break;
            }

            BoardPosition pos2 = new BoardPosition(row, i);
            if (whatsAtPos(pos2) == p) {
                count++;
            }
            else {
                break;
            }

            if (count == getNumToWin()) {
                return true;
            }
            row++;
        }

        row = r - 1;
        for (int i = c + 1; i <= r + getNumToWin() - 1; ++i) {

            if (i > getColumns() - 1 || row < 0) {
                break;
            }

            BoardPosition pos2 = new BoardPosition(row, i);
            if (whatsAtPos(pos2) == p) {
                count++;
            }
            else {
                break;
            }

            if (count == getNumToWin()) {
                return true;
            }
            row--;
        }

        return false;
    }

    /**
     * Retrieves the character at the given board position, accepts one param.
     * @param pos the position to check
     *
     * @return the character at position pos ('X', 'O', or a blank space if no token is placed there)
     *
     * @pre pos is a valid BoardPosition
     *
     * @post whatsAtPos = the character at the given position on the board
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * Checks if the given player's token is at the specified position, accepts two params.
     * @param pos the position to check
     * @param player the player's token ('X' or 'O')
     *
     * @return true if the player's token is at position pos, otherwise false
     *
     * @pre pos is a valid BoardPosition AND player ∈ {'X', 'O'}
     *
     * @post isPlayerAtPos = true IFF the player's token is at pos, false OW
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player) {

        char p = whatsAtPos(pos);
        return p == player;
    }

    /**
     * Standard getter for numToWin.
     *
     * @return numToWin, an int
     *
     * @pre none
     *
     * @post getNumToWin = numToWin AND board = #board AND rows = #rows AND columns = #columns
     *      AND numToWin = #numToWin
     */
    public int getNumToWin();

    /**
     * Standard getter for rows.
     *
     * @return rows, an int
     *
     * @pre none
     *
     * @post getRows = rows AND board = #board AND rows = #rows AND columns = #columns
     *      AND numToWin = #numToWin
     */
    public int getRows();

    /**
     * Standard getter for columns.
     *
     * @return columns, an int
     *
     * @pre none
     *
     * @post getColumns = columns AND board = #board AND rows = #rows AND columns = #columns
     *      AND numToWin = #numToWin
     */
    public int getColumns();
}