package Views;

import java.awt.GridLayout;

import javax.swing.JPanel;

import Main.TicTacToeController;
import Model.TicTacToeModel;

public class TicTacToeView extends JPanel {
	
	private static final long serialVersionUID = -6442309963858143796L;
	private TicTacToeButton[][] buttons;
	private TicTacToeController ticTacToeController;
	
	public TicTacToeView(TicTacToeController ticTacToeController, int rowsAndColumns) {
		super(new GridLayout(rowsAndColumns, rowsAndColumns));
		this.ticTacToeController = ticTacToeController;
		buttons = new TicTacToeButton[rowsAndColumns][rowsAndColumns];
		createButtons(ticTacToeController);
	}

	public void createButtons(TicTacToeController ticTacToeController) {	
		int move = 0;
		for(int i = 0; i < buttons.length; i++) {
            for(int j = 0; j < buttons[i].length; j++) {
            	buttons[i][j] = new TicTacToeButton();
            	buttons[i][j].addActionListener(ticTacToeController);
            	buttons[i][j].setActionCommand(TicTacToeController.COMMAND_MOVE + " "+ move);
            	add(buttons[i][j]);
            	move++;
            }
		}
	}
	
	public void lockButtons() {
		for(int i = 0; i < buttons.length; i++) {
            for(int j = 0; j < buttons[i].length; j++) {
            	buttons[i][j].setEnabled(false);
            }
		}
	}
	
	public void unlockButtons() {
		int board[][] = ticTacToeController.getTicTacToeModel().getBoard();
		for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if (board[i][j] == TicTacToeModel.EMPTY) {
                	buttons[i][j].setEnabled(true);
                }
            }
		}
	}
	
	public void updateView(int x, int y, int icon) {
		buttons[x][y].setEnabled(false);
		buttons[x][y].setIcon(icon);
		buttons[x][y].repaint();
	}
}
