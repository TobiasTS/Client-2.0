package Views;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Button for the Tic Tac Toe field.
 * 
 * @author Tobias Schlichter
 * @version 1.0
 */
public class TicTacToeButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	private ImageIcon defaultImage = new ImageIcon(this.getClass().getResource("/images/E.png"));
	private ImageIcon xImage = new ImageIcon(this.getClass().getResource("/images/X.png"));
	private ImageIcon oImage = new ImageIcon(this.getClass().getResource("/images/O.png"));
	
	/**
	 * constructor for a new Tic Tac Toe button.
	 * 
	 * @param value int that indicates the position of the button.
	 */
	public TicTacToeButton() {
		setIcon(defaultImage);
	}
	
	/**
	 * Sets move by changing the icon and disables the button.
	 * 
	 * @param type int indicating X or O.
	 */
	public void setIcon(char icon) {
		if(icon == 'X') {
			setIcon(xImage);
		}
		else if (icon == 'O') {
			setIcon(oImage);
		}
		setEnabled(false);
	}
	
	/**
	 * Clears the button.
	 */
	public void clearButton() {
		setIcon(defaultImage);
	}
	

}
