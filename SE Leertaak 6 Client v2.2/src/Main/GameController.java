package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Abstract class for game controllers.
 * For every game there is a game controller and implements this abstract class.
 * 
 * @author Chris de Windt
 * @author Tobias Schlichter
 * @version 1.0
 */
public abstract class GameController implements ActionListener {

	@Override
	public abstract void actionPerformed(ActionEvent e);
}
