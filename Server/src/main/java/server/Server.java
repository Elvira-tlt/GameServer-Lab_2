package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import requests.*;
import responses.ClientActionHandler;
import server.clientActionHandlers.*;

public class Server {
	public static final int PORT = 8008;
	//private Socket socket;
	private int countConnecting;
	private Map<Class, ClientActionHandler> actions2Handlers = new HashMap<>();
	private UserDatabase userDatabase;
	private ConnectedUsers connectedUsers;

	public Server() {
		userDatabase = new UserDatabase();
		connectedUsers = new ConnectedUsers();
		prepareActions2HandlersToConnectors();
	}

	
	private void createClientsInteraction (Socket socket) {
		ClientConnector clientConnector = new ClientConnector(socket);
		clientConnector.setActions2HandlersForConnector(actions2Handlers);
		clientConnector.setConnectedUsers(connectedUsers);
		clientConnector.start();
	}
	
	
	private void prepareActions2HandlersToConnectors() {
		LoginRequestHandler loginRequestHandler = new LoginRequestHandler();
		RegistrationRequestHandler registrationRequestHandler = new RegistrationRequestHandler();
		UsersRequestHandler usersRequestHandler = new UsersRequestHandler();
		ExitRequestHandler exitRequestHandler = new ExitRequestHandler();
		ExitFromGameRequestHandler exitFromGameRequestHandler = new ExitFromGameRequestHandler();
			
		//setting other classes to ActionHandlers:
		loginRequestHandler.setUserDatabase(userDatabase);
		loginRequestHandler.setConnectingUsers(connectedUsers);
		registrationRequestHandler.setUserDatabase(userDatabase);
		registrationRequestHandler.setOnlineUsers(connectedUsers);
		usersRequestHandler.setOnlineUsers(connectedUsers);

		exitRequestHandler.setConnectedUsers(connectedUsers);
		
		//TODO
			
			
			
		//add ActionHandlers to Map actions2Handlers:
		actions2Handlers.put(LoginClientRequest.class,loginRequestHandler);
		actions2Handlers.put(RegistrationClientRequest.class, registrationRequestHandler);
		actions2Handlers.put(UsersClientRequest.class,usersRequestHandler);
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



