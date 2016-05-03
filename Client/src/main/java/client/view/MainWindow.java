package client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.WindowListener;

import client.ServerConnector;
import client.actionListeners.LoginInSystemListener;
import client.actionListeners.RegistrationInSystemListener;
import requests.ExitClientRequest;

import javax.jws.Oneway;
import javax.swing.*;

public class MainWindow extends JFrame{
	private final String TITLEGAME = "Игра \"Морской бой\"";
	
	private ConnectingWindow connectingWindow = new ConnectingWindow();
    private PanelOnlineUsers panelOnlineUsers = new PanelOnlineUsers();
    private JPanel gamePanel = new JPanel();
    private JPanel panelIdentificationClient;
    private JSplitPane splitMain = new JSplitPane();
    
    private ServerConnector serverConnector;
    
    private LoginInSystemListener loginInSystemListener;
    private RegistrationInSystemListener registrationInSystemListener;
    
    public MainWindow(ServerConnector serverConnector) {
        this.serverConnector = serverConnector;

        createListeners();
        prepareConnectingWindow();

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
        panelOnlineUsers.display();
        panelOnlineUsers.updatePanelOnlineUser();

        SwingUtilities.updateComponentTreeUI(this);
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

        Dimension minimumSize = new Dimension(620, 650);
        splitMain.getLeftComponent().setMinimumSize(minimumSize);
        splitMain.setResizeWeight(1.0);
        splitMain.setSize(900, 650);
        splitMain.setDividerSize(5);

        add(splitMain);
    }

    private void prepareConnectingWindow(){
        connectingWindow.setListenerToButtonLogin(loginInSystemListener);
        connectingWindow.setListenerToButtonRegistration(registrationInSystemListener);
    }

    private void createListeners(){
        //TODO
        loginInSystemListener = new LoginInSystemListener(this);
        registrationInSystemListener = new RegistrationInSystemListener(this);

        //setting serverConnector to ActionListener
        loginInSystemListener.setServerConnector(serverConnector);
        registrationInSystemListener.setServerConnector(serverConnector);

    }
    
    public PanelOnlineUsers getPanelOnlineUsers(){
    	System.out.println("return panel:" + panelOnlineUsers);
    	return panelOnlineUsers;
    }
    
    private WindowListener closingProgram = new java.awt.event.WindowAdapter() {
        public void windowClosing(java.awt.event.WindowEvent evt) {
          serverConnector.sendToClientConnector(new ExitClientRequest());
        }
    };
    
   
    
}
