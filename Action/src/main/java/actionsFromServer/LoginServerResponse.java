package actionsFromServer;



public class LoginServerResponse implements Action {
    private LoginTypeResponseFromServer typeResponseFromServer;

    public LoginServerResponse(LoginTypeResponseFromServer typeResponseFromServer) {
        this.typeResponseFromServer = typeResponseFromServer;
    }
    
    public LoginTypeResponseFromServer getResponse() {
    	return typeResponseFromServer;
    }
}
