package responses;



public class ConnectingServerResponse implements Action {
    private boolean connectIsSuccessful;

    public ConnectingServerResponse(boolean connectIsSuccessful) {
        this.connectIsSuccessful = connectIsSuccessful;
    }
    
    public boolean getResponse() {
    	return connectIsSuccessful;
    }
}
