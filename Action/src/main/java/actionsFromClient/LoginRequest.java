package actionsFromClient;


import actionsFromServer.Action;

public class LoginRequest implements Action {
    private String loginUser;

    public LoginRequest(String loginUser) {
        this.loginUser = loginUser;
    }
    
    public String getLogin() {
    	return loginUser;
    }
}
