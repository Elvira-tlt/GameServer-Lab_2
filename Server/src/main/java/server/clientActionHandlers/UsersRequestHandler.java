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
    	User userForRequest = connector.getConnectedUser();
    	onlineUsers.remove(userForRequest);
    	UsersServerResponse response = new UsersServerResponse(onlineUsers);
    	connector.sendAction(response);
    }
    
	public void setOnlineUsers(ConnectedUsers onlineUsers) {
	        this.connectedUsers = onlineUsers;
	}
}
