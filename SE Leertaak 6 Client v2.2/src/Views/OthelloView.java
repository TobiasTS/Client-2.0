package Views;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Main.OthelloController;

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

	public void updateView(char[][] board) {
		for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
            	if (board[i][j] == othelloController.getOthelloModel().BLACK) {
            		buttons[i][j].setIcon(bImage);
            	} else if (board[i][j] == othelloController.getOthelloModel().WHITE) {
            		buttons[i][j].setIcon(wImage);
            	}
            }
		}
	}
}
