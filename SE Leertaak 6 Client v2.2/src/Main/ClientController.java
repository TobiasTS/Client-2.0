package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Model.ClientModel;
import Views.ChallengeView;
import Views.ClientView;

public class ClientController implements ActionListener {

	public static final String COMMAND_USER_LOGIN = "USERLOGIN";
	public static final String COMMAND_AI_LOGIN = "AILOGIN";
	public static final String COMMAND_LOGOUT = "LOGOUT";
	public static final String COMMAND_GET_PLAYER_LIST = "GETPLAYERLIST";
	public static final String COMMAND_GET_GAME_LIST = "GETGAMELIST";
	public static final String COMMAND_CHALLENGE = "CHALLENGE";
	
	public static final int STATUS_READY = 1;
	public static final int STATUS_WAITING = 2;
	
	private ClientModel model;
	private ClientView view;
	
	private int status = STATUS_READY;
	
	public ClientController() {
		view = new ClientView("Client v2.2", this);
	}
	
	public void setModel(ClientModel model) {
		this.model = model;
	}
	
	public ClientModel getModel() {
		return model;
	}
	
	public ClientView getView() {
		return view;
	}
	
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
	
	public void createMatchController() {
		MatchController matchController = new MatchController();
		matchController.startMatch(this);
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
				view.setLobbyScreen();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		case COMMAND_AI_LOGIN:
			try {
				model.loginClient(view.getLoginName());
				model.setHuman(false);
				view.setLobbyScreen();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		case COMMAND_LOGOUT:
			try {
				model.closeConnection();
				System.exit(0);
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
				String player = e.getActionCommand().split(" ")[1];
				ChallengeView challengeView = new ChallengeView(player, model.getGameList());
				status = STATUS_WAITING;
				while (status == STATUS_WAITING) {
					game = challengeView.getChoice();
					if (game != null && player != null){
						status = STATUS_READY;
						try {
							model.challengePlayer(game, player);;
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			break;
		}
	}
}
