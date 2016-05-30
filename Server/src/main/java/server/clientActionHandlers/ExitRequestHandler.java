package server.clientActionHandlers;


import requests.ExitClientRequest;
import server.ClientActionHandler;
import server.ClientConnector;
import server.ConnectedUsers;
import user.User;

public class ExitRequestHandler implements ClientActionHandler<ExitClientRequest> {
    private ConnectedUsers connectedUsers;
    private boolean thisUserIsPlayer;

    @Override
    public void handle(ExitClientRequest exitRequest, ClientConnector clientConnector) {
        User onlineUserForExit = clientConnector.getConnectedUser();

        thisUserIsPlayer = onlineUserForExit.getStatusUser();
        if(thisUserIsPlayer){
            connectedUsers.changeStatusPlayerTo(onlineUserForExit, false);
        }
        connectedUsers.removeOnlineUser(onlineUserForExit);
    }

    public void setConnectedUsers(ConnectedUsers connectedUsers) {
        this.connectedUsers = connectedUsers;
    }
}
