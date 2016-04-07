package Views;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class EditorAndRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	private static final long serialVersionUID = 1L;
	private final CellPanel renderer = new CellPanel();
    private final CellPanel editor = new CellPanel();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        renderer.setRecord((Record) value);
        return renderer;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        editor.setRecord((Record) value);
        return editor;
    }

    @Override
    public Object getCellEditorValue() {
        return editor.getRecord();
    }

    @Override
    public boolean isCellEditable(EventObject ev) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject ev) {
        return false;
    }
}
