package Checkers;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class CheckersBoardManagerTest {

    private CheckersBoardManager testCheckersBoardManager;
    private CheckersBoardManager testCheckersBoardManager2;

    private final int size = 8;


    /**
     * Setup with a blank board
     */
    @Before
    public void setup() {
        testCheckersBoardManager = new CheckersBoardManager(size, true);
        CheckersBoard board;
        CheckersTile[][] tiles = new CheckersTile[size][size];
        String id;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if ((row + col) % 2 == 0) {
                    id = CheckersTile.BLACK_TILE;
                } else {
                    id = CheckersTile.EMPTY_WHITE_TILE;
                }
                tiles[row][col] = new CheckersTile(id);
            }
        }
        testCheckersBoardManager.board = new CheckersBoard(tiles, size);
        CheckersBoard checkersBoard = testCheckersBoardManager.getBoard();
        CheckersGameFile slidingGameFile = new CheckersGameFile(checkersBoard, "test");
        testCheckersBoardManager2 = new CheckersBoardManager(slidingGameFile);
    }

    @Test
    public void testIsValidSelect() {
        assertFalse(testCheckersBoardManager.isValidSelect(1));

        CheckersBoardTest.addPiece(
                testCheckersBoardManager.board, 0, 1, CheckersTile.RED_KING);
        assertTrue(testCheckersBoardManager.isValidSelect(1));

        CheckersBoardTest.addPiece(
                testCheckersBoardManager.board, 0, 3, CheckersTile.WHITE_PAWN);
        assertFalse(testCheckersBoardManager.isValidSelect(3));
    }

    /**
     * Confirm that a tile can jump an enemy piece
     */
    @Test
    public void testIsValidMoveCanJumpEnemy() {
        CheckersBoardTest.addPiece(
                testCheckersBoardManager.board, 1, 2, CheckersTile.WHITE_PAWN);
        CheckersBoardTest.addPiece(
                testCheckersBoardManager.board, 2, 3, CheckersTile.RED_PAWN);
        testCheckersBoardManager.isValidSelect(2 * size + 3);
        assertTrue(testCheckersBoardManager.isValidMove(1));
        CheckersBoardTest.addPiece(
                testCheckersBoardManager.board, 1, 2, CheckersTile.WHITE_PAWN);
        CheckersBoardTest.addPiece(
                testCheckersBoardManager.board, 2, 3, CheckersTile.RED_PAWN);
        testCheckersBoardManager.swapRedsTurn();
        testCheckersBoardManager.isValidSelect(1 * size + 2);
        assertTrue(testCheckersBoardManager.isValidMove(3 * size + 4));
    }

    @Test
    public void testIsValidMoveCantJumpFriend() {
        CheckersBoardTest.addPiece(
                testCheckersBoardManager.board, 1, 2, CheckersTile.RED_PAWN);
        CheckersBoardTest.addPiece(
                testCheckersBoardManager.board, 2, 3, CheckersTile.RED_PAWN);
        testCheckersBoardManager.isValidSelect(2 * size + 3);
        assertFalse(testCheckersBoardManager.isValidMove(1));
    }

    @Test
    public void testIsValidMovePawnSteps() {
        CheckersBoardTest.addPiece(
                testCheckersBoardManager.board, 1, 2, CheckersTile.RED_PAWN);
        CheckersBoardTest.addPiece(
                testCheckersBoardManager.board, 0, 3, CheckersTile.WHITE_PAWN);
        testCheckersBoardManager.isValidSelect(size + 2);
        assertFalse(testCheckersBoardManager.isValidMove(2 * size + 1));
        assertTrue(testCheckersBoardManager.isValidMove(1));

        testCheckersBoardManager.swapRedsTurn();
        testCheckersBoardManager.isValidSelect(3);
        assertFalse(testCheckersBoardManager.isValidMove(4));
        assertTrue(testCheckersBoardManager.isValidMove(size + 4));
    }

    @Test
    public void testTouchMoveSlayingEnemy() {
        CheckersBoardTest.addPiece(
                testCheckersBoardManager.board, 4, 5, CheckersTile.RED_PAWN);
        CheckersBoardTest.addPiece(
                testCheckersBoardManager.board, 3, 4, CheckersTile.WHITE_PAWN);
        CheckersBoardTest.addPiece(
                testCheckersBoardManager.board, 1, 2, CheckersTile.WHITE_PAWN);
        testCheckersBoardManager.isValidSelect(size * 4 + 5);
        testCheckersBoardManager.touchMove(size * 2 + 3);
        assertTrue(testCheckersBoardManager.board.getCheckersTile(
                4, 5).getCheckersId().equals(CheckersTile.EMPTY_WHITE_TILE));
        assertTrue(testCheckersBoardManager.board.getCheckersTile(
                3, 4).getCheckersId().equals(CheckersTile.EMPTY_WHITE_TILE));
        assertTrue(testCheckersBoardManager.isHasSlain());
        testCheckersBoardManager.touchMove(1);
        assertTrue(testCheckersBoardManager.board.getCheckersTile(
                2, 3).getCheckersId().equals(CheckersTile.EMPTY_WHITE_TILE));
        assertTrue(testCheckersBoardManager.board.getCheckersTile(
                1, 2).getCheckersId().equals(CheckersTile.EMPTY_WHITE_TILE));
    }

    @Test
    public void testTouchMoveStepping() {
        CheckersBoardTest.addPiece(
                testCheckersBoardManager.board, 1, 2, CheckersTile.RED_PAWN);
        testCheckersBoardManager.isValidSelect(size + 2);
        testCheckersBoardManager.touchMove(1);
        assertTrue(testCheckersBoardManager.board.getCheckersTile(0, 1).getCheckersId().equals(CheckersTile.RED_KING));
        assertFalse(testCheckersBoardManager.isRedsTurn());
        testCheckersBoardManager.swapRedsTurn();
        testCheckersBoardManager.isValidSelect(1);
        assertFalse(testCheckersBoardManager.isValidMove(0));
        assertFalse(testCheckersBoardManager.isValidMove(22));
        assertFalse(testCheckersBoardManager.isValidMove(size + 1));
        assertTrue(testCheckersBoardManager.isValidMove(size));
    }

    @Test
    public void testGameComplete() {
        CheckersBoardTest.addPiece(testCheckersBoardManager.board, 0, 1, CheckersTile.WHITE_PAWN);
        assertTrue(testCheckersBoardManager.gameComplete());
        assertEquals(1, testCheckersBoardManager.score());
        CheckersBoardTest.addPiece(testCheckersBoardManager.board, 1, 2, CheckersTile.RED_PAWN);
        assertFalse(testCheckersBoardManager.gameComplete());
        CheckersBoardTest.addPiece(testCheckersBoardManager.board, 0, 1, CheckersTile.BLACK_TILE);
        assertTrue(testCheckersBoardManager.gameComplete());
    }


    @Test
    public void testUndo() {
        CheckersBoardTest.addPiece(testCheckersBoardManager2.board, size-1, 0, CheckersTile.RED_PAWN);
        testCheckersBoardManager2.setMaxUndos(1);
        testCheckersBoardManager2.isValidSelect((size-1)*size);
        testCheckersBoardManager2.touchMove((size-2)*size+1);
        testCheckersBoardManager2.isValidSelect((size-2)*size+1);
        testCheckersBoardManager2.touchMove((size-3)*size);
        testCheckersBoardManager2.isValidSelect((size-3)*size);
        testCheckersBoardManager2.undo();
    }

}