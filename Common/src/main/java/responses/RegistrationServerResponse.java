package responses;



public class RegistrationServerResponse implements Action {
    private boolean loginIsSuccessful;
    private String nameConnectedUser;

	public RegistrationServerResponse(boolean loginIsSuccessful) {
        this.loginIsSuccessful = loginIsSuccessful;
    }
    
    public boolean getResponse() {
    	return loginIsSuccessful;
    }
    
    public String getNameConnectedUser() {
		return nameConnectedUser;
	}

	public void setNameConnectedUser(String nameConnectedUser) {
		this.nameConnectedUser = nameConnectedUser;
	}
}
