package Main;

import Model.ClientModel;

public class ClientMVC {
	private static ClientController controller;
	private static ClientModel model;

	public static void main(String[] args) {
		controller = new ClientController();
		model = new ClientModel(controller);
		controller.setModel(model);
		
	}

}
