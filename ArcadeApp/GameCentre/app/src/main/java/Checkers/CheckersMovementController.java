package Checkers;

import android.content.Context;
import android.widget.Toast;


import fall2018.csc2017.CoreClasses.MovementController;

/**
 * A class to process movements
 */
public class CheckersMovementController extends MovementController {

    /**
     * An attribute which tells if a tile has been selected.
     */
    private boolean moving = false;

    public CheckersMovementController() {
        moving = false;
    }

    /**
     * Method checks if a move is valid or if game is over and performs the valid input move.
     * @param context
     * @param toastMessage
     * @param position
     */
    @Override
    public void processMovement(Context context, String toastMessage, int position) {
        if (moving && boardManager.isValidMove(position)) {
            if (((CheckersBoardManager) boardManager).isRedsTurn()){
                super.performMove(context, "Red wins!", position);}
                else {
                super.performMove(context, "White wins!", position);
            }

            if (!((CheckersBoardManager)boardManager).isHasSlain()) {
                moving = false;
            }
        }
        else if (((CheckersBoardManager)boardManager).isValidSelect(position)){
            moving = true;
        }
        else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }

}