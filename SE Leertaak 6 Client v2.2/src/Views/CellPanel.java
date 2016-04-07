package Views;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class CellPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Record record;
    private final JLabel field = new JLabel();
    private Action challengeAction = new AbstractAction("Challenge") {
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e) {
        	record.challenge();
        }
    };
    
    private final JButton button = new JButton(challengeAction);

    public CellPanel() {
        add(field);
        add(button);
    }

    public void setRecord(Record record) {
        this.record = record;
        field.setText(this.record.getChallenged());
    }

    public Record getRecord() {
        return record;
    }
}
