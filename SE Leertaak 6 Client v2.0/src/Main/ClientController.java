package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Model.ClientModel;
import Views.ClientView;

public class ClientController implements ActionListener {
	
	private ClientModel model;
	private ClientView view;
	
	public ClientController() {
		view = new ClientView(this);
	}
	
	public void setModel(ClientModel model) {
		this.model = model;
	}
	
	public ClientModel getModel() {
		return model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("USERLOGIN")) {
			try {
				model.loginClient(view.getLoginName());
				model.setHuman(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand().equals("AILOGIN")) {
			try {
				model.loginClient(view.getLoginName());
				model.setHuman(false);
				view.setLobbyScreen();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public ClientView getView() {
		return view;
	}
}
