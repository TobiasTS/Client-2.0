package Model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Main.ClientController;

/**
 * The model for the client.
 * 
 * @author Chris de Windt
 * @author Tobias Schlichter
 * @version 1.0
 */
public class ClientModel {
	
	private ClientController controller;
	private String clientName = "";
	
	private ClientSocket clientSocket;
	
	private boolean human;
	
	private boolean loggedIn = false;
	
	private ArrayList<String> playerList;
	private ArrayList<String> gameList;

	private ChallengeModel challengeModel;
	private MatchModel matchModel;
	
	/**
	 * Constructor for the model.
	 * Sets the controller and creates a new ClientSocket.
	 * 
	 * @param controller ClientController sets the controller of this class.
	 */
	public ClientModel(ClientController controller) {
		this.controller = controller;
		clientSocket = new ClientSocket(controller);
	}
	
	/**
	 * Connects to the server by calling the method from this clientSocket.
	 * 
	 * @throws UnknownHostException thrown to indicate that the IP address of a host could not be determined.
	 * @throws IOException signals that an I/O exception of some sort has occurred.
	 */
	public void connectToServer() throws UnknownHostException, IOException {
		clientSocket.connectToServer();
	}
	
	/**
	 * Connects to the server by calling the method from this clientSocket.
	 * Logs the client in with the name thats is given as a parameter.
	 * 
	 * @param name String the name for the player.
	 * @throws UnknownHostException thrown to indicate that the IP address of a host could not be determined.
	 * @throws IOException signals that an I/O exception of some sort has occurred.
	 */
	public void loginClient(String name) throws IOException {
		connectToServer();
		clientName = clientSocket.clientLogin(name);
	}
	
	/**
	 * Closes the connection by logging out the client.
	 * 
	 * @throws IOException signals that an I/O exception of some sort has occurred.
	 */
	public void closeConnection() throws IOException {
		clientSocket.writeToServer("logout");
		loggedIn = false;
	}
	
	/**
	 * Method to subscribe the client to a game.
	 * 
	 * @param gameName String the game name to subscribe to.
	 * @throws IOException signals that an I/O exception of some sort has occurred.
	 */
	public void subscribeClient(String gameName) throws IOException {
		clientSocket.writeToServer("subscribe " + gameName);
	}
	
	/**
	 * Method to unsubscribe the client from a game.
	 * 
	 * @throws IOException signals that an I/O exception of some sort has occurred.
	 */
	public void unsubscribeClient() throws IOException {
		clientSocket.writeToServer("unsubscribe");
	}
	
	/**
	 * Method to challenge a player in a game.
	 * 
	 * @param playerName String name of the player to challenge.
	 * @param gameName String name of the game to play.
	 * @throws IOException signals that an I/O exception of some sort has occurred.
	 */
	public void challengePlayer(String playerName, String gameName) throws IOException {
		clientSocket.writeToServer("challenge " + '"' + playerName + '"' + " " + '"' + gameName + '"');
	}
	
	/**
	 * Method to accept a challenge.
	 * 
	 * @param challengeNumber String the number of the challenge to accept.
	 * @throws IOException signals that an I/O exception of some sort has occurred.
	 */
	public void acceptChallenge(String challengeNumber) throws IOException {
		clientSocket.writeToServer("challenge accept " + challengeNumber);
	}
	
	/**
	 * Method to play a move.
	 * 
	 * @param move String containing the move information.
	 * @throws IOException signals that an I/O exception of some sort has occurred.
	 */
	public void doMove(String move) throws IOException {
		clientSocket.writeToServer("move " + move);
	}
	
	/**
	 * Sends a command to the server to get the game list.
	 * 
	 * @throws IOException signals that an I/O exception of some sort has occurred.
	 */
	public void getGameListCommand() throws IOException {
		clientSocket.writeToServer("get gamelist");
	}
	
	/**
	 * Sends a command to the server to get the player list.
	 * 
	 * @throws IOException signals that an I/O exception of some sort has occurred.
	 */
	public void getPlayerListCommand() throws IOException {
		clientSocket.writeToServer("get playerlist");
	}
	
	/**
	 * Sends a command to the server to get the help list.
	 * 
	 * @throws IOException signals that an I/O exception of some sort has occurred.
	 */
	public void getHelpCommand() throws IOException {
		clientSocket.writeToServer("help");
	}
	
	/**
	 * Getter for the client name.
	 * 
	 * @return String the name of the client.
	 */
	public String getClientName() {
		return clientName;
	}
	
	/**
	 * Setter for the human boolean.
	 * 
	 * @param human boolean value for the human boolean.
	 */
	public void setHuman(boolean human) {
		this.human = human;
	}
	
	/**
	 * Getter for the human boolean.
	 * 
	 * @return boolean value for the human boolean.
	 */
	public boolean getHuman() {
		return human;
	}
	
	/**
	 * Getter for the player list.
	 * 
	 * @return ArrayList<String> the player list.
	 */
	public ArrayList<String> getPlayerList() {
		return playerList;
	}
	
	/**
	 * Setter for the player list.
	 * 
	 * @param input String unparsed String containing the player list.
	 */
	public void setPlayerList(String input) {
		playerList = parseStringToArrayList(input, clientName);
	}
	
	/**
	 * Getter for the game list.
	 * 
	 * @return ArrayList<String> the game list.
	 */
	public ArrayList<String> getGameList() {
		return gameList;
	}
	
	/**
	 * Setter for the game list.
	 * 
	 * @param input String unparsed String containing the game list.
	 */
	public void setGameList(String input) {
		gameList = parseStringToArrayList(input, "DEFAULT");
	}
	
	/**
	 * Parser for the ArrayList<String>, used for the player and game list.
	 * 
	 * @param input String that needs to be parsed.
	 * @param skipValue String the value that is kept out of the parsed ArrayList<String>.
	 * @return ArrayList<String> the parsed array list.
	 */
	private ArrayList<String> parseStringToArrayList(String input, String skipValue) {
		ArrayList<String> list = new ArrayList<>();
		Pattern pattern = Pattern.compile("\"([^\"]*)\"");
 		Matcher matcher = pattern.matcher(input);
 		while (matcher.find()) {
 			if(!matcher.group(1).equals(skipValue)) {
				list.add(matcher.group(1));
			}
 		}
		return list;
	}
	
	/**
	 * Changes the status of the loggedIn boolean.
	 */
	public void changeLoggedIn() {
		if(loggedIn) {
			loggedIn = false;
		}
		else {
			loggedIn = true;
		}
	}
	
	/**
	 * Getter for the loggedIn boolean.
	 * 
	 * @return boolean if logged in.
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	/**
	 * Getter for the challenge model.
	 * 
	 * @return challengeModel the current challenge model.
	 */
	public ChallengeModel getChallenge() {
		return challengeModel;
	}
	
	/**
	 * Setter for the challengeModel.
	 * 
	 * @param input String unparsed input containing the challenge information.
	 */
	public void setChallenge(String input) {
		challengeModel = parseChallenge(input);
	}
	
	/**
	 * Parser for a challenge.
	 * 
	 * @param input String unparsed input.
	 * @return ChallengeModel a new ChallengeModel made from the input.
	 */
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
	
	/**
	 * Getter for the matchModel.
	 * 
	 * @return matchModel the model of the current match.
	 */
	public MatchModel getMatch(){
		return matchModel;
	}
	
	/**
	 * Setter for the matchModel.
	 * 
	 * @param input String containing the information for the match.
	 */
	public void setMatch(String input) {
 		matchModel = parseMatch(input);
 	}
		 	
	/**
	 * Parser for a match.
	 * 
	 * @param input String unparsed input.
	 * @return MatchModel a new MatchModel made from the input.
	 */
 	private MatchModel parseMatch(String input) {
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
 			return new MatchModel(playerToMove, gameType, opponent);
 	}

 	/**
 	 * Parser for a move.
 	 * 
 	 * @param input String containing the information for the move.
 	 * @return String the parsed move.
 	 */
	public String parseMove(String input) {
		Pattern pattern = Pattern.compile("\"([^\"]*)\"");
 		Matcher matcher = pattern.matcher(input);
 		int index = 0;
 		while (matcher.find()) {
 		  System.out.println();
 		  switch(index){
 		  case 1:
 			  return matcher.group(1);
 		  }
 		  index++;
 		}
		return null;
	}
}

