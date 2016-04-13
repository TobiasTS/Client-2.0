package Main;

import java.awt.event.ActionEvent;
import java.io.IOException;

import Model.MatchModel;
import Model.OthelloModel;
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
	private boolean iStart;
	
	public OthelloController(ClientController clientController, MatchModel match) {
		this.clientController = clientController;
		this.match = match;
		if (match.getPlayerToMove().equals(clientController.getModel().getClientName())){
			playerOne = clientController.getModel().getClientName();
			playerTwo = match.getOpponent();
			iStart = true;
		} else {
			playerOne = match.getOpponent();
			playerTwo = clientController.getModel().getClientName();
			iStart = false;
		}
		othelloModel = new OthelloModel(playerOne, playerTwo);
		othelloView = new OthelloView(this, AMOUNT_OF_ROWS_AND_COLUMNS);
		othelloView.updateView(othelloModel.getBoard());
		if (iStart) {
			othelloModel.setSide(OthelloModel.ME);
		} else {
			othelloModel.setSide(OthelloModel.OPPONENT);
		}
		System.out.println("PLAYERONE: " + playerOne);
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
				if (othelloModel.getSide() == OthelloModel.OPPONENT) {
					int x = Integer.parseInt(move) / 8;
					int y = Integer.parseInt(move) % 8;
					othelloModel.doPlayerMove(match.getOpponent(), x + "," + y);
					othelloView.updateView(othelloModel.getBoard());
					othelloModel.setSide(OthelloModel.ME);
				} else {
					int x = Integer.parseInt(move) / 8;
					int y = Integer.parseInt(move) % 8;
					if (othelloModel.moveIsLegal(x, y, false, othelloModel.getBoard())) {
						clientController.getModel().doMove(move);
						othelloModel.doPlayerMove(clientController.getModel().getClientName(), x + "," + y);
						othelloView.updateView(othelloModel.getBoard());
						othelloModel.setSide(OthelloModel.OPPONENT);	
					}				
				}
			} catch (IOException | IllegalStateException ex) {
				ex.printStackTrace();
			}
			break;
		case COMMAND_YOURTURN:
			if (!clientController.getModel().getHuman()) {
				try {
					othelloModel.setSide(OthelloModel.ME);
					double[] doubleMoveAi = othelloModel.doAIMove(clientController.getModel().getClientName(), 6, othelloModel.getBoard());
					String stringMoveAi = String.valueOf((int) doubleMoveAi[0]) + "," + String.valueOf((int) doubleMoveAi[1]);
					clientController.getModel().doMove(String.valueOf((int)doubleMoveAi[0] * 8 + (int)doubleMoveAi[1]));
					othelloModel.doPlayerMove(clientController.getModel().getClientName(), stringMoveAi);
					othelloView.updateView(othelloModel.getBoard());
					othelloModel.setSide(OthelloModel.OPPONENT);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			} else {
				othelloView.unlockButtons();
			}
			break;
		}
	}
}
