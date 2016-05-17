package server.clientActionHandlers;


import requests.MoveGameRequest;
import server.ClientActionHandler;
import server.ClientConnector;
import server.ConnectedUsers;

public class MoveGameRequestHandler implements ClientActionHandler<MoveGameRequest> {
    private ConnectedUsers connectedUsers;

    @Override
    public void handle(MoveGameRequest moveGameRequest, ClientConnector clientConnector) {


    }

}
