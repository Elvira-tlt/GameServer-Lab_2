package client.view;

import client.ServerConnector;
import client.actionListeners.LoginInSystemListener;
import client.actionListeners.RegistrationInSystemListener;

import javax.swing.*;

public class MainWindow extends JFrame{
	private final String TITLEGAME = "Игра \"Морской бой\"";
	
	private ConnectingWindow connectingWindow;
    private JPanel panelOnlineUsers;
    private JPanel gamePanel;
    private JPanel panelIdentificationClient;
    
    private ServerConnector serverConnector;
    
    private LoginInSystemListener loginInSystemListener;
    private RegistrationInSystemListener registrationInSystemListener;
    
    public MainWindow(ServerConnector serverConnector) {
        setServerConnector(serverConnector);
        setSize(900, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle(TITLEGAME);
        setLocationRelativeTo(null);
        setResizable(false);

        createListeners();
        displayConnectingWindow();

	}
    
    public ConnectingWindow getLoginJFrame(){
        return connectingWindow;
    }
    
    private void createListeners(){
    	//TODO
    	loginInSystemListener = new LoginInSystemListener(this);
    	//registrationInSystemListener = new RegistrationInSystemListener();
    	

        //setting serverConnector to ActionListener
        loginInSystemListener.setServerConnector(serverConnector);
    }
    
    
    private void displayConnectingWindow() {
    	connectingWindow = new ConnectingWindow();
        connectingWindow.displayWindow();
    	connectingWindow.setAlwaysOnTop(true);

        connectingWindow.setLoginListenerToButtonLogin(loginInSystemListener);
    }
    
    public void setServerConnector(ServerConnector serverConnector){
    	this.serverConnector = serverConnector;
    }
}
