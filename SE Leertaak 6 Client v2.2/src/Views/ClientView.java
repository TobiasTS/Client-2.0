package Views;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Main.ClientController;

public class ClientView extends JFrame {
	
	private static final int LOGINSCREEN = 1;
	private static final int LOBBYSCREEN = 2;
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<JPanel> listViews = new ArrayList<>();
	
	private ClientController controller;
	private LoginView loginView;
	private LobbyView lobbyView;
	
	private int currentScreen = 0;
	
	JMenu menuFile;
	
	public ClientView(String name, ClientController controller) {
		super(name);
		this.controller = controller;
		
		loginView = new LoginView();
		loginView.setActionListener(controller);
		registerView(loginView);
		
		lobbyView = new LobbyView(controller);
		registerView(lobbyView);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setJMenuBar(createMenuBar());
		
		add(loginView);
		setVisible(true);
		pack();
		setFrameMiddle();
	}
	
	public String getLoginName() {
		return loginView.getLoginName();
	}
	
	public void setFrameMiddle(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screenSize.width /4, screenSize.height /4); 									//Size
        setLocation(screenSize.width/2-getSize().width/2, screenSize.height/2-getSize().height/2); 	//Location
	}
	
	public void registerView(JPanel view) {
		listViews.add(view);
	}
	
	public void setView(JPanel view) {
		Iterator<JPanel> iterator = listViews.iterator();
		while (iterator.hasNext()) {
			JPanel next = iterator.next();
			next.setVisible(false);
			remove(next);
		}
		view.setVisible(true);
		add(view);
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
	
	public void setLobbyScreen() {
		if(currentScreen != LOBBYSCREEN) {
			clearScreen();
			try {
				controller.getModel().getGameListCommand();
				while (controller.getModel().getGameList() == null); //Wait for server
				lobbyView.createTable(controller, controller.getModel().getPlayerList());
				add(lobbyView);
				lobbyView.setVisible(true);
				menuFile.setEnabled(true);
				pack();
				currentScreen = LOBBYSCREEN;
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private JMenuBar createMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		//File menu
		menuFile = new JMenu("File");
		menuFile.setEnabled(false);
		//Player list item
		JMenuItem itemPlayerList = new JMenuItem("Display player list");
		itemPlayerList.setActionCommand(ClientController.COMMAND_GET_PLAYER_LIST);
		itemPlayerList.addActionListener(controller);
		//Game list item
		JMenuItem itemGameList = new JMenuItem("Display game list");
		itemGameList.setActionCommand(ClientController.COMMAND_GET_GAME_LIST);
		itemGameList.addActionListener(controller);
		
		//Log out menu item
		JMenuItem itemLogout = new JMenuItem("Log out");
		itemLogout.setActionCommand(ClientController.COMMAND_LOGOUT);
		itemLogout.addActionListener(controller);
		
		menuFile.add(itemPlayerList);
		menuFile.add(itemGameList);
		menuFile.addSeparator();
		menuFile.add(itemLogout);
		
		menuBar.add(menuFile);
		
		return menuBar;
	}
}
