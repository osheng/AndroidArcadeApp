package Checkers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckersTileTest {

    private CheckersTile redPawn;
    private CheckersTile whitePawn;

    @Before
    public void setup(){
        redPawn = new CheckersTile(CheckersTile.RED_PAWN);
        whitePawn = new CheckersTile(CheckersTile.WHITE_PAWN);
    }

    @Test
    public void testHighlightTile(){
        redPawn.highlight();
        assertEquals("CheckersTile.highlight() failed", CheckersTile.RED_PAWN + CheckersTile.HIGHLIGHTED, redPawn.getCheckersId());

    }

    @Test
    public void testDehighlightTile(){
        redPawn.highlight();
        redPawn.dehighlight();
        assertEquals("CheckersTile.dehighlight() failed", CheckersTile.RED_PAWN, redPawn.getCheckersId());
    }

    @Test
    public void testChangeTile(){
        whitePawn.changeTile(CheckersTile.EMPTY_WHITE_TILE);
        assertEquals("CheckersTile.changeTile() failed", CheckersTile.EMPTY_WHITE_TILE, whitePawn.getCheckersId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentInConstructor(){
        CheckersTile purplePawn = new CheckersTile("purplePawn");
    }
}
