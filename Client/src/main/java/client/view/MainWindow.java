package client.view;

import javax.swing.*;

public class MainWindow extends JFrame{
	private final String TITLEGAME = "Игра \"Морской бой\"";
	
	private ConnectingWindow connectingWindow;
	
    private JPanel panelOnlineUsers;
    private JPanel gamePanel;
    private JPanel panelIdentificationClient;
    
    public MainWindow() {
    	setSize(700, 550);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle(TITLEGAME);
        setLocationRelativeTo(null);
        // setResizable(false);
        
        displayConnectingWindow();
	}
    
    
    
    
    
    
    
    private void displayConnectingWindow() {
    	connectingWindow = new ConnectingWindow();
    	connectingWindow.displayWindow();
    	connectingWindow.setAlwaysOnTop(true);
    }



    public static void main(String[] args) throws InterruptedException {
    	MainWindow mainWindow = new MainWindow();
    }

}
