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
	private ImageIcon xImage;
	private ImageIcon oImage;
	
	/**
	 * constructor for a new Tic Tac Toe button.
	 * 
	 * @param value int that indicates the position of the button.
	 */
	public TicTacToeButton(int value) {
		super(String.valueOf(value));
//		xImage = new ImageIcon(this.getClass().getResource("/Images/X.png"));
//		oImage = new ImageIcon(this.getClass().getResource("/Images/O.png"));
	}
	
	/**
	 * Sets move by changing the icon and disables the button.
	 * 
	 * @param type int indicating X or O.
	 */
	public void setMove(int type) {
		if(type == 0) {
			setIcon(xImage);
		}
		else {
			setIcon(oImage);
		}
		setEnabled(false);
	}
	
	/**
	 * Clears the button.
	 */
	public void clearButton() {
		setIcon(null);
	}
	

}
