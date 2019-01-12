package fall2018.csc2017.CoreClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Checkers.CheckersBoardManager;
import Sliding.SlidingBoardManager;
import Twenty.TwentyBoardManager;

/**
 * The activity for determining settings for a new game.
 */
public class SettingsActivity extends AppCompatActivity {

    /**
     * The board manager.
     */
    protected BoardManager boardManager;

    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";

    /**
     * Size of the board specified
     */
    protected int size = 4;

    protected String activeGameName;
    protected int contentId;
    protected int viewUndoId;
    protected int startId;
    protected int sizeId;

    /**
     * Shows activity_settings.xml file and starts the listener for the start button.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Savable.saveToFile(TEMP_SAVE_FILENAME, boardManager);
        activeGameName = AccountManager.activeAccount.getActiveGameName();
        sizeId = R.id.boardSizeInput;
        contentId = R.layout.activity_settings;
        viewUndoId = R.id.undosInput;
        startId = R.id.start;
        setContentView(contentId);
        makeGameSettingsText();
        addStartButtonListener();
    }

    /**
     * Activate Start button.
     */
    public void addStartButtonListener() {
        final Button start = findViewById(startId);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSize();
                if (AccountManager.activeAccount.getActiveGameName().equals(Game.SLIDING_NAME)) {
                    if (size > 2 && size < 6) {
                        boardManager = new SlidingBoardManager(size);
                        setUndos();
                        switchToGame();
                    } else {
                        makeToastSize();
                    }
                } else if (AccountManager.activeAccount.getActiveGameName().equals(Game.TWENTY_NAME)) {
                    if (size > 2 && size < 9) {
                        boardManager = new TwentyBoardManager(size);
                        setUndos();
                        switchToGame();
                    } else {
                        makeToastSize();
                    }
                } else if (AccountManager.activeAccount.getActiveGameName().equals(Game.CHECKERS_NAME))
                    if (size > 3 && size < 13 && size % 2 == 0) {
                        boardManager = new CheckersBoardManager(size, true);
                        setUndos();
                        switchToGame();
                    } else {
                        makeToastSize();
                    }
            }
        });
    }

    /**
     * Displays that a size is not supported.
     */
    private void makeToastSize() {
        if (AccountManager.activeAccount.getActiveGameName().equals(Game.CHECKERS_NAME)) {
            Toast.makeText(this, "Size must be even!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter a valid size!.", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Sets size from textView.
     */
    private void setSize() {
        String strSize = ((EditText) findViewById(sizeId)).getText().toString();
        if (strSize.equals("")) {
            size = 4;
        } else {
            size = Integer.parseInt(strSize);
        }
    }

    /**
     * Sets undos from textView.
     */
    void setUndos() {
        int numUndos;
        String strUndos = ((EditText) findViewById(viewUndoId)).getText().toString();
        if (strUndos.equals("")) {
            numUndos = 3;
        } else {
            numUndos = Integer.parseInt(strUndos);
        }
        boardManager.setMaxUndos(numUndos);

    }

    /**
     * Saves sliding board manager to temporary file and switches to starting activity.
     */
    void switchToGame() {
        Intent start = new Intent(this, GameActivity.class);
        AccountManager.activeAccount.setActiveGameFile(boardManager.getGameFile());
        AccountManager.activeAccount.addGameFile(boardManager.getGameFile());
        Savable.saveToFile(SettingsActivity.TEMP_SAVE_FILENAME, boardManager);
        startActivity(start);
    }

    /**
     * Displays the relevant settings title with the game-specific acceptable input range for size as well as default values.
     */
    public void makeGameSettingsText() {
        TextView title = findViewById(R.id.textView7);
        TextView rangeSize = findViewById(R.id.textView5);
        EditText sizeInput = findViewById(R.id.boardSizeInput);
        sizeInput.setText("4");
        EditText undoInput = findViewById(R.id.undosInput);
        undoInput.setText("3");

        if (AccountManager.activeAccount.getActiveGameName().equals(Game.SLIDING_NAME)) {
            title.setText("SlidingTiles Settings");
            rangeSize.setText("Enter a board size from 3-5!");
        } else if (AccountManager.activeAccount.getActiveGameName().equals(Game.TWENTY_NAME)) {
            title.setText("2048 Settings");
            rangeSize.setText("Enter a board size from 3-8!");
        } else if (AccountManager.activeAccount.getActiveGameName().equals(Game.CHECKERS_NAME)) {
            title.setText("Checkers Settings");
            rangeSize.setText("Enter a board size from 4-12!");
        }

    }

    /**
     * Override the functionality of the back button
     */
    @Override
    public void onBackPressed() {
        Intent startingActivity = new Intent(this, StartingActivity.class);
        startActivity(startingActivity);
    }

}



