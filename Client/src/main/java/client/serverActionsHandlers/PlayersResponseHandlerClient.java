package client.serverActionsHandlers;

import actionsFromClient.ServerActionHandler;
import actionsFromServer.ClientActionHandler;
import actionsFromServer.PlayersServerResponse;


public class PlayersResponseHandlerClient implements ServerActionHandler<PlayersServerResponse> {

    @Override
    public void handle(PlayersServerResponse playersResponse) {
        System.out.println("FreeUsersResponseHandlerClient");
    }

}
