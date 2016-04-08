package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Model.MatchModel;
import Model.TicTacToeModel;
import Views.TicTacToeView;

public class TicTacToeController extends GameController {
	
	public static final String COMMAND_MOVE = "MOVE";
	private static final int AMOUNT_OF_ROWS_AND_COLUMNS = 3;

	private ClientController clientController;
	private TicTacToeView ticTacToeView;
	private TicTacToeModel ticTacToeModel;
	
	public TicTacToeController(ClientController clientController, MatchModel match) {
		this.clientController = clientController;
		ticTacToeView = new TicTacToeView(this, AMOUNT_OF_ROWS_AND_COLUMNS);
		ticTacToeModel = new TicTacToeModel(this, AMOUNT_OF_ROWS_AND_COLUMNS, match);
	}
	
	public TicTacToeModel getTicTacToeModel() {
		return ticTacToeModel;
	}

	public TicTacToeView getTicTacToeView() {
		return ticTacToeView;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		String command = e.getActionCommand().split(" ")[0];
		String move = e.getActionCommand().split(" ")[1];
		switch(command) {
		case COMMAND_MOVE:
			try {
				
					int x = Integer.parseInt(move) / TicTacToeController.AMOUNT_OF_ROWS_AND_COLUMNS;
					int y = Integer.parseInt(move) % TicTacToeController.AMOUNT_OF_ROWS_AND_COLUMNS;
					if (ticTacToeModel.getSide() == TicTacToeModel.OPPONENT) {
						ticTacToeView.updateView(x, y, ticTacToeModel.getOpponentChar());
//						ticTacToeView.lockButtons();
					} else {
						ticTacToeView.updateView(x, y, ticTacToeModel.getMyChar());
						ticTacToeView.unlockButtons();
					}
					clientController.getModel().doMove(move);
					ticTacToeModel.makeMove(Integer.parseInt(move));
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		}
	}

}
