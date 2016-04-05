package Views;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.ClientController;

public class ClientView extends JFrame {

	private static final long serialVersionUID = 1L;
	private ClientController controller;
	
	private LoginView loginView;
	private LobbyView lobbyView;
	private LinkedList<JPanel> listPanels = new LinkedList<>();
	
	
	public ClientView(ClientController controller) {
		this.controller = controller;
		
		loginView = new LoginView();
		loginView.setActionListener(controller);
		listPanels.add(loginView);
		// ------------------- TEST
		ArrayList<String> playerNames = new ArrayList<>();
		playerNames.add("Jan");
		playerNames.add("Peter");
		playerNames.add("Bob");
		ArrayList<String> gameNames = new ArrayList<>();
		gameNames.add("BO3");
		gameNames.add("FIFA16");
		// ------------------- TEST
		lobbyView = new LobbyView(playerNames, gameNames);
		lobbyView.setActionListener(controller);
		listPanels.add(lobbyView);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(loginView);
		
		pack();
		setMiddle();		
		setVisible(true);
		
	}
	
	public String getLoginName() {
		return loginView.getLoginName();
	}
	
	private void setMiddle(){
		// Set Frame size and location
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screenSize.width /4, screenSize.height /4); 									//Size
        setLocation(screenSize.width/2-getSize().width/2, screenSize.height/2-getSize().height/2); 	//Location
	}
	
	public void clearScreen() {
		Iterator<JPanel> iteratorPanels = listPanels.iterator();
		while (iteratorPanels.hasNext()){
			JPanel next = iteratorPanels.next();
			remove(next);
			next.setVisible(false);
		}
	}
	
	public void setLoginScreen() {
		clearScreen();
		add(loginView);
		loginView.setVisible(true);
		setVisible(true);
		pack();
	}
	
	public void setLobbyScreen() {
		clearScreen();
		add(lobbyView);
		lobbyView.setVisible(true);
		setVisible(true);
		pack();
	}
}
