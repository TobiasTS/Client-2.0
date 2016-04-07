package Views;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import Main.TicTacToeController;

public class TicTacToeView extends JPanel {
	
	
	private static final long serialVersionUID = -6442309963858143796L;
	private TicTacToeButton[] buttons;
	
	public TicTacToeView(TicTacToeController ticTacToeController, int rowsAndColumns) {
		super(new GridLayout(rowsAndColumns, rowsAndColumns));
		buttons = new TicTacToeButton[rowsAndColumns * rowsAndColumns];
		createButtons(ticTacToeController, rowsAndColumns * rowsAndColumns);
	}

	
	public void createButtons(TicTacToeController ticTacToeController, int numberOfRowsAndColumns) {		
		for(int i = 0; i < numberOfRowsAndColumns; i++) {
			buttons[i] = new TicTacToeButton(i);
			buttons[i].setActionCommand(TicTacToeController.COMMAND_MOVE + i);
			buttons[i].addActionListener(ticTacToeController);
			add(buttons[i]);
		}

	}
	
//	public void setYourTurn(boolean yourTurn) {
//		if(yourTurn) {
//			unlockButtons();
//		}
//		else {
//			lockButtons();
//		}
//	}
	
//	private void lockButtons() {
//		for(int i = 0; i < 9; i++) {
//			buttons[i].setEnabled(false);
//		}
//	}
//	
//	private void unlockButtons() {
//		for(int i = 0; i < 9; i++) {
//			if(board[i]) {
//				buttons[i].setEnabled(true);
//			}
//		}
//	}
}
