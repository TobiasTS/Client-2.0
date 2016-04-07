package Views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class RecordModel extends AbstractTableModel {

	private static final long serialVersionUID = 8777847257613249729L;
	private final List<Record> records = new ArrayList<Record>();

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        return records.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return records.get(rowIndex);
    }
    
    @Override
	public String getColumnName(int column) {
		return null;
	}
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    public void addRecord(Record record) {
        records.add(record);
        fireTableRowsInserted(records.size()-1, records.size()-1);
    }
}
