package fall2018.csc2017.CoreClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class LogInActivity extends AppCompatActivity {

    /**
     * Shows the activity_log_in.xml file, gets info from .ser file, and starts the listener for various buttons.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Set the context of the AccountManager, which will store all of the games data.
        AccountManager.contextPath = getApplicationContext().getFilesDir().getAbsolutePath();

        // Load the .ser files containing this app's data (from the app's context) on first load.
        AccountManager.loadAccounts();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        if (AccountManager.activeAccount != null &&
                !AccountManager.activeAccount.getUsername().equals("guest")){
            EditText enterUsername = findViewById(R.id.enterUsername);
            enterUsername.setText(AccountManager.activeAccount.getUsername());
            EditText enterPassword = findViewById(R.id.enterPassword);
            enterPassword.setText(AccountManager.activeAccount.getPassword());
        }



        addLogInButtonListener();
        addSignUpButtonListener();
        addGuestButtonListener();
        addLogOutButtonListener();
    }

    /**
     * Activate LogOut Button
     */
    private void addLogOutButtonListener() {
        Button logOut = findViewById(R.id.LogOutButton);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountManager.activeAccount = null;
                makeToastLogOut();
            }
        });
    }

    /**
     * Switches to GameSelectActivity to choose game to play.
     */
    private void switchToGameSelect() {
        Intent gameSelect = new Intent(this, GameSelectActivity.class);
        startActivity(gameSelect);
    }

    /**
     * Activate Login button.
     */
    private void addLogInButtonListener() {
        Button logIn = findViewById(R.id.logInButton);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, password;
                boolean usernameExists, passwordVerified;

                username = ((EditText) findViewById(R.id.enterUsername)).getText().toString();
                password = ((EditText) findViewById(R.id.enterPassword)).getText().toString();
                usernameExists = AccountManager.verifyUsername(username);
                passwordVerified = AccountManager.verifyPassword(username, password);

                if (username.equals("guest")){
                    boolean guestExists = AccountManager.verifyUsername("guest");
                    if (!guestExists) {
                        AccountManager.addAccount("guest", "guest");
                    }
                    AccountManager.setActiveAccount(username);
                    switchToGameSelect();
                }

                else if (!usernameExists) {
                    makeToastSignUp();
                }else if (!passwordVerified) {
                    makeToastPassword();
                } else {
                    // Set this user's account as the Active Account, and
                    // switch to the StartingGameActivity.
                    AccountManager.setActiveAccount(username);
                    switchToGameSelect();
                }
            }
        });
    }

    /**
     * Activate Sign up button.
     */
    private void addSignUpButtonListener() {

        Button signUp = findViewById(R.id.signUpButton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, password;
                boolean usernameExists;
                username = ((EditText) findViewById(R.id.enterUsername)).getText().toString();
                password = ((EditText) findViewById(R.id.enterPassword)).getText().toString();
                usernameExists = AccountManager.verifyUsername(username);

                if (usernameExists) {
                    makeToastUsername();
                } else if (password.length() < 5){
                    makeToastShortPassword();
                } else if (username.length() < 1){
                    makeToastUsername2();
                }
                else {
                    // Create an Account with these credentials in AccountManager,
                    // which will save it and log it as the current active account
                    AccountManager.addAccount(username, password);
                    AccountManager.setActiveAccount(username);
                    switchToGameSelect();
                }
            }
        });
    }

    /**
     * Activate guest button.
     */
    private void addGuestButtonListener() {
        Button guest = findViewById(R.id.guestButton);
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean usernameExists = AccountManager.verifyUsername("guest");
                if (!usernameExists) {
                    AccountManager.addAccount("guest", "guest");
                }
                AccountManager.setActiveAccount("guest");
                switchToGameSelect();


            }
        });
    }

    /**
     * Displays that user has logged out.
     */
    private void makeToastLogOut() {
        Toast.makeText(this, "You logged out! T_T", Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays that a username does not exist and recommends signing up.
     */
    private void makeToastSignUp() {
        Toast.makeText(this, "Username does not exist. Please sign up!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays that a username already exists.
     */
    private void makeToastUsername() {
        Toast.makeText(this, "Username already exists. Please select another one.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays that a username already exists.
     */
    private void makeToastUsername2() {
        Toast.makeText(this, "Please enter a username.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays that password does not match username.
     */
    private void makeToastPassword() {
        Toast.makeText(this, "Password does not match username.", Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays that password is too short.
     */
    private void makeToastShortPassword() {
        Toast.makeText(this, "Password must be at least 5 characters!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Make back button minimize app.
     *
     * Code taken from DarckBlezzer's answer on StackOverflow
     * https://stackoverflow.com/questions/28162815/i-need-to-minimize-the-android-application-on-back-button-click
     */
    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }
}
