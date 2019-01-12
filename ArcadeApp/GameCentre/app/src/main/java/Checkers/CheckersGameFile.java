package Checkers;

import fall2018.csc2017.CoreClasses.GameFile;

public class CheckersGameFile extends GameFile {

    /**
     * Maximum number of undos for a set of SlidingGameFile.
     */
    public int maxUndos;

    /**
     * Undos remaining for a set of CheckersGameFile.
     */
    public int remainingUndos;

    /**
     * Constructor called when creating a new game, with it's specified initial game state and game name.
     *
     * @param initialBoard: The initial state of the SlidingTiles board
     * @param name:         The name of this game file
     */
    @SuppressWarnings("unchecked")
    public CheckersGameFile(CheckersBoard initialBoard, String name) {
        this.gameStates.push(initialBoard);
        this.name = name;
    }
}
