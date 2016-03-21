package client.serverActionsHandlers;

import actionsFromClient.ServerActionHandler;
import actionsFromServer.ClientActionHandler;
import actionsFromServer.ConnectingServerResponse;

public class ConnectingResponseHandlerClient implements ServerActionHandler<ConnectingServerResponse> {

    @Override
    public void handle(ConnectingServerResponse connectingResponse) {
        System.out.println("ConnectingResponseHandlerClient");
    }

	

}
