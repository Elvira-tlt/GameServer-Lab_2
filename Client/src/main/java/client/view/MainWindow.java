package client.view;

import java.awt.Dimension;
import java.awt.event.WindowListener;

import client.ServerConnector;
import client.actionListeners.ClickOnGameTable;
import client.actionListeners.LoginInSystemListener;
import client.actionListeners.RegistrationInSystemListener;
import client.actionListeners.StartedGameListener;
import requests.ExitClientRequest;

import javax.swing.*;

public class MainWindow extends JFrame{
	private final String TITLEGAME = "Игра \"Морской бой\"";
	private ConnectingWindow connectingWindow = new ConnectingWindow();
    private PanelOnlineUsers panelOnlineUsers = new PanelOnlineUsers();
    private GamePanel gamePanel = new GamePanel();
    private JSplitPane splitMain = new JSplitPane();
    private ServerConnector serverConnector;

    private LoginInSystemListener loginInSystemListener;
    private RegistrationInSystemListener registrationInSystemListener;
    private StartedGameListener startGameListener;
    private ClickOnGameTable clickOnGameTableListener;
   
    public MainWindow(ServerConnector serverConnector) {
        this.serverConnector = serverConnector;

        createListeners();
        addListenersTo();

        setSize(900, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(closingProgram);
        setTitle(TITLEGAME);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);

	}

    public void displayWindow(){
    	prepareMainWindowElements();
    }
    
    public void displayGamePanel(){
    	gamePanel.display();
    }
    
    public void displayStartWindow(){
        displayConnectingWindow();
    }

    public void displayConnectingWindow() {
        connectingWindow.setAlwaysOnTop(true);
        connectingWindow.setVisible(true);
    }

    public ConnectingWindow getLoginJFrame(){
        return connectingWindow;
    }

    private void prepareMainWindowElements(){
        splitMain.setRightComponent(panelOnlineUsers);
        splitMain.setLeftComponent(gamePanel);

        Dimension minimumSize = new Dimension(580, 650);
        splitMain.getLeftComponent().setMinimumSize(minimumSize);
        splitMain.setResizeWeight(1.0);
        splitMain.setSize(900, 650);
        splitMain.setDividerSize(5);

        add(splitMain);
    }

    private void createListeners(){
        //TODO
        loginInSystemListener = new LoginInSystemListener(this);
        registrationInSystemListener = new RegistrationInSystemListener(this);
        startGameListener = new StartedGameListener();
        clickOnGameTableListener = new ClickOnGameTable(gamePanel.getTableGameMoved());

        //setting serverConnector to ActionListener
        loginInSystemListener.setServerConnector(serverConnector);
        registrationInSystemListener.setServerConnector(serverConnector);
        startGameListener.setServerConnector(serverConnector);
        startGameListener.setPanelOnlineUsers(panelOnlineUsers);
        clickOnGameTableListener.setServerConnector(serverConnector);
    }

    private void addListenersTo(){
        connectingWindow.setListenerToButtonLogin(loginInSystemListener);
        connectingWindow.setListenerToButtonRegistration(registrationInSystemListener);
        panelOnlineUsers.setListenerToPlayButton(startGameListener);
        gamePanel.setListenersToGameTable(clickOnGameTableListener);
    }

    public PanelOnlineUsers getPanelOnlineUsers(){
    	return panelOnlineUsers;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    private WindowListener closingProgram = new java.awt.event.WindowAdapter() {
        public void windowClosing(java.awt.event.WindowEvent evt) {
          serverConnector.sendToClientConnector(new ExitClientRequest());
        }
    };
}
