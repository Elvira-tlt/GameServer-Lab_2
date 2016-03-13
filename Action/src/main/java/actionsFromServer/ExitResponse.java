package actionsFromServer;



public class ExitResponse implements Action {
	private boolean isExit;

    public ExitResponse(boolean isExit) {
        this.isExit = isExit;
    }
    
    public boolean getResponse() {
    	return isExit;
    }
}
