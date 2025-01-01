/*
George Jubenvill - gjubenv
Jake Barz - uppishdonkey
Haagen Williams - haagenwilliams
 */
package cpsc2150.extendedConnectX.models;

public class BoardPosition {
    private int Row;
    private int Column;

    /**
     * A parameterized constructor for BoardPosition, accepts 2 params.
     * @param aRow the value to be set for Row.
     * @param aColumn the value to be set for Column
     *
     * @pre aRow > 0 AND aRow <= MAX_ROW aColumn > 0 AND aColumn <= MAX_COL
     *
     * @post Row = aRow AND Column = aColumn
     */
    public BoardPosition(int aRow, int aColumn) {

        Row = aRow;
        Column = aColumn;
    }

    /**
     * Standard getter for Row.
     *
     * @return Row, an int
     *
     * @pre none
     *
     * @post getRow = Row AND Row = #Row AND Column = #Column
     */
    public int getRow() {

        return Row;
    }

    /**
     * Standard getter for Column.
     *
     * @return Column, an int
     *
     * @pre none
     *
     * @post getColumn = Column AND Row = #Row AND Column = #Column
     */
    public int getColumn() {

        return Column;
    }

    /**
     * Override of equals() method that checks if a BoardPosition object has the same Row and Column as this BoardPosition, accepts 1 param.
     * @param obj, a BoardPosition object
     *
     * @return [A boolean for if obj is equal to this BoardPosition]
     *
     * @pre [obj is a BoardPosition object]
     *
     * @post equals = true IFF Row = obj.Row AND Column = obj.Column, false OW, AND Row = #Row AND Column = #Column
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BoardPosition pos)) {
            return false;
        }

        return this.getRow() == pos.getRow() && this.getColumn() == pos.getColumn();
    }

    /**
     * Override of the default toString() method to create a formatted string.
     *
     * @return [A formatted string containing Row and Column]
     *
     * @pre none
     *
     * @post toString = "<Row>,<Column>" AND Row = #Row AND Column = #Column
     */
    @Override
    public String toString() {

        return Row + "," + Column;
    }
}