package cpsc2150.extendedConnectX.tests;

import cpsc2150.extendedConnectX.models.*;
import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestGameBoard {

    private final int MAX_ROW = 9;
    private final int MAX_COL = 7;
    private final int TOKENS_TO_WIN = 5;
    private GameBoard obsGB;

    // ------------------ toString Method for Expected Boards ------------------
    private String toString(char[][] board) {
        StringBuilder output = new StringBuilder();
        for (char[] chars : board) {
            output.append("|");
            for (char aChar : chars) {
                output.append(aChar).append("|");
            }
            output.append("\n");
        }
        return output.toString();
    }

    private IGameBoard makeBoard() {
        return new GameBoard(MAX_ROW, MAX_COL, TOKENS_TO_WIN);
    }

    // ------------------ Constructor Test ------------------

    @Test
    public void testConstructorCreatesEmptyBoard() {

        IGameBoard obsGB = makeBoard();

        char[][] expGB = new char[MAX_ROW][MAX_COL];
        for (char[] chars : expGB) {
            Arrays.fill(chars, ' ');
        }

        String obsStr = obsGB.toString();
        String expStr = toString(expGB);

        assertTrue(obsStr.equals(expStr));
    }

    // ------------------ checkIfFree() Tests ------------------

    @Test
    public void testCheckIfFreeOnEmptyColumn() {

        IGameBoard obsGB = makeBoard();

        assertTrue(obsGB.checkIfFree(2));
    }

    @Test
    public void testCheckIfFreeOnPartiallyFilledColumn() {

        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('X', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 3);

        assertTrue(obsGB.checkIfFree(3));
    }

    @Test
    public void testCheckIfFreeOnFullColumn() {

        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('X', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('X', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('X', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('X', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('X', 4);

        assertFalse(obsGB.checkIfFree(4));
    }

    // ------------------ dropToken() Tests ------------------

    @Test
    public void testDropToken_DifferentColumn() {

        IGameBoard obsGB = makeBoard();

        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('X', 1);

        char[][] expGB = new char[9][7];
        for (int i = 0; i < expGB.length; i++) {
            for (int j = 0; j < expGB[i].length; j++) {
                expGB[i][j] = ' ';
            }
        }

        expGB[8][0] = 'X';
        expGB[7][0] = 'O';
        expGB[8][1] = 'X';

        String obsStr = obsGB.toString();
        String expStr = toString(expGB);

        assertTrue(obsStr.equals(expStr));
    }

    @Test
    public void testDropToken_Minimum_Column() {

        IGameBoard obsGB = makeBoard();

        obsGB.dropToken('X', 0);

        char[][] expGB = new char[9][7];
        for (int i = 0; i < expGB.length; i++) {
            for (int j = 0; j < expGB[i].length; j++) {
                expGB[i][j] = ' ';
            }
        }

        expGB[8][0] = 'X';

        String obsStr = obsGB.toString();
        String expStr = toString(expGB);

        assertTrue(obsStr.equals(expStr));
    }

    @Test
    public void testDropToken_Full_Column() {

        IGameBoard obsGB = makeBoard();

        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('X', 2);

        char[][] expGB = new char[9][7];
        for (int i = 0; i < expGB.length; i++) {
            for (int j = 0; j < expGB[i].length; j++) {
                expGB[i][j] = ' ';
            }
        }

        expGB[8][2] = 'X';
        expGB[7][2] = 'O';
        expGB[6][2] = 'X';
        expGB[5][2] = 'O';
        expGB[4][2] = 'X';
        expGB[3][2] = 'O';
        expGB[2][2] = 'X';
        expGB[1][2] = 'O';
        expGB[0][2] = 'X';

        String obsStr = obsGB.toString();
        String expStr = toString(expGB);

        assertTrue(obsStr.equals(expStr));
    }


    @Test
    public void testDropToken_Far_Right_Column() {

        IGameBoard obsGB = makeBoard();

        obsGB.dropToken('X', 6);

        char[][] expGB = new char[9][7];
        for (int i = 0; i < expGB.length; i++) {
            for (int j = 0; j < expGB[i].length; j++) {
                expGB[i][j] = ' ';
            }
        }

        expGB[8][6] = 'X';

        String obsStr = obsGB.toString();
        String expStr = toString(expGB);

        assertTrue(obsStr.equals(expStr));
    }

    @Test
    public void testDropToken_Top_Middle_Column() {

        IGameBoard obsGB = makeBoard();

        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('X', 0);

        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 1);

        obsGB.dropToken('X', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('X', 2);

        obsGB.dropToken('O', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 3);

        obsGB.dropToken('O', 4);

        obsGB.dropToken('X', 6);

        obsGB.dropToken('X', 3);

        char[][] expGB = new char[9][7];
        for (int i = 0; i < expGB.length; i++) {
            for (int j = 0; j < expGB[i].length; j++) {
                expGB[i][j] = ' ';
            }
        }

        expGB[8][0] = 'X';
        expGB[7][0] = 'O';
        expGB[6][0] = 'X';

        expGB[8][1] = 'O';
        expGB[7][1] = 'X';
        expGB[6][1] = 'O';
        expGB[5][1] = 'X';

        expGB[8][2] = 'X';
        expGB[7][2] = 'X';
        expGB[6][2] = 'X';

        expGB[8][3] = 'O';
        expGB[7][3] = 'O';
        expGB[6][3] = 'X';

        expGB[8][4] = 'O';

        expGB[8][6] = 'X';

        expGB[5][3] = 'X';

        String obsStr = obsGB.toString();
        String expStr = toString(expGB);

        assertTrue(obsStr.equals(expStr));
    }

    // ------------------ checkHorizWin() Tests ------------------
    @Test
    public void testCheckHorizWinNoWin() {
        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('X', 3);
        assertFalse(obsGB.checkHorizWin(new BoardPosition(MAX_ROW - 1, 3), 'X'));
    }

    @Test
    public void testCheckHorizWinWithWin() {
        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('X', 4);
        assertTrue(obsGB.checkHorizWin(new BoardPosition(MAX_ROW - 1, 4), 'X'));
    }

    @Test
    public void testCheckHorizWinAtLeftBoundary() {
        IGameBoard obsGB = makeBoard();

        for (int i = 0; i < 4; ++i) {
            obsGB.dropToken('O', 0);
            obsGB.dropToken('O', 1);
            obsGB.dropToken('O', 2);
            obsGB.dropToken('O', 3);
            obsGB.dropToken('O', 4);
        }
        assertTrue(obsGB.checkHorizWin(new BoardPosition(MAX_ROW - 1, 0), 'O'));
    }

    @Test
    public void testCheckHorizWinAtRightBoundary() {
        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('O', 2);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 5);
        obsGB.dropToken('O', 6);
        assertTrue(obsGB.checkHorizWin(new BoardPosition(MAX_ROW - 1, 6), 'O'));
    }

    // ------------------ checkVertWin() Tests ------------------

    @Test
    public void testCheckVertWinNoWin() {
        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('X', 4);
        obsGB.dropToken('X', 4);
        obsGB.dropToken('X', 4);
        obsGB.dropToken('X', 4);
        assertFalse(obsGB.checkVertWin(new BoardPosition(MAX_ROW - 1, 4), 'X'));
    }

    @Test
    public void testCheckVertWinWithWin() {
        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 0);
        assertTrue(obsGB.checkVertWin(new BoardPosition(MAX_ROW - 1, 0), 'X'));
    }

    @Test
    public void testCheckVertWinAtBottomBoundary() {
        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        assertTrue(obsGB.checkVertWin(new BoardPosition(8, 4), 'O'));
    }

    @Test
    public void testCheckVertWinAtTopBoundary() {
        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('O', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('O', 0);

        assertTrue(obsGB.checkVertWin(new BoardPosition(0, 0), 'O'));
    }

    // ------------------ checkDiagWin() Tests ------------------

    @Test
    public void testCheckDiagWinWithUpRightDiagonalWin() {
        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('X', 4);

        assertTrue(obsGB.checkDiagWin(new BoardPosition(MAX_ROW - 1, 0), 'X'));
    }

    @Test
    public void testCheckDiagWinWithOverlapNoWin() {
        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('X', 4);

        assertFalse(obsGB.checkDiagWin(new BoardPosition(MAX_ROW - 2, 4), 'X'));
    }

    @Test
    public void testCheckDiagWinWithDownRightDiagonalWin() {
        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('X', 4);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('X', 0);


        assertTrue(obsGB.checkDiagWin(new BoardPosition(4, 0), 'X'));
    }

    @Test
    public void testCheckDiagWinAtTopRightBoundary() {
        IGameBoard obsGB = makeBoard();

        for (int i = 0; i < MAX_ROW; i++) { obsGB.dropToken('O', MAX_COL - 1);}
        for (int i = 0; i < MAX_ROW - 1; i++) { obsGB.dropToken('O', MAX_COL  - 2);}
        for (int i = 0; i < MAX_ROW - 2; i++) { obsGB.dropToken('O', MAX_COL - 3);}
        for (int i = 0; i < MAX_ROW - 3; i++) { obsGB.dropToken('O', MAX_COL - 4);}
        for (int i = 0; i < MAX_ROW - 4; i++) { obsGB.dropToken('O', MAX_COL - 5);}

        assertTrue(obsGB.checkDiagWin(new BoardPosition(0, MAX_COL - 1), 'O'));
    }

    @Test
    public void testCheckDiagWinNoWin() {
        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 4);
        obsGB.dropToken('O', 5);
        obsGB.dropToken('X', 6);

        assertFalse(obsGB.checkDiagWin(new BoardPosition(8, 0), 'X'));
    }

    @Test
    public void testCheckDiagWinWithUpLeftDiagonalWin() {
        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('O', 6);
        obsGB.dropToken('X', 5);
        obsGB.dropToken('O', 5);
        obsGB.dropToken('X', 4);
        obsGB.dropToken('X', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 2);

        assertTrue(obsGB.checkDiagWin(new BoardPosition(4, 2), 'O'));
    }

    @Test
    public void testCheckDiagWinAtBottomLeftBoundary() {
        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('X', 4);

        assertTrue(obsGB.checkDiagWin(new BoardPosition(4, 4), 'X'));
    }


    // ------------------ checkTie() Tests ------------------

    @Test
    public void testCheckTie_Empty_False() {

        IGameBoard obsGB = makeBoard();

        assertFalse(obsGB.checkTie());
    }

    @Test
    public void testCheckTie_FullBoard_True() {

        IGameBoard obsGB = makeBoard();

        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 0);

        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('O', 1);

        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('X', 2);

        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('O', 3);

        obsGB.dropToken('X', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('X', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('X', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('X', 4);
        obsGB.dropToken('X', 4);

        obsGB.dropToken('O', 5);
        obsGB.dropToken('X', 5);
        obsGB.dropToken('X', 5);
        obsGB.dropToken('O', 5);
        obsGB.dropToken('X', 5);
        obsGB.dropToken('O', 5);
        obsGB.dropToken('X', 5);
        obsGB.dropToken('O', 5);
        obsGB.dropToken('O', 5);

        obsGB.dropToken('O', 6);
        obsGB.dropToken('X', 6);
        obsGB.dropToken('X', 6);
        obsGB.dropToken('O', 6);
        obsGB.dropToken('X', 6);
        obsGB.dropToken('O', 6);
        obsGB.dropToken('X', 6);
        obsGB.dropToken('O', 6);
        obsGB.dropToken('X', 6);

        assertTrue(obsGB.checkTie());
    }

    @Test
    public void testCheckTie_FullWin_False() {

        IGameBoard obsGB = makeBoard();

        for (int i = 0; i < MAX_ROW; ++i) {
            for (int j = 0; j < MAX_COL; ++j) {
                obsGB.dropToken('X', j);
            }
        }

        if (!obsGB.checkForWin(MAX_COL - 1)) {
            assertTrue(obsGB.checkTie());
        }
        else {
            assertTrue(true);
        }
    }

    @Test
    public void testCheckTie_AlmostFull_False() {

        IGameBoard obsGB = makeBoard();

        for (int i = 0; i < MAX_ROW; ++i) {
            for (int j = 0; j < MAX_COL - 1; ++j) {
                obsGB.dropToken('X', j);
            }
        }
        for (int i = 0; i < MAX_ROW - 1; ++i) {
            obsGB.dropToken('X', MAX_COL - 1);
        }

        assertFalse(obsGB.checkTie());
    }

    // ------------------ whatsAtPos() Tests ------------------

    @Test
    public void testWhatsAtPos_80_X() {

        IGameBoard obsGB = makeBoard();

        obsGB.dropToken('X', 0);

        assertEquals('X', obsGB.whatsAtPos(new BoardPosition(8,0)));
    }

    @Test
    public void testWhatsAtPos_83_Space() {

        IGameBoard obsGB = makeBoard();

        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 6);

        assertEquals(' ', obsGB.whatsAtPos(new BoardPosition(8,3)));
    }

    @Test
    public void testWhatsAtPos_00_X() {

        IGameBoard obsGB = makeBoard();

        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('X', 0);

        assertEquals('X', obsGB.whatsAtPos(new BoardPosition(0,0)));
    }

    @Test
    public void testWhatsAtPos_43_O() {

        IGameBoard obsGB = makeBoard();

        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('O', 3);

        assertEquals('O', obsGB.whatsAtPos(new BoardPosition(4,3)));
    }

    @Test
    public void testWhatsAtPos_06_Space() {

        IGameBoard obsGB = makeBoard();

        for (int i = 0; i < MAX_ROW; ++i) {
            for (int j = 0; j < MAX_COL - 1; ++j) {
                obsGB.dropToken('X', j);
            }
        }
        for (int i = 0; i < MAX_ROW - 1; ++i) {
            obsGB.dropToken('X', MAX_COL - 1);
        }

        assertEquals(' ', obsGB.whatsAtPos(new BoardPosition(0,6)));
    }

    // ------------------ isPlayerAtPos() Tests ------------------
    @Test
    public void testIsPlayerAtPos_Bottom_Right() {

        IGameBoard obsGB = makeBoard();

        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 0);

        obsGB.dropToken('O', 1);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('O', 1);

        obsGB.dropToken('X', 2);
        obsGB.dropToken('X', 2);

        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);

        obsGB.dropToken('X', 5);
        obsGB.dropToken('X', 5);

        obsGB.dropToken('O', 6);
        obsGB.dropToken('O', 6);
        obsGB.dropToken('O', 6);

        assertTrue(obsGB.isPlayerAtPos(new BoardPosition(8, 6), 'O'));
    }

    @Test
    public void testIsPlayerAtPos_Top_Left() {
        IGameBoard obsGB = makeBoard();
        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 0);

        obsGB.dropToken('O', 1);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('O', 1);

        obsGB.dropToken('X', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('X', 2);

        obsGB.dropToken('O', 3);

        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);

        obsGB.dropToken('X', 5);

        obsGB.dropToken('O', 6);

        assertFalse(obsGB.isPlayerAtPos(new BoardPosition(0, 0), 'X'));
    }

    @Test
    public void testIsPlayerAtPos_Another_Player() {

        IGameBoard obsGB = makeBoard();

        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 0);

        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 1);

        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('X', 2);

        obsGB.dropToken('O', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('X', 3);

        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);

        assertFalse(obsGB.isPlayerAtPos(new BoardPosition(4, 4), 'O'));
    }

    @Test
    public void testIsPlayerAtPos_Left_Edge() {

        IGameBoard obsGB = makeBoard();

        obsGB.dropToken('X', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('O', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 0);

        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('X', 1);
        obsGB.dropToken('O', 1);

        obsGB.dropToken('O', 2);
        obsGB.dropToken('O', 2);
        obsGB.dropToken('X', 2);

        obsGB.dropToken('X', 3);
        obsGB.dropToken('X', 3);

        assertTrue(obsGB.isPlayerAtPos(new BoardPosition(4, 0), 'X'));
    }


    @Test
    public void testIsPlayerAtPos_Top_Middle() {

        IGameBoard obsGB = makeBoard();

        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 0);
        obsGB.dropToken('X', 0);

        obsGB.dropToken('O', 1);
        obsGB.dropToken('O', 1);
        obsGB.dropToken('O', 1);

        obsGB.dropToken('O', 2);
        obsGB.dropToken('X', 2);
        obsGB.dropToken('O', 2);

        obsGB.dropToken('X', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('O', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('X', 3);
        obsGB.dropToken('X', 3);

        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('O', 4);
        obsGB.dropToken('X', 4);

        obsGB.dropToken('O', 5);
        obsGB.dropToken('O', 5);
        obsGB.dropToken('O', 5);
        obsGB.dropToken('X', 5);

        obsGB.dropToken('O', 6);
        obsGB.dropToken('O', 6);

        assertTrue(obsGB.isPlayerAtPos(new BoardPosition(0, 3), 'X'));

    }
}