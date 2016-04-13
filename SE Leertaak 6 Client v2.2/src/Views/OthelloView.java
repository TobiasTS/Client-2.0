package Views;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Main.OthelloController;
import Model.TicTacToeModel;

public class OthelloView extends JPanel {

	private ImageIcon defaultImage = new ImageIcon("images/E.png");
	private ImageIcon bImage = new ImageIcon("images/B.png");
	private ImageIcon wImage = new ImageIcon("images/W.png");
	
	private static final long serialVersionUID = -7446106282897877074L;
	private OthelloController othelloController;
	private JButton buttons[][]; 
	
	public OthelloView(OthelloController othelloController, int rowsAndColumns) {
		super(new GridLayout(rowsAndColumns, rowsAndColumns));
		buttons = new JButton[rowsAndColumns][rowsAndColumns];
		this.othelloController = othelloController;
		createButtons(othelloController);
	}

	private void createButtons(OthelloController othelloController) {
		for(int i = 0; i < buttons.length; i++) {
            for(int j = 0; j < buttons[i].length; j++) {
            	buttons[i][j] = new JButton();
            	buttons[i][j].setIcon(defaultImage);
            	buttons[i][j].addActionListener(othelloController);
            	buttons[i][j].setActionCommand(OthelloController.COMMAND_MOVE + " "+ i + "," + j);
            	add(buttons[i][j]);
            }
		}
	}
	
	public void lockButtons() {
		int board[][] = othelloController.getOthelloModel().getBoard();
		for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if (board[i][j] == TicTacToeModel.EMPTY) {
                	buttons[i][j].setEnabled(false);
                }
            }
		}
	}
	
	public void unlockButtons() {
		int board[][] = othelloController.getOthelloModel().getBoard();
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
		if (icon == 1) buttons[x][y].setIcon(bImage);
		else buttons[x][y].setIcon(wImage);		
		buttons[x][y].repaint();
	}

	public void updateView(int[][] board) {
		for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
            	if (board[i][j] == 1) {
            		buttons[i][j].setIcon(bImage);
            		buttons[i][j].repaint();
            	} else if (board[i][j] == 0) {
            		buttons[i][j].setIcon(wImage);
            		buttons[i][j].repaint();
            	}
            }
		}
	}
}
