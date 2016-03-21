package server.clientActionHandlers;


import actionsFromServer.LoginTypeResponseFromServer;
import server.ClientConnector;
import server.User;
import server.UserDatabase;
import actionsFromClient.LoginClientRequest;
import actionsFromServer.ClientActionHandler;
import actionsFromServer.LoginServerResponse;

public class LoginRequestHandler implements ClientActionHandler<LoginClientRequest> {
	private UserDatabase userDatabase;

    @Override
    public void handle(LoginClientRequest loginFromClientAction, ClientConnector connector) {
    	User connectedUser = null;
        LoginTypeResponseFromServer loginTypeResponseFromServer;

        String login = loginFromClientAction.getLogin();
        String password = loginFromClientAction.getPassword();
            try {
               connectedUser = userDatabase.getUser(login,password);
                loginTypeResponseFromServer = LoginTypeResponseFromServer.SUCCESSFUL;
            } catch (NotFoundExeption notFoundExeption) {
                loginTypeResponseFromServer = LoginTypeResponseFromServer.NOT_FOUND;
            } catch (IncorrectPasswordExeption incorrectPasswordExeption) {
                loginTypeResponseFromServer = LoginTypeResponseFromServer.INCORRECT_PASSWORD;
            }

        sendResponseToClient(connector, loginTypeResponseFromServer);
    }

    private void sendResponseToClient(ClientConnector connector, LoginTypeResponseFromServer loginTypeResponseFromServer) {
        LoginServerResponse loginServerResponse = new LoginServerResponse(loginTypeResponseFromServer);
        connector.sendAction(loginServerResponse);
    }

    public void setUserDatabase(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }
}
