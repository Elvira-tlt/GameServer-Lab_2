package server.clientActionHandlers;


import requests.RegistrationClientRequest;
import responses.LoginTypeResponseFromServer;
import server.ClientActionHandler;
import responses.RegistrationServerResponse;
import server.ClientConnector;
import server.ConnectedUsers;
import user.User;
import server.UserDatabase;

import java.util.List;

public class RegistrationRequestHandler implements ClientActionHandler<RegistrationClientRequest> {
	private UserDatabase userDatabase;
	private ConnectedUsers connectedUsers;

    @Override
    public void handle(RegistrationClientRequest registrationClientRequest, ClientConnector connector) {
        User connectedUser = null;
        LoginTypeResponseFromServer loginTypeResponseFromServer;

        String login = registrationClientRequest.getLogin();
        String password = registrationClientRequest.getPassword();

        boolean thisNameIsUnigue = checkUniqueName(login);
        if(thisNameIsUnigue){
            connectedUser = createNewUser(login, password);
            loginTypeResponseFromServer = LoginTypeResponseFromServer.SUCCESSFUL;
            userDatabase.addUser(connectedUser);
            connector.setUser(connectedUser);
        } else {
            loginTypeResponseFromServer = LoginTypeResponseFromServer.NOT_UNIQUE_NAME;
        }
        sendResponseToClient(connector, loginTypeResponseFromServer);
    }

    private User createNewUser(String userName, String passwordUser) {
    	int userID = userDatabase.getNextFreeID();
    	User connectedNewUser = new User(userID);
    	connectedNewUser.setNameUser(userName);
        connectedNewUser.setPasswordUser(passwordUser);
    	return connectedNewUser;
    }
    
    public void setUserDatabase(UserDatabase userDatebase) {
    	this.userDatabase = userDatebase;
    }
    
    public void setOnlineUsers(ConnectedUsers connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    private void sendResponseToClient(ClientConnector connector, LoginTypeResponseFromServer responseAboutConnected) {
       RegistrationServerResponse registrationServerResponse = new RegistrationServerResponse(responseAboutConnected);
       connector.sendAction(registrationServerResponse);
    }

    private boolean checkUniqueName(String nameFirCheck){
        boolean thisNameIsUnique = true;
        List<User> usersInDatabase = userDatabase.getIdentidiedUsers();
        for(User userInDatabase: usersInDatabase){
            String nameUserInDatabase = userInDatabase.getNameUser();
            if(nameUserInDatabase.equals(nameFirCheck)){
                thisNameIsUnique = false;
                break;
            }
        }
        return thisNameIsUnique;
    }
}
