package server;

import actionsFromClient.*;
import actionsFromServer.*;
import server.clientActionsHandlers.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//взаимодействие с одним клиентом
public class ClientConnector extends Thread {
	private Socket socket;
	private ObjectInputStream fromClient;
	private ObjectOutputStream toClient;

	private Map<Class, ActionHandler> actionsHandlers = new HashMap<>();

	public ClientConnector(Socket socket) {
		this.socket = socket;
		prepareActionsHandler();
		//////////
		System.out.println("Server socket: " + socket);
		////
	}

	public void run() {
		try {
			toClient = new ObjectOutputStream(socket.getOutputStream());
			fromClient = new ObjectInputStream(socket.getInputStream());
			listeningClient();

		} catch (IOException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void sendAction(Action actionResponse) {
		try {
			toClient.writeObject(actionResponse);
			toClient.flush();

			////////////
			System.out.println("	Server: actionRequest" + actionResponse + "\nAction send is successful");
			///////////

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void listeningClient() throws IOException, ClassNotFoundException {
			while(true) {
				Action actionResponse = (Action) fromClient.readObject();

				////////////
				System.out.println("	Server: Action recive is successful");
				///////////

				handleActionsAndSendResponse(actionResponse);
		}
	}

	private void handleActionsAndSendResponse(Action actionFromClient) {
		Class actionFromClientClass = actionFromClient.getClass();

		Set<Map.Entry<Class, ActionHandler>> entreiesActionsHandlers = actionsHandlers.entrySet();
		for (Map.Entry<Class, ActionHandler> entry: entreiesActionsHandlers) {
			if (entry.getKey().equals(actionFromClientClass)) {
				ActionHandler handlerAction = entry.getValue();
				handlerAction.handle();
				// TODO Добавить отправку сообщения клиенту
			}
		}
	}

	private void prepareActionsHandler() {
		actionsHandlers.put(LoginRequest.class,new LoginRequestHandler());
		actionsHandlers.put(PlayersRequest.class,new PlayersRequestHandler());
		actionsHandlers.put(FreeUsersRequest.class,new FreeUsersRequestHandler());
		actionsHandlers.put(ExitRequest.class,new ExitRequestHandler());
		actionsHandlers.put(ExitFromGameRequest.class,new ExitFromGameRequestHandler());
	}
}
