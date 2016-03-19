package server;

import actionsFromServer.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//�������������� � ����� ��������
public class ClientConnector extends Thread {
	private Socket socket;
	private ObjectInputStream fromClient;
	private ObjectOutputStream toClient;

	private Map<Class, ClientActionHandler> actions2Handlers = new HashMap<>();

	public ClientConnector(Socket socket) {
		this.socket = socket;
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
				System.out.println("	Server: Action recive is successful:" + actionResponse);
				///////////

				handleActions(actionResponse);
		}
	}

	private void handleActions(Action actionFromClient) {

		Class<? extends Action> actionFromClientClass = actionFromClient.getClass();

		Set<Map.Entry<Class, ClientActionHandler>> entreiesActionsHandlers = actions2Handlers.entrySet();
		for (Map.Entry<Class, ClientActionHandler> entry: entreiesActionsHandlers) {

			if (entry.getKey().equals(actionFromClientClass)) {
				ClientActionHandler<Action> handlerAction = entry.getValue();

				handlerAction.handle(actionFromClient, this);
				// TODO �������� �������� ��������� �������
				//break;
			}
		}
	}
	
		public void setActions2HandlersForConnector(Map<Class, ClientActionHandler> action2handlerMap) {
		actions2Handlers = action2handlerMap;
	}
}
