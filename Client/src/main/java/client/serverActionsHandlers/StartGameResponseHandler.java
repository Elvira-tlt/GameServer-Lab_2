package client.serverActionsHandlers;

import client.view.GamePanel;
import client.view.MainWindow;
import move.TypeValueCurrentStateGame;
import typeTeam.TypeTeam;
import requests.ServerActionHandler;
import responses.StartGameResponse;

public class StartGameResponseHandler implements ServerActionHandler<StartGameResponse> {
    private MainWindow mainWindow;
    private String currentPlayerName;
    private String otherPlayerName;
    private String namePlayerCurrentStroke;
    private TypeTeam typeTeamThisPlayer;
	private TypeTeam typeTeamRival;

    @Override
    public void handle(StartGameResponse startGameResponse) {
        GamePanel gamePanel = mainWindow.getGamePanel();
        currentPlayerName = startGameResponse.getCurrentPlayerName();
        otherPlayerName = startGameResponse.getOtherPlayerName();
        typeTeamThisPlayer = startGameResponse.getTypeTeamCurrentPlayer();
        typeTeamRival = startGameResponse.getTypeTeamOtherPlayer();
        namePlayerCurrentStroke = startGameResponse.getNamePlayerCurrentStroke();
        
		gamePanel.setTypeTeamToPlayers(typeTeamThisPlayer, typeTeamRival);
        gamePanel.setNameToPlayers(currentPlayerName, otherPlayerName);
        mainWindow.changePanelButtonToGame(true);
        gamePanel.changeTextInformationGamePanel(TypeValueCurrentStateGame.CHANGE_CURRENT_PLAYER, namePlayerCurrentStroke);
        gamePanel.retrieveListenersToGameTable();
        gamePanel.display();

        //
        System.out.println("IN StartGameResponse");
        System.out.println(currentPlayerName + ": " + typeTeamThisPlayer);
        System.out.println(otherPlayerName + ": " + typeTeamRival);
        //
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }






}
