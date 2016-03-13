package server;

import java.net.Socket;

//взаимодействие с одним клиентом
public class ConnectionWithClient extends Thread {
	private Socket socket;
	
	public ConnectionWithClient(Socket socket) {
		this.socket = socket;
		
		
		
	}
	
	//!!!!!!!!!!!!!!!!!!!
	public void run() {
		//TODO 	
		System.out.println("socket: " + socket);
		
		//условие закрытия потока - нажатие на крестик
		//
		
	}
}
