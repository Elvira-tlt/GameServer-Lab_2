package client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import client.serverActionsHandlers.*;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import requests.ServerActionHandler;
import responses.*;
import client.view.MainWindow;

public class Client {
	private static final Logger LOG = LoggerFactory.getLogger(Client.class);
	private ServerConnector serverConnector = new ServerConnector();
	private MainWindow mainWindow;
	private String fileNameToSaveLog = "client_log" + System.currentTimeMillis()+".txt";

	public void connectingToServer(){
		configureLogging();
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
		MoveGameResponseHandler moveGameResponseHandler = new MoveGameResponseHandler();
		EndedGameResponseHandler endedGameResponseHandler = new EndedGameResponseHandler();

		//setting other classes to
		loginResponseHandlerClient.setMainWindow(mainWindow);
		loginResponseHandlerClient.setServerconnector(serverConnector);
		registrationResponseHandlerClient.setMainWindow(mainWindow);
		startGameResponseHandler.setMainWindow(mainWindow);
		moveGameResponseHandler.setGamePanel(mainWindow.getGamePanel());
		endedGameResponseHandler.setMainWindow(mainWindow);
		
		usersResponseHandler.setPanelOnlineUsers(mainWindow.getPanelOnlineUsers());
		
		Map<Class, ServerActionHandler> actions2Handlers = new HashMap<Class, ServerActionHandler>();
		actions2Handlers.put(LoginServerResponse.class, loginResponseHandlerClient);
		actions2Handlers.put(RegistrationServerResponse.class, registrationResponseHandlerClient);
		actions2Handlers.put(UsersServerResponse.class, usersResponseHandler);
		actions2Handlers.put(MoveGameResponse.class, moveGameResponseHandler);
		actions2Handlers.put(StartGameResponse.class, startGameResponseHandler);
		actions2Handlers.put(EndedGameResponse.class, endedGameResponseHandler);
		return actions2Handlers;
	}

	private void configureLogging() {
		Properties properties = new Properties();
		properties.put("log4j.rootLogger", "ALL, MY_APPENDER, CONSOLE_APPENDER");

		properties.put("log4j.appender.MY_APPENDER", "org.apache.log4j.FileAppender");
		properties.put("log4j.appender.MY_APPENDER.layout", "org.apache.log4j.PatternLayout");
		String pattern = "[%p] - %d{HH:mm:ss.SSS}-[%t] <%c> - %m%n";
		properties.put("log4j.appender.MY_APPENDER.layout.conversionPattern", pattern);
		properties.put("log4j.appender.MY_APPENDER.File", fileNameToSaveLog);

		properties.put("log4j.appender.CONSOLE_APPENDER", "org.apache.log4j.ConsoleAppender");
		properties.put("log4j.appender.CONSOLE_APPENDER.layout", "org.apache.log4j.PatternLayout");
		properties.put("log4j.appender.CONSOLE_APPENDER.layout.conversionPattern", pattern);
		PropertyConfigurator.configure(properties);
	}
}
