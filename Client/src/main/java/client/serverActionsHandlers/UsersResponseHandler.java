package client.serverActionsHandlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import client.view.PanelOnlineUsers;
import requests.ServerActionHandler;
import responses.UsersServerResponse;
import user.User;


public class UsersResponseHandler implements ServerActionHandler<UsersServerResponse> {
	private PanelOnlineUsers panelOnlineUsers;
	private List<User> freeUsers = new ArrayList<User>();
	private List<User> players = new ArrayList<User>();

	
    @Override
    public void handle(UsersServerResponse usersResponse) {
        List<User> allOnlineUsers = usersResponse.getOnlineUsers();
        selectionOnlineUsers(allOnlineUsers);
        panelOnlineUsers.setOnlineUsersToPanel(freeUsers, players);
    }

    
    private void selectionOnlineUsers(List<User> onlineUsers){
    	Iterator<User> iteratorOnlineUsers = onlineUsers.iterator();
		freeUsers = new ArrayList<>();
		players = new ArrayList<>();

        while(iteratorOnlineUsers.hasNext()){
        	User onlineUser = iteratorOnlineUsers.next();
        	boolean isPlayers = onlineUser.getStatusUser();
        	if(isPlayers) {
        		players.add(onlineUser);
        	} else {
        		freeUsers.add(onlineUser);
        	}
        }
        
    }
    
	public void setPanelOnlineUsers(PanelOnlineUsers panelOnlineUsers) {
		this.panelOnlineUsers = panelOnlineUsers;
	}
}
