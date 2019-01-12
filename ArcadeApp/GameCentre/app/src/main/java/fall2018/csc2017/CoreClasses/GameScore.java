package fall2018.csc2017.CoreClasses;

import java.io.Serializable;

/**
 * A class for recording the results of completed games.
 */

public class GameScore implements Serializable, Comparable<GameScore> {

    /**
     * The name of the game, e.g., "SlidingTiles"
     */
    private String gameName;

    /**
     * The name of that particular instance of the game
     */
    private String instanceGameName;

    /**
     * The username of whoever completed the game.
     */
    private String accountName;

    /**
     * The score on completion of the game
     */
    private int score;

    /**
     * A constructor for GameScores
     *
     * @param gameName         The name of the game, e.g., "SlidingTiles"
     * @param instanceGameName The name of that particular instance of the game
     * @param accountName      The username of whoever completed the game.
     * @param score            The score on completion of the game
     */
    GameScore(String gameName, String instanceGameName, String accountName, int score) {
        this.gameName = gameName;
        this.instanceGameName = instanceGameName;
        this.accountName = accountName;
        this.score = score;
    }

    /**
     * A getter for the game name, e.g., "SlidingTiles"
     *
     * @return the name of the game
     */
    String getGameName() {
        return this.gameName;
    }


    /**
     * Comapare two GameScores based on which one has the higher score
     *
     * @param other GameScore you're comparing with
     * @return 1, 0, or -1 if this GameScore scored higher, same, or less than the other
     */
    @Override
    public int compareTo(GameScore other) {
        return Integer.compare(this.score, other.score);
    }

    /**
     * Returns of string representation of the attributes of GameScore
     *
     * @return a string representation of GameScore
     */
    @Override
    public String toString() {
        return gameName + ", " + instanceGameName + ", " + accountName + ", " + score;
    }

}
