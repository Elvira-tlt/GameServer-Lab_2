package server.clientActionHandlers;


import requests.ExitFromGameClientRequest;
import server.ClientActionHandler;
import server.ClientConnector;

public class ExitFromGameRequestHandler implements ClientActionHandler<ExitFromGameClientRequest> {
    @Override
    public void handle(ExitFromGameClientRequest exitFromGameRequest, ClientConnector connector) {
        System.out.println("ExitFromGameRequestHandler");
    }
}
