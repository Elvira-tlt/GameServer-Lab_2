package client;

import actionsFromServer.*;
import client.serverActionsHandlers.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ServerConnector extends Thread{
	private static final int SERVER_PORT = 8008;
	private static final String SERVER_ADDRESS = "127.0.0.1";

	private Socket socket;
	private ObjectInputStream fromServer;
	private ObjectOutputStream toServer;

	private Map<Class, ActionHandler> actionsHandlers = new HashMap<>();

	private boolean isConnectedToServer = false;

	public void run() {
		prepareActionsHandler();
		connectingToServer();
		listeningClientConnector();
	}

	private void connectingToServer() {
		try {
			InetAddress inetAdress = InetAddress.getByName(SERVER_ADDRESS);
			socket = new Socket(inetAdress, SERVER_PORT);
			toServer = new ObjectOutputStream(socket.getOutputStream());
			fromServer = new ObjectInputStream(socket.getInputStream());

			isConnectedToServer = true;

			//////
			System.out.println("Client socket "+socket);
			///

		}catch (ConnectException e) {
			System.out.println("Не удалось подключиться к серверу. \nСервер недоступен \n");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void listeningClientConnector() {
		try {
			while(true) {
				Action actionResponseFromServer = (Action) fromServer.readObject();

				////////////
				System.out.println("	Client: Action recive");
				///////////

				handleActions(actionResponseFromServer);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendToClientConnector(Action actionRequest) {
		boolean isNotSendedAction = false;
		try {
			while (!isNotSendedAction) {
				if (!isConnectedToServer) {
					Thread.sleep(100L);
				} else {
					toServer.writeObject(actionRequest);
					toServer.flush();
					isNotSendedAction = true;
				}
			}

			////////////
			System.out.println("	Client: actionRequest" + actionRequest + "\nAction send is successful");
			///////////

		} catch (IOException |InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void prepareActionsHandler() {
		actionsHandlers.put(LoginResponse.class,new LoginResponseHandler());
		actionsHandlers.put(PlayersResponse.class,new PlayersResponseHandler());
		actionsHandlers.put(FreeUsersResponse.class,new FreeUsersResponseHandler());
		actionsHandlers.put(ExitResponse.class,new ExitResponseHandler());
		actionsHandlers.put(ExitFromGameResponse.class,new ExitFromGameResponseHandler());
	}

	private void handleActions(Action actionFromServer) {
		Class actionFromServerClass = actionFromServer.getClass();
		Set<Map.Entry<Class, ActionHandler>> entreiesActionsHandlers = actionsHandlers.entrySet();
		for (Map.Entry<Class, ActionHandler> entry: entreiesActionsHandlers) {
			if (entry.getKey().equals(actionFromServerClass)) {
				ActionHandler handlerAction = entry.getValue();
				handlerAction.handle();
			}
		}
	}
}
