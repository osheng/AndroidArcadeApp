package Checkers;

import java.util.HashMap;

import fall2018.csc2017.CoreClasses.R;
import fall2018.csc2017.CoreClasses.Tile;

/**
 * A Tile in checkers Game
 */
public class CheckersTile extends Tile {

    /**
     * Overwrite id to be a String
     */
    private String id;


    /**
     * HashMap of images
     */
    private HashMap<String, Integer> picts = new HashMap<>();

    /**
     * Strings used to denote different tile images
     */

    static final String EMPTY_WHITE_TILE = "empty";

    static final String BLACK_TILE = "black";

    static final String RED = "red";

    static final String WHITE = "white";

    static final String KING = "king";

    static final String PAWN = "pawn";

    static final String RED_KING = RED + KING;

    static final String WHITE_KING = WHITE + KING;

    static final String RED_PAWN = RED + PAWN;

    static final String WHITE_PAWN = WHITE + PAWN;

    static final String HIGHLIGHTED = "_highlighted";

    /**
     * A constructor for a checker tile with a background id
     * @param id string representing the type of tile
     */
    CheckersTile(String id) {
        super();
        this.id = id;
        picts.put(EMPTY_WHITE_TILE, R.drawable.checkers_empty_white_tile);
        picts.put(BLACK_TILE, R.drawable.checkers_empty_black_tile);
        picts.put(RED_PAWN, R.drawable.checkers_red_pawn);
        picts.put(RED_KING, R.drawable.checkers_red_king);
        picts.put(WHITE_PAWN, R.drawable.checkers_white_pawn);
        picts.put(WHITE_KING, R.drawable.checkers_white_king);
        picts.put(RED_PAWN + HIGHLIGHTED, R.drawable.checkers_red_pawn_highlighted);
        picts.put(RED_KING + HIGHLIGHTED, R.drawable.checkers_red_king_highlighted);
        picts.put(WHITE_PAWN + HIGHLIGHTED, R.drawable.checkers_white_pawn_highlighted);
        picts.put(WHITE_KING + HIGHLIGHTED, R.drawable.checkers_white_king_highlighted);
        if (picts.containsKey(id)){
            setBackground(picts.get(id));}
        else
            {throw new IllegalArgumentException("You're passing an invalid id to the CheckersTile Constructor");
        }


    }



    /**
     * Highlight the selected CheckersTile
     */
    void highlight() {
        if (!this.id.contains("empty")) {
            this.id += "_highlighted";
            setBackground(picts.get(id));}
    }

    /**
     * Make the selected CheckersTile look normal again
     */
    void dehighlight() {
        this.id = this.id.replaceAll("_highlighted","");
        setBackground(picts.get(id));
    }

    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    String getCheckersId(){return id;}

    /**
     * change the CheckersTile into a tile with newId
     * @param newId the new type of CheckersTile
     */
    void changeTile(String newId){
        id = newId;
        setBackground(picts.get(id));
    }
}
