package client;

import actionsFromClient.LoginClientRequest;
import client.view.MainWindow;

public class Client {
	private ServerConnector serverConnector = new ServerConnector();
	private MainWindow mainWindow;

	public void connectingToServer(){
		serverConnector.start();
		mainWindow = new MainWindow(serverConnector);
		mainWindow.setServerConnector(serverConnector);
	}

	public static void main (String[] args) {
			Client client = new Client();
			client.connectingToServer();

	}
	
}
