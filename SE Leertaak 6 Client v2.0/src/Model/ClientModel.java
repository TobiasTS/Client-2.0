package Model;

import java.io.IOException;
import java.net.UnknownHostException;

import Main.ClientController;

public class ClientModel {
	
	private ClientController controller;
	private String clientName = "";
	
	private ClientSocket clientSocket;
	private boolean messageChecked;
	
	private boolean human;
	
	public boolean loggedIn = false;
	
	public ClientModel(ClientController controller) {
		this.controller = controller;
		clientSocket = new ClientSocket(controller);
	}
	
	public void connectToServer() throws UnknownHostException, IOException {
		clientSocket.connectToServer();
	}
	
	public void loginClient(String name) throws IOException {
		connectToServer();
		clientName = clientSocket.clientLogin(name);
		controller.getView().setLobbyScreen();
	}
	
	public void subscribeClient(String gameName) throws IOException {
		clientSocket.writeToServer("subscribe " + gameName);
	}
	
	public void unsubscribeClient() throws IOException {
		clientSocket.writeToServer("unsubscribe");
	}
	
	public void challengePlayer(String gameName, String playerName) throws IOException {
		clientSocket.writeToServer("challenge " + '"' + playerName + '"' + " " + '"' + gameName + '"');
	}
	
	public void doMove(String move) throws IOException {
		clientSocket.writeToServer("move " + move);
	}
	
	public void getGameList() throws IOException {
		clientSocket.writeToServer("get gamelist");
	}
	
	public void getPlayerList() throws IOException {
		clientSocket.writeToServer("get playerlist");
	}
	
	public void getHelp() throws IOException {
		clientSocket.writeToServer("help");
	}
	
	public String getClientName() {
		return clientName;
	}
	
	public void setMessageChecked(boolean value) {
		messageChecked = value;
	}
	
	public boolean getMessageChecked() {
		return messageChecked;
	}
	
	public void setHuman(boolean human) {
		this.human = human;
	}
	
	public boolean getHuman() {
		return human;
	}
}

