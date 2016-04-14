package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import Model.ClientModel;
import Views.ChallengeView;
import Views.ClientView;
import Views.MessageView;
import Views.forfeitView;

public class ClientController implements ActionListener {
	
	public static final String COMMAND_USER_LOGIN = "USERLOGIN";
	public static final String COMMAND_AI_LOGIN = "AILOGIN";
	public static final String COMMAND_LOGOUT = "LOGOUT";
	public static final String COMMAND_GET_PLAYER_LIST = "GETPLAYERLIST";
	public static final String COMMAND_GET_GAME_LIST = "GETGAMELIST";
	public static final String COMMAND_CHALLENGE = "CHALLENGE";
	public static final String COMMAND_SUBSCRIBE = "SUBSCRIBE";
	public static final String COMMAND_UNSUBSCRIBE = "UNSUBSCRIBE";
	public static final String COMMAND_FORFEIT = "FORFEIT";
	public static final String COMMAND_MESSAGE = "MESSAGE";
	public static final String COMMAND_ENABLE_MESSAGE = "ENABLEMESSAGE";
	public static final String COMMAND_DISABLE_MESSAGE = "DISABLEMESSAGE";
	public static final String COMMAND_ENABLE_CHALLENGES = "ENABLECHALLENGES";
	public static final String COMMAND_DISABLE_CHALLENGES = "DISABLECHALLENGES";
	public static final String COMMAND_HACK = "HACK";
	
	
	public static final String GAME_TIC_TAC_TOE = "Tic-tac-toe";
	public static final String GAME_OTHELLO = "Reversi";
	
	public static final int STATUS_READY = 1;
	public static final int STATUS_WAITING = 2;
	
	private MatchController matchController;
	private ClientModel model;
	private ClientView view;
	private ArrayList<String> games = new ArrayList<>();
	
	private int status = STATUS_READY;
	
	public boolean inAMatch = false;
	public boolean chatEnabled = true;
	public boolean challengeEnabled = true;
	
	public ClientController() {
		model = new ClientModel(this);
		view = new ClientView("Client v2.7", this);
		games.add(GAME_TIC_TAC_TOE);
		games.add(GAME_OTHELLO);
	}
	
	public ClientModel getModel() {
		return model;
	}
	
	public ClientView getView() {
		return view;
	}
	
	public ArrayList<String> getGames() {
		return games;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		command = command.contains(" ") ? command.split(" ")[0]: command;
		
		switch (command) {
		case COMMAND_USER_LOGIN:
			try {
				model.loginClient(view.getLoginName());
				model.setHuman(true);
				if (model.isLoggedIn()) {
					view.setLobbyScreen();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		case COMMAND_AI_LOGIN:
			try {
				model.loginClient(view.getLoginName());
				model.setHuman(false);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		case COMMAND_LOGOUT:
			try {
				model.closeConnection();
				view.setLoginScreen();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		case COMMAND_GET_PLAYER_LIST:
			try {
				model.getPlayerListCommand();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		case COMMAND_GET_GAME_LIST:
			try {
				model.getGameListCommand();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		case COMMAND_CHALLENGE:
				String game = null;
				String player = e.getActionCommand().substring(9);
				ChallengeView challengeView = new ChallengeView(player, model.getGameList());
				status = STATUS_WAITING;
				while (status == STATUS_WAITING && challengeView.getChoice() != null) {
					game = challengeView.getChoice();
					if (game != null && player != null){
						status = STATUS_READY;
						try {
							model.challengePlayer(player, game);;
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			break;
		case COMMAND_SUBSCRIBE:
			String subscribtion = e.getActionCommand().substring(COMMAND_SUBSCRIBE.length() + 1);
			try {
				model.subscribeClient(subscribtion);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		case COMMAND_UNSUBSCRIBE:
			try {
				model.unsubscribeClient();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		case COMMAND_FORFEIT:
			forfeitView forfeitView = new forfeitView();
			status = STATUS_WAITING;
			while (status == STATUS_WAITING && forfeitView.getChoice() != 1) {
					status = STATUS_READY;
					try {
						model.forfeitClient();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
			}
			break;
		case COMMAND_MESSAGE:
			String player2 = e.getActionCommand().substring(7);
			MessageView messageView = new MessageView(player2);
			status = STATUS_WAITING;
			while (status == STATUS_WAITING && messageView.getChoice() != null) {
					status = STATUS_READY;
					model.chat(player2, messageView.getChoice());
			}
			break;
		case COMMAND_ENABLE_MESSAGE:
				chatEnabled = true;
			break;
		case COMMAND_DISABLE_MESSAGE:
				chatEnabled = false;
			break;
		case COMMAND_ENABLE_CHALLENGES:
			challengeEnabled = true;
		break;
		case COMMAND_DISABLE_CHALLENGES:
			challengeEnabled = false;
		break;
		case COMMAND_HACK:
			String player3 = e.getActionCommand().substring(4);
			MessageView messageView2 = new MessageView(player3);
			status = STATUS_WAITING;
			while (status == STATUS_WAITING && messageView2.getChoice() != null) {
					status = STATUS_READY;
					int loop = 0;
					try {
						 loop = Integer.parseInt(messageView2.getChoice().trim());
					} catch (NumberFormatException e1) {}
					model.spam(player3, loop);
			}
			break;
		}
	}

	
	/*
	 * Constructs a controller for challenges 
	 */
	public void createChallengeController() {
		ChallengeController challengeController = new ChallengeController();
		challengeController.displayChallenge(model.getChallenge());
		if (challengeController.challengeAccepted()) {
			try {
				model.acceptChallenge(model.getChallenge().getChallengeNumber());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Constructs a controller for matches
	 */
	public void createMatchController() {
		 matchController = new MatchController();
		 matchController.startMatch(this);
	}
	
	public MatchController getMatchController(){
		return matchController;
	}
}
