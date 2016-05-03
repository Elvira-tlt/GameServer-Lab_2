package responses;



public class ExitFromGameServerResponse implements Action {
	private boolean isExitFromGame;

    public ExitFromGameServerResponse(boolean isExit) {
        this.isExitFromGame = isExit;
    }
    
    public boolean getResponse() {
    	return isExitFromGame;
    }
}
