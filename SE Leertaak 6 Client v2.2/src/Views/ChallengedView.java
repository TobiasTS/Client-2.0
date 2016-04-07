package Views;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChallengedView extends JPanel {

	public static final int ORIGINAL_VALUE = -2;
	private static final long serialVersionUID = 338979938391081829L;
	int choice = ORIGINAL_VALUE;
	
	public ChallengedView(String challenger, String game){
		choice = choose(challenger, game);
	}
	
	private int choose(String challenger, String game) {
		return JOptionPane.showConfirmDialog(
			    this,
			    "Would you like to play " + game + " against " + challenger,
			    "A wild challenger appears",
			    JOptionPane.YES_NO_OPTION);
	}
	
	public int getChoice(){
		return choice;
	}
}
