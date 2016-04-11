package Main;

import Model.ClientModel;

/**
 * The main that starts the application.
 * 
 * @author Chris de Windt
 * @author Tobias Schlichter
 * @version 1.0
 */
public class ClientMVC {
	private static ClientController controller;
	private static ClientModel model;

	/**
	 * Creates the controller and model for the application.
	 * Adds the model to the controller.
	 * 
	 * @param args String[] generated from Java.
	 */
	public static void main(String[] args) {
		controller = new ClientController();
		model = new ClientModel(controller);
		controller.setModel(model);
	}
}
