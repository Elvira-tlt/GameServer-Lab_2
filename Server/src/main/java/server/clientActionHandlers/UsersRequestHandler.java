package server.clientActionHandlers;



import requests.UsersClientRequest;
import server.ClientActionHandler;
import responses.UsersServerResponse;
import server.ClientConnector;
import server.ConnectedUsers;
import user.User;

import java.util.List;

public class UsersRequestHandler implements ClientActionHandler<UsersClientRequest> {
	private ConnectedUsers connectedUsers;
	
	
    @Override
    public void handle(UsersClientRequest playersRequest, ClientConnector connector) {
    	List<User> onlineUsers = connectedUsers.getOnlineUsersToConnector();
    	UsersServerResponse response = new UsersServerResponse(onlineUsers);
    	
    	connector.sendAction(response);
    }
    
	public void setOnlineUsers(ConnectedUsers onlineUsers) {
	        this.connectedUsers = onlineUsers;
	}
}
