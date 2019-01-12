package fall2018.csc2017.CoreClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * The activity for showing a user their personal high scores on a game.
 */
public class PersonalLeaderBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_leader_board);

        ArrayList<GameScore> scores = LeaderBoard.getTopScores(AccountManager.activeAccount.getActiveGameName(), LeaderBoard.PERSONAL);
        ListAdapter scoreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scores);
        ListView listV = findViewById(R.id.topScores);
        listV.setAdapter(scoreAdapter);

        addGlobalTopScoresButtonListener();
    }

    /**
     * Activate GlobalTopScoresButton
     */
    private void addGlobalTopScoresButtonListener() {
        Button globalTopScoresButton = findViewById(R.id.GlobalTopScoresButton);
        globalTopScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGlobalTopScores();
            }
        });
    }

    /**
     * Switches to the global LeaderBoard
     */
    private void switchToGlobalTopScores() {
        Intent globalLeaderBoard = new Intent(this, GlobalLeaderBoardActivity.class);
        startActivity(globalLeaderBoard);
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