package Views;

import java.awt.event.ActionEvent;

import Main.ClientController;

public class Record {

    private String challenged;
    private ClientController controller;
    
    public Record(String challenged, ClientController controller) {
	    this.challenged = challenged;
	    this.controller = controller;
    }
    
    public String getChallenged(){
    	return challenged;
    }
    
    public void challenge(){
    	controller.actionPerformed(new ActionEvent(this, 0, ClientController.COMMAND_CHALLENGE+ " " + challenged));
    }
    public void chat(){
    	controller.actionPerformed(new ActionEvent(this, 0, ClientController.COMMAND_MESSAGE+ " " + challenged));
    }
}
