package server.clientActionHandlers;


import actionsFromClient.ExitClientRequest;
import actionsFromServer.ClientActionHandler;
import server.ClientConnector;

public class ExitRequestHandler implements ClientActionHandler<ExitClientRequest> {
    @Override
    public void handle(ExitClientRequest eExitRequest, ClientConnector connector) {
        System.out.println("ExitRequestHandler");
    }
}
