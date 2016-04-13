package Views;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Main.ClientController;

public class ClientView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private ClientController controller;
	
	private LoginView loginView;
	private LobbyView lobbyView;
	private ArrayList<JPanel> listViews = new ArrayList<>();
	
	private JMenuBar menuBar;
	private JMenu menuFile;
	
	private JMenuBar menuBar2;
	private JMenu menuFile2;
	
	public ClientView(String name, ClientController controller) {
		super(name);
		this.controller = controller;
		
		loginView = new LoginView();
		loginView.setActionListener(controller);
		registerView(loginView);
		
		lobbyView = new LobbyView(controller);
		registerView(lobbyView);

		createMenuBar();
		setJMenuBar(menuBar);
		
		setView(loginView);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setName("Client v2.7");
		setVisible(true);
		pack();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screenSize.width /4, screenSize.height /4); 									//Size
        setLocation(screenSize.width/2-getSize().width/2, screenSize.height/2-getSize().height/2); 	//Location
	}
	
	public String getLoginName() {
		return loginView.getLoginName();
	}
	
	public void setLobbyScreen() {
		createLobby();
		menuFile.setEnabled(true);
		menuFile2.setEnabled(true);

		setView(lobbyView);
	}
	
	public void createLobby() {
		lobbyView.createTable(controller, controller.getModel().getPlayerList());
		
	}
	
	public void setLoginScreen() {
		menuFile.setEnabled(false);
		menuFile2.setEnabled(false);

		setView(loginView);
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
 		this.pack();
 		invalidate();
 		validate();
  	}
	
	private void createMenuBar() {
		menuBar = new JMenuBar();
		//File menu
		menuFile = new JMenu("Actions");
		menuFile.setEnabled(false);
		//Player list item
		JMenuItem itemPlayerList = new JMenuItem("Update player list");
		itemPlayerList.setActionCommand(ClientController.COMMAND_GET_PLAYER_LIST);
		itemPlayerList.addActionListener(controller);
		//Game list item
		JMenuItem itemGameList = new JMenuItem("Update game list");
		itemGameList.setActionCommand(ClientController.COMMAND_GET_GAME_LIST);
		itemGameList.addActionListener(controller);
		
		//Log out menu item
		JMenuItem itemLogout = new JMenuItem("Log out");
		itemLogout.setActionCommand(ClientController.COMMAND_LOGOUT);
		itemLogout.addActionListener(controller);
		
		menuFile.add(itemPlayerList);
		menuFile.add(itemGameList);
		menuFile.add(createSubscribeMenu());
		menuFile.addSeparator();
		menuFile.add(itemLogout);
		
		menuBar.add(menuFile);
		
		menuBar2 = new JMenuBar();
		//File menu
		menuFile2 = new JMenu("Options");
		menuFile2.setEnabled(false);
		//Player list item
		JMenuItem disableMessage = new JMenuItem("Disable Message");
		disableMessage.setActionCommand(ClientController.COMMAND_DISABLE_MESSAGE);
		disableMessage.addActionListener(controller);
		//Game list item
		JMenuItem enableMessage = new JMenuItem("Enable Message");
		enableMessage.setActionCommand(ClientController.COMMAND_ENABLE_MESSAGE);
		enableMessage.addActionListener(controller);
				
		menuFile2.add(disableMessage);
		menuFile2.add(enableMessage);
		
		menuBar.add(menuFile2);
	}

	private JMenu createSubscribeMenu() {
		JMenu menuSubscribe = new JMenu("Subscribe");
		ArrayList<String> games = new ArrayList<>();
		games.add(ClientController.GAME_TIC_TAC_TOE);
		games.add(ClientController.GAME_OTHELLO);
		Iterator<String> it = games.iterator();
		while (it.hasNext()) {
			String next = it.next();
			JMenuItem itemGame = new JMenuItem(next);
			itemGame.setActionCommand(ClientController.COMMAND_SUBSCRIBE + " " + next);
			itemGame.addActionListener(controller);
			menuSubscribe.add(itemGame);
		}
		
		return menuSubscribe;
	}
}
