package Model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Main.ClientController;

public class ClientModel {
	
	private ClientController controller;
	private ClientSocket clientSocket;
//	private boolean messageChecked;
	private boolean human;
	private boolean loggedIn = false;
	private String clientName = "";
	private ChallengeModel challengeModel;
	private Match match;
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
	
	public void acceptChallenge(String challengeNumber) throws IOException {
		clientSocket.writeToServer("challenge accept " + challengeNumber);
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
	
//	public void setMessageChecked(boolean value) {
//		messageChecked = value;
//	}
//	
//	public boolean getMessageChecked() {
//		return messageChecked;
//	}
	
	public void setHuman(boolean human) {
		this.human = human;
	}
	
	public boolean getHuman() {
		return human;
	}
	
	public Match getMatch(){
		return match;
	}
	
	public void setMatch(String input) {
		match = parseMatch(input);
	}
	
	private Match parseMatch(String input) {
		Pattern pattern = Pattern.compile("\"([^\"]*)\"");
		Matcher matcher = pattern.matcher(input);
		int index = 0;
		String playerToMove = null, gameType = null,  opponent = null;
		while (matcher.find()) {
		  System.out.println();
		  switch(index){
		  	case 0:
		  		playerToMove = matcher.group(1);
			  	break;
		  	case 1:
		  		gameType = matcher.group(1);
		  		break;
		  	case 2:
		  		opponent = matcher.group(1);
		  		break;
		  }
		  index++;
		}
			return new Match(playerToMove, gameType, opponent);
	}

	public ChallengeModel getChallenge(){
		return challengeModel;
	}
	
	public void setChallenge(String input) {
		challengeModel = parseChallenge(input);
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

	public ArrayList<String> getPlayerList() {
		return playerList;
	}
	
	public void setPlayerList(String input) {
		playerList = parsePlayerList(input);
	}
	
	public ArrayList<String> getGameList(){
		return gameList;
	}
	
	public void setGameList(String input) {
		gameList = parseGameList(input);
	}
	
	private ArrayList<String> parseGameList(String input){
		ArrayList<String> list = new ArrayList<>();
		Pattern pattern = Pattern.compile("\"([^\"]*)\"");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			list.add(matcher.group(1));
		}
		return list;
	}
	
	private ArrayList<String> parsePlayerList(String input) {
		ArrayList<String> list = new ArrayList<>();
		Pattern pattern = Pattern.compile("\"([^\"]*)\"");
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			if(!matcher.group(1).equals(clientName)) {
				list.add(matcher.group(1));
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
	
	public void setLogIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
}

