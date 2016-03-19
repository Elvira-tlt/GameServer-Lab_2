package server.clientActionHandlers;


import actionsFromClient.FreeUsersClientRequest;
import actionsFromServer.ClientActionHandler;
import server.ClientConnector;

public class FreeUsersRequestHandler implements ClientActionHandler<FreeUsersClientRequest> {
    @Override
    public void handle(FreeUsersClientRequest freeUsersRequest, ClientConnector connector) {
        System.out.println("FreeUsersRequestHandler");
    }
}
