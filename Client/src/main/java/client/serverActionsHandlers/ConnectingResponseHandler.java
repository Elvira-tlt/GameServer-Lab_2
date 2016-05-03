package client.serverActionsHandlers;

import requests.ServerActionHandler;
import responses.ClientActionHandler;
import responses.ConnectingServerResponse;

public class ConnectingResponseHandler implements ServerActionHandler<ConnectingServerResponse> {

    @Override
    public void handle(ConnectingServerResponse connectingResponse) {
        System.out.println("ConnectingResponseHandlerClient");
    }

	

}
