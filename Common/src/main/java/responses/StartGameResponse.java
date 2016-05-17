package responses;



public class StartGameResponse implements Action {
    private String currentPlayerName;
    private String otherPlayerName;

    public  StartGameResponse(String currentPlayerName, String otherPlayerName){
        this.currentPlayerName = currentPlayerName;
        this.otherPlayerName = otherPlayerName;
    }


	
}
