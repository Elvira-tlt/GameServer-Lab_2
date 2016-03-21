package client.serverActionsHandlers;

import actionsFromClient.ServerActionHandler;
import actionsFromServer.ClientActionHandler;
import actionsFromServer.LoginServerResponse;
import actionsFromServer.LoginTypeResponseFromServer;


public class LoginResponseHandlerClient implements ServerActionHandler<LoginServerResponse> {

    @Override
    public void handle(LoginServerResponse loginResponse) {
        System.out.println("IN LoginResponseHandlerClient \nrecive action: " + loginResponse);
        LoginTypeResponseFromServer responseFromServer = loginResponse.getResponse();
        System.out.println("Response: " + responseFromServer);
        //TODO
        
    }
}
