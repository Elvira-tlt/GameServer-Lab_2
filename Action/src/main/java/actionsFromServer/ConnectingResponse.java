package actionsFromServer;



public class ConnectingResponse implements Action {
    private boolean connectIsSuccessful;

    public ConnectingResponse(boolean connectIsSuccessful) {
        this.connectIsSuccessful = connectIsSuccessful;
    }
    
    public boolean getResponse() {
    	return connectIsSuccessful;
    }
}
