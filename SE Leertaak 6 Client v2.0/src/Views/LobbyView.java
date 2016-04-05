package Views;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class LobbyView extends JPanel implements View {

	private static final long serialVersionUID = 1L;
	private static final String COMMANDCHALLENGE = "CHALLENGE";
	private ArrayList<String> listPlayers;
	private ArrayList<String> listGames;
	private ArrayList<JButton> listButtons;
	/*
	private JButton updatePlayerListButton;
	*/
	
	public LobbyView(ArrayList<String> playerNames, ArrayList<String> gameNames) {
		this.listPlayers = playerNames;
		this.listGames = gameNames;
		listButtons = new ArrayList<>();
		setLayout(new GridLayout(playerNames.size(), gameNames.size()));
		createObjectsOfPanel();
		
	}
	
	@Override
	public void createObjectsOfPanel() {
		Iterator<String> iteratorPlayers = listPlayers.iterator();
		while(iteratorPlayers.hasNext()){
			String nextPlayer = iteratorPlayers.next();
			JLabel playerName = new JLabel(nextPlayer);
			add(playerName);
			Iterator<String> iteratorGames = listGames.iterator();
			while(iteratorGames.hasNext()){
				String nextGame = iteratorGames.next();
				JButton buttonGame = new JButton(nextGame);
				buttonGame.setActionCommand(COMMANDCHALLENGE + " " + nextPlayer + " " + nextGame);
				add(buttonGame);
				listButtons.add(buttonGame); // add to list so that we can set actionListener later on
			}	
		}
		
	}

	@Override
	public void addToPanel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setActionListener(ActionListener a) {
		Iterator<JButton> iteratorButton = listButtons.iterator();
		while(iteratorButton.hasNext()) {
			iteratorButton.next().addActionListener(a);
		}
	}

}
