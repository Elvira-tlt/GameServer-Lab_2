package server.clientActionHandlers;


import actionsFromClient.PlayersClientRequest;
import actionsFromServer.ClientActionHandler;
import server.ClientConnector;

public class PlayersRequestHandler implements ClientActionHandler<PlayersClientRequest> {
    @Override
    public void handle(PlayersClientRequest playersRequest, ClientConnector connector) {
        System.out.println("PlayersRequestHandler");
    }
}
