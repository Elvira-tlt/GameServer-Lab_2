

//import com.sun.security.ntlm.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {
	public static final int PORT = 8008;
	//private ServerSocket serverSocket;
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
				
				Thread interactionWithClient = new ConnectionWithClient(socket);
				interactionWithClient.start();
				
			}
		
		
		
		
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
					
			
			
			
			/*BufferedReader fromClient = new BufferedReader
					(new InputStreamReader(socket.getInputStream()));
		
			PrintWriter toClient = new PrintWriter(socket.getOutputStream(), true);
			*/
			
			
			
		
		
	
		
		
	}

	
	public static void main (String[] args) throws IOException {
		new Server().startServer();
	}



}



