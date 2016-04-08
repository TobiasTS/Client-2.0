package Model;

import Main.TicTacToeController;

public class TicTacToeModel {
	
	public static final int OPPONENT = 0;
    public static final int ME = 1;
    public static final char EMPTY = 'E';
    
	private TicTacToeController ticTacToeController;
	private char board[][];
	private boolean start;
	private int side;
	private char myChar, opponentChar;
	
	public TicTacToeModel(TicTacToeController ticTacToeController, int rowAndColumns, MatchModel match){
		this.ticTacToeController = ticTacToeController;
		board = new char[rowAndColumns][rowAndColumns];
		start = match.getPlayerToMove().equals(match.getOpponent()) ? false : true;
		side = start ? ME: OPPONENT; 
		initChars();
		clearBoard();
	}
	
	public char[][] getBoard() {
		return board;
	}
	
	public boolean doesStarts(){
		return start;
	}
	
	public int getSide() {
		return side;
	}
	
	public char getChar(int side) {
		if(side == ME) {
			return myChar;
		}
		else {
			return opponentChar;
		}
	}
		
	public char getMyChar() {
		return myChar;
	}

	public char getOpponentChar() {
		return opponentChar;
	}

	private void initChars() {
	    if (start) {
	    	myChar='X';
	    	opponentChar='O';
	    } else {
	    	myChar='O';
	    	opponentChar='X';
	    }
	}
	
	private void clearBoard() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = EMPTY;
            }
        }
    }
	
	public boolean isBoardFull() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidMove(int move) {
        return (move >= 0 && move <= 8 && board[move/3][move%3] == EMPTY);
    }
    
    public void changeSide(int side) {
    	this.side = side;
    }

	public void makeMove(int move) {
		if (side == ME ) {
			board[move/3][move%3] = myChar;
		} else if (side == OPPONENT) {
			board[move/3][move%3] = opponentChar;
		}
    }


}
