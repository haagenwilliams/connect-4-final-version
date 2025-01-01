package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard {

    private int dimensions;

    /**
     * Provides a string representation of the game board.
     *
     * @return a string representation of the current state of the game board
     *
     * @post toString = the current game board as a string, showing all tokens placed
     *
     * @pre none
     */
    @Override
    public String toString() {

        StringBuilder string = new StringBuilder();

        for (int r = 0; r < getRows(); ++r) {
            string.append("|");
            for (int c = 0; c < getColumns(); ++c) {
                if (getColumns() > 9) {
                    string.append(whatsAtPos(new BoardPosition(r, c))).append(" |");
                }
                else {
                    string.append(whatsAtPos(new BoardPosition(r, c))).append("|");
                }
            }
            string.append("\n");
        }

        return string.toString();
    }
}
