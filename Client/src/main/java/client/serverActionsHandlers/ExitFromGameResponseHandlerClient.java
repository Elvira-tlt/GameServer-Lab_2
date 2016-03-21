package client.serverActionsHandlers;

import actionsFromClient.ServerActionHandler;
import actionsFromServer.ClientActionHandler;
import actionsFromServer.ExitFromGameServerResponse;


public class ExitFromGameResponseHandlerClient implements ServerActionHandler<ExitFromGameServerResponse> {

    @Override
    public void handle(ExitFromGameServerResponse exitFromGameResponse) {
        System.out.println("ExitFromGameResponseHandlerClient");
    }

}
