package fall2018.csc2017.CoreClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * The initial activity for the sliding puzzle tile game.
 */
public class StartingActivity extends AppCompatActivity {

    /**
     * The main save file.
     */
    public static final String SAVE_FILENAME = "save_file.ser";
    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_starting_);
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        addLeaderBoardListener();
        setWelcomeText();
    }

    /**
     * Activate LeaderBoard Button
     */

    private void addLeaderBoardListener(){
        Button leaderBoardButton = findViewById(R.id.LeaderBoardButton);
        leaderBoardButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switchToLeaderBoard();
            }
        });
    }

    /**
     * Switch to LeaderBoardActivity
     */
    private void switchToLeaderBoard(){
        Intent leaderBoard = new Intent(this, GlobalLeaderBoardActivity.class);
        startActivity(leaderBoard);
    }

    /**
     * Activate the start button.
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSettings();

            }
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLoad();
            }
        });
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFile(SAVE_FILENAME);
                saveToFile(TEMP_SAVE_FILENAME);
                makeToastSavedText();
            }
            });
    }

    /**
     * Displays welcoming text and instructions for the selected game.
     */
    private void setWelcomeText(){
        TextView welcomeText = findViewById(R.id.GameText);
        if(AccountManager.activeAccount.getActiveGameName().equals(Game.SLIDING_NAME)) {
            welcomeText.setText("Welcome to the Sliding Tiles Game! \n Arrange the numbers in ascending order to win!");
        }
        else if(AccountManager.activeAccount.getActiveGameName().equals(Game.CHECKERS_NAME)) {
            welcomeText.setText("Welcome to the Checkers Game! \n Capture all your opponents pieces to win!");
        }
        else if(AccountManager.activeAccount.getActiveGameName().equals(Game.TWENTY_NAME)) {
            welcomeText.setText("Welcome to the 2048 Game! \n Combine identical tiles and try to get or surpass 2048!");
        }
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Switch to the SettingsActivity view to set parameters of the game.
     */
    private void switchToSettings(){
        Intent settings = new Intent(this, SettingsActivity.class);
        startActivity(settings);
    }

    /**
     * Switch to LoadGameActiviy view to select games to load from.
     */
    private void switchToLoad(){
        Intent load = new Intent(this, LoadGameActivity.class);
        startActivity(load);
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Override the functionality of the back button
     */
    @Override
    public void onBackPressed(){
        Intent selectGame = new Intent(this, GameSelectActivity.class);
        startActivity(selectGame);
    }
}
