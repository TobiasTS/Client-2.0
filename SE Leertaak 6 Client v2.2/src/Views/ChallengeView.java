package Views;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChallengeView extends JPanel {

	private static final long serialVersionUID = 8160787261068616149L;
	private String choice;

	public ChallengeView(String challenged, ArrayList<String> gameList){
		
		Object[] possibilities = new String[gameList.size()];
		possibilities = gameList.toArray(possibilities);
		
		choice = choose(challenged, possibilities);
	}
	
	private String choose(String challenged, Object[] possibilities){
		return (String)JOptionPane.showInputDialog(
                this,
                "Choose game you wish to play",
                "Challenge " + challenged,
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                possibilities[0]);
	}
	
	public String getChoice(){
		return choice;
	}
}
