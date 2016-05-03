package client.serverActionsHandlers;

import requests.ServerActionHandler;
import responses.LoginServerResponse;
import responses.LoginTypeResponseFromServer;
import responses.RegistrationServerResponse;


public class RegistrationResponseHandler implements ServerActionHandler<RegistrationServerResponse> {
	
    @Override
    public void handle(RegistrationServerResponse registrationResponse) {
        //TODO
    	boolean responseFromServer = registrationResponse.getResponse();
    	System.out.println("IN RegistrationResponseHandlerClient \nrecive action: " + registrationResponse);
    	System.out.println("Response: is " + responseFromServer);
    }
}
