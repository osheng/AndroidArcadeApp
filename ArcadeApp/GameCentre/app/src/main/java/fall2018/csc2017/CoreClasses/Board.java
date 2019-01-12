package fall2018.csc2017.CoreClasses;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/**
 * The sliding tiles board.
 */

public class Board implements Serializable, Iterable<Tile> {

    /**
     * The number of rows.
     */
    protected int numRows;

    /**
     * The number of rows.
     */
    protected int numCols;
    /**
     * The tiles on the board in row-major order.
     */
    public Tile[][] tiles;


    /**
     * Stores the list of Tile Iterator of this Board.
     */
    @NonNull
    public Iterator<Tile> iterator() {
        return new TileIterator();
    }

    /**
     * The tile iterator, iterates through the tiles on the board.
     */
    class TileIterator implements Iterator<Tile> {
        /**
         * The column of the next Tile.
         */
        private int nextColumn = 0;
        /**
         * The row of the next Tile.
         */
        private int nextRow = 0;

        /**
         * @return the next checkers tile on the board
         */
        @Override
        public Tile next() {
            Tile nextTile = tiles[nextRow][nextColumn];
            nextColumn++;
            if (nextColumn >= tiles[0].length) {
                nextColumn = 0;
                nextRow++;
            }
            return nextTile;
        }

        /**
         * @return true if and only if there is a next tile on the board
         */
        @Override
        public boolean hasNext() {
            return nextRow < tiles.length;
        }
    }

    /**
     * default board constructor.
     */
    protected Board() {
    }

    /**
     * A board constructor which may be reused
     */
    public Board(Tile[][] listOfTiles, int rows, int columns) {
        this.numRows = rows;
        this.numCols = columns;
        this.tiles = listOfTiles;
    }

    /**
     * Returns a deep-copy of this Board.
     */
    public Board createDeepCopy() {
        Tile[][] copyTile = new Tile[getNumRows()][getNumCols()];
        for (int row = 0; row != getNumRows(); row++) {
            for (int col = 0; col != getNumCols(); col++) {
                copyTile[row][col] = this.tiles[row][col];
            }
        }
        Board copiedBoard = new Board(copyTile, getNumRows(), getNumCols());
        copiedBoard.tiles = copyTile;
        return copiedBoard;
    }


    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    public int numTiles() {
        return numRows * numCols;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Returns the string representation of board.
     *
     * @return string of Board.
     */
    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    /**
     * Returns the number of rows in a board.
     *
     * @return rows in board.
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Sets number of rows in a board.
     *
     * @param newNumRows int
     */
    protected void setNumRows(int newNumRows) {
        this.numRows = newNumRows;
    }

    /**
     * Returns the number of columns in a board.
     *
     * @return columns in board.
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * Sets number of columns in a board.
     *
     * @param newNumCols int
     */
    protected void setNumCols(int newNumCols) {
        this.numCols = newNumCols;
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    public void swapTiles(int row1, int col1, int row2, int col2) {

        Tile[][] holdTile = new Tile[1][2];
        holdTile[0][0] = getTile(row1, col1);
        holdTile[0][1] = getTile(row2, col2);
        this.tiles[row2][col2] = holdTile[0][0];
        this.tiles[row1][col1] = holdTile[0][1];
    }

    /**
     * Replaces the tile at row, col with the given TwentyTile. This method is used when testing.
     */
    public void insertTile(int row, int col, Tile tile) {
        this.tiles[row][col] = tile;
    }

}