package client.serverActionsHandlers;

import javax.swing.JFrame;

import requests.ServerActionHandler;
import requests.UsersClientRequest;
import responses.LoginServerResponse;
import responses.LoginTypeResponseFromServer;
import client.ServerConnector;
import client.view.ConnectingWindow;
import client.view.MainWindow;
import client.view.TypeInformationText;


public class LoginResponseHandler implements ServerActionHandler<LoginServerResponse> {
	private MainWindow mainWindow;
	private ServerConnector serverConnector;

    @Override
    public void handle(LoginServerResponse loginResponse) {
    	ConnectingWindow connectingWindow = mainWindow.getLoginJFrame();
    	
    	LoginTypeResponseFromServer responseServer = loginResponse.getResponse();
    	if(responseServer == LoginTypeResponseFromServer.NOT_FOUND){
    		connectingWindow.setTextToProcessInformation(TypeInformationText.NEGATIVE, "Пожалуйста, проверьте правильность введеных Вами данных");
			mainWindow.displayStartWindow();
    	} else if (responseServer == LoginTypeResponseFromServer.INCORRECT_PASSWORD){
    		connectingWindow.setTextToProcessInformation(TypeInformationText.NEGATIVE,"Неверный пароль!");
    		mainWindow.displayStartWindow();
		}else if(responseServer == LoginTypeResponseFromServer.USER_CONNECTED){
			connectingWindow.setTextToProcessInformation(TypeInformationText.NEGATIVE,"Данный пользователь уже вошел в систему!");
			mainWindow.displayStartWindow();
		}else if (responseServer == LoginTypeResponseFromServer.SUCCESSFUL){
    		connectingWindow.setTextToProcessInformation(TypeInformationText.POSITIVE, "УСПЕШНО!");
    		String nameConnectedUser = loginResponse.getNameConnectedUser();
			serverConnector.setNameConnectedUser(nameConnectedUser);
    		mainWindow.displayWindow();
    		
			serverConnector.sendToClientConnector(new UsersClientRequest());
    	}
    }
    
    public void setMainWindow(MainWindow mainWindow){
    	this.mainWindow = mainWindow;
    }
    
    public void setServerconnector (ServerConnector serverConnector){
    	this.serverConnector = serverConnector;
    }
    
}
