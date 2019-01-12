package fall2018.csc2017.CoreClasses;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * AccountManager class saves and retrieves accounts and sets active accounts.
 */
public class AccountManager {

    /**
     * The application's context, which will store the location where game data will be saved.
     */
    public static String contextPath;

    /**
     * The current Account that is logged into the system
     * (Only one account can be active at once for phase 1)
     */
    public static Account activeAccount;

    /**
     * The main save file where the <accounts> map will be saved.
     */
    private static String SAVE_FILENAME = "/accounts.ser";

    /**
     * Accounts in the system.
     */
    private static HashMap<String, Account> accounts = new HashMap<>();

    /**
     * @param accUsername: Valid account username to add to the system.
     * @param accPassword: Valid account password to add to the system.
     *                     Preconditions: acc is not currently a user in the system.
     */
    static void addAccount(String accUsername, String accPassword) {
        // Verify preconditions
        if (!verifyUsername(accUsername)) {
            Account newAcc = new Account(accUsername, accPassword);
            accounts.put(accUsername, newAcc);
            saveAccounts();
        }
    }

    /**
     * @param user: Username of the account that is now active
     *              Preconditions: Username belongs to an existing Account in the system.
     */
    public static void setActiveAccount(String user) {
        // Verify preconditions
        if (verifyUsername(user)) {
            activeAccount = accounts.get(user);
        }
    }

    /**
     * @param user: Username
     * @return True if the username exists in the system, false otherwise.
     */
    static boolean verifyUsername(String user) {
        return accounts.containsKey(user);
    }

    /**
     * @param user: Username
     * @param pass: Password to verify with associated username.
     * @return True if the username exists in the system, false otherwise.
     * Preconditions: Username denotes a user that does exist in the system.
     */
    static boolean verifyPassword(String user, String pass) {
        // Verify preconditions
        if (!verifyUsername(user)) {
            return false;
        }
        Account currentAccount = accounts.get(user);
        return pass.equals(currentAccount.getPassword());
    }

    /**
     * Retrieve the hashmap from the .ser file, if it exists. If it doesn't, that means
     * this is the first time the game is being run, and we can initialize an empty <accounts> map
     * and save it in a new .ser file.
     */
    @SuppressWarnings("unchecked")
    static void loadAccounts() {
        try {
            String path = contextPath;
            File file = new File(path + SAVE_FILENAME);
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(input);
            accounts = (HashMap<String, Account>) inputStream.readObject();
        } catch (IOException e1) {
            accounts = new HashMap<>();
            // Save this empty initialized map of accounts at the specified file path.
            saveAccounts();
        } catch (ClassNotFoundException e2) {
            // Shouldn't happen, because if the .ser file doesn't exist
            // an IOException should be raised.
            System.out.println(e2.toString());
        }
    }

    /**
     * Overwrite and save the <accounts> hashmap in a serializable file.
     */
    @SuppressWarnings("unchecked")
    private static void saveAccounts() {
        // We can't serialize static variables, so make a non-static copy of the <accounts> map
        HashMap<String, Account> accountsToSave = createAccountsCopy();
        try {
            String path = contextPath;
            File file = new File(path + SAVE_FILENAME);
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(output);
            outputStream.writeObject(accountsToSave);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * @return A deep-copy of AccontManager's <accounts> hashmap.
     */
    @SuppressWarnings("unchecked")
    private static HashMap<String, Account> createAccountsCopy() {
        HashMap<String, Account> newAccounts = new HashMap<>();
        for (Map.Entry<String, Account> pair : accounts.entrySet()) {
            newAccounts.put(pair.getKey(), pair.getValue());
        }
        return newAccounts;
    }
}
