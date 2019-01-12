package fall2018.csc2017.CoreClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * The activity for showing all the high scores on a game.
 */
public class GlobalLeaderBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_leader_board);
        ArrayList<GameScore> scores = LeaderBoard.getTopScores(AccountManager.activeAccount.getActiveGameName(), LeaderBoard.GLOBAL);
        ListAdapter scoreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scores);
        ListView listV = findViewById(R.id.topScores);
        listV.setAdapter(scoreAdapter);
        addPersonalTopScoresButtonListener();
    }

    /**
     * Activate GlobalTopScoresButton
     */
    private void addPersonalTopScoresButtonListener() {
        Button personalTopScoresButton = findViewById(R.id.LeaderBoardButton);
        personalTopScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AccountManager.activeAccount == null) {
                    makeToastPlzLogin();
                } else {
                    switchToPersonalTopScores();
                }

            }
        });
    }

    /**
     * Switches to the personal LeaderBoard
     */
    private void switchToPersonalTopScores() {
        Intent personalLeaderBoard = new Intent(this, PersonalLeaderBoardActivity.class);
        startActivity(personalLeaderBoard);
    }

    /**
     * Remind the user to log in.
     */

    private void makeToastPlzLogin() {
        Toast.makeText(this, "You must log in to see your own top scores", Toast.LENGTH_SHORT).show();
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