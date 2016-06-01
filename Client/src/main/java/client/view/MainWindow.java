package client.view;

import java.awt.Dimension;
import java.awt.event.WindowListener;

import client.ServerConnector;
import client.actionListeners.*;
import requests.ExitClientRequest;
import requests.ExitFromGameClientRequest;

import javax.swing.*;

public class MainWindow extends JFrame{
	private final static String TITLEGAME = "Игра \"Крестики - нолики\"";
	private ConnectingWindow connectingWindow = new ConnectingWindow();
    private PanelOnlineUsers panelOnlineUsers = new PanelOnlineUsers();
    private GamePanel gamePanel = new GamePanel();
    private JSplitPane splitMain = new JSplitPane();
    private ServerConnector serverConnector;

    private LoginInSystemListener loginInSystemListener;
    private RegistrationInSystemListener registrationInSystemListener;
    private StartedGameListener startGameListener;
    private ClickOnGameTable clickOnGameTableListener;
    private QuitInGameListener quitInGameListener;
   
    public MainWindow(ServerConnector serverConnector) {
        this.serverConnector = serverConnector;
        createListeners();
        addListenersTo();
        setSize(900, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(closingProgram);
        setTitle(TITLEGAME);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
	}

    public void displayWindow(){
    	prepareMainWindowElements();
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
        loginInSystemListener = new LoginInSystemListener(this);
        registrationInSystemListener = new RegistrationInSystemListener(this);
        startGameListener = new StartedGameListener();
        clickOnGameTableListener = new ClickOnGameTable(gamePanel.getTableGameMoved());
        quitInGameListener = new QuitInGameListener();

        //setting serverConnector to ActionListener
        loginInSystemListener.setServerConnector(serverConnector);
        registrationInSystemListener.setServerConnector(serverConnector);
        startGameListener.setServerConnector(serverConnector);
        startGameListener.setPanelOnlineUsers(panelOnlineUsers);
        clickOnGameTableListener.setServerConnector(serverConnector);
        quitInGameListener.setServerConnector(serverConnector);
    }

    private void addListenersTo(){
        connectingWindow.setListenerToButtonLogin(loginInSystemListener);
        connectingWindow.setListenerToButtonRegistration(registrationInSystemListener);
        panelOnlineUsers.setListenerToPlayButton(startGameListener);
        panelOnlineUsers.setListenerToQuitButton(quitInGameListener);
        gamePanel.setListenersToGameTable(clickOnGameTableListener);
    }

    public PanelOnlineUsers getPanelOnlineUsers(){
    	return panelOnlineUsers;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void changePanelButtonToGame(boolean isGame){
        if(isGame){
            panelOnlineUsers.changeButtonToQuitButton();
        } else {
            panelOnlineUsers.changeButtonToPlayButton();
        }
    }

    private transient WindowListener closingProgram = new java.awt.event.WindowAdapter() {
        public void windowClosing(java.awt.event.WindowEvent evt) {
          serverConnector.sendToClientConnector(new ExitClientRequest());
        }
    };
}
