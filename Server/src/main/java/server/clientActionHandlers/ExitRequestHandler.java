package server.clientActionHandlers;


import requests.ExitClientRequest;
import responses.ClientActionHandler;
import server.ClientConnector;
import server.ConnectedUsers;
import user.User;

public class ExitRequestHandler implements ClientActionHandler<ExitClientRequest> {
    private ConnectedUsers connectedUsers;

    @Override
    public void handle(ExitClientRequest eExitRequest, ClientConnector clientConnector) {
        System.out.println("ExitRequestHandler");


        User onlineUserForExit = clientConnector.getConnectedUser();
        connectedUsers.removeOnlineUser(onlineUserForExit);
    }

    public void setConnectedUsers(ConnectedUsers connectedUsers) {
        this.connectedUsers = connectedUsers;
    }
}
