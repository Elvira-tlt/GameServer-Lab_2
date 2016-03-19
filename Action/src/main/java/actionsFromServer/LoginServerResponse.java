package actionsFromServer;



public class LoginServerResponse implements Action {
    private boolean loginIsSuccessful;

    public LoginServerResponse(boolean loginIsSuccessful) {
        this.loginIsSuccessful = loginIsSuccessful;
    }
    
    public boolean getResponse() {
    	return loginIsSuccessful;
    }
    
    /*если false, то указывается номер ошибки: 
     * не заполнено поле login
     * 
     * 
     * 
     * 
     * 
     */
}
