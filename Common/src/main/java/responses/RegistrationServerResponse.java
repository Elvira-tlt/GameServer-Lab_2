package responses;



public class RegistrationServerResponse implements Action {
    private LoginTypeResponseFromServer loginTypeResponseFromServer;
    private String nameConnectedUser;

	public RegistrationServerResponse(LoginTypeResponseFromServer loginTypeResponseFromServer) {
        this.loginTypeResponseFromServer = loginTypeResponseFromServer;
    }
    
    public LoginTypeResponseFromServer getResponse() {
    	return loginTypeResponseFromServer;
    }
    
    public String getNameConnectedUser() {
		return nameConnectedUser;
	}

	public void setNameConnectedUser(String nameConnectedUser) {
		this.nameConnectedUser = nameConnectedUser;
	}
}
