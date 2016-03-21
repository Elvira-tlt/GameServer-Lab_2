package client.serverActionsHandlers;

import actionsFromClient.ServerActionHandler;
import actionsFromServer.ClientActionHandler;
import actionsFromServer.FreeUsersServerResponse;


public class FreeUsersResponseHandlerClient implements ServerActionHandler<FreeUsersServerResponse> {

    @Override
    public void handle(FreeUsersServerResponse freeUsersResponse) {
        System.out.println("FreeUsersResponseHandlerClient");
    }

}
