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
    	 //
    	System.out.println("IN LoginResponseHandlerClient recive action: " + loginResponse);
         //TODO
    	
    	ConnectingWindow connectingWindow = mainWindow.getLoginJFrame();
    	
    	LoginTypeResponseFromServer responseServer = loginResponse.getResponse();
    	if(responseServer == LoginTypeResponseFromServer.NOT_FOUND){
    		connectingWindow.setTextToProcessInformation(TypeInformationText.NEGATIVE, "Пожалуйста, проверьте правильность введеных Вами данных");
			System.out.println(connectingWindow);
			mainWindow.displayStartWindow();
    	} else if (responseServer == LoginTypeResponseFromServer.INCORRECT_PASSWORD){
    		connectingWindow.setTextToProcessInformation(TypeInformationText.NEGATIVE,"Неверный пароль!");
    		mainWindow.displayStartWindow();
    		
    	}else if (responseServer == LoginTypeResponseFromServer.SUCCESSFUL){
    		//TODO
    		//MainWindow  - start process
    		
    		////
    		//connectingWindow.setTextToProcessInformation(TypeInformationText.POSITIVE, "УСПЕШНО!");
    		//mainWindow.displayStartWindow();
    		/////

			mainWindow.displayWindow();
			///
			System.out.println("До отправки запроса на UsersOnline");
			
			///
			
			serverConnector.sendToClientConnector(new UsersClientRequest());
			///
			System.out.println("запрос на UsersOnline отправлен: ");
			
			///
			
    	}
    		
       


    }
    
    public void setMainWindow(MainWindow mainWindow){
    	this.mainWindow = mainWindow;
    }
    
    public void setServerconnector (ServerConnector serverConnector){
    	this.serverConnector = serverConnector;
    }
    
}
