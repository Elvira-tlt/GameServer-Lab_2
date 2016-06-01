package server.clientActionHandlers;


import requests.LoginClientRequest;
import server.ClientActionHandler;
import responses.LoginServerResponse;
import responses.LoginTypeResponseFromServer;
import server.ClientConnector;
import user.User;
import server.UserDatabase;
import server.ConnectedUsers;

import java.util.List;

public class LoginRequestHandler implements ClientActionHandler<LoginClientRequest> {
	private UserDatabase userDatabase;
	private ConnectedUsers connectedUsers;

    @Override
    public void handle(LoginClientRequest loginFromClientAction, ClientConnector clientConnector) {
    	User connectedUser = null;
        String nameConnectedUser = null;
        LoginTypeResponseFromServer loginTypeResponseFromServer;

        String login = loginFromClientAction.getLogin();
        String password = loginFromClientAction.getPassword();
            try {
               connectedUser = userDatabase.getUser(login,password);
               boolean thisUserIsConnectedNow = checkConnectedThisUserNow(connectedUser);

                if(thisUserIsConnectedNow){
                    loginTypeResponseFromServer = LoginTypeResponseFromServer.USER_CONNECTED;
                } else {
                    loginTypeResponseFromServer = LoginTypeResponseFromServer.SUCCESSFUL;
                    clientConnector.setUser(connectedUser);
                    connectedUsers.addOnlineUser(connectedUser, clientConnector);
                }
            } catch (NotFoundException notFoundExeption) {
                loginTypeResponseFromServer = LoginTypeResponseFromServer.NOT_FOUND;
            } catch (IncorrectPasswordExeption incorrectPasswordExeption) {
                loginTypeResponseFromServer = LoginTypeResponseFromServer.INCORRECT_PASSWORD;
            }
        if(connectedUser != null) {
            nameConnectedUser = connectedUser.getNameUser();
        }
        sendResponseToClient(clientConnector, loginTypeResponseFromServer, nameConnectedUser );
    }

    private void sendResponseToClient(ClientConnector connector, LoginTypeResponseFromServer loginTypeResponseFromServer, String nameConnectedUser) {
        LoginServerResponse loginServerResponse = new LoginServerResponse(loginTypeResponseFromServer);
        loginServerResponse.setNameConnectedUser(nameConnectedUser);
        connector.sendAction(loginServerResponse);
    }

    private boolean checkConnectedThisUserNow(User userForCheck){
        boolean userIsConnectedNow = false;
        List<User> onlineUsers = connectedUsers.getOnlineUsersToConnector();
        for(User onlineUser: onlineUsers){
            if(userForCheck.equals(onlineUser)){
                userIsConnectedNow = true;
                break;
            }
        }
        return userIsConnectedNow;
    }

    public void setUserDatabase(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }
    
    public void setConnectingUsers(ConnectedUsers connectedUsers) {
        this.connectedUsers = connectedUsers;
    }


}
