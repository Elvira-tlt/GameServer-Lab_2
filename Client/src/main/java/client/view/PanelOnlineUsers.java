package client.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import user.User;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PanelOnlineUsers extends JPanel {
	private final int WIDTH_TABLES = 230;
	private final int HEIGHT_ROWS_TABLES = 25;
	private final Dimension MINIMUM_PANEL_SIZE = new Dimension(250, 650);
	private final Dimension MAXIMUM_PANEL_SIZE = new Dimension(280, 650);
	private final Dimension PREFERRED_PANEL_SIZE = new Dimension(265, 650);
	private final Dimension MINIMUM_PANEL_ONLINE_USERS_SIZE = new Dimension(240, 450);
	private final String TEXT_TO_BUTTON_PLAY= "<html> Выберите соперника для игры и нажмите <br> 'начать игру'   "+
			"Если соперник не выбран,<br> то  он будет выбран случайным образом </html> ";
	private final String NAME_BUTTON_PLAY = "Начать игру";
	private final String TEXT_TO_BUTTON_QUIT= "<html> Если Вам необходимо покинуть игру, <br> нажмите \"Сдаться\". " +
			" В этом случае победа <br> будет присуждена Вашему сопернику </html> "; // <br>
	private final String NAME_BUTTON_QUIT = "Сдаться";

	private List<User> freeUsersList = new ArrayList<>();
	private List<User> playersList = new ArrayList<>();

	private TableModelOnlineUsers tableModelFreeUsers = new TableModelOnlineUsers();
	private TableModelOnlineUsers tableModelPlayers = new TableModelOnlineUsers();

	private JTable tableFreeUsers = new JTable(tableModelFreeUsers);
	private JTable tablePlayers = new JTable(tableModelPlayers);

	private JPanel panelFreeUsers = new JPanel();
	private JPanel panelPlayers = new JPanel();
	private JPanel panelButton = new JPanel();

	private JTabbedPane tabedOnlineUsers = new JTabbedPane();

	private JLabel textToButton = new JLabel(TEXT_TO_BUTTON_PLAY);
	private JButton playButton = new JButton(NAME_BUTTON_PLAY);
	private JButton quitButton = new JButton(NAME_BUTTON_QUIT);

	public PanelOnlineUsers(){
		prepareElements();
		tableFreeUsers.setPreferredSize(MINIMUM_PANEL_ONLINE_USERS_SIZE);
		tablePlayers.setPreferredSize(MINIMUM_PANEL_ONLINE_USERS_SIZE);

		setMinimumSize(MINIMUM_PANEL_SIZE);
		setMaximumSize(MAXIMUM_PANEL_SIZE);
		setPreferredSize(PREFERRED_PANEL_SIZE);
		setLayout(new FlowLayout());
		setBorder(new LineBorder(Color.black));
		setVisible(true);
	}
	
	public void setOnlineUsersToPanel(List<User> freeUsers, List<User> players){
		freeUsersList = freeUsers;
		playersList = players;
		tableModelFreeUsers.setOnlineUsers(freeUsersList);
		tableModelPlayers.setOnlineUsers(playersList);
		updatePanelOnlineUser();
	}
	
	public void updatePanelOnlineUser(){
		updateTableModel(tableModelFreeUsers);
		updateTableModel(tableModelPlayers);
	}

	private void updateTableModel(final TableModelOnlineUsers tableModelOnlineUsers) {
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

	private void prepareElements(){
		tableFreeUsers.setRowHeight(HEIGHT_ROWS_TABLES);
		tablePlayers.setRowHeight(HEIGHT_ROWS_TABLES);
		tableFreeUsers.getColumnModel().getColumn(0).setPreferredWidth(WIDTH_TABLES);
		tablePlayers.getColumnModel().getColumn(0).setPreferredWidth(WIDTH_TABLES);

		panelFreeUsers.add(tableFreeUsers);
		panelPlayers.add(tablePlayers);

		tabedOnlineUsers.addTab("Free users", panelFreeUsers);
		tabedOnlineUsers.addTab("Players", panelPlayers);

		decorButton();
		panelButton.add(textToButton);
		panelButton.add(playButton);

		textToButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));

		add(tabedOnlineUsers);
		add(panelButton, BorderLayout.SOUTH);
	}

	private void decorButton(){
		Color colorButtonPlay = new Color(128, 228, 101);
		Color colorButtonQuit = new Color(248, 10, 50);
		playButton.setBackground(colorButtonPlay);
		playButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		quitButton.setBackground(colorButtonQuit);
		quitButton.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panelButton.setLayout(new GridLayout(2, 1));
	}

	public void changeButtonToQuitButton(){
		textToButton.setText(TEXT_TO_BUTTON_QUIT);
		panelButton.remove(playButton);
		panelButton.add(quitButton);
		decorButton();
	}

	public void changeButtonToPlayButton(){
		textToButton.setText(TEXT_TO_BUTTON_PLAY);
		panelButton.remove(quitButton);
		panelButton.add(playButton);
		decorButton();
	}
	
	public List<User> getFreeUsersList() {
		return freeUsersList;
	}

	public TableModelOnlineUsers getTableModelFreeUsers() {
		return tableModelFreeUsers;
	}

	public JTable getTableFreeUsers() {
		return tableFreeUsers;
	}
	
	 public void setListenerToPlayButton(ActionListener listener){
	        playButton.addActionListener(listener);;
	 }

	public void setListenerToQuitButton(ActionListener listener){
		quitButton.addActionListener(listener);;
	}
}
