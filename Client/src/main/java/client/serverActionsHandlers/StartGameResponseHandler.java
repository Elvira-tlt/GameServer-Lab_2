package client.serverActionsHandlers;

import client.view.GamePanel;
import move.TypeValueCurrentStateGame;
import typeTeam.TypeTeam;
import requests.ServerActionHandler;
import responses.StartGameResponse;

public class StartGameResponseHandler implements ServerActionHandler<StartGameResponse> {
    private GamePanel gamePanel;
    private String currentPlayerName;
    private String otherPlayerName;
    private String namePlayerCurrentStroke;
    private TypeTeam typeTeamThisPlayer;
	private TypeTeam typeTeamRival;

    @Override
    public void handle(StartGameResponse startGameResponse) {
        currentPlayerName = startGameResponse.getCurrentPlayerName();
        otherPlayerName = startGameResponse.getOtherPlayerName();
        typeTeamThisPlayer = startGameResponse.getTypeTeamCurrentPlayer();
        typeTeamRival = startGameResponse.getTypeTeamOtherPlayer();
        namePlayerCurrentStroke = startGameResponse.getNamePlayerCurrentStroke();
        
		gamePanel.setTypeTeamToPlayers(typeTeamThisPlayer, typeTeamRival);
        gamePanel.setNameToPlayers(currentPlayerName, otherPlayerName);
        gamePanel.changeTextInformationGamePanel(TypeValueCurrentStateGame.CHANGE_CURRENT_PLAYER, namePlayerCurrentStroke);
        gamePanel.display();
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }






}
