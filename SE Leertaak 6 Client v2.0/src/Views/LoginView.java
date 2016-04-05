package Views;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The view for the login screen.
 * 
 * @author Tobias Schlichter
 * @version 1.0
 */
public class LoginView extends JPanel implements View {
	
	private static final long serialVersionUID = 1L;
	private JTextField loginName;
	private JButton loginButton;
	private JButton loginAIButton;
	
	/**
	 * Constructor for a LoginView object.
	 * Calls the createObjectsOfPanel method.
	 * Adds the text field and button to this.
	 */
	public LoginView() {
		createObjectsOfPanel();
		addToPanel();
	}

	/**
	 * Creates the objects that need to be on this screen.
	 */
	@Override
	public void createObjectsOfPanel() {
		loginButton = new JButton("login");
		loginButton.setActionCommand("USERLOGIN");
		
		loginAIButton = new JButton("login as AI");
		loginAIButton.setActionCommand("AILOGIN");
		
		loginName = new JTextField(20);
	}

	/**
	 * Adds the objects to the panel.
	 */
	@Override
	public void addToPanel() {
		add(loginName);
		add(loginButton);
		add(loginAIButton);
	}
	
	/**
	 * Sets the ActionListener to the buttons from this screen.
	 */
	@Override
	public void setActionListener(ActionListener a) {
		loginButton.addActionListener(a);
		loginAIButton.addActionListener(a);
	}
	
	/**
	 * Getter for the login name.
	 * 
	 * @return String login name.
	 */
	public String getLoginName() {
		return loginName.getText();
	}

}
