package responses;



public class LoginServerResponse implements Action {
    private LoginTypeResponseFromServer typeResponseFromServer;
    private String nameConnectedUser;

    public LoginServerResponse(LoginTypeResponseFromServer typeResponseFromServer) {
        this.typeResponseFromServer = typeResponseFromServer;
    }
    
    public LoginTypeResponseFromServer getResponse() {
    	return typeResponseFromServer;
    }
    
    public String getNameConnectedUser(){
    	return nameConnectedUser;
    }
    
    public void setNameConnectedUser(String nameConnectedUser){
    	this.nameConnectedUser = nameConnectedUser;
    }
}
