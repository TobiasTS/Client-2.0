package Model;

import java.io.IOException;

import Main.ClientController;

public class ServerMessageHandler {
	
	private ClientController controller;
	
	private static final String messageOk = "OK";
	private static final String messageError = "ERR";
	private static final String messageMatch = "SVR GAME MATCH ";
	private static final String messageYourTurn = "SVR GAME YOURTURN";
	private static final String messageMove = "SVR GAME MOVE";
	private static final String messageResult = "PLAYERONESCORE";
	private static final String messageChallenge = "SVR GAME CHALLENGE";
	private static final String messageChallengeCancelled = "CHALLENGE CANCELLED";
	private static final String messageHelp = "SVR HELP";
	private static final String messagePlayerlist = "SVR PLAYERLIST";
	private static final String messageGamelist = "SVR GAMELIST";
	private static final String messageloginError = "ERR Duplicate name exists";
	
	public ServerMessageHandler(ClientController controller) {
		this.controller = controller;
	}
	
	public void handleMessage(String message) {
		if(message.equals(messageloginError)) {
			System.out.println("ERROR LOGIN");
			try {
				controller.getModel().closeConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(message.equals(messageOk)) {
			if(!controller.getModel().getLoggedIn()) {
				controller.getModel().changeLoggedIn();
				try {
					controller.getModel().getPlayerListCommand();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("OK");
		}
		else if(message.contains(messageError)) {
			System.out.println("ERROR");
		}
		else if(message.contains(messageMatch)) {
			System.out.println("MATCH");
		}
		else if(message.contains(messageYourTurn)) {
			System.out.println("YOURTURN");
		}
		else if(message.contains(messageMove)) {
			System.out.println("MOVE");
		}
		else if(message.contains(messageResult)) {
			System.out.println("RESULT");
		}
		else if(message.contains(messageChallenge)) {
			System.out.println("CHALLENGE");
		}
		else if(message.contains(messageChallengeCancelled)) {
			System.out.println("CANCELLED");
		}
		else if(message.contains(messageHelp)) {
			System.out.println("HELP");
		}
		else if(message.contains(messageGamelist)) {
			System.out.println("GAMELIST");
		}
		else if(message.contains(messagePlayerlist)) {
			System.out.println("PLAYERLIST");
			controller.getModel().setPlayerList(message);
			controller.getView().setLobbyScreen();
		}
		else {
			System.out.println("DEFAULT");
		}
		controller.getModel().setMessageChecked(true);
	}
}
