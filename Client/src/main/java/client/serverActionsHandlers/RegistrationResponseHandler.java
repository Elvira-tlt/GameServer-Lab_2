package client.serverActionsHandlers;

import client.ServerConnector;
import client.view.ConnectingWindow;
import client.view.MainWindow;
import client.view.TypeInformationText;
import requests.ServerActionHandler;
import responses.LoginServerResponse;
import responses.LoginTypeResponseFromServer;
import responses.RegistrationServerResponse;


public class RegistrationResponseHandler implements ServerActionHandler<RegistrationServerResponse> {
	private MainWindow mainWindow;
	private ServerConnector serverConnector;
	private final String TEXT_SUCCESSFUL_REGISTRATION = "<html>Вы успешно зарегистрировались!" + 
			"<br> Пожалуйста, введите ваши данные еще раз, чтобы войти в систему. </html>" ;
    
	@Override
    public void handle(RegistrationServerResponse registrationResponse) {
		//TODO
		
		ConnectingWindow connectingWindow = mainWindow.getLoginJFrame();
		
    	boolean responseFromServer = registrationResponse.getResponse();
    	if(responseFromServer){
    		connectingWindow.setTextToProcessInformation(TypeInformationText.POSITIVE,TEXT_SUCCESSFUL_REGISTRATION);
    		mainWindow.displayStartWindow();
    	}
   
    	
    	
	
	}
	
	public void setMainWindow(MainWindow mainWindow){
    	this.mainWindow = mainWindow;
    }
}
