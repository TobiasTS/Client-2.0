package Main;

import java.awt.event.ActionEvent;
import java.io.IOException;

import Model.MatchModel;
import Model.TicTacToeModel;
import Views.TicTacToeView;

public class TicTacToeController extends GameController {
	
	public static final String COMMAND_MOVE = "MOVE";
	public static final String COMMAND_YOURTURN = "YOURTURN";
	private static final int AMOUNT_OF_ROWS_AND_COLUMNS = 3;

	private ClientController clientController;
	private TicTacToeView ticTacToeView;
	private TicTacToeModel ticTacToeModel;
	
	public TicTacToeController(ClientController clientController, MatchModel match) {
		this.clientController = clientController;
		ticTacToeView = new TicTacToeView(this, AMOUNT_OF_ROWS_AND_COLUMNS);
		ticTacToeModel = new TicTacToeModel(this, AMOUNT_OF_ROWS_AND_COLUMNS, match);
		ticTacToeView.lockButtons();
	}
	
	public TicTacToeModel getTicTacToeModel() {
		return ticTacToeModel;
	}

	public TicTacToeView getTicTacToeView() {
		return ticTacToeView;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("ACTION: " + e.getActionCommand());
		String command = e.getActionCommand().split(" ")[0];
		switch(command) {
		case COMMAND_MOVE:
			String move = e.getActionCommand().split(" ")[1];
			try {
					int x = Integer.parseInt(move) / TicTacToeController.AMOUNT_OF_ROWS_AND_COLUMNS;
					int y = Integer.parseInt(move) % TicTacToeController.AMOUNT_OF_ROWS_AND_COLUMNS;
					if (ticTacToeModel.getSide() == TicTacToeModel.OPPONENT) {
						ticTacToeView.updateView(x, y, ticTacToeModel.getOpponentChar());
						ticTacToeModel.changeSide(ticTacToeModel.ME);
					} else {
						setPlayerMove(x, y, move);
					}
					ticTacToeModel.makeMove(Integer.parseInt(move));				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		case COMMAND_YOURTURN:
			ticTacToeModel.changeSide(ticTacToeModel.ME);
			ticTacToeView.unlockButtons();
		}
	}
	
	public void setPlayerMove(int x, int y, String move) throws IOException {
		ticTacToeView.updateView(x, y, ticTacToeModel.getMyChar());
		clientController.getModel().doMove(move);
		ticTacToeView.lockButtons();
		ticTacToeModel.changeSide(ticTacToeModel.OPPONENT);
	}

}
