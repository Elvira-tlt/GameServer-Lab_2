package actionsFromServer;



public class LoginResponse implements Action {
    private boolean loginIsSuccessful;

    public LoginResponse(boolean loginIsSuccessful) {
        this.loginIsSuccessful = loginIsSuccessful;
    }
    
    public boolean getResponse() {
    	return loginIsSuccessful;
    }
}
