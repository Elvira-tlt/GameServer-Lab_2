package actionsFromServer;



public class ExitFromGameResponse implements Action {
	private boolean isExitFromGame;

    public ExitFromGameResponse(boolean isExit) {
        this.isExitFromGame = isExit;
    }
    
    public boolean getResponse() {
    	return isExitFromGame;
    }
}
