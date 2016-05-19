package client.serverActionsHandlers;

import client.view.GamePanel;
import typeTeam.TypeTeam;
import requests.ServerActionHandler;
import responses.StartGameResponse;

import java.util.Random;


public class StartGameResponseHandler implements ServerActionHandler<StartGameResponse> {
    private GamePanel gamePanel;
    private String currentPlayerName;
    private String otherPlayerName;
    private TypeTeam typeTeamThisPlayer;
	private TypeTeam typeTeamRival;

    @Override
    public void handle(StartGameResponse startGameResponse) {
        currentPlayerName = startGameResponse.getCurrentPlayerName();
        otherPlayerName = startGameResponse.getOtherPlayerName();
        typeTeamThisPlayer = startGameResponse.getTypeTeamCurrentPlayer();
        typeTeamRival = startGameResponse.getTypeTeamOtherPlayer();
        
		gamePanel.setTypeTeamToPlayers(typeTeamThisPlayer, typeTeamRival);
        gamePanel.setNameToPlayers(currentPlayerName, otherPlayerName);
        gamePanel.display();
        
      ///
    	System.out.println("FROM ResponseHandler:");
    	System.out.println("currentPlayerName :" + currentPlayerName + " - " + typeTeamThisPlayer);
    	System.out.println("otherPlayerName :" + otherPlayerName + " - " + typeTeamRival);
    	///
        
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }






}
