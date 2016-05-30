package client.actionListeners;

import client.ServerConnector;
import client.view.PanelOnlineUsers;
import client.view.TableModelOnlineUsers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.StartGameRequest;
import user.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.JTable;


public class StartedGameListener implements ActionListener {
	private static final Logger LOG = LoggerFactory.getLogger(StartedGameListener.class);
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
			LOG.info("Selected rival: {}", rival);
		} else {
			rival = getRandomSelectedRival();
			LOG.info("Selected random rival: {}", rival);
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
    
    private User getRandomSelectedRival(){
    	List<User> freeUsers = panelOnlineUsers.getFreeUsersList();
    	int countFreeUsers = freeUsers.size();
    	Random random = new Random();
    	int randomNumberForSelectedRival = random.nextInt(countFreeUsers);
    	User randomSelectedRival = freeUsers.get(randomNumberForSelectedRival);

    	return randomSelectedRival;
    }
}
