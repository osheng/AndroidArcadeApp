package Twenty;

import fall2018.csc2017.CoreClasses.Board;
import fall2018.csc2017.CoreClasses.GameFile;

public class TwentyGameFile extends GameFile {

    /**
     * Maximum number of undos for a set of TwentyGameFile.
     */
    public int maxUndos;

    /**
     * Number of moves this user has made.
     */
    public int numMoves;

    /**
     * Undos remaining for a set of TwentyGameFile.
     */
    public int remainingUndos;

    /**
     * Constructor called when creating a new game, with it's specified initial game state and game name.
     *
     * @param initialBoard: The initial state of the Twenty board
     * @param name:         The name of this game file
     */
    @SuppressWarnings("unchecked")
    public TwentyGameFile(Board initialBoard, String name) {
        this.gameStates.push(initialBoard);
        this.name = name;
    }

    public void increaseNumMoves(){
        numMoves++;
    }

}
