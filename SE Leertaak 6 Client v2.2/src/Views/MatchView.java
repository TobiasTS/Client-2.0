package Views;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MatchView extends JPanel {

	private static final long serialVersionUID = 4845752766490037879L;

	private JLabel playerToMove;
	
	public MatchView(String game, String opponent){
		setLayout(new GridLayout(4, 0));
		add(new JLabel("Playing: " + game));
		add(new JLabel("Opponent: " + opponent));
		playerToMove = new JLabel();
		add(playerToMove);
	}	
	
	public void updatePlayerToMove(String playerToMove) {
		this.playerToMove.setText(playerToMove);
	}
}
