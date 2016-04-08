package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GameController implements ActionListener{

	@Override
	public abstract void actionPerformed(ActionEvent e);
}
