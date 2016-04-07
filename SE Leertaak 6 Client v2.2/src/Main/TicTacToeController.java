package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Views.TicTacToeView;

public class TicTacToeController implements ActionListener {
	
	public static final String COMMAND_MOVE = "MOVE";
	private static final int AMOUNT_OF_ROWS_AND_COLUMNS = 3;

	private ClientController controller;
	private TicTacToeView ticTacToeView;
	
	public TicTacToeController(ClientController clientController) {
		this.controller = clientController;
		ticTacToeView = new TicTacToeView(this, AMOUNT_OF_ROWS_AND_COLUMNS);
	}
	
	public TicTacToeView getTicTacToeView() {
		return ticTacToeView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand().split(" ")[0];
		String move = e.getActionCommand().split(" ")[1];
		switch(command) {
		case COMMAND_MOVE:
			try {
				controller.getModel().doMove(move);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;
		}
	}

}
