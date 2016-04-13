package Main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import Views.MatchView;

public class MatchController {
	
	private static final String GAME_TIC_TAC_TOE = ClientController.GAME_TIC_TAC_TOE;
	private static final String GAME_OTHELLO = ClientController.GAME_OTHELLO;
	
	private GameController gameController;
	private JPanel panelGame;
	
	public MatchController(){
		panelGame = new JPanel();
		panelGame.setLayout(new GridBagLayout());
	}
	
	public GameController getGameController(){
		return gameController;
	}
	
	public void startMatch(ClientController clientController) {
		String gameType = clientController.getModel().getMatch().getGameType();
		MatchView matchView = new MatchView(gameType, clientController.getModel().getMatch().getOpponent());
		
		JButton forfeitButton = new JButton("Forfeit");
		forfeitButton.addActionListener(clientController);
		forfeitButton.setActionCommand(ClientController.COMMAND_FORFEIT);
		matchView.add(forfeitButton);
		
		if (gameType.contains(GAME_TIC_TAC_TOE)) {
			gameController = new TicTacToeController(clientController, clientController.getModel().getMatch());
			panelGame.add(((TicTacToeController) gameController).getTicTacToeView(), createConstraints(false));
			panelGame.add(matchView, createConstraints(true));
			clientController.getView().registerView(panelGame);
			clientController.getView().setView(panelGame);
			clientController.inAMatch = true;
		} else if (gameType.contains(GAME_OTHELLO)) {
			gameController = new OthelloController(clientController, clientController.getModel().getMatch());
			panelGame.add(((OthelloController) gameController).getOthelloView(), createConstraints(false));
			panelGame.add(matchView, createConstraints(true));
			clientController.getView().registerView(panelGame);
			clientController.getView().setView(panelGame);
			clientController.inAMatch = true;
		}
		
	}
	
	public GridBagConstraints createConstraints(boolean matchView){
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		if (matchView) {
			constraints.anchor = GridBagConstraints.FIRST_LINE_END;
			constraints.gridx = 3;
			constraints.gridy = 0;
		} else {
			constraints.anchor = GridBagConstraints.FIRST_LINE_START;
			constraints.gridx = 0;
			constraints.gridy = 0;
			constraints.gridwidth = 2;
		}
		
		return constraints;
	}

}
