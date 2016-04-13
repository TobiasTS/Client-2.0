package Views;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Main.ClientController;

public class LobbyView extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private ClientController controller;
	private RecordModel model;
	public boolean hasView = false;
	
	public LobbyView(ClientController controller) {
		super(new FlowLayout());
		setVisible(true);
		this.controller = controller;
	}
	
	public void createTable(ClientController controller, ArrayList<String> players) {
		model = new RecordModel();
		Iterator<String> iterator = players.iterator();
		while (iterator.hasNext()) {
			model.addRecord(new Record(iterator.next(), controller));
		}
        JTable table = new JTable(model);
		EditorAndRenderer editorAndRenderer = new EditorAndRenderer();
		table.setRowHeight(38);
		table.setDefaultRenderer(Object.class, editorAndRenderer);
	    table.setDefaultEditor(Object.class, editorAndRenderer);
		table.setPreferredScrollableViewportSize(new Dimension(600, 400));
		table.setFillsViewportHeight(true);
		add(new JScrollPane(table));
		hasView = true;
	}
	
	public void updateTable(String player){
		model.addRecord(new Record(player, controller));
		model.fireTableDataChanged();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand().split(" ")[0];
		String player = e.getActionCommand().split(" ")[1];
		switch (command) {
		case ClientController.COMMAND_GET_GAME_LIST:
			updateTable(player);
			break;
			
		}
	}
}
