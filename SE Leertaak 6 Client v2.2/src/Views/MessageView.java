package Views;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MessageView extends JPanel {

	private static final long serialVersionUID = 8160787261068616149L;
	private String message;

	public MessageView(String challenged){
			message = choose(challenged);
	}
	
	private String choose(String challenged){
		return (String)JOptionPane.showInputDialog(
                this,
                "Type a message:")            
               ;
	}
	
	public String getChoice(){
		return message;
	}
}
