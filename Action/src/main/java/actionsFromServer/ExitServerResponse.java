package actionsFromServer;



public class ExitServerResponse implements Action {
	private boolean isExit;

    public ExitServerResponse(boolean isExit) {
        this.isExit = isExit;
    }
    
    public boolean getResponse() {
    	return isExit;
    }
}
