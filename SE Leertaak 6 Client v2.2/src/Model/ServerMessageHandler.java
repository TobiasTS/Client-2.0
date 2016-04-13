package Model;

import java.awt.event.ActionEvent;
import java.io.IOException;

import Main.ClientController;

public class ServerMessageHandler {
	
	private ClientController controller;
	
	private static final String MESSAGE_OK = "OK";
	private static final String MESSAGE_ERROR = "ERR";
	private static final String MESSAGE_MATCH = "SVR GAME MATCH ";
	private static final String MESSAGE_YOUR_TURN = "SVR GAME YOURTURN";
	private static final String MESSAGE_MOVE = "SVR GAME MOVE";
	private static final String MESSAGE_RESULT = "PLAYERONESCORE";
	private static final String MESSAGE_CHALLENGE = "SVR GAME CHALLENGE";
	private static final String MESSAGE_CHALLENGE_CANCELLED = "CHALLENGE CANCELLED";
	private static final String MESSAGE_HELP = "SVR HELP";
	private static final String MESSAGE_PLAYERLIST = "SVR PLAYERLIST";
	private static final String MESSAGE_GAMELIST = "SVR GAMELIST";
	private static final String MESSAGE_LOGIN_ERROR = "ERR Duplicate name exists";
	
	public ServerMessageHandler(ClientController controller) {
		this.controller = controller;
	}
	
	public void handleMessage(String message) {
		if(message.equals(MESSAGE_LOGIN_ERROR)) {
			System.out.println("HANDLED MESSAGE: ERROR LOGIN");
			try {
				controller.getModel().closeConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(message.equals(MESSAGE_OK)) {
			if(!controller.getModel().isLoggedIn()) {
				controller.getModel().changeLoggedIn();
				try {
					controller.getModel().getPlayerListCommand();
					controller.getModel().getGameListCommand();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("HANDLED MESSAGE: OK");
		}
		else if(message.contains(MESSAGE_ERROR)) {
			System.out.println("HANDLED MESSAGE: ERROR");
		}
		else if(message.contains(MESSAGE_MATCH)) {
			System.out.println("HANDLED MESSAGE: MATCH");
			controller.getModel().setMatch(message);
			controller.createMatchController();
		}
		else if(message.contains(MESSAGE_YOUR_TURN)) {
			System.out.println("HANDLED MESSAGE: YOURTURN");
			controller.getMatchController().getGameController().actionPerformed(new ActionEvent(this, 0, "YOURTURN"));
		}
		else if(message.contains(MESSAGE_MOVE)) {
			if(!message.contains(controller.getModel().getClientName())) {
				System.out.println("HANDLED MESSAGE: MOVE");
				String move = "MOVE" + " " + controller.getModel().parseMove(message);
				controller.getMatchController().getGameController().actionPerformed(new ActionEvent(this, 0, move));
			}
		}
		else if(message.contains(MESSAGE_RESULT)) {
			controller.inAMatch = false;
			System.out.println("HANDLED MESSAGE: RESULT");
			try {
				controller.getModel().getPlayerListCommand();
			} catch (IOException e) {
				e.printStackTrace();
			}
			controller.getView().setLobbyScreen();
			
		}
		else if(message.contains(MESSAGE_CHALLENGE)) {
			System.out.println("HANDLED MESSAGE: CHALLENGE");
			if(!controller.inAMatch) {
				controller.getModel().setChallenge(message);
				controller.createChallengeController();
				System.out.print("HANDLED MESSAGE: CHALLENGE ACCEPTED");
			}
			else {
				System.out.print("HANDLED MESSAGE: CHALLENGE IGNORED");
			}

		}
		else if(message.contains(MESSAGE_CHALLENGE_CANCELLED)) {
			System.out.println("HANDLED MESSAGE: CANCELLED");
		}
		else if(message.contains(MESSAGE_HELP)) {
			System.out.println("HANDLED MESSAGE: HELP");
		}
		else if(message.contains(MESSAGE_GAMELIST)) {
			System.out.println("HANDLED MESSAGE: GAMELIST");
			controller.getModel().setGameList(message);
		}
		else if(message.contains(MESSAGE_PLAYERLIST)) {
			System.out.println("HANDLED MESSAGE: PLAYERLIST");
			controller.getModel().setPlayerList(message);
			controller.getView().setLobbyScreen();
		}
		else {
			System.out.println("HANDLED MESSAGE: DEFAULT");
		}
	}
}
