package Main;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MatchController {
	
	private static final String GAME_TIC_TAC_TOE = "Tic Tac Toe";
	private static final String GAME_OTHELLO = "Othello";
	
	private JPanel panelGame;
	private JPanel panelMatch;
	
	public MatchController(){
		panelGame = new JPanel();
		panelGame.setLayout(new GridLayout(1, 2));
		panelGame.setVisible(true);
		panelMatch = new JPanel();
		panelMatch.add(new JLabel("Insert match details here"));
		panelMatch.setVisible(true);
	}
	
	public void startMatch(ClientController clientController) {
		String gameType = clientController.getModel().getMatch().getGameType();
		if (gameType.contains(GAME_TIC_TAC_TOE)) {
			TicTacToeController ticTacToeController = new TicTacToeController(clientController);
			clientController.getView().registerView(ticTacToeController.getTicTacToeView());
			clientController.getView().setView(ticTacToeController.getTicTacToeView());
		} else if (gameType.contains(GAME_OTHELLO)) {
			//new OthelloController
		}
		
	}

}
