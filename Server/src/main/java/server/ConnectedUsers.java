package server;

import responses.Action;
import responses.UsersServerResponse;
import server.clientActionHandlers.UsersRequestHandler;
import user.User;

import java.util.*;

public class ConnectedUsers {
	private Map<User, ClientConnector> onlineUsersToConnector;
	
	public ConnectedUsers(){
		onlineUsersToConnector = new HashMap<>();
	}
	
	public List<User> getOnlineUsersToConnector(){
		List<User> onlineUsers = new ArrayList<>();

		Set<User> userSet = onlineUsersToConnector.keySet();
		Iterator iterator = userSet.iterator();
		while (iterator.hasNext()) {
			User userElements = (User)iterator.next();
			onlineUsers.add(userElements);
		}
		return onlineUsers;
	}
	
	public void addOnlineUser(User newOnlineUser, ClientConnector clientConnector) {
		onlineUsersToConnector.put(newOnlineUser, clientConnector);
		sendActionAllConnectedUsers();

	}
	
	public void removeOnlineUser(User userForRemove){
			onlineUsersToConnector.remove(userForRemove);
			sendActionAllConnectedUsers();
	}

	private void sendActionAllConnectedUsers(){
			Collection<ClientConnector> clientConnectors = onlineUsersToConnector.values();

			List<User> allConnectedUsers = getOnlineUsersToConnector();
			UsersServerResponse connectedUsersResponse = new UsersServerResponse(allConnectedUsers);
			for(ClientConnector clientConnector: clientConnectors) {
				clientConnector.sendAction(connectedUsersResponse);
		}
	}
}
