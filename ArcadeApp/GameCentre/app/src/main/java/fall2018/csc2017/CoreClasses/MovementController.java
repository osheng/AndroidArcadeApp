package fall2018.csc2017.CoreClasses;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * A class to connect the UI with the game Classes
 */
public class MovementController extends AppCompatActivity {

    /**
     * boardManager of the game being played
     */
    protected BoardManager boardManager;

    /**
     * Rejects input if invalid. Otherwise calls performMove
     * @param context in the phone
     * @param toastMessage message to display
     * @param position position on the board
     */
    public void processMovement(Context context, String toastMessage, int position) {
        if (boardManager.isValidMove(position)) {
            performMove(context, toastMessage, position);
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * performs a valid move on the board of boardManager
     * @param context in the phone
     * @param toastMessage message to display
     * @param position on the board of boardManager
     */
    protected void performMove(Context context, String toastMessage, int position) {
        boardManager.touchMove(position);
        AccountManager.activeAccount.setActiveGameFile(boardManager.getGameFile());
        AccountManager.activeAccount.addGameFile(boardManager.getGameFile());
        if (boardManager.gameComplete()) {
            if(!AccountManager.activeAccount.getActiveGameName().equals(Game.CHECKERS_NAME) || !toastMessage.contains("White")){
                LeaderBoard.updateScores(new GameScore(
                        AccountManager.activeAccount.getActiveGameName(),
                        boardManager.getGameFile().getName(),
                        AccountManager.activeAccount.getUsername(),
                        boardManager.score()));
            }

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * A setter for boardManager
     * @param boardManager the new boardManager
     */
    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }


}
