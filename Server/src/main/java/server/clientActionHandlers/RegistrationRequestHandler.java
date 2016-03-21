package server.clientActionHandlers;


import actionsFromClient.RegistrationClientRequest;
import actionsFromServer.ClientActionHandler;
import actionsFromServer.RegistrationServerResponse;
import server.ClientConnector;
import server.User;
import server.UserDatabase;

public class RegistrationRequestHandler implements ClientActionHandler<RegistrationClientRequest> {
	private UserDatabase userDatabase;

    @Override
    public void handle(RegistrationClientRequest registrationClientRequest, ClientConnector connector) {
    	User connectedUser = null;
        boolean isSuccessfulRegistration = false;

        String login = registrationClientRequest.getLogin();
        String password = registrationClientRequest.getPassword();
        
            connectedUser = createNewUser(login, password);
            userDatabase.addUser(connectedUser);
            isSuccessfulRegistration = true;

        sendResponseToClient(connector, isSuccessfulRegistration);
    }

    private User createNewUser(String userName, String passwordUser) {
    	User connectedNewUser = new User();
    	connectedNewUser.setNameUser(userName);
        connectedNewUser.setPasswordUser(passwordUser);
    	
    	int userID = userDatabase.getNextFreeID();
    	connectedNewUser.setIdUser(userID);
    	
    	return connectedNewUser;
    }
    
    public void setUserDatabase(UserDatabase userDatebase) {
    	this.userDatabase = userDatebase;
    }

    private void sendResponseToClient(ClientConnector connector, boolean responseAboutConnected) {
       RegistrationServerResponse registrationServerResponse = new RegistrationServerResponse(responseAboutConnected);
        connector.sendAction(registrationServerResponse);
    }
}
