package Main;

import java.awt.event.ActionEvent;
import java.io.IOException;

import Model.MatchModel;
import Model.OthelloModel;
import Model.ServerMessageHandler;
import Model.TicTacToeModel;
import Views.OthelloView;

public class OthelloController extends GameController {
	
	public static final String COMMAND_MOVE = "MOVE";
	public static final String COMMAND_YOURTURN = "YOURTURN";
	private static final int AMOUNT_OF_ROWS_AND_COLUMNS = 8;
	
	private ClientController clientController;
	private OthelloModel othelloModel;
	private OthelloView othelloView;
	private MatchModel match;
	private String playerOne;
	private String playerTwo;
	private boolean turn;
	
	public OthelloController(ClientController clientController, MatchModel match) {
		this.clientController = clientController;
		this.match = match;
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
//		othelloView.lockButtons();
		System.out.println("PlayerOne = " + playerOne);
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
				if (turn) {
					int x = Integer.parseInt(move.split(",")[0]);
					int y = Integer.parseInt(move.split(",")[1]);
					if (othelloModel.moveIsLegal(x, y, false, othelloModel.getBoard())) {
						clientController.getModel().doMove(move);
						othelloModel.doPlayerMove(clientController.getModel().getClientName(), move);
						othelloView.updateView(othelloModel.getBoard());
						turn = false;
//						othelloView.lockButtons();
					//
					
//					othelloView.lockButtons();
				} else {
					//othelloModel.nextPlayer();
					othelloModel.doPlayerMove(othelloModel.getPlayerToMove(), move);
					//othelloModel.nextPlayer();
					othelloView.updateView(othelloModel.getBoard());
					}
				}				
			} catch (IOException | IllegalStateException ex) {
				ex.printStackTrace();
			}
			break;
		case COMMAND_YOURTURN:
			turn = true;
			othelloView.updateView(othelloModel.getBoard());
			othelloView.unlockButtons();
			break;
		}
	}
}
