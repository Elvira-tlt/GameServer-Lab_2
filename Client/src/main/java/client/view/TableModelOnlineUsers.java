package client.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import user.User;

public class TableModelOnlineUsers implements TableModel {
    private List<User> onlineUsers = new ArrayList<>();
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    public TableModelOnlineUsers(){
    }

    public Set<TableModelListener> getListeners() {
        return listeners;
    }
    
    public void setOnlineUsers(List<User> onlineUsers) {
		this.onlineUsers = onlineUsers;
	}

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return User.class ;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Online users";
        }
        return "";
    }

    @Override
    public int getRowCount() {
        return onlineUsers.size();
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndexs) {
        User user = onlineUsers.get(rowIndex);
        String userName = user.getNameUser();
        return userName;
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
