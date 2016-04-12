package Main;

import java.awt.event.ActionEvent;
import java.io.IOException;

import Model.MatchModel;
import Model.OthelloModel;
import Model.TicTacToeModel;
import Views.OthelloView;

public class OthelloController extends GameController {
	
	public static final String COMMAND_MOVE = "MOVE";
	public static final String COMMAND_YOURTURN = "YOURTURN";
	private static final int AMOUNT_OF_ROWS_AND_COLUMNS = 8;
	
	private ClientController clientController;
	private OthelloModel othelloModel;
	private OthelloView othelloView;
	private String playerOne;
	private String playerTwo;
	
	public OthelloController(ClientController clientController, MatchModel match) {
		this.clientController = clientController;
//		othelloModel = new OthelloModel(AMOUNT_OF_ROWS_AND_COLUMNS, match);
		if (match.getPlayerToMove().equals(clientController.getModel().getClientName())){
			playerOne = clientController.getModel().getClientName();
			playerTwo = match.getOpponent();
		} else {
			playerOne = match.getOpponent();
			playerTwo = clientController.getModel().getClientName();
		}
		othelloModel = new OthelloModel(playerOne, playerTwo);
		othelloView = new OthelloView(this, AMOUNT_OF_ROWS_AND_COLUMNS);
		othelloView.updateView(othelloModel.getBoard());
	}

	public OthelloModel getOthelloModel() {
		return othelloModel;
	}


	public OthelloView getOthelloView() {
		return othelloView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("ACTION: " + e.getActionCommand());
		String command = e.getActionCommand().split(" ")[0];
		switch(command) {
		case COMMAND_MOVE:
			String move = e.getActionCommand().split(" ")[1];		
			try {
				othelloModel.doPlayerMove(othelloModel.getCurrentPlayer(), move);
				clientController.getModel().doMove(move);
				othelloView.updateView(othelloModel.getBoard());
				clientController.getModel().doMove(move);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		case COMMAND_YOURTURN:
			othelloView.updateView(othelloModel.getBoard());
			othelloView.unlockButtons();
			break;
		}
	}
}
