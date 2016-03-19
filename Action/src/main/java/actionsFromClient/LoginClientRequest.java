package actionsFromClient;


import actionsFromServer.Action;

public class LoginClientRequest implements Action {
    private String loginUser;

    public LoginClientRequest(String loginUser) {
        this.loginUser = loginUser;
    }
    
    public String getLogin() {
    	return loginUser;
    }
}
