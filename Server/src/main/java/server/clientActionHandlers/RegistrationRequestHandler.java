package server.clientActionHandlers;


import requests.RegistrationClientRequest;
import server.ClientActionHandler;
import responses.RegistrationServerResponse;
import server.ClientConnector;
import server.ConnectedUsers;
import user.User;
import server.UserDatabase;

public class RegistrationRequestHandler implements ClientActionHandler<RegistrationClientRequest> {
	private UserDatabase userDatabase;
	private ConnectedUsers connectedUsers;

    @Override
    public void handle(RegistrationClientRequest registrationClientRequest, ClientConnector connector) {
          //////
        System.out.println("IN Registration Server's handler");
        //////

        User connectedUser = null;
        boolean isSuccessfulRegistration = false;

        String login = registrationClientRequest.getLogin();
        String password = registrationClientRequest.getPassword();
        
            connectedUser = createNewUser(login, password);
            userDatabase.addUser(connectedUser);
            isSuccessfulRegistration = true;
            connector.setUser(connectedUser);
            connectedUsers.addOnlineUser(connectedUser, connector);

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
    
    public void setOnlineUsers(ConnectedUsers connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    private void sendResponseToClient(ClientConnector connector, boolean responseAboutConnected) {
       RegistrationServerResponse registrationServerResponse = new RegistrationServerResponse(responseAboutConnected);
       connector.sendAction(registrationServerResponse);
    }
}
