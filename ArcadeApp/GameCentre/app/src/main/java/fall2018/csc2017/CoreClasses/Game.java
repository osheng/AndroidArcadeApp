package fall2018.csc2017.CoreClasses;

public interface Game {

    /**
     * Save a board to the stack of game files.
     *
     * @param board is a board.
     */
    void save(Board board);

    /**
     * Switches the gameState back one move, if the user has undos left
     */
    Board undo();

    /**
     * Returns the score of a game.
     */
    int score();

    /**
     * Name of SlidingTiles -- a type of Game in this GameCenter.
     */
    String SLIDING_NAME = "SlidingTiles";

    /**
     * Name of Twenty -- a type of Game in this GameCenter.
     */
    String TWENTY_NAME = "2048";

    /**
     * Name of Checkers -- a type of Game in this GameCenter.
     */
    String CHECKERS_NAME = "Checkers";

    /**
     * Games to have listed in scores
     */
    String[] GAME_NAMES = {CHECKERS_NAME, SLIDING_NAME, TWENTY_NAME};

}
