package Sliding;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import fall2018.csc2017.CoreClasses.GameFile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class SlidingBoardManagerTest {

    /**
     * Sliding board manager for test.
     */
    private SlidingBoardManager slidingBoardManager;

    /**
     * Sliding board manager generated from gamefile for test.
     */
    private SlidingBoardManager slidingBoardManager2;

    /**
     * Set up initalizes a context path, activate account and Sliding game name
     * before instantiating a board manager and board for test.
     */
    @Before
    public void setup() {
        slidingBoardManager = new SlidingBoardManager(3);
        SlidingBoard slidingBoard = slidingBoardManager.getBoard();
        makeBoard(slidingBoard);
        SlidingGameFile slidingGameFile = new SlidingGameFile(slidingBoard, "test");
        slidingBoardManager2 = new SlidingBoardManager(slidingGameFile);
        slidingBoardManager2.setMaxUndos(3);
    }

    /**
     * Populates board with tiles.
     */
    private void makeBoard(SlidingBoard testBoard) {
        int numIt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                SlidingTile tile = new SlidingTile(numIt, 9);
                testBoard.insertTile(i, j, tile);
                numIt++;
            }
        }
    }

    /**
     * Tests that Gamefile is not empty.
     */
    @Test
    public void testGetGameFile() {
        GameFile gameFile = slidingBoardManager.getGameFile();
        assertNotNull("slidingBoardManager.getGameFile() failed test 1.", gameFile);
    }

    /**
     * Test if gameComplete returns true when tiles are organized in order.
     */
    @Test
    public void testGameComplete() {
        assertTrue("slidingBoardManager.puzzleSolved() failed test 1.", slidingBoardManager.gameComplete());
        //Since no moves made, max score is 4,096.
        assertEquals("slidingBoardManager.score() failed test 2.", 4096, slidingBoardManager.score());
        slidingBoardManager.touchMove(7);
        assertFalse("slidingBoardManager.puzzleSolved() failed test 3.", slidingBoardManager.gameComplete());
        assertEquals("slidingBoardManager.score() failed test 4.", 0, slidingBoardManager.score());
        slidingBoardManager.touchMove(8);
    }

    /**
     * Tests to see if valid move can be made at various positions.
     */
    @Test
    public void testIsValidMove() {
        //  Test if a valid move can be made at various position
        assertTrue("slidingBoardManager.isValidTap() failed test 1.", slidingBoardManager.isValidMove(7));
        assertTrue("slidingBoardManager.isValidTap() failed test 2.", slidingBoardManager.isValidMove(5));
        assertFalse("slidingBoardManager.isValidTap() failed test 3.", slidingBoardManager.isValidMove(2));
    }

    /**
     * Tests touchMove, which checks if position selected is
     * adjacent to the blank tile.
     */
    @Test
    public void testTouchMove() {
        slidingBoardManager.touchMove(7);
        assertEquals("slidingBoardManager.isValidTap() failed test 1.", 9, slidingBoardManager.getBoard().getTile(2, 1).getId());
        assertEquals("slidingBoardManager.isValidTap() failed test 2.", 8, slidingBoardManager.getBoard().getTile(2, 2).getId());
        slidingBoardManager.touchMove(4);
        assertEquals("slidingBoardManager.isValidTap() failed test 3.", 5, slidingBoardManager.getBoard().getTile(2, 1).getId());
        assertEquals("slidingBoardManager.isValidTap() failed test 4.", 9, slidingBoardManager.getBoard().getTile(1, 1).getId());
        slidingBoardManager.touchMove(7);
        assertEquals("slidingBoardManager.isValidTap() failed test 5.", 9, slidingBoardManager.getBoard().getTile(2, 1).getId());
        assertEquals("slidingBoardManager.isValidTap() failed test 6.", 8, slidingBoardManager.getBoard().getTile(1, 1).getId());

    }

    /**
     * Test setMaxUndo method, which sets max undos for the board manager.
     */
    @Test
    public void testSetMaxUndo() {
        // Tests whether set up set max undo was set to 3.
        assertEquals(3, slidingBoardManager2.getGameFile().getMaxUndos());
    }

    /**
     * Test undo method
     */
    @Test
    public void testUndo() {
        // Make 2 moves, undo moves and compare board memory addresses.
        SlidingBoard newBoard = slidingBoardManager2.getBoard();
        slidingBoardManager2.touchMove(7);
        slidingBoardManager2.getGameFile().addUndos();
        slidingBoardManager2.touchMove(5);
        slidingBoardManager2.getGameFile().addUndos();
        SlidingBoard test2 = slidingBoardManager2.getBoard();
        slidingBoardManager2.undo();
        slidingBoardManager2.undo();
        SlidingBoard test3 = slidingBoardManager2.getBoard();

        //Case 1 - Initial board should be the same as board after making a move and undoing it.
        assertSame(newBoard, test3);
        //Case 2 - After making two moves, current board should not equal board after undos.
        assertNotSame(newBoard, test2);

    }
}
