package responses;

import typeTeam.TypeTeam;



public class StartGameResponse implements Action {
    private String currentPlayerName;

    private String otherPlayerName;
    private TypeTeam typeTeamCurrentPlayer;
    private TypeTeam typeTeamOtherPlayer;
    private String namePlayerCurrentStroke;

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

    public String getNamePlayerCurrentStroke() {
        return namePlayerCurrentStroke;
    }

    public void setNamePlayerCurrentStroke(String namePlayerCurrentStroke) {
        this.namePlayerCurrentStroke = namePlayerCurrentStroke;
    }

    @Override
    public String toString() {
        return "StartGameResponse{" +
                "currentPlayerName='" + currentPlayerName + '\'' +
                ", otherPlayerName='" + otherPlayerName + '\'' +
                ", typeTeamCurrentPlayer=" + typeTeamCurrentPlayer +
                ", typeTeamOtherPlayer=" + typeTeamOtherPlayer +
                '}';
    }
}
