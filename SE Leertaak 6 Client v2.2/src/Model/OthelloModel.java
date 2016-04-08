package Model;

public class OthelloModel {

	public final int OPPONENT = 0;
    public final int ME = 1;
    public static final char EMPTY = 'E';
    public static final char BLACK = 'B';
    public static final char WHITE = 'W';
    private static final int[] OFFSET_X = {-1, -1, -1,  0,  0,  1,  1,  1};
	private static final int[] OFFSET_Y = {-1,  0,  1, -1,  1, -1,  0,  1};
    
	
    private char board[][];
	private boolean iStart;
	private int side;
	private char myChar, opponentChar;
	
	public OthelloModel(int rowAndColumns, MatchModel match){
		board = new char[rowAndColumns][rowAndColumns];
		iStart = match.getPlayerToMove().equals(match.getOpponent()) ? false : true;
		side = iStart ? ME: OPPONENT; 
		initChars();
		clearBoard();
		initBoard();
	}
	
	public char[][] getBoard() {
		return board;
	}
	
	public int getSide() {
		return side;
	}
	
	public char getMyChar() {
		return myChar;
	}

	public char getOpponentChar() {
		return opponentChar;
	}
	
	public int getOpponent() {
		return OPPONENT;
	}

	public int getMe() {
		return ME;
	}
	
	public void switchSide() {
		side = side == ME ? OPPONENT: ME;  
	}

	private void initBoard() {
		board[3][3] = WHITE;
		board[3][4] = BLACK;
		board[4][3] = BLACK;
		board[4][4] = WHITE;
	}

	private void clearBoard() {
	  for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = EMPTY;
            }
        }
	}

	private void initChars() {
	 if (iStart) {
	    	myChar = BLACK;
	    	opponentChar = WHITE;
	    } else {
	    	myChar = WHITE;
	    	opponentChar = BLACK;
	    }
	}
	
	public void placeMove(int X, int Y, char player, char opponent){
		for(int i=0; i < 8; i++){
			int xOffset = X + OFFSET_X[i];
			int yOffset = Y + OFFSET_Y[i];
			
			if(!isValid(xOffset, yOffset)) {
				continue;
			}
			if(board[xOffset][yOffset] == opponent){
				if(offsetCheck(xOffset, yOffset, i, player, opponent)){
						updateTiles(xOffset, yOffset, i, player, opponent);
	
				}
			}
		}
		
	}
	
	/**
	 * Recursive - checks if theres a currentplayer coin on the other side
	 * 
	 * @param X starting row
	 * @param Y starting column
	 * @param i direction to check
	 * @return true if theres a current player coin on the other side
	 */
	private boolean offsetCheck(int X, int Y, int i, char player, char opponent) {
		
		int xOffset = X + OFFSET_X[i];
		int yOffset = Y + OFFSET_Y[i];
		
		if(!isValid(xOffset, yOffset)) {
			return false;
		}
		if(board[xOffset][yOffset] == player){
			return true;
		}
		if(board[xOffset][yOffset] == EMPTY){
			return false;
		}
		return offsetCheck(xOffset, yOffset, i, player, opponent);
	
	}

	/**
	 * When a move is legal, all the tiles in that row should be switched
	 * 
	 * @param X row
	 * @param Y column
	 * @param i direction
	 */
	private void updateTiles(int X, int Y, int i, char player, char opponent) {
		board[X][Y] = player;
		
		int xOffset = X + OFFSET_X[i];
		int yOffset = Y + OFFSET_Y[i];
		
		if(!isValid(xOffset, yOffset)) {
			return;
		}
		if(board[xOffset][yOffset] == player){
			return;
		}
		
		if(board[xOffset][yOffset] == opponent){
			updateTiles(xOffset, yOffset, i,  player, opponent);
		}
	}
	
	/**
	 * returns true if X and Y are within the board
	 * @param X row
	 * @param Y columns
	 * @return boolean
	 */
	private boolean isValid(int X, int Y){
		if(X >= 0 && X <= 7 && Y >= 0 && Y <= 7) {
			return true;
		}
		return false;
	}	
}
