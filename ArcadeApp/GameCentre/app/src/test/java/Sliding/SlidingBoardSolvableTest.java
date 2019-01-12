package Sliding;

import org.junit.Test;

import fall2018.csc2017.CoreClasses.Tile;

import static org.junit.Assert.*;

/**
 * Test class for SlidingBoardSolvable.
 */
public class SlidingBoardSolvableTest {

    /**
     * returns a 2D tile array based on input size.
     *
     * @param size int
     * @return 2D array of tiles.
     */
    private Tile[][] makeTiles(int size) {
        Tile[][] tiles = new Tile[size][size];
        final int numTiles = size * size;
        int tileNumber = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                tiles[row][col] = new SlidingTile(tileNumber, numTiles);
                tileNumber++;
            }
        }
        return tiles;
    }

    /**
     * Swaps tiles in 2D tile array.
     */
    private void swapTiles(int row1, int col1, int row2, int col2, Tile[][] tiles) {
        SlidingTile temp = (SlidingTile) tiles[row1][col1];
        SlidingTile temp2 = (SlidingTile) tiles[row2][col2];
        tiles[row2][col2] = temp;
        tiles[row1][col1] = temp2;
    }


    /**
     * Tests isBoardSolvable method in SlidingBoardSolvable under 3 cases.
     */
    @Test
    public void isBoardSolvableTest() {
        // Case 1 - odd board size checks if the number of inversions are even.
        Tile[][] newTiles = makeTiles(5);
        swapTiles(2, 3, 4, 4, newTiles);
        SlidingBoardSolvable newSolver = new SlidingBoardSolvable(newTiles);
        assertTrue(newSolver.isBoardSolvable());

        //Case 2 - even board size with blank tile on even row from bottom of
        //tile array and an even number of inversions.
        Tile[][] newTiles2 = makeTiles(4);
        swapTiles(2, 2, 3, 3, newTiles2);
        SlidingBoardSolvable newSolver2 = new SlidingBoardSolvable(newTiles2);
        assertTrue(newSolver2.isBoardSolvable());

        //Case 3 - even board with blank tile on odd row from bottom of
        //tile array and an even number of inversions.
        Tile[][] newTiles3 = makeTiles(4);
        swapTiles(1, 2, 3, 3, newTiles3);
        SlidingBoardSolvable newSolver3 = new SlidingBoardSolvable(newTiles3);
        assertFalse(newSolver3.isBoardSolvable());


    }
}