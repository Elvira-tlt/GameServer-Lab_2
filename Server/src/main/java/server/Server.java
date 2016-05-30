package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.*;
import responses.StartGameResponse;
import server.clientActionHandlers.*;
import server.game.GameRepository;

public class Server {
	private static final Logger LOG = LoggerFactory.getLogger(Server.class);
	public static final int PORT = 8008;
	private Map<Class, ClientActionHandler> actions2Handlers = new HashMap<>();
	private UserDatabase userDatabase;
	private ConnectedUsers connectedUsers;
	private GameRepository gameRepository;

	public Server() {
		userDatabase = new UserDatabase();
		connectedUsers = new ConnectedUsers();
		gameRepository = new GameRepository();
		gameRepository.setConnectedUsers(connectedUsers);
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
		StartGameRequestHandler startGameRequestHandler = new StartGameRequestHandler();
		MoveGameRequestHandler moveGameRequestHandler = new MoveGameRequestHandler();
			
		//setting other classes to ActionHandlers:
		loginRequestHandler.setUserDatabase(userDatabase);
		loginRequestHandler.setConnectingUsers(connectedUsers);
		registrationRequestHandler.setUserDatabase(userDatabase);
		usersRequestHandler.setOnlineUsers(connectedUsers);
		startGameRequestHandler.setConnectedUsers(connectedUsers);
		startGameRequestHandler.setGameRepository(gameRepository);
		moveGameRequestHandler.setGameRepository(gameRepository);
		exitFromGameRequestHandler.setConnectedUsers(connectedUsers);
		exitFromGameRequestHandler.setGameRepository(gameRepository);
		exitRequestHandler.setConnectedUsers(connectedUsers);

		//add ActionHandlers to Map actions2Handlers:
		actions2Handlers.put(LoginClientRequest.class,loginRequestHandler);
		actions2Handlers.put(RegistrationClientRequest.class, registrationRequestHandler);
		actions2Handlers.put(UsersClientRequest.class,usersRequestHandler);
		actions2Handlers.put(ExitClientRequest.class,exitRequestHandler);
		actions2Handlers.put(ExitFromGameClientRequest.class,exitFromGameRequestHandler);
		actions2Handlers.put(StartGameRequest.class, startGameRequestHandler);
		actions2Handlers.put(MoveGameRequest.class, moveGameRequestHandler);
	}

	public void startServer () {
		configureLogging();
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			LOG.info("Started: {}", serverSocket);
			while(true) {
				Socket socket = serverSocket.accept();
				LOG.info("Connection accepted: {}", socket);
				createClientsInteraction(socket);
			}
		} catch (IOException e) {
			LOG.error("Не удалось запустить сервер на порту {}", PORT, e);
		}
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

	public static void main (String[] args) throws IOException {
		new Server().startServer();
	}
}



