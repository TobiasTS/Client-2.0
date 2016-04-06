package Views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LobbyView extends JPanel implements View {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> players;
	
	private JButton updatePlayerListButton;
	
	private ArrayList<JLabel> playerNames;
	private ArrayList<JButton> playerButtons;
	
	private JPanel buttonPanel = new JPanel();

	public LobbyView(ArrayList<String> players) {
		this.players = players;
		this.setLayout(new BorderLayout(2, 2));
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(players.size() + 1, 2));
		
		playerNames = new ArrayList<>();
		playerButtons = new ArrayList<>();
		
		createObjectsOfPanel();
	}
	
	private void createTable() {
		for(int i = 0; i < players.size(); i++) {
			playerNames.add(new JLabel(players.get(i)));
			playerButtons.add(new JButton("CHALLENGE"));
			playerButtons.get(i).setActionCommand("CHALLENGE" + i);
			
			buttonPanel.add(playerNames.get(i));
			buttonPanel.add(playerButtons.get(i));
		}
	}
	
	@Override
	public void createObjectsOfPanel() {
		createTable();
		updatePlayerListButton = new JButton("Update playerlist");
		updatePlayerListButton.setActionCommand("UPDATEPLAYERLIST");
		add(buttonPanel, BorderLayout.NORTH);
		add(updatePlayerListButton, BorderLayout.SOUTH);
	}

	@Override
	public void setActionListener(ActionListener a) {
		for(int i = 0; i < players.size(); i++) {
			playerButtons.get(i).addActionListener(a);
		}
		updatePlayerListButton.addActionListener(a);
	}

	@Override
	public void addToPanel() {		
	}

}
