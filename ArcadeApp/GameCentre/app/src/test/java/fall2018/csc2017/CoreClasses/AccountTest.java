package fall2018.csc2017.CoreClasses;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetUsername() {
        /* Should return the username used to initialize the Account object. */
        Account testAccount = new Account("username","password");
        Assert.assertEquals("Account.getUsername() Test 1 failed!","username", testAccount.getUsername());
    }

    @Test
    public void testGetPassword() {
        /* Should return the password used to initialize the Account object. */
        Account testAccount = new Account("username","password");
        Assert.assertEquals("Account.getPassword() Test 1 failed!", "password",testAccount.getPassword());
    }

    @Test
    public void testGetGames() {

    }

    @Test
    public void testAddGameFile() {
    }

    @Test
    public void testGetActiveGameName() {
        /* Should return the ActiveGameName initialized in the Account object */
        Account testAccount = new Account("username","password");
        Assert.assertEquals("Account.getActiveGameName() Test 1","",testAccount.getActiveGameName());
    }

    @Test
    public void testSetActiveGameName() {
        /* Should return the ActiveGameName initialized in the Account object and then altered by the setActiveGameName method */
        Account testAccount = new Account("username","password");
        testAccount.setActiveGameName("TestGameName");
        Assert.assertEquals("Account.setActiveGameName() Test 1","",testAccount.getActiveGameName());
    }

    @Test
    public void testSaveAccountGameData() {
    }

    @Test
    public void testLoadAccountGameData() {
    }

    @Test
    public void testGetActiveGameFile() {

    }

    @Test
    public void testSetActiveGameFile() {
    }

    @Test
    public void testGetLeaderBoard() {
        /* Should return the new leaderboard initialized by the Account object. */
        Account testAccount = new Account("username","password");
        Assert.assertEquals("Account.getLeaderboard() Test 1 failed",new LeaderBoard(),testAccount.getLeaderBoard());
    }

}