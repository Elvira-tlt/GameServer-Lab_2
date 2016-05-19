package responses;

import typeTeam.TypeTeam;



public class StartGameResponse implements Action {
    private String currentPlayerName;
    private String otherPlayerName;
    private TypeTeam typeTeamCurrentPlayer;

	private TypeTeam typeTeamOtherPlayer;

    public void setTypeTeamAndNameThisPlayer(String currentPlayerName, TypeTeam typeTeamCurrentPlayer){
    	this.currentPlayerName = currentPlayerName;
    	this.typeTeamCurrentPlayer = typeTeamCurrentPlayer;
    }
    
    public void setTypeTeamAndNameOtherPlayer(String otherPlayerName, TypeTeam typeTeamOtherPlayer){
    	this.otherPlayerName = otherPlayerName;
    	this.typeTeamOtherPlayer = typeTeamOtherPlayer;
    }

    public String getCurrentPlayerName() {
        return currentPlayerName;
    }

    public String getOtherPlayerName() {
        return otherPlayerName;
    }
    
    public TypeTeam getTypeTeamCurrentPlayer() {
		return typeTeamCurrentPlayer;
	}

	public TypeTeam getTypeTeamOtherPlayer() {
		return typeTeamOtherPlayer;
	}
}
