package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import responses.*;
import user.User;

public class ClientConnector extends Thread {
	private static final Logger LOG = LoggerFactory.getLogger(ClientConnector.class);
	private Socket socket;
	private ObjectInputStream fromClient;
	private ObjectOutputStream toClient;
	private User userConnecting;
	private ConnectedUsers connectedUsers;
	private Map<Class, ClientActionHandler> actions2Handlers = new HashMap<>();


	public ClientConnector(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			toClient = new ObjectOutputStream(socket.getOutputStream());
			fromClient = new ObjectInputStream(socket.getInputStream());
			listeningClient();

		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public void sendAction(Action actionResponse) {
		try {
			LOG.info("Server: send actionResponse {} to {} ", actionResponse, userConnecting);
			toClient.writeObject(actionResponse);
			toClient.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void listeningClient() throws IOException, ClassNotFoundException {
		try{
			while(true) {
				Action actionRequest = (Action) fromClient.readObject();
				LOG.info("Server: recive actionRequest {} ", actionRequest);
				handleActions(actionRequest);
			}
		} catch (SocketException e) {
			String nameConnectedUser = "(Not Identified)";
			if(userConnecting != null){
				nameConnectedUser = userConnecting.getNameUser();
				connectedUsers.removeOnlineUser(userConnecting);
			}
			LOG.info("Client {} disconnected ", nameConnectedUser, e);
		}
	}

	private void handleActions(Action actionFromClient) {

		Class<? extends Action> actionFromClientClass = actionFromClient.getClass();

		Set<Map.Entry<Class, ClientActionHandler>> entreiesActionsHandlers = actions2Handlers.entrySet();
		for (Map.Entry<Class, ClientActionHandler> entry: entreiesActionsHandlers) {

			if (entry.getKey().equals(actionFromClientClass)) {
				ClientActionHandler<Action> handlerAction = entry.getValue();
				handlerAction.handle(actionFromClient, this);
			}
		}
	}

	public void setActions2HandlersForConnector(Map<Class, ClientActionHandler> action2handlerMap) {
		actions2Handlers = action2handlerMap;
	}

	public void setUser(User user){
		this.userConnecting = user;
	}


	public void setConnectedUsers(ConnectedUsers connectedUsers) {
		this.connectedUsers = connectedUsers;
	}

	public User getConnectedUser(){
		User user = userConnecting;
		return user;
	}

	private void configureLogging() {
		Properties properties = new Properties();
		properties.put("log4j.rootLogger", "ALL, MY_APPENDER, CONSOLE_APPENDER");

		properties.put("log4j.appender.MY_APPENDER", "org.apache.log4j.FileAppender");
		properties.put("log4j.appender.MY_APPENDER.layout", "org.apache.log4j.PatternLayout");
		String pattern = "[%p] - %d{HH:mm:ss.SSS}-[%t] <%c> - %m%n";
		properties.put("log4j.appender.MY_APPENDER.layout.conversionPattern", pattern);
		properties.put("log4j.appender.MY_APPENDER.File", "server_log.txt");

		properties.put("log4j.appender.CONSOLE_APPENDER", "org.apache.log4j.ConsoleAppender");
		properties.put("log4j.appender.CONSOLE_APPENDER.layout", "org.apache.log4j.PatternLayout");
		properties.put("log4j.appender.CONSOLE_APPENDER.layout.conversionPattern", pattern);
		PropertyConfigurator.configure(properties);
	}
}
