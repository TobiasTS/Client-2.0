package Model;

import java.util.Arrays;

public class TicTacToeAI {
	
	private static final int OPPONENT = 0;
    private static final int COMPUTER = 1;
    private static final int EMPTY = 2;

    private static final int OPPONENT_WIN = 0;
    private static final int DRAW = 1;
    private static final int UNCLEAR = 2;
    private static final int COMPUTER_WIN = 3;
    
    private int side;
    private int position = UNCLEAR;
    private char computerChar, humanChar;

    private int[][] board = new int[3][3];

    public TicTacToeAI() {
    	clearBoard();
    	initSide();
    }
    
    public void setComputerPlays() {
        side = COMPUTER;
        initSide();
    }
    
    public void setOpponentPlays() {
        side = OPPONENT;
        initSide();
    }
    
    private boolean isValidMove(int move) {
        return (move >= 0 && move <= 8 && board[move/3][move%3] == EMPTY);
    }
    
    private void initSide() {
        if (this.side == COMPUTER) {
            computerChar='X';
            humanChar='O';
        } else {
            computerChar='O';
            humanChar='X';
        }
    }
    
    private void clearBoard() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = EMPTY;
            }
        }
    }
    
    private boolean isBoardFull() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
       
    public void makeMove(int move) {
        board[move/3][move%3] = side;
        side = side == COMPUTER ? OPPONENT : COMPUTER;
    }
    
    public void place(int row, int column, int piece) {
        board[row][column] = piece;
    }
    
    public int chooseMove() {
        Best best = chooseMove(COMPUTER);
        return best.row * 3 + best.column;
    }
   
    private Best chooseMove(int side) {
    	int opp; // The other side
    	 
        if (side == COMPUTER)
            opp = OPPONENT;
        else
            opp = COMPUTER;
 
        Best reply; // Opponent's best reply
 
        if (side == COMPUTER)
            reply = new Best(OPPONENT_WIN);
        else
            reply = new Best(COMPUTER_WIN);
 
        int simpleEval; // Result of an immediate evaluation
 
        if ((simpleEval = positionValue()) != UNCLEAR)
            return new Best(simpleEval);
 
        for (int i = 0; i < 9; i++) {
            if (isValidMove(i)) {
                // Place it on the board temporary
                place(i / 3, i % 3, side);
                Best turn = chooseMove(opp);
 
                // Check the side
                if (side == COMPUTER) {
                    if (reply.val < turn.val) {
                        reply.val = turn.val;
                        reply.row = i / 3;
                        reply.column = i % 3;
                    }
                } else {
                    if (reply.val > turn.val) {
                        reply.val = turn.val;
                        reply.row = i / 3;
                        reply.column = i % 3;
                    }
                }
                // Reset the board
                place(i / 3, i % 3, EMPTY);
            }
        }
 
        return reply;
    }

    protected int positionValue() {
        // Initialize arrays for the sums of rows, columns and diagonals
        String[] columns = new String[3];
        String[] rows = new String[3];
        String[] diagonals = new String[2];

        // Fill the arrays with an empty string
        Arrays.fill(columns, "");
        Arrays.fill(rows, "");
        Arrays.fill(diagonals, "");

        // Check column and row wins
        for (int i = 0; i < 3; i++) {
            diagonals[0] += board[i][i]+"";
            diagonals[1] += board[i][2-i]+ "";
            for (int j = 0; j < 3; j++) {
                columns[i] += board[j][i];
                rows[i] += board[i][j];
            }
        }

        // Check for column wins
        for (int i = 0; i < 3; i++) {
            if (rows[i].equals("111") || columns[i].equals("111")) {
                return COMPUTER_WIN;
            } else if (rows[i].equals("000") || columns[i].equals("000")) {
                return OPPONENT_WIN;
            }
        }

        // Check for diagonal wins
        for (int i = 0; i < 2; i++) {
            if (diagonals[i].equals("111")) {
                return COMPUTER_WIN;
            } else if (diagonals[i].equals("000")) {
                return OPPONENT_WIN;
            }
        }

        // Check if board is full (draw)
        if (isBoardFull()) {
            return DRAW;
        }

        return UNCLEAR;
    }

    private class Best {
        int row;
        int column;
        int val;

        Best(int val) {
            this(val, 0, 0);
        }

        Best (int val, int row, int column) {
            this.val = val;
            this.row = row;
            this.column = column;
        }
    }
}
