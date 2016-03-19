package client;

import actionsFromClient.LoginClientRequest;
import actionsFromClient.PlayersClientRequest;


public class Client {
	ServerConnector serverConnector = new ServerConnector();

	public void connectingToServer(){
		serverConnector.start();
	}

	public static void main (String[] args) {
			Client client = new Client();
			client.connectingToServer();
			client.serverConnector.sendToClientConnector(new LoginClientRequest("Vadim"));
	}
	
}
