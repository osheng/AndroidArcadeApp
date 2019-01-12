package Sliding;


import org.junit.Test;

import fall2018.csc2017.CoreClasses.Tile;

import static org.junit.Assert.*;

public class SlidingBoardTest {

    /**
     * size of board
     */
    private int size = 4;

    /**
     * Make a set of tiles that are in order.
     *
     * @return a set of tiles that are in order
     */
    private Tile[][] makeTiles() {
        final int numTiles = size * size;
        Tile[][] newTile = new Tile[size][size];
        int tileNum = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                newTile[row][col] = new SlidingTile(tileNum, numTiles);
                tileNum++;
            }
        }
        return newTile;
    }

    /**
     * The sliding board for testing.
     */
    private SlidingBoard slidingBoard;

    /**
     * Set up a correct board.
     */
    private void setUpCorrect() {
        Tile[][] tiles = makeTiles();
        slidingBoard = new SlidingBoard(tiles, size, size);
    }

    /**
     * Test whether swapping the first two tiles works.
     */
    @Test
    public void testSwapFirstTwo() {
        setUpCorrect();
        assertEquals(1, slidingBoard.getTile(0, 0).getId());
        assertEquals(2, slidingBoard.getTile(0, 1).getId());
        slidingBoard.swapTiles(0, 0, 0, 1);
        assertEquals(2, slidingBoard.getTile(0, 0).getId());
        assertEquals(1, slidingBoard.getTile(0, 1).getId());
    }

    /**
     * Test whether swapping the last two tiles works.
     */
    @Test
    public void testSwapLastTwo() {
        setUpCorrect();
        assertEquals(15, slidingBoard.getTile(3, 2).getId());
        assertEquals(16, slidingBoard.getTile(3, 3).getId());
        slidingBoard.swapTiles(3, 3, 3, 2);
        assertEquals(16, slidingBoard.getTile(3, 2).getId());
        assertEquals(15, slidingBoard.getTile(3, 3).getId());
    }

    /**
     * Test whether deepcopy returns a new board.
     */
    @Test
    public void testCreateDeepCopy() {
        setUpCorrect();
        SlidingBoard newBoard2 = slidingBoard.createDeepCopy();
        assertNotSame(slidingBoard, newBoard2);
    }

    /**
     * Test numTiles method returns the correct number of tiles.
     */
    @Test
    public void testNumTiles() {
        setUpCorrect();
        int sumTiles = slidingBoard.numTiles();
        assertEquals(16, sumTiles);
    }

}