package Model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	private ChallengeModel challengeModel;
	
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
		loggedIn = false;
	}
	
	public void subscribeClient(String gameName) throws IOException {
		clientSocket.writeToServer("subscribe " + gameName);
	}
	
	public void unsubscribeClient() throws IOException {
		clientSocket.writeToServer("unsubscribe");
	}
	
	public void challengePlayer(String playerName, String gameName) throws IOException {
		clientSocket.writeToServer("challenge " + '"' + playerName + '"' + " " + '"' + gameName + '"');
	}
	
	public void acceptChallenge(String challengeNumber) throws IOException {
		clientSocket.writeToServer("challenge accept " + challengeNumber);
	}
	
	public void setChallenge(String input) {
		challengeModel = parseChallenge(input);
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
		playerList = parseStringToArrayList(input, clientName);
	}
	
	public ArrayList<String> getGameList() {
		return gameList;
	}
	
	public void setGameList(String input) {
		gameList = parseStringToArrayList(input, "DEFAULT");
	}
	
	private ArrayList<String> parseStringToArrayList(String input, String skipValue) {
		ArrayList<String> list = new ArrayList<>();
		boolean readingName = false;
		boolean skip = false;
		String playerName = "";
		for(int i = 0; i < input.length(); i++) {
			skip = false;
			if(readingName) {
				if(input.charAt(i) == '"') {
					
					if(!playerName.equals(skipValue)) {
						list.add(playerName);
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
		return list;
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
	
	private ChallengeModel parseChallenge(String input) {
		Pattern pattern = Pattern.compile("\"([^\"]*)\"");
		Matcher matcher = pattern.matcher(input);
		int index = 0;
		String challenger = null, challengerNumber = null, gameType = null;
		while (matcher.find()) {
		  System.out.println();
		  switch(index){
		  	case 0:
			  	challenger = matcher.group(1);
			  	break;
		  	case 1:
		  		challengerNumber = matcher.group(1);
		  		break;
		  	case 2:
		  		gameType = matcher.group(1);
		  		break;
		  }
		  index++;
		}
		return new ChallengeModel(challenger, challengerNumber, gameType);
	}

	public ChallengeModel getChallenge() {
		return challengeModel;
	}
}

