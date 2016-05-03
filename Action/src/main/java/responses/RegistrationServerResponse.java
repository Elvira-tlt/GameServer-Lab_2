package responses;



public class RegistrationServerResponse implements Action {
    private boolean loginIsSuccessful;

    public RegistrationServerResponse(boolean loginIsSuccessful) {
        this.loginIsSuccessful = loginIsSuccessful;
    }
    
    public boolean getResponse() {
    	return loginIsSuccessful;
    }
}
