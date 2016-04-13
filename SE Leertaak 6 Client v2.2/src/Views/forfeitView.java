package Views;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class forfeitView extends JPanel {

	private static final long serialVersionUID = 8160787261068616149L;
	private int choice;

	public forfeitView(){
	
		choice = choose();
	}
	
	private int choose(){
		return JOptionPane.showConfirmDialog(
                this,
                "Do you want to forfeit the match?",
                "Forfeit",
                JOptionPane.YES_NO_OPTION);
	}
	
	public int getChoice(){
		return choice;
	}
}
