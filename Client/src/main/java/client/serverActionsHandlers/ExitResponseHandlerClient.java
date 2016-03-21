package client.serverActionsHandlers;

import actionsFromClient.ServerActionHandler;
import actionsFromServer.ClientActionHandler;
import actionsFromServer.ExitServerResponse;


public class ExitResponseHandlerClient implements ServerActionHandler<ExitServerResponse> {

    @Override
    public void handle(ExitServerResponse exitResponse) {
        System.out.println("ExitResponseHandlerClient");
    }

}
