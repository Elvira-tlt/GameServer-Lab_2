package client;

import actionsFromClient.LoginRequest;
import actionsFromClient.PlayersRequest;


public class Client {
	ServerConnector serverConnector = new ServerConnector();

	public void connectingToServer(){
		serverConnector.start();
	}

	public static void main (String[] args) {
			Client client = new Client();
			client.connectingToServer();
			client.serverConnector.sendToClientConnector(new PlayersRequest());
			client.serverConnector.sendToClientConnector(new LoginRequest("Vadim"));
	}
	
}
