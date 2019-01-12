package Sliding;

import java.io.Serializable;

import fall2018.csc2017.CoreClasses.Board;
import fall2018.csc2017.CoreClasses.GameFile;

public class SlidingGameFile extends GameFile implements Serializable {


    /**
     * Number of moves this user has made.
     */
    private int numMoves;

    /**
     * Constructor called when creating a new game, with it's specified initial game state and game name.
     *
     * @param initialBoard: The initial state of the SlidingTiles board
     * @param name:         The name of this game file
     */
    @SuppressWarnings("unchecked")
    SlidingGameFile(Board initialBoard, String name) {
        this.gameStates.push(initialBoard);
        this.name = name;
    }

    /**
     * Increments NumMoves by 1.
     */
    void increaseNumMoves() {
        numMoves++;
    }


    int getNumMoves() {
        return numMoves;
    }

}
