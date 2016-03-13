package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
	public static final int PORT = 8008;
	Socket socket;

//	private List <Client> allClients = new ArrayList<Client>();
	//private List <Client> players = new ArrayList<Client>();

	public void startServer () {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);

			//////
			System.out.println("Started: " + serverSocket);
			////

			while(true) {
				socket = serverSocket.accept();
				/////
				System.out.println("Connection accepted: " + socket);
				////

				Thread interactionWithClient = new ClientConnector(socket);
				interactionWithClient.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Не удается запустить сервер");
			e.printStackTrace();
		}
	}

	public static void main (String[] args) throws IOException {
		new Server().startServer();
	}
}



