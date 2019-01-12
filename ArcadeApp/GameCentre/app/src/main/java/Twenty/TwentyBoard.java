package Twenty;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Sliding.SlidingBoard;
import fall2018.csc2017.CoreClasses.Board;
import fall2018.csc2017.CoreClasses.Tile;

public class TwentyBoard extends Board {

    /**
     * Initializes the TwentyBoard with a list of tiles.
     */
    TwentyBoard(Tile[][] tiles, int numRows, int numCols) {
        super(tiles, numRows, numCols);
    }


    /*
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     * Preconditions: The tile at row1, col1 has the same id as the tile at row2, col2
     * Postconditions: The tile at row1, col1 will be replaced with the merged tile, and the
     *                  tile at row2, col2 will be replaced with a blank tile.
     */
    void mergeTiles(int row1, int col1, int row2, int col2){
        int tile1Id = this.tiles[row1][col1].getId(), tile2Id = this.tiles[row2][col2].getId();
        TwentyTile mergedTile, blankTile;
        // Verify preconditions.
        if(tile1Id == tile2Id){
            mergedTile =  new TwentyTile((int)(Math.log(tile1Id)/Math.log(2))+1, tile1Id*tile2Id);
            blankTile = new TwentyTile(0, 0);
            this.tiles[row1][col1] = mergedTile;
            this.tiles[row2][col2] = blankTile;
        }
    }

    /* Generate a random tile in place of an empty tile in the Board. */
    void generateRandomTile(){
        int position[] = getRanEmptyPos();
        int tileRow = position[0], tileCol = position[1];

        // Pick a random value from (2^1, 2^2, 2^3)
        int ranExp = (int)(Math.random() * 3 + 1);
        int ranNum = (int)(Math.pow(2, ranExp));

        TwentyTile randomTile = new TwentyTile(ranExp, ranNum);

        this.tiles[tileRow][tileCol] = randomTile;

    }

    /* Get the positions of a random empty tile in this Board. */
    private int[] getRanEmptyPos(){
        int[] ranEmptyPos = new int[2];
        ArrayList<int[]> emptyPositions = getAllEmptyPos();
        if(emptyPositions.isEmpty()){
            // There are no empty tiles. Having a position of '-1' will denote that no empty tile exists.
            ranEmptyPos[0] = -1;
        }else{
            int ranIndex = (int)(Math.random() * emptyPositions.size());
            ranEmptyPos = emptyPositions.get(ranIndex);
        }
        return ranEmptyPos;

    }

    /* Get the positions of all the empty tiles in this Board. */
    private ArrayList<int[]> getAllEmptyPos(){
        ArrayList<int[]> emptyPositions = new ArrayList<>();
        // Iterate through this TwentyBoard to find & retrieve the position of all the empty tiles
        TwentyTile currentTile;
        for(int row = 0; row<this.numRows; row++){
            for(int col = 0; col<this.numCols; col++){
                currentTile = (TwentyTile)this.tiles[row][col];
                if(currentTile.isBlank()){
                    int emptyPosition[] = {row, col};
                    emptyPositions.add(emptyPosition);
                }
            }
        }
        return emptyPositions;
    }

     boolean isCollapsable(boolean horizDir){
        // Left to right if horizontal, and top to bottom if vertical,
        // see if there are two adjacent tiles that are of the same id.
        int tile1Id, tile2Id;
        int row, col;
        for(int i = 0; i < this.numRows; i++){
            for(int j = 0; j < this.numCols - 1; j++){
                if(horizDir){
                    row = i;
                    col = j;
                    tile1Id = this.tiles[row][col].getId();
                    tile2Id = this.tiles[row][col+1].getId();
                }else{
                    col = i;
                    row = j;
                    tile1Id = this.tiles[row][col].getId();
                    tile2Id = this.tiles[row+1][col].getId();
                }
                // Check that the two tiles are equal value, or if one is a blank tile (aka of id=0)
                if(tile1Id == tile2Id || tile1Id == 0 || tile2Id == 0){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Creates a copy of the board and all its tiles and returns it.
     * @return TwentyBoard
     */
    public TwentyBoard createDeepCopy() {
        Tile[][] tiles = super.createDeepCopy().tiles;
        return new TwentyBoard(tiles, getNumRows(), getNumCols());
    }


}