package client.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import user.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PanelOnlineUsers extends JPanel {
	private final int WIDTH_TABLES = 150;
	private final int HEIGHT_ROWS_TABLES = 25;
	private final Dimension MINIMUM_PANEL_SIZE = new Dimension(180, 550);
	private final Dimension MAXIMUM_PANEL_SIZE = new Dimension(280, 650);

	private List<User> freeUsersList = new ArrayList<>();
	private List<User> playersList = new ArrayList<>();

	private TableModelOnlineUsers tableModelFreeUsers = new TableModelOnlineUsers();
	private TableModelOnlineUsers tableModelPlayers = new TableModelOnlineUsers();

	private JTable tableFreeUsers = new JTable(tableModelFreeUsers);
	private JTable tablePlayers = new JTable(tableModelPlayers);

	private JPanel panelFreeUsers = new JPanel();
	private JPanel panelPlayers = new JPanel();

	private JTabbedPane tabedOnlineUsers = new JTabbedPane();

	public PanelOnlineUsers(){
		setMinimumSize(MINIMUM_PANEL_SIZE);
		setMaximumSize(MAXIMUM_PANEL_SIZE);
		setLayout(new GridLayout(1, 1));
		setBorder(new LineBorder(Color.black));
		setVisible(true);

		tableFreeUsers.setRowHeight(HEIGHT_ROWS_TABLES);
		tablePlayers.setRowHeight(HEIGHT_ROWS_TABLES);
		tableFreeUsers.getColumnModel().getColumn(0).setPreferredWidth(WIDTH_TABLES);
		tablePlayers.getColumnModel().getColumn(0).setPreferredWidth(WIDTH_TABLES);

		panelFreeUsers.add(tableFreeUsers);
		panelPlayers.add(tablePlayers);


		tabedOnlineUsers.addTab("Free users", panelFreeUsers);
		tabedOnlineUsers.addTab("Players", panelPlayers);

		add(tabedOnlineUsers);
	}
	
	public void setOnlineUsersToPanel(List<User> freeUsers, List<User> players){
		freeUsersList = freeUsers;
		playersList = players;
		tableModelFreeUsers.setOnlineUsers(freeUsersList);
		tableModelPlayers.setOnlineUsers(playersList);
		updateTableModel(tableModelFreeUsers);
		updateTableModel(tableModelPlayers);
	}
	
	public void display(){
	//	tableFreeUsers = new JTable(tableModelFreeUsers);
	}

	public void updatePanelOnlineUser(){
		updateTableModel(tableModelFreeUsers);
		updateTableModel(tableModelPlayers);
	}

	private void updateTableModel(TableModelOnlineUsers tableModelOnlineUsers) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Set<TableModelListener> listenersTableModel = tableModelOnlineUsers.getListeners();
				Iterator<TableModelListener> listenersIterator = listenersTableModel.iterator();
				while (listenersIterator.hasNext()) {
					TableModelListener listenerElem = listenersIterator.next();
					listenerElem.tableChanged(new TableModelEvent(tableModelOnlineUsers));
				}
			}
		});
	}
}
