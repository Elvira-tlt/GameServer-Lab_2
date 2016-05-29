package client.serverActionsHandlers;

import client.view.GamePanel;
import client.view.MainWindow;
import move.TypeValueCurrentStateGame;
import requests.ServerActionHandler;
import responses.EndedGameResponse;
import user.User;


public class EndedGameResponseHandler implements ServerActionHandler<EndedGameResponse> {
    private String nameWinUser = new String();
    private GamePanel gamePanel;


    @Override
    public void handle(EndedGameResponse endedGameResponse) {
        nameWinUser = endedGameResponse.getNameWinUser();
            //
            System.out.println("IN EndedGameResponseHandler: nameWinUser" + nameWinUser );
            //

        gamePanel.changeTextInformationGamePanel(TypeValueCurrentStateGame.GAME_ENDED, nameWinUser);
        gamePanel.deleteListenersInGameTable();
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}
