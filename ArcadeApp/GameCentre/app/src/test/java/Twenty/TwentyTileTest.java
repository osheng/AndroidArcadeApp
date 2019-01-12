package Twenty;

import org.junit.Before;
import org.junit.Test;

import fall2018.csc2017.CoreClasses.Account;
import fall2018.csc2017.CoreClasses.AccountManager;
import fall2018.csc2017.CoreClasses.Game;

import static org.junit.Assert.assertEquals;

public class TwentyTileTest {

    @Before
    public void setup(){
        AccountManager.activeAccount = new Account("twentytest", "1234");
        AccountManager.activeAccount.setActiveGameName(Game.TWENTY_NAME);
    }

    @Test
    public void testIsBlank(){
        TwentyTile tile = new TwentyTile(0,0);
        assertEquals("tile.isBlank() failed test 1.", true, tile.isBlank());
    }

    @Test
    public void testCompareTo(){
        TwentyTile tile1 = new TwentyTile(0,0);
        TwentyTile tile2 = new TwentyTile(2,0);
        TwentyTile tile3 = new TwentyTile(2,0);

        assertEquals("tile.compareTo() failed test 1.", -1, tile1.compareTo(tile2));
        assertEquals("tile.compareTo() failed test 2.", 1, tile2.compareTo(tile3));
    }


}
