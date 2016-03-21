package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import actionsFromClient.ExitClientRequest;
import actionsFromClient.ExitFromGameClientRequest;
import actionsFromClient.FreeUsersClientRequest;
import actionsFromClient.LoginClientRequest;
import actionsFromClient.PlayersClientRequest;
import actionsFromServer.ClientActionHandler;
import server.clientActionHandlers.*;

public class Server {
	public static final int PORT = 8008;
	//private Socket socket;
	private int countConnecting;
	private Map<Class, ClientActionHandler> actions2Handlers = new HashMap<>();
	private UserDatabase userDatabase;

	public Server() {
		userDatabase = new UserDatabase();
		prepareActions2HandlersToConnectors();
	}

	
	private void createClientsInteraction (Socket socket) {
		ClientConnector clientConnector = new ClientConnector(socket);
		clientConnector.setActions2HandlersForConnector(actions2Handlers);
		clientConnector.start();
	}
	
	
	private void prepareActions2HandlersToConnectors() {
		LoginRequestHandler loginRequestHandler = new LoginRequestHandler();
		RegistrationRequestHandler registrationRequestHandler = new RegistrationRequestHandler();
		PlayersRequestHandler playersRequestHandler = new PlayersRequestHandler();
		FreeUsersRequestHandler freeUsersRequestHandler = new FreeUsersRequestHandler();
		ExitRequestHandler exitRequestHandler = new ExitRequestHandler();
		ExitFromGameRequestHandler exitFromGameRequestHandler = new ExitFromGameRequestHandler();
			
		//setting other classes to ActionHandlers:
		loginRequestHandler.setUserDatabase(userDatabase);
		registrationRequestHandler.setUserDatabase(userDatabase);
		//TODO
			
			
			
		//add ActionHandlers to Map actions2Handlers:
		actions2Handlers.put(LoginClientRequest.class,loginRequestHandler);
		actions2Handlers.put(RegistrationRequestHandler.class, registrationRequestHandler);
		actions2Handlers.put(PlayersClientRequest.class,playersRequestHandler);
		actions2Handlers.put(FreeUsersClientRequest.class,freeUsersRequestHandler);
		actions2Handlers.put(ExitClientRequest.class,exitRequestHandler);
		actions2Handlers.put(ExitFromGameClientRequest.class,exitFromGameRequestHandler);
	}

	public void startServer () {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);

			//////
			System.out.println("Started: " + serverSocket);
			////
			while(true) {
				Socket socket = serverSocket.accept();
				/////
				System.out.println("Connection accepted: " + socket);
				////
				createClientsInteraction(socket);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�� ������� ��������� ������");
			e.printStackTrace();
		}
	}

	public static void main (String[] args) throws IOException {
		new Server().startServer();
	}
}



