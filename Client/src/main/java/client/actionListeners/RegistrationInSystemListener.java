package client.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import requests.LoginClientRequest;
import requests.RegistrationClientRequest;
import client.ServerConnector;
import client.view.ConnectingWindow;
import client.view.MainWindow;
import client.view.TypeInformationText;

public class RegistrationInSystemListener implements ActionListener {
	private ServerConnector serverConnector;
	private MainWindow mainWindow;
	private ConnectingWindow loginJFrame;
	private String loginValue;
	private String passwordValue;
	private final static String WARN_INFORMATION_ON_LOGIN_FRAME = "Поля Логин и пароль обязательно должны быть заполнены.";

	
	public RegistrationInSystemListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
    	loginJFrame = mainWindow.getLoginJFrame();
    	loginValue = loginJFrame.getLoginFromField();
    	passwordValue = loginJFrame.getPasswordFromField();
    	
		boolean loginIsNotNull = (loginValue != null) && (!loginValue.isEmpty())  ;
		boolean passwordIsNotNull = (passwordValue != null) && (!passwordValue.isEmpty());

		if (loginIsNotNull && passwordIsNotNull) {
			sendRequest(loginValue, passwordValue);
		} else {
			loginJFrame.setTextToProcessInformation(TypeInformationText.NEGATIVE, WARN_INFORMATION_ON_LOGIN_FRAME);
		}
    }

	private void sendRequest(String loginValue, String passwordValue){
		RegistrationClientRequest registrationRequest = new RegistrationClientRequest(loginValue, passwordValue);
		serverConnector.sendToClientConnector(registrationRequest);
		loginJFrame.dispose();
	}
    
    public void setServerConnector(ServerConnector serverConnector){
    	this.serverConnector = serverConnector;
    }
}
