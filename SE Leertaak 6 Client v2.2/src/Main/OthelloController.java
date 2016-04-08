package Main;

import java.awt.event.ActionEvent;
import java.io.IOException;

import Model.MatchModel;
import Model.OthelloModel;
import Views.OthelloView;

public class OthelloController extends GameController {
	
	public static final String COMMAND_MOVE = "MOVE";
	private static final int AMOUNT_OF_ROWS_AND_COLUMNS = 8;
	
	private ClientController clientController;
	private OthelloModel othelloModel;
	private OthelloView othelloView;
	
	public OthelloController(ClientController clientController, MatchModel match) {
		this.clientController = clientController;
		othelloModel = new OthelloModel(AMOUNT_OF_ROWS_AND_COLUMNS, match);
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
		System.out.println(e.getActionCommand());
		String command = e.getActionCommand().split(" ")[0];
		String move = e.getActionCommand().split(" ")[1];		
		int x = Integer.parseInt(move.split(",")[0]);
		int y = Integer.parseInt(move.split(",")[1]);
		switch(command) {
		case COMMAND_MOVE:
			char player = othelloModel.getSide() == othelloModel.getMe() ? othelloModel.getMyChar(): othelloModel.getOpponentChar();
			char opponent = othelloModel.getSide() == othelloModel.getOpponent() ? othelloModel.getOpponentChar(): othelloModel.getMyChar();
				othelloModel.placeMove(x, y, player, opponent);
				othelloView.updateView(othelloModel.getBoard());
			try {
				clientController.getModel().doMove(move);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			othelloModel.switchSide();
			break;
		}
	}	
}
