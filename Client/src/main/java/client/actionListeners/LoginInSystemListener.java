package client.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import actionsFromClient.LoginClientRequest;
import client.ServerConnector;
import client.view.ConnectingWindow;
import client.view.MainWindow;

import javax.swing.*;

public class LoginInSystemListener implements ActionListener {
	private ServerConnector serverConnector;
	private MainWindow mainWindow;
	private ConnectingWindow loginJFrame;
	private String loginValue;
	private String passwordValue;

	
	public LoginInSystemListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}


    @Override
    public void actionPerformed(ActionEvent e) {
		loginJFrame = mainWindow.getLoginJFrame();
    	loginValue = loginJFrame.getLoginFromField();
    	passwordValue = loginJFrame.getPasswordFromField();

		boolean loginIsNotNull = loginValue != null;
		boolean passwordIsNotNull = passwordValue != null;

		if (loginIsNotNull && passwordIsNotNull) {
			sendRequest(loginValue, passwordValue);
		} else {
			/////// TODO
			System.out.println("Поля Логин и пароль обязательно должны быть заполнены:");
			System.out.println("Login: " + loginValue + "; Password: " + passwordValue);
			///////
		}
    }

	private void sendRequest(String loginValue, String passwordValue){
		LoginClientRequest loginRequest = new LoginClientRequest(loginValue, passwordValue);
		serverConnector.sendToClientConnector(loginRequest);

		loginJFrame.dispose();
	}
    
    public void setServerConnector(ServerConnector serverConnector){
    	this.serverConnector = serverConnector;
    }
}
