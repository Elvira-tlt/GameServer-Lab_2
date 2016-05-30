package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.ServerActionHandler;
import responses.Action;
import user.User;


public class ServerConnector extends Thread{
	private static final Logger LOG = LoggerFactory.getLogger(ServerConnector.class);
	private static final int SERVER_PORT = 8008;

	private static final String SERVER_ADDRESS = "127.0.0.1";
	private Socket socket;
	private ObjectInputStream fromServer;

	private ObjectOutputStream toServer;

	private Map<Class, ServerActionHandler> actions2Handlers = new HashMap<>();

	private boolean isConnectedToServer = false;
	private String nameConnectedUser;

	public void run() {
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

		}catch (ConnectException e) {
			LOG.error("Не удалось подключиться к серверу. \nСервер недоступен \n", e);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void listeningClientConnector() {
		try {
			while(true) {
				Action actionResponse = (Action) fromServer.readObject();
				LOG.info("Recive actionResponse {}", actionResponse);
				handleActions(actionResponse);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void setActions2Handlers(Map<Class, ServerActionHandler> actions2Handlers) {
		this.actions2Handlers = actions2Handlers;
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
					LOG.info("Send actionRequest {}", actionRequest);
				}
			}

		} catch (IOException |InterruptedException e) {
			e.printStackTrace();
			LOG.error("Exception ", e);
		}
	}

	private void handleActions(Action actionFromServer) {
		Class actionFromServerClass = actionFromServer.getClass();
		Set<Map.Entry<Class, ServerActionHandler>> entreiesActionsHandlers = actions2Handlers.entrySet();
		for (Map.Entry<Class, ServerActionHandler> entry: entreiesActionsHandlers) {
			if (entry.getKey().equals(actionFromServerClass)) {
				ServerActionHandler handlerAction = entry.getValue();
				handlerAction.handle(actionFromServer);
			}
		}
	}
	
	public String getNameConnectedUser() {
		return nameConnectedUser;
	}

	public void setNameConnectedUser(String nameConnectedUser) {
		this.nameConnectedUser = nameConnectedUser;
	}
}
