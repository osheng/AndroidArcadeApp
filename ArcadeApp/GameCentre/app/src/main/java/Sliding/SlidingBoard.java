package Sliding;

import fall2018.csc2017.CoreClasses.Board;
import fall2018.csc2017.CoreClasses.Tile;

/**
 * The sliding tiles board.
 */
public class SlidingBoard extends Board {

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */

    SlidingBoard(Tile[][] tiles, int rows, int cols) {
        super(tiles, rows, cols);
    }

    /**
     * Returns a deep-copy of this Board.
     */
    @Override
    public SlidingBoard createDeepCopy() {
        Tile[][] tiles = super.createDeepCopy().tiles;
        return new SlidingBoard(tiles, getNumRows(), getNumCols());
    }
}