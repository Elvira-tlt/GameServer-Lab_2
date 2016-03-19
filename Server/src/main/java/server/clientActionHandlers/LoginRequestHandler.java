package server.clientActionHandlers;


import server.ClientConnector;
import server.User;
import server.UserDatabase;
import actionsFromClient.LoginClientRequest;
import actionsFromServer.ClientActionHandler;
import actionsFromServer.LoginServerResponse;

public class LoginRequestHandler implements ClientActionHandler<LoginClientRequest> {
	private UserDatabase userDatabase;
	//private int countGuests;
	private boolean isSuccessfulLogin;

	

    @Override
    public void handle(LoginClientRequest loginFromClientAction, ClientConnector connector) {
    	User connectedUser;
        isSuccessfulLogin = false;
    	
    	//////////
        System.out.println("IN LoginRequestHandler");
        //////

        String login = loginFromClientAction.getLogin();
        
        //сделать проверка на !null на стороне клиента??
        if (login != null) {
            String userName = login;
            
          //searchUserInDatabase:
            connectedUser = userDatabase.getUserOfName(userName);
            if (connectedUser != null) {
            	isSuccessfulLogin = true;
            } else {
            	connectedUser = createNewUser(userName);
            	isSuccessfulLogin = true;
            }
        }
        
        sendResponseToClient(connector);
    }
    private User createNewUser(String userName) {
    	User connectedNewUser = new User();
    	connectedNewUser.setNameUser(userName);
    	
    	int userID = userDatabase.getNextFreeID();
    	connectedNewUser.setIdUser(userID);
    	
    	return connectedNewUser;
    }
    
    public void setUserDatabase(UserDatabase userDatebase) {
    	this.userDatabase = userDatebase;
    }


    private void sendResponseToClient(ClientConnector connector) {
        LoginServerResponse loginServerResponse = new LoginServerResponse(isSuccessfulLogin);
        connector.sendAction(loginServerResponse);
    }


}
