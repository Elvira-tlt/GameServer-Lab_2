package server.clientActionHandlers;


import requests.LoginClientRequest;
import server.ClientActionHandler;
import responses.LoginServerResponse;
import responses.LoginTypeResponseFromServer;
import server.ClientConnector;
import user.User;
import server.UserDatabase;
import server.ConnectedUsers;

public class LoginRequestHandler implements ClientActionHandler<LoginClientRequest> {
	private UserDatabase userDatabase;
	private ConnectedUsers connectedUsers;

    @Override
    public void handle(LoginClientRequest loginFromClientAction, ClientConnector clientConnector) {
    	User connectedUser = null;
        LoginTypeResponseFromServer loginTypeResponseFromServer;

        String login = loginFromClientAction.getLogin();
        String password = loginFromClientAction.getPassword();
            try {
               connectedUser = userDatabase.getUser(login,password);
               loginTypeResponseFromServer = LoginTypeResponseFromServer.SUCCESSFUL;
               clientConnector.setUser(connectedUser);
               connectedUsers.addOnlineUser(connectedUser, clientConnector);
               
               
            } catch (NotFoundExeption notFoundExeption) {
                loginTypeResponseFromServer = LoginTypeResponseFromServer.NOT_FOUND;
            } catch (IncorrectPasswordExeption incorrectPasswordExeption) {
                loginTypeResponseFromServer = LoginTypeResponseFromServer.INCORRECT_PASSWORD;
            }

        sendResponseToClient(clientConnector, loginTypeResponseFromServer);
    }

    private void sendResponseToClient(ClientConnector connector, LoginTypeResponseFromServer loginTypeResponseFromServer) {
        LoginServerResponse loginServerResponse = new LoginServerResponse(loginTypeResponseFromServer);
        connector.sendAction(loginServerResponse);
    }

    public void setUserDatabase(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }
    
    public void setConnectingUsers(ConnectedUsers connectedUsers) {
        this.connectedUsers = connectedUsers;
    }


}
