package client.actionListeners;

import client.ServerConnector;
import client.view.PanelOnlineUsers;
import client.view.TableModelOnlineUsers;
import requests.StartGameRequest;
import user.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;

public class StartedGameListener implements ActionListener {
	private ServerConnector serverConnector;
	private TableModelOnlineUsers tableModelFreeUsers;
	private JTable tableFreeUsers;
	private User rival;
	private PanelOnlineUsers panelOnlineUsers;
	private boolean rivalIsSelected;

	@Override
    public void actionPerformed(ActionEvent e) {
		int indexNumberSelectedUser = tableFreeUsers.getSelectedRow();
		rivalIsSelected = indexNumberSelectedUser >=0;
		
		if(rivalIsSelected) {
			List<User> freeUsers = panelOnlineUsers.getFreeUsersList();
			rival = freeUsers.get(indexNumberSelectedUser);

			///
			System.out.println("free users: " + freeUsers);
			System.out.println("SELECTED RIVAL: " + rival.getNameUser() + "User: " + rival);
			//

		} else {
			//TODO
			randomSelectedRival();
			//
			System.out.println("SELECTED RANDOM RIVAL: " + rival.getNameUser());
			//
		}
		
		sendRequest(rival);

		
    }

	private void sendRequest(User otherUser){
		StartGameRequest startGameRequest = new StartGameRequest(rival);
		serverConnector.sendToClientConnector(startGameRequest);
	}
    
    public void setServerConnector(ServerConnector serverConnector){
    	this.serverConnector = serverConnector;
    }
    
    public void setPanelOnlineUsers(PanelOnlineUsers panelOnlineUsers) {
		this.panelOnlineUsers = panelOnlineUsers;
		this.tableFreeUsers = panelOnlineUsers.getTableFreeUsers();
		this.tableModelFreeUsers = panelOnlineUsers.getTableModelFreeUsers();
	}
    
    private void randomSelectedRival(){
    	//TODO
    }
}
