package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import responses.*;
import user.User;

public class ClientConnector extends Thread {
	private Socket socket;
	private ObjectInputStream fromClient;
	private ObjectOutputStream toClient;
	private User userConnecting;

	private ConnectedUsers connectedUsers;

	private Map<Class, ClientActionHandler> actions2Handlers = new HashMap<>();

	public ClientConnector(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			toClient = new ObjectOutputStream(socket.getOutputStream());
			fromClient = new ObjectInputStream(socket.getInputStream());
			listeningClient();

		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public void sendAction(Action actionResponse) {
		try {
			toClient.writeObject(actionResponse);
			toClient.flush();

			////////////
			System.out.println("	Server: send actionResponse -" + actionResponse);
			///////////

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void listeningClient() throws IOException, ClassNotFoundException {
		try{
			while(true) {
				Action actionRequest = (Action) fromClient.readObject();

				////////////
				System.out.println("	Server: recive actionResponse -" + actionRequest);
				///////////

				handleActions(actionRequest);
			}
		} catch (SocketException e) {
			String nameConnectedUser = "(Not Identified)";
			if(userConnecting != null){
				nameConnectedUser = userConnecting.getNameUser();
				connectedUsers.removeOnlineUser(userConnecting);
			}
			System.out.println("Client" + " '"+ nameConnectedUser + "' " + "disconnected");

		}
	}

	private void handleActions(Action actionFromClient) {

		Class<? extends Action> actionFromClientClass = actionFromClient.getClass();

		Set<Map.Entry<Class, ClientActionHandler>> entreiesActionsHandlers = actions2Handlers.entrySet();
		for (Map.Entry<Class, ClientActionHandler> entry: entreiesActionsHandlers) {

			if (entry.getKey().equals(actionFromClientClass)) {
				ClientActionHandler<Action> handlerAction = entry.getValue();

				handlerAction.handle(actionFromClient, this);
			}
		}
	}

	public void setActions2HandlersForConnector(Map<Class, ClientActionHandler> action2handlerMap) {
		actions2Handlers = action2handlerMap;
	}

	public void setUser(User user){
		this.userConnecting = user;
	}


	public void setConnectedUsers(ConnectedUsers connectedUsers) {
		this.connectedUsers = connectedUsers;
	}

	public User getConnectedUser(){
		User user = userConnecting;
		return user;
	}

}
