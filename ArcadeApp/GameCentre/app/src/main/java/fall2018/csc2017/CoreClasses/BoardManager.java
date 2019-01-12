package fall2018.csc2017.CoreClasses;

import java.io.Serializable;
import java.util.Observable;
import java.util.Stack;

public class BoardManager extends Observable implements Serializable, Game {

    /**
     * Holds a stack of boards, with each Board representing a specific game state.
     */
    protected Stack<Board> gameStates;

    /**
     * The GameFile holding the data for this board.
     */
    protected GameFile gameFile;

    /**
     * The board being managed.
     */
    private Board board;

    /**
     * The size of the board. Refers to the length of one side (as all boards used are squares).
     */
    private int size;

    /**
     * BoardManager constructor when called with a gameFile parameter.
     * @param gameFile
     */
    public BoardManager (GameFile gameFile){
        this.gameFile = gameFile;
        this.gameStates = gameFile.getGameStates();

        if (!gameFile.getGameStates().isEmpty()) {
            this.board = (Board) gameFile.getGameStates().peek();
        }
    }

    /**
     * BoardManager constructor when called with a size parameter.
     * @param size
     */
    public BoardManager (int size){
        Tile[][] tiles = new Tile[size][size];
        this.size = size;
        board = new Board(tiles, size, size);
    }

    /**
     * Getter for the BoardManager's gameFile.
     * @return gameFile
     */
    public GameFile getGameFile() {
        return gameFile;
    }

    /**
     * Checks to see if the game is complete.
     * @return boolean
     */
    public boolean gameComplete(){
        return false;
    }

    /**
     * Returns the score of a game.
     */
    public int score() {return 0;}


    /**
     * Saves a new state of board to game.
     *
     * @param board a board
     */
    @SuppressWarnings("unchecked")
    public void save(Board board) {
        gameStates.push(board);
    }

    /**
     * Switches the gameState back one move, if the user has undos left
     */
    public Board undo(){
        if (gameFile.getRemainingUndos() >0) {
            gameFile.lowerUndos();
            this.gameStates.pop();
            Board lastBoard = this.gameStates.peek();
            setChanged();
            notifyObservers();
            return lastBoard;
        }
        return board;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void touchMove(int position){
    }

    public boolean isValidMove(int position){
        return false;
    }

    public void setGameFile(GameFile gameFile) {
        this.gameFile = gameFile;
        this.gameStates = gameFile.getGameStates();
    }

    /**
     * @param maxUndoValue: Maximum number of undo tries for this file.
     *                      Also initializes the number of undo's this file currently has (denoted by <remainingUndos>)
     */
    public void setMaxUndos(int maxUndoValue) {
        gameFile.setMaxUndos(maxUndoValue);
        gameFile.setRemainingUndos(0);
    }

    public Board getBoard() {
        return board;
    }

    public int getSize() {
        return size;
    }
}
