package fall2018.csc2017.CoreClasses;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Account class manages user accounts.
 */
public class Account implements Serializable {

    private LeaderBoard scores;

    /**
     * This Account's username
     */
    private String username;

    /**
     * This Account's password
     */
    private String password;

    /**
     * The log of this accounts top scores for each game
     */
    private LeaderBoard leaderBoard;

    /**
     * A 2-D hashMap of GameFiles that belong to this Account. Each entry is a type of game,
     * and each hashMap inside contains the game files for said game.
     */
    private HashMap<String, HashMap<String, GameFile>> accountGameData;

    /**
     * The current gameFile being played by this Account.
     */
    private GameFile activeGameFile;

    /**
     * The main save file where the <games> map will be saved, dependent on this account's username.
     */
    private String saveFileName;

    /**
     * Name of the game that is currently active in this account.
     */
    private String activeGameName = "";

    public Account(String user, String pass) {
        this.username = user;
        this.password = pass;
        this.accountGameData = new HashMap<>();
        this.initializeGameFiles();
        this.leaderBoard = new LeaderBoard();
        // Set the 'save' file name based off of this username.
        this.saveFileName = "/" + this.username + ".ser";

    }

    /**
     * Initializes the <accountGameData> attribute in this class.
     */
    private void initializeGameFiles() {
        HashMap<String, GameFile> slidingMap = new HashMap<>();
        HashMap<String, GameFile> twentyMap = new HashMap<>();
        HashMap<String, GameFile> checkersMap = new HashMap<>();
        this.accountGameData.put(Game.SLIDING_NAME, slidingMap);
        this.accountGameData.put(Game.TWENTY_NAME, twentyMap);
        this.accountGameData.put(Game.CHECKERS_NAME, checkersMap);
    }


    /**
     * Getter for username
     *
     * @return this account's username
     */
    String getUsername() {
        return this.username;
    }

    /**
     * Getter for password
     *
     * @return this account's password
     */
    String getPassword() {
        return this.password;
    }

    /**
     * Returns hashMap that contains all the account's games and corresponding gameFiles.
     *
     * @return said hashMap
     */
    HashMap<String, GameFile> getGames(String gameType) {
        this.loadAccountGameData();
        return this.accountGameData.get(gameType);
    }


    /**
     * Inserts a gameFile in the current game's map of gameFiles.
     *
     * @param gameFile: The gameFile to be inserted.
     */
    public void addGameFile(GameFile gameFile) {
        HashMap<String, GameFile> gameFiles = this.accountGameData.get(this.activeGameName);
        gameFiles.put(gameFile.getName(), gameFile);
        this.activeGameFile = gameFile;
        this.saveAccountGameData();
    }

    /**
     * Returns the name of the game currently being played
     *
     * @return activeGamename
     */
    public String getActiveGameName() {
        return this.activeGameName;
    }

    /**
     * Sets the desire game name
     *
     * @param activeGameName The desire name of the game
     */
    public void setActiveGameName(String activeGameName) {
        this.activeGameName = activeGameName;
    }

    /**
     * Overwrite and save the <games> hashMap in a serializable file.
     */
    private void saveAccountGameData() {
        try {
            String path = AccountManager.contextPath;
            File file = new File(path + this.saveFileName);
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(output);
            outputStream.writeObject(this.accountGameData);
            outputStream.close();
        } catch (IOException e) {
            System.out.println(e);
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Retrieve the <games> hashmap from the .ser file, if it exists. If it doesn't, that means
     * that the user has not yet made a game file. In this case, save the existing empty map of
     * game files to the .ser file.
     */
    @SuppressWarnings("unchecked")
    void loadAccountGameData() {
        try {
            String path = AccountManager.contextPath;
            File file = new File(path + this.saveFileName);
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(input);
            this.accountGameData = (HashMap<String, HashMap<String, GameFile>>) inputStream.readObject();
        } catch (IOException e1) {
            this.saveAccountGameData();
        } catch (ClassNotFoundException e1) {
            System.out.println(e1);
        }
    }


    /**
     * Setter for the active game file of this account.
     *
     * @param gameFile the current active game file for this account.
     */
    public void setActiveGameFile(GameFile gameFile) {
        this.activeGameFile = gameFile;
    }

    /**
     * A getter for leaderBoard
     *
     * @return leaderBoard
     */
    LeaderBoard getLeaderBoard() {
        return leaderBoard;
    }
}
