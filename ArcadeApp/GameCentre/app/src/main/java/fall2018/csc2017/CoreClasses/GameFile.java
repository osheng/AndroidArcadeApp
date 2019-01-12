package fall2018.csc2017.CoreClasses;

import java.io.Serializable;
import java.util.Stack;

/**
 * @param <T> generic state of game file. Board in sliding tiles.
 */
public abstract class GameFile<T> implements Serializable {

    /**
     * Maximum number of undos for a set of SlidingGameFile.
     */
    private int maxUndos;


    /**
     * Undos remaining for a set of SlidingGameFile.
     */
    private int remainingUndos;


    /**
     * name is the name of GameFile.
     */
    protected String name;

    /**
     * gameStates is a stack of boards, in the case of sliding tiles.
     */
    protected Stack<T> gameStates = new Stack<>();

    /**
     * @return Returns the Stack of game states for this file.
     */
    public Stack getGameStates() {
        return gameStates;
    }

    /**
     * @return The name of this Game File
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the number of undos to the desired number
     *
     * @param remainingUndos Desire number of undos
     */
      void setRemainingUndos(int remainingUndos) {
        this.remainingUndos = remainingUndos;
    }

    /**
     * Raises the remaining number of undos by one if not at max.
     */
    public void addUndos() {
        if (remainingUndos < maxUndos) {
            remainingUndos++;
        }
    }

    /**
     * Lowers the remaining number of undos by one
     */
     void lowerUndos() {
        remainingUndos--;
    }

    /**
     * Sets the max undos to the desired number
     *
     * @param maxUndos Desire max number of undos
     */
     void setMaxUndos(int maxUndos) {
        this.maxUndos = maxUndos;
    }

    /**
     * @return The number of remaining undos
     */
    public int getRemainingUndos() {
        return remainingUndos;

    }

    /**
     * @return The max number of Undos
     */
    public int getMaxUndos() {
        return this.maxUndos;
    }
}
