package client;

import java.util.HashMap;
import java.util.Map;

import client.serverActionsHandlers.*;
import requests.ServerActionHandler;
import responses.*;
import client.view.MainWindow;

public class Client {
	private ServerConnector serverConnector = new ServerConnector();
	private MainWindow mainWindow;

	public void connectingToServer(){
		mainWindow = new MainWindow(serverConnector);
		serverConnector.setActions2Handlers(getPreparingActions2Handlers());
		serverConnector.start();
		mainWindow.displayStartWindow();
	}

	public static void main (String[] args) {
			Client client = new Client();
			client.connectingToServer();
	}

	private Map<Class, ServerActionHandler> getPreparingActions2Handlers() {
		LoginResponseHandler loginResponseHandlerClient = new LoginResponseHandler();
		RegistrationResponseHandler registrationResponseHandlerClient = new RegistrationResponseHandler();
		UsersResponseHandler usersResponseHandler = new UsersResponseHandler();
		StartGameResponseHandler startGameResponseHandler = new StartGameResponseHandler();
		
		
		//setting other classes to
		loginResponseHandlerClient.setMainWindow(mainWindow);
		loginResponseHandlerClient.setServerconnector(serverConnector);
		registrationResponseHandlerClient.setMainWindow(mainWindow);
		startGameResponseHandler.setGamePanel(mainWindow.getGamePanel());
		
		
		usersResponseHandler.setPanelOnlineUsers(mainWindow.getPanelOnlineUsers());
		
		Map<Class, ServerActionHandler> actions2Handlers = new HashMap<Class, ServerActionHandler>();
		actions2Handlers.put(LoginServerResponse.class, loginResponseHandlerClient);
		actions2Handlers.put(RegistrationServerResponse.class, registrationResponseHandlerClient);
		actions2Handlers.put(UsersServerResponse.class, usersResponseHandler);
		actions2Handlers.put(ExitServerResponse.class,new ExitResponseHandler());
		actions2Handlers.put(ExitFromGameServerResponse.class,new ExitFromGameResponseHandler());
		actions2Handlers.put(ConnectingServerResponse.class, new ConnectingResponseHandler());
		actions2Handlers.put(MoveGameResponse.class, new MoveGameResponseHandler());
		actions2Handlers.put(StartGameResponse.class, startGameResponseHandler);
		
		return actions2Handlers;
	}
}
