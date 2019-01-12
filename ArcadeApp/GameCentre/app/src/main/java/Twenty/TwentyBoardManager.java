package Twenty;

import java.time.Instant;

import fall2018.csc2017.CoreClasses.Board;
import fall2018.csc2017.CoreClasses.BoardManager;
import fall2018.csc2017.CoreClasses.AccountManager;

public class TwentyBoardManager extends BoardManager {

    /**
     * The board being managed.
     */
    protected TwentyBoard twentyBoard;

    @SuppressWarnings("unchecked")
    public TwentyBoardManager(TwentyGameFile gameFile) {
        super(gameFile);
        this.gameStates = gameFile.getGameStates();
        AccountManager.activeAccount.addGameFile(gameFile);
        if (!gameFile.getGameStates().isEmpty()) {
            this.twentyBoard = (TwentyBoard) gameFile.getGameStates().peek();
            super.setSize(this.twentyBoard.getNumCols());
        }
    }

    /**
     * Manage a new blank board.
     * @param size: the size of this new board.
     */
    @SuppressWarnings("unchecked")
    public TwentyBoardManager(int size) {
        super(size);
        TwentyTile boardTiles[][] = new TwentyTile[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                boardTiles[i][j] = new TwentyTile(0,0);
            }
        }

        // Create the board, and specify number of rows, columns
        this.twentyBoard = new TwentyBoard(boardTiles, size, size);
        this.twentyBoard.generateRandomTile();
        TwentyGameFile gameFile = new TwentyGameFile(this.twentyBoard, Instant.now().toString());
        setGameFile(gameFile);
        save(this.twentyBoard);
    }

    /**
     * Checks if a move is valid, and if so moves & merges the tiles in that direction
     * as much as possible
     * @param dir: int which indicated the direction of the swipe. 0 for up, 1 for down,
     *           2 for left, 3 for right.
     * Preconditions: dir is an element of: {0, 1, 2, 3}
     */
    @Override
    public void touchMove(int dir){
        TwentyBoard newBoard = twentyBoard.createDeepCopy();
        this.twentyBoard = newBoard;
        System.out.println("Direction: " + dir);
        System.out.println("Before the move:");
        for(int i =0; i<3; i++){
            System.out.println(this.twentyBoard.getTile(i,0).id + " " +this.twentyBoard.getTile(i,1).id + " "+this.twentyBoard.getTile(i,2).id);
        }
        System.out.println("During the move!");
        boolean boardChanged = false;
        if(dir == 0 && isValidMove(dir)){
            boardChanged = mergeTilesInDir('U');
        }else if(dir == 1 && isValidMove(dir)){
            boardChanged = mergeTilesInDir('D');
        }else if(dir == 2 && isValidMove(dir)){
            boardChanged = mergeTilesInDir('L');
        }else if(dir == 3 && isValidMove(dir)){
            boardChanged = mergeTilesInDir('R');
        }

        if(boardChanged){
            this.twentyBoard.generateRandomTile();
            this.gameFile.addUndos();
        }
        System.out.println("After the move:");
        for(int i =0; i<3; i++){
            System.out.println(this.twentyBoard.getTile(i,0).id + " " +this.twentyBoard.getTile(i,1).id + " "+this.twentyBoard.getTile(i,2).id);
        }
        save(this.twentyBoard);
        setChanged();
        notifyObservers();
    }

    /**
     * Given a direction, move the tiles in the board in that direction as much as possible.
     * @param dir: char which indicated the direction of the swipe.
     * Preconditions: dir is an element of: {'U', 'D', 'L', 'R'}
     * Postconditions: The board will be altered so all tiles are moved as much as possible in
     *      the direction dir. Also all tiles of equal id will collapse into one tile.
     */
    private boolean mergeTilesInDir(char dir){
        TwentyTile tile1, tile2;
        int tile1Row = 0, tile2Row = 0, tile1Col = 0, tile2Col = 0;
        int sqBoardSize = this.twentyBoard.getNumRows();
        boolean boardChanged = false;
        // This algorithm must be done three times for correctness.
        // Although it is O(N^2) with NxN board, the board typically won't be greater than 5x5.
        for(int i = 0; i<sqBoardSize-1; i++){
            for(int j = 0; j < sqBoardSize; j++){
                for(int k = 0; k < sqBoardSize - 1; k++){
                    if(dir == 'U'){
                        tile1Row = k;
                        tile1Col = j;
                        tile2Row = k+1;
                        tile2Col = j;
                    }else if(dir == 'D'){
                        tile1Row = k+1;
                        tile1Col = j;
                        tile2Row = k;
                        tile2Col = j;
                    }else if(dir == 'L'){
                        tile1Row = j;
                        tile1Col = k;
                        tile2Row = j;
                        tile2Col = k+1;
                    }else if(dir == 'R'){
                        tile1Row = j;
                        tile1Col = k+1;
                        tile2Row = j;
                        tile2Col = k;
                    }
                    tile1 = (TwentyTile)this.twentyBoard.getTile(tile1Row, tile1Col);
                    tile2 = (TwentyTile)this.twentyBoard.getTile(tile2Row, tile2Col);

                    if(tile1.getId() == 0){
                        this.twentyBoard.swapTiles(tile1Row, tile1Col, tile2Row, tile2Col);
                        if(tile2.getId() != 0){
                            boardChanged = true;
                        }
                    } else if(tile1.getId() == tile2.getId()) {
                        this.twentyBoard.mergeTiles(tile1Row, tile1Col, tile2Row, tile2Col);
                        boardChanged = true;
                    }
                }
            }
        }
        return boardChanged;
    }

    /* Getter for the GameFile that this board manager exists in.
     * @return the game file this board manager exists in.
     */
    public TwentyGameFile getTwentyGameFile(){
        return (TwentyGameFile) getGameFile();
    }

    /* Gauges whether or not the game is complete, i.e, no more possible moves can be made. */
    @Override
    public boolean gameComplete(){
        return !isValidMove(0) && !isValidMove(1) && !isValidMove(2) && !isValidMove(3);
    }

    /* Checks if a swipe results in a change in this TwentyBoard
     * @param dir: int which indicated the direction of the swipe. 0 for up, 1 for down,
     *           2 for left, 3 for right.
     * Preconditions: dir is an element of: {0, 1, 2, 3}
     */
    @Override
    public boolean isValidMove(int dir){
        return this.twentyBoard.isCollapsable(dir == 2 || dir == 3);
    }

    /**
     * Accesses the protected Board
     */
    @Override
    public TwentyBoard getBoard(){
        return this.twentyBoard;
    }

    /**
     * Saves a new state of board to game.
     *
     * @param board a board
     */
    @SuppressWarnings("unchecked")
    public void save(TwentyBoard board) {
        super.save(board);
        this.gameStates = getTwentyGameFile().getGameStates();
        this.twentyBoard = board;
        System.out.println("Board saved!");
    }

    /**
     * Switches the board back one move, if the user has undos left
     */
    @Override
    public Board undo() {
        if (getTwentyGameFile().getRemainingUndos() > 0) {
            this.twentyBoard = (TwentyBoard) super.undo();
        }
        setChanged();
        notifyObservers();

        return this.twentyBoard;
    }

    /**
     *
     * Returns the calculated score
     */
    @Override
    public int score() {
        int finalScore = 0;
        for (int i = 0; i < this.getSize(); i++){
            for (int j = 0; j < this.getSize(); j++){
                finalScore += this.twentyBoard.getTile(i,j).getId();
            }
        }
        return finalScore;
    }

}