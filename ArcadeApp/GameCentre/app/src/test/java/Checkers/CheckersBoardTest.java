package Checkers;


import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class CheckersBoardTest {

    private CheckersBoard board;

    private static final int size = 8;

    @Before
    public void setup(){
        CheckersTile[][] tiles = new CheckersTile[size][size];
        String id;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if ((row + col) % 2 == 0) {
                    id = CheckersTile.BLACK_TILE;
                } else {
                    id = CheckersTile.EMPTY_WHITE_TILE;
                }
                tiles[row][col] = new CheckersTile(id);
            }
        }
        board = new CheckersBoard(tiles, size);
        addPiece(board, 3,2, CheckersTile.WHITE_PAWN);
        addPiece(board, 4,3, CheckersTile.RED_PAWN);
    }

    static void addPiece(CheckersBoard testBoard, int row, int column, String type){
        testBoard.getCheckersTile(row,column).changeTile(type);
    }
    @Test
    public void testSetHighlightedTile(){
        addPiece(board, 3,2, CheckersTile.WHITE_PAWN);
        board.setHighLightedTile(3,2);
        assertTrue("setHighLightedTile faild",
                (CheckersTile.WHITE_PAWN + CheckersTile.HIGHLIGHTED).equals(
                        board.getCheckersTile(3,2).getCheckersId()));

    }
    @Test(expected = NullPointerException.class)
    public void testSetHighlightedTileExpectException(){
        board.setHighLightedTile(0,0);
    }

    @Test
    public void testMaybeMakeKing(){
        addPiece(board, size-2, size-2, CheckersTile.WHITE_PAWN);
        board.swapTiles(size-2,size-2,size-1, size-1);
        assertTrue("Test maybeMakeKing white",CheckersTile.WHITE_KING.equals(
                board.getCheckersTile(size-1,size-1).getCheckersId()));

        addPiece(board, 1,size-2, CheckersTile.RED_PAWN);
        board.swapTiles(1,size-2, 0,size-1);
        assertTrue("Test maybeMakeKing red failed",CheckersTile.RED_KING.equals(
                board.getCheckersTile(0,size-1).getCheckersId()));

    }

    @Test
    public void testCreateDeepCopy(){
        CheckersBoard boardCopy = board.createDeepCopy();
        assertTrue(boardCopy!=board);
        for (int i=0; i < size; i++){
            for (int j = 0; j < size; j++){
                assertTrue(boardCopy.getCheckersTile(i,j).getCheckersId().equals(
                        board.getCheckersTile(i,j).getCheckersId()
                ));
            }
        }

    }

}
