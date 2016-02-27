import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private static final int SERVER_PORT = 8008;
	private static final String SERVER_ADRESS = "127.0.0.1";
	
	private Socket socket;
	private BufferedReader fromServer;
	private PrintWriter toServer;
	

	private void connectingToServer() {
		try {
			InetAddress inetAdress = InetAddress.getByName(SERVER_ADRESS);
			socket = new Socket(inetAdress, SERVER_PORT);
			
			
			
			//////
			System.out.println("socket "+socket);
			///
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			
		}catch (ConnectException e) {
			System.out.println("Не удалось подключиться к серверу. \nСервер недоступен");
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main (String[] args) {
		Client client = new Client();
		client.connectingToServer();
		
		
	}
	
}
