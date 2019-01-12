package fall2018.csc2017.CoreClasses;

import android.support.annotation.NonNull;
import java.io.Serializable;

/**
 * A Tile in any board-game
 */
public abstract class Tile implements Comparable<Tile>, Serializable {

    /**
     * The background id to find the tile image.
     */
    private int background;

    /**
     * The id.
     */
    public int id;

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return background;
    }

    /**
     * Set background id.
     *
     * @param newBackground an int.
     */
    public void setBackground(int newBackground) {
        this.background = newBackground;
    }

    /**
     * Set id.
     *
     * @param newId an int.
     */
    public void setId(int newId) {this.id = newId;}

    /**
     * Get id.
     */
    public int getId() {return id;}

    /**
     * A tile with a background id; look up and set the id.
     */
    public Tile(){}

    /**
     * Compares two Tiles by id for order.
     */
    @Override
    public int compareTo(@NonNull Tile other) {
        return other.id - this.id;
    }

}
