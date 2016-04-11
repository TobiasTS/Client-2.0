package Main;

import java.awt.event.ActionEvent;
import java.io.IOException;
import Model.MatchModel;
import Model.TicTacToeAI;
import Model.TicTacToeModel;
import Views.TicTacToeView;

/**
 * Controller for Tic Tac Toe.
 * Controls the Tic Tac Toe model and view.
 * 
 * @author Chris de Windt
 * @author Tobias Schlichter
 * @version 1.0
 */
public class TicTacToeController extends GameController {

	public static final String COMMAND_MOVE = "MOVE";
	public static final String COMMAND_YOURTURN = "YOURTURN";
	private static final int AMOUNT_OF_ROWS_AND_COLUMNS = 3;

	private ClientController clientController;
	private TicTacToeView ticTacToeView;
	private TicTacToeModel ticTacToeModel;
	private TicTacToeAI ticTacToeAi;
	
	/**
	 * Constructor for the Tic Tac Toe Controller.
	 * 
	 * @param clientController ClientController the controller from the client.
	 * @param match MatchModel the model of the match.
	 */
	public TicTacToeController(ClientController clientController, MatchModel match) {
		this.clientController = clientController;
		ticTacToeView = new TicTacToeView(this, AMOUNT_OF_ROWS_AND_COLUMNS);
		ticTacToeModel = new TicTacToeModel(this, AMOUNT_OF_ROWS_AND_COLUMNS, match);
		ticTacToeView.lockButtons();
		if (!clientController.getModel().getHuman()) {
			ticTacToeAi = new TicTacToeAI();
		}
	}
	
	/**
	 * Invoked when an action occurs.
	 * 
	 * @param ActionEvent the action event.
	 */
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
						ticTacToeModel.changeSide(TicTacToeModel.ME);
					} else {
						setPlayerMove(x, y, move);
					}
					ticTacToeModel.makeMove(Integer.parseInt(move));	
					if (!clientController.getModel().getHuman()) ticTacToeAi.makeMove(Integer.parseInt(move));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			break;
		case COMMAND_YOURTURN:
			ticTacToeModel.changeSide(TicTacToeModel.ME);
			if (!clientController.getModel().getHuman()) {
				ticTacToeAi.setComputerPlays();
				int moveAi = ticTacToeAi.chooseMove();
				int x = moveAi / TicTacToeController.AMOUNT_OF_ROWS_AND_COLUMNS;
				int y = moveAi % TicTacToeController.AMOUNT_OF_ROWS_AND_COLUMNS;
				ticTacToeAi.makeMove(moveAi);
				try {
					setPlayerMove(x, y, String.valueOf(moveAi));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			} else {
				ticTacToeView.unlockButtons();
			}
			break;			
		}
	}
	
	/**
	 * Does the required actions for when the player sets a move.
	 * 
	 * @param x int coordinate representing the x.
	 * @param y int coordinate representing the y.
	 * @param move String that represents the move.
	 * @throws IOException when doMove throws the IOExeption.
	 */
	public void setPlayerMove(int x, int y, String move) throws IOException {
		ticTacToeView.updateView(x, y, ticTacToeModel.getMyChar());
		clientController.getModel().doMove(move);
		ticTacToeView.lockButtons();
		ticTacToeModel.changeSide(TicTacToeModel.OPPONENT);
		if (!clientController.getModel().getHuman()) ticTacToeAi.setOpponentPlays();
	}
		
	/**
	 * Returns the Tic Tac Toe model.
	 * 
	 * @return TicTacToeModel of this controller.
	 */
	public TicTacToeModel getTicTacToeModel() {
		return ticTacToeModel;
	}

	/**
	 * Returns the Tic Tac Toe view.
	 * 
	 * @return TicTacToeView of this controller.
	 */
	public TicTacToeView getTicTacToeView() {
		return ticTacToeView;
	}
}
