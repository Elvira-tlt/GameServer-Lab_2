package client.view;

import user.User;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TableModelGame implements TableModel {
    private String[][] madeMoves;
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    public Set<TableModelListener> getListeners() {
        return listeners;
    }
    
    public void setMadeMoves(String[][] madeMovesNew) {
		madeMoves = madeMovesNew;
	}

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class ;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return "";
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndexs) {
        String valueTable = madeMoves[rowIndex][columnIndexs];
        return valueTable;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }
    
    
	@Override
	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
		
	}
	@Override
	public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
	}

}
