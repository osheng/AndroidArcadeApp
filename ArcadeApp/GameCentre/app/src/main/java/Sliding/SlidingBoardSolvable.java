package Sliding;


import java.util.ArrayList;
import java.util.Arrays;


import fall2018.csc2017.CoreClasses.Tile;

/**
 * SlidingBoardSolvable checks a SlidingBoard to determine if it is solvable.
 * <p>
 * Solvability of a sliding tiles board based on
 * https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
 */
public class SlidingBoardSolvable {

    /**
     * size of the board.
     */
    private int boardSize;

    /**
     * The number of inversions in the board.
     */
    private int inversions;

    /**
     * 2D array of tiles.
     */
    private Tile[][] tiles;


    /**
     * List of Sliding tiles.
     */
    private ArrayList<Tile> tileList;

    /**
     * Position of blank tile ArrayList of Sliding tiles.
     */
    private int position;

    /**
     * Constructor for SlidingBoardSolvable
     *
     * @param TilesToBeChecked is a SlidingBoard.
     */
    SlidingBoardSolvable(Tile[][] TilesToBeChecked) {
        this.boardSize = TilesToBeChecked.length;
        this.inversions = 0;
        this.tiles = TilesToBeChecked;
        this.position = 0;
        this.tileList = convertArray(this.tiles);
    }

    private ArrayList<Tile> convertArray(Tile[][] tiles) {
        ArrayList<Tile> tilesList = new ArrayList<>();
        for (int row = 0; row < boardSize; row++) {
            tilesList.addAll(Arrays.asList(tiles[row]).subList(0, boardSize));
        }
        return tilesList;
    }

    /**
     * Calculates the number of inversions of board.
     * An inversion is when a tile precedes another
     * tile with a lower number on it assuming the tiles are arranged
     * in a row.
     */
    private void calculateInversions() {
        for (int i = 0; i < tileList.size() - 1; i++) {

            for (int j = i + 1; j < tileList.size(); j++) {
                if (tileList.get(i).getId() > tileList.get(j).getId()
                        && tileList.get(i).getId() != tileList.size()
                        && tileList.get(j).getId() != tileList.size()) {
                    inversions++;
                }
            }
        }
    }

    /**
     * Returns true if the number of inversions of board are even.
     *
     * @return boolean true or false.
     */
    private boolean areInversionsEven() {
        calculateInversions();
        return (inversions % 2 == 0);
    }

    /**
     * Returns true if Board contains an even number oftiles.
     *
     * @return boolean true or false.
     */
    private boolean isBoardSizeEven() {
        return (boardSize % 2 == 0);
    }

    /**
     * calculates position of Blank tile in board.
     */
    private void positionOfBlankTile() {

        for (int i = 0; i < tileList.size(); i++) {
            if (tileList.get(i).getId() == (tileList.size())) {
                position = i + 1;
            }
        }
    }

    /**
     * Return true if the blank SlidingTile is on an odd row from the bottom of the grid.
     *
     * @return boolean true or false.
     */
    private boolean isBlankTileOnOddRow() {
        positionOfBlankTile();
        if (boardSize == 3) {
            return (position > 3 && position < 7);
        } else if (boardSize == 4) {
            return (position < 5) || (position > 8 && position < 13);
        } else {
            return (position > 5 && position < 11) || (position > 15 && position < 21);
        }
    }

    /**
     * Return true if the board is solvable.
     *
     * @return boolean true or false.
     */
    boolean isBoardSolvable() {
        if (!isBoardSizeEven()) {
            return areInversionsEven();
        } else {
            if (!isBlankTileOnOddRow()) {
                return !areInversionsEven();
            } else {
                return areInversionsEven();
            }
        }
    }

    /**
     * Set list of sliding tiles to newTiles.
     *
     * @param newTiles List of sliding tiles
     */
    protected void setTiles(Tile[][] newTiles) {
        this.tiles = newTiles;
    }
}
