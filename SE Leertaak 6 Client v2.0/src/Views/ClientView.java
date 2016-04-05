package Views;
import javax.swing.JFrame;

import Main.ClientController;

public class ClientView extends JFrame {

	private static final long serialVersionUID = 1L;
	private ClientController controller;
	
	private LoginView loginView;
	private LobbyView lobbyView;
	
	public ClientView(ClientController controller) {
		this.controller = controller;
		
		loginView = new LoginView();
		loginView.setActionListener(controller);
		
		lobbyView = new LobbyView();
		lobbyView.setActionListener(controller);
		
		add(loginView);
		
		this.setVisible(true);
		this.pack();
	}
	
	public String getLoginName() {
		return loginView.getLoginName();
	}
	
	public void setLobbyScreen() {
		clearScreen();
		add(lobbyView);
		pack();
	}
	
	public void clearScreen() {
		remove(loginView);
		remove(lobbyView);
		loginView.setVisible(false);
		lobbyView.setVisible(false);
	}
	
	public void setLoginScreen() {
		clearScreen();
		add(loginView);
		loginView.setVisible(true);
		setVisible(true);
		pack();
	}
	
	

}
