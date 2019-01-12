package fall2018.csc2017.CoreClasses;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameScoreTest {

    @Test
    public void testGetGameName() {
        GameScore testGameScore = new GameScore(Game.CHECKERS_NAME,"currentgame","user",75);
        Assert.assertEquals("GameScore.getGameName() Test 1 failed",Game.CHECKERS_NAME,testGameScore.getGameName());
    }

    @Test
    public void testCompareTo() {
        GameScore testGameScore1 = new GameScore(Game.CHECKERS_NAME,"currentgame1","user1",75);
        GameScore testGameScore2 = new GameScore(Game.CHECKERS_NAME,"currentgame2","user2",100);
        GameScore testGameScore3 = new GameScore(Game.CHECKERS_NAME,"currentgame3","user3",75);

        Assert.assertEquals("GameScore.compareTo() Test 1 failed",-1,testGameScore1.compareTo(testGameScore2));
        Assert.assertEquals("GameScore.compareTo() Test 2 failed",0,testGameScore1.compareTo(testGameScore3));
        Assert.assertEquals("GameScore.compareTo() Test 3 failed",1,testGameScore2.compareTo(testGameScore3));
    }

    @Test
    public void TestToString() {
        GameScore testGameScore = new GameScore(Game.CHECKERS_NAME,"currentgame","user",75);
        Assert.assertEquals("GameScore.toString() Test 1 failed",
                Game.CHECKERS_NAME + ", " + "currentgame" + ", " + "user" + ", " + 75, testGameScore.toString());
    };

}