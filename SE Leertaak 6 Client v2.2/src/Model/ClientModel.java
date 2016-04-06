package Model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Main.ClientController;

public class ClientModel {
	
	private ClientController controller;
	private String clientName = "";
	
	private ClientSocket clientSocket;
	private boolean messageChecked;
	
	private boolean human;
	
	private boolean loggedIn = false;
	
	private ArrayList<String> playerList;
	private ArrayList<String> gameList;

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
	}
	
	public void closeConnection() throws IOException {
		clientSocket.writeToServer("logout");
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
	
	public void getGameListCommand() throws IOException {
		clientSocket.writeToServer("get gamelist");
	}
	
	public void getPlayerListCommand() throws IOException {
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
	
	public ArrayList<String> getPlayerList() {
		return playerList;
	}
	
	public void setPlayerList(String input) {
		playerList = parseStringToArrayList(input);
	}
	
	private ArrayList<String> parseStringToArrayList(String input) {
		playerList = new ArrayList<>();
		boolean readingName = false;
		boolean skip = false;
		String playerName = "";
		for(int i = 0; i < input.length(); i++) {
			skip = false;
			if(readingName) {
				if(input.charAt(i) == '"') {
					
					if(!playerName.equals(clientName)) {
						playerList.add(playerName);
					}
					
					playerName = "";
					readingName = false;
					skip = true;
				} 
				else {
					playerName += input.charAt(i);
				}
			}
			if(input.charAt(i) == '"' && !skip) {
				readingName = true;
			}
		}
		return playerList;
	}
	
	public void changeLoggedIn() {
		if(loggedIn) {
			loggedIn = false;
		}
		else {
			loggedIn = true;
		}
	}
	
	public boolean getLoggedIn() {
		return loggedIn;
	}
}

