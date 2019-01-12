package Twenty;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import fall2018.csc2017.CoreClasses.Account;
import fall2018.csc2017.CoreClasses.AccountManager;
import fall2018.csc2017.CoreClasses.Game;

public class TwentyBoardTest {

    private TwentyBoard twentyBoard;
    private TwentyTile tile1, tile2, tile3;

    @Before
    public void setup(){
        AccountManager.activeAccount = new Account("twentytest", "1234");
        AccountManager.activeAccount.setActiveGameName(Game.TWENTY_NAME);
        // Initialize an empty TwentyBoard, a process done when initializing a new TwentyBoardManager
        TwentyBoardManager twentyBoardManager = new TwentyBoardManager(3);
        twentyBoard = twentyBoardManager.twentyBoard;
    }


    @Test
    public void testMergeTiles(){
        tile1 = new TwentyTile(2, 2);
        tile2 = new TwentyTile(2, 2);
        twentyBoard.insertTile(0,0, tile1);
        twentyBoard.insertTile(0,1, tile2);
        twentyBoard.mergeTiles(0, 0, 0, 1);

        assertEquals("twentyBoard.mergeTiles failed test 1.", 8, ((TwentyTile)(twentyBoard.getTile(0,0))).getId());
        assertEquals("twentyBoard.mergeTiles failed test 1.", 0, ((TwentyTile)(twentyBoard.getTile(0,1))).getId());
    }

    @Test
    public void testGenerateRandomTile(){
        // Initialize the board as all blank tiles.
        twentyBoard.generateRandomTile();
        // Check and see if there's a random tile somewhere in the board (that's not blank)
        boolean ranTileExists = false;
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if(twentyBoard.getTile(row, col).getId() != 0){
                    ranTileExists = true;
                }
            }
        }

        assertEquals("twentyBoard.generateRandomTile failed test 1.", true, ranTileExists);
    }

    @Test
    public void testIsCollapsableHoriz(){
        /* To see if collapsable horizontally. */
        tile1 = new TwentyTile(2, 2);
        tile2 = new TwentyTile(2, 2);
        tile3 = new TwentyTile(2, 2);

        twentyBoard.insertTile(0,0, tile1);
        twentyBoard.insertTile(0,1, tile2);
        twentyBoard.insertTile(0,2, tile3);
        assertEquals("twentyBoard.isCollapsable() failed horizontal test 1.", true, twentyBoard.isCollapsable(true));

        tile1.setId(0);
        tile2.setId(2);
        tile3.setId(0);
        assertEquals("twentyBoard.isCollapsable() failed horizontal test 2.", true, twentyBoard.isCollapsable(true));

        tile1.setId(4);
        tile2.setId(2);
        tile3.setId(8);
        assertEquals("twentyBoard.isCollapsable() failed horizontal test 3.", true, twentyBoard.isCollapsable(true));

        tile1.setId(4);
        tile2.setId(2);
        tile3.setId(8);
        for(int row = 0; row < 3; row++){
            twentyBoard.insertTile(row,0, tile1);
            twentyBoard.insertTile(row,1, tile2);
            twentyBoard.insertTile(row,2, tile3);
        }
        assertEquals("twentyBoard.isCollapsable() failed horizontal test 4.", false, twentyBoard.isCollapsable(true));
    }


    @Test
    public void testIsCollapsableVertically(){
        /* Same as above but for vertically */
        tile1 = new TwentyTile(2, 2);
        tile2 = new TwentyTile(2, 2);
        tile3 = new TwentyTile(2, 2);

        twentyBoard.insertTile(0,0, tile1);
        twentyBoard.insertTile(1,0, tile2);
        twentyBoard.insertTile(2,0, tile3);
        assertEquals("twentyBoard.isCollapsable() failed vertical test 1.", true, twentyBoard.isCollapsable(false));

        tile1.setId(0);
        tile2.setId(2);
        tile3.setId(0);
        assertEquals("twentyBoard.isCollapsable() failed vertical test 2.", true, twentyBoard.isCollapsable(false));

        tile1.setId(4);
        tile2.setId(2);
        tile3.setId(8);
        assertEquals("twentyBoard.isCollapsable() failed vertical test 3.", true, twentyBoard.isCollapsable(false));

        tile1.setId(4);
        tile2.setId(2);
        tile3.setId(8);
        for(int col = 0; col < 3; col++){
            twentyBoard.insertTile(0,col, tile1);
            twentyBoard.insertTile(1,col, tile2);
            twentyBoard.insertTile(2,col, tile3);
        }
        assertEquals("twentyBoard.isCollapsable() failed vertical test 4.", false, twentyBoard.isCollapsable(false));
    }

}
