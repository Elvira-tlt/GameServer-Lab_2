package requests;


import responses.Action;

public class RegistrationClientRequest implements Action {
    private String loginUser;
    private String passwordUser;

    public RegistrationClientRequest(String loginUser, String passwordUser) {
        this.loginUser = loginUser;
        this.passwordUser = passwordUser;
    }

    public String getLogin() {
    	return loginUser;
    }

    public String getPassword() {
        String passwordUs = passwordUser;
        return passwordUs;
    }
}
