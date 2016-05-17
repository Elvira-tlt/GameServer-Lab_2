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
			UsersServerResponse connectedUsersResponse;
			Collection<ClientConnector> clientConnectors = onlineUsersToConnector.values();
			List<User> allConnectedUsers = getOnlineUsersToConnector();
			
			for(ClientConnector clientConnector: clientConnectors) {
				List<User> allConnectedUsersWithoutThisUser = new ArrayList<User>();
				allConnectedUsersWithoutThisUser.addAll(allConnectedUsers);
				User thisUser = clientConnector.getConnectedUser();
				allConnectedUsersWithoutThisUser.remove(thisUser);
				connectedUsersResponse = new UsersServerResponse(allConnectedUsersWithoutThisUser);
				clientConnector.sendAction(connectedUsersResponse);
				
		}
	}
	
	public ClientConnector getClientConnectorByUser(User user){
		ClientConnector clientConnectorThisUser = onlineUsersToConnector.get(user);
		onlineUsersToConnector.get(user);
		return clientConnectorThisUser;
	}
	
	/*public User getRundomSelectedOnlineUser(User notThisUser){
		User randomSelectedUser;
		List<User> freeUsers
		
		return randomSelectedUser;
	}*/
	
}
