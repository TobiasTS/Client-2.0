package Views;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Main.ClientController;

public class ClientView extends JFrame {
	
	private static final int LOGINSCREEN = 1;
	private static final int LOBBYSCREEN = 2;


	private static final long serialVersionUID = 1L;
	private ClientController controller;
	
	private LoginView loginView;
	private LobbyView lobbyView;
	
	private int currentScreen = 0;
	
	public ClientView(String name, ClientController controller) {
		super(name);
		this.controller = controller;
		
		loginView = new LoginView();
		loginView.setActionListener(controller);
		
		add(loginView);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setName("Client v2.2");
		setVisible(true);
		pack();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //setBounds(0,0,screenSize.width /4, screenSize.height /4); 									//Size
        setLocation(screenSize.width/2-getSize().width/2, screenSize.height/2-getSize().height/2); 	//Location
	}
	
	public String getLoginName() {
		return loginView.getLoginName();
	}
	
	public void setLobbyScreen() {
		/*
		if(currentScreen != LOBBYSCREEN) {
			clearScreen();
			
			ArrayList<String> a = new ArrayList<>();
			a.add("game1");
			a.add("game2");
			
			ArrayList<String> b = new ArrayList<>();
			b.add("game1");
			b.add("game2");
			
			lobbyView = new LobbyView(b);
			
			try {
				controller.getModel().getPlayerListCommand();
				ArrayList<String> c = controller.getModel().getPlayerList();
				while(c == null) {
					
				}
				lobbyView.setPlayerList(c);
				lobbyView.setActionListener(controller);

			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			
			add(lobbyView);
			lobbyView.setVisible(true);
			pack();
			currentScreen = LOBBYSCREEN;
		}
		*/
		if(currentScreen != LOBBYSCREEN) {
			clearScreen();
			
			lobbyView = new LobbyView(controller.getModel().getPlayerList());
			lobbyView.setActionListener(controller);

			add(lobbyView);
			lobbyView.setVisible(true);
			
			pack();
		}
	}
	
	public void clearScreen() {
		if(lobbyView != null) {
			remove(lobbyView);
			lobbyView.setVisible(false);
		}
		remove(loginView);
		loginView.setVisible(false);
	}
	
	public void setLoginScreen() {
		if(currentScreen != LOGINSCREEN) {
			clearScreen();
			add(loginView);
			loginView.setVisible(true);
			pack();
			currentScreen = LOGINSCREEN;
		}

	}
}
