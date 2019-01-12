package Twenty;

import org.junit.Before;
import org.junit.Test;

import fall2018.csc2017.CoreClasses.Account;
import fall2018.csc2017.CoreClasses.AccountManager;
import fall2018.csc2017.CoreClasses.Game;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;


public class TwentyBoardManagerTest {

    private TwentyBoardManager twentyBoardManager;
    private TwentyTile tile1, tile2, tile3;

    @Before
    public void setup(){
        AccountManager.contextPath = "/data/user/0/fall2018.csc2017.slidingtiles/files";
        AccountManager.activeAccount = new Account("twentytest", "1234");
        AccountManager.activeAccount.setActiveGameName(Game.TWENTY_NAME);
        twentyBoardManager = new TwentyBoardManager(3);
    }

    @Test
    public void testIsValidMoveBlank(){
        //  Initialize the board as all blank tiles, and test if a valid move can be made.
        assertEquals("twentyBoardManager.isValidMove() failed test 1.", true, twentyBoardManager.isValidMove(1));
        assertEquals("twentyBoardManager.isValidMove() failed test 2.", true, twentyBoardManager.isValidMove(3));
    }

    @Test
    public void testIsValidMoveFilled(){
        // Fill the board such that it's 'complete' in the horizontal direction, and check
        // the value of isValidMove.
        TwentyBoard twentyBoard = twentyBoardManager.twentyBoard;
        tile1 = new TwentyTile(2, 2);
        tile2 = new TwentyTile(4, 2);
        tile3 = new TwentyTile(8, 2);
        for(int row = 0; row < 3; row++){
            twentyBoard.insertTile(row,0, tile1);
            twentyBoard.insertTile(row,1, tile2);
            twentyBoard.insertTile(row,2, tile3);
        }
        assertEquals("twentyBoard.isValidMove() failed test 3.", false, twentyBoardManager.isValidMove(3));
    }

    @Test
    public void testTouchMoveInvalid(){
        // Fill the board such that it's 'complete' in the horizontal direction, and check
        // if a move is made by touchMove
        TwentyBoard twentyBoard = twentyBoardManager.twentyBoard;
        tile1 = new TwentyTile(2, 2);
        tile2 = new TwentyTile(4, 2);
        tile3 = new TwentyTile(8, 2);
        for(int row = 0; row < 3; row++){
            twentyBoard.insertTile(row,0, tile1);
            twentyBoard.insertTile(row,1, tile2);
            twentyBoard.insertTile(row,2, tile3);
        }

        twentyBoardManager.touchMove('L');
        // Check if the altered board is equal to the previous one.
        boolean notEqual = false;
        for(int row = 0; row < 3; row++){
            notEqual = twentyBoard.getTile(row, 0).getId() != tile1.getId() ||
                    twentyBoard.getTile(row, 1).getId() != tile2.getId() ||
                    twentyBoard.getTile(row, 2).getId() != tile3.getId();
            assertEquals("twentyBoard.touchMove() failed test 1.", false, notEqual);
        }
    }

    @Test
    public void twentytestertestTouchMove(){
        // TODO: Perhaps write this in a cleaner way.
        // Fill the board such that a tile can be shifted leftwards, and see if it does get
        // shifted leftwards after touchMove()
        TwentyBoard twentyBoard = twentyBoardManager.twentyBoard;
        tile1 = new TwentyTile(2, 2);
        twentyBoard.insertTile(0,2,tile1);
        twentyBoardManager.touchMove('L');
        assertEquals("twentyBoard.touchMove() failed test 1.", 4, twentyBoard.getTile(0,0).getId());

        // Test on a rightwards movement
        twentyBoardManager = new TwentyBoardManager(3);
        twentyBoard = twentyBoardManager.twentyBoard;
        tile1 = new TwentyTile(2, 2);
        twentyBoard.insertTile(0,0,tile1);
        twentyBoardManager.touchMove('R');
        assertEquals("twentyBoard.touchMove() failed test 2.", 4, twentyBoard.getTile(0,2).getId());

        // Test on a downwards movement
        twentyBoardManager = new TwentyBoardManager(3);
        twentyBoard = twentyBoardManager.twentyBoard;
        tile1 = new TwentyTile(2, 2);
        twentyBoard.insertTile(0,2,tile1);
        twentyBoardManager.touchMove('D');
        assertEquals("twentyBoard.touchMove() failed test 3.", 4, twentyBoard.getTile(2,2).getId());

        // Test on an upwards movement
        twentyBoardManager = new TwentyBoardManager(3);
        twentyBoard = twentyBoardManager.twentyBoard;
        tile1 = new TwentyTile(2, 2);
        twentyBoard.insertTile(2,0,tile1);
        twentyBoardManager.touchMove('U');
        assertEquals("twentyBoard.touchMove() failed test 4.", 4, twentyBoard.getTile(0,0).getId());
    }

    @Test
    public void testGetSize(){
        /* Test to see if the size of the board is returned correctly. */
        assertEquals("twentyBoardManager.getSize() failed test 1", 3, twentyBoardManager.getSize());
    }

    @Test
    public void testGetBoard(){
        /* Test to see if the twentyBoard initialized in twentyBoardManager is called correctly with the getBoard() method. */
        TwentyBoard twentyBoard = twentyBoardManager.twentyBoard;
        assertEquals("twentyBoardManager.getBoard() failed test 1",twentyBoard, twentyBoardManager.getBoard());
    }

    @Test public void testScore(){
        /* Test to see score on a blank board with a brand new randomly generated tile which can be 2, 4 , or 8 */
        twentyBoardManager = new TwentyBoardManager(3);
        int score = twentyBoardManager.score();
        assertThat("twentyBoardManager.score() failed test 1",score>=2 && score<=8);

        /* Test to see score on a board with an inserted Tile with a value of 4. The min is 4 because the first tile
         * may get replaced by the insertTile method. */
        twentyBoardManager = new TwentyBoardManager(3);
        tile1 = new TwentyTile(2, 2);
        twentyBoardManager.twentyBoard.insertTile(1,1,tile1);
        int score1 = twentyBoardManager.score();
        assertThat("twentyBoardManager.score() failed test 2",score1>=4 && score<=12);
    }


}
