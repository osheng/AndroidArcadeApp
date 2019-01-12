package Checkers;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.CoreClasses.CustomAdapter;
import fall2018.csc2017.CoreClasses.GameActivity;
import fall2018.csc2017.CoreClasses.R;
import fall2018.csc2017.CoreClasses.AccountManager;
import fall2018.csc2017.CoreClasses.Savable;

import static fall2018.csc2017.CoreClasses.SettingsActivity.TEMP_SAVE_FILENAME;

public class CheckersGameActivity extends GameActivity implements Observer {


    /**
     * The board manager.
     */
    CheckersBoardManager boardManager;

    /**
     * The buttons to display.
     */
    ArrayList<Button> tileButtons;

    // Grid View and calculated column height and width based on device size
    private CheckersGestureDetectGridView gridView;

    private static int columnWidth, columnHeight;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    public void display() {
        updateTileButtons();
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    /**
     * Undo botton restores game to previous state.
     */
    private void addUndoButtonListener(){
        Button undoButton = findViewById(R.id.CheckersUndoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager.undo();
                AccountManager.activeAccount.setActiveGameFile(boardManager.getGameFile());
                AccountManager.activeAccount.addGameFile(boardManager.getGameFile());
            }
        });

    }

    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    void createTileButtons(Context context) {
        CheckersBoard board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != board.getNumRows(); row++) {
            for (int col = 0; col != board.getNumCols(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getCheckersTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardManager = (CheckersBoardManager) Savable.loadFromFile(TEMP_SAVE_FILENAME);
        createTileButtons(this);
        setContentView(R.layout.activity_checkers_game);

        // Add View to activity
        gridView = findViewById(R.id.CheckersGrid);
        gridView.setNumColumns(boardManager.getBoard().getNumCols());
        gridView.setBoardManager(boardManager);
        boardManager.addObserver(this);
        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();

                        columnWidth = displayWidth / boardManager.getBoard().getNumCols();
                        columnHeight = displayHeight / boardManager.getBoard().getNumCols();

                        display();
                    }
                });
        addUndoButtonListener();
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    void updateTileButtons() {
        CheckersBoard board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / board.getNumCols();
            int col = nextPos % board.getNumCols();
            b.setBackgroundResource(board.getCheckersTile(row, col).getBackground());
            nextPos++;
        }
    }
}
