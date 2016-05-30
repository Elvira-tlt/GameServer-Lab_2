package client.serverActionsHandlers;

import client.view.GamePanel;
import client.view.MainWindow;
import move.TypeValueCurrentStateGame;
import requests.ServerActionHandler;
import responses.EndedGameResponse;
import user.User;


public class EndedGameResponseHandler implements ServerActionHandler<EndedGameResponse> {
    private String nameWinUser = new String();
    private MainWindow mainWindow;


    @Override
    public void handle(EndedGameResponse endedGameResponse) {
        GamePanel gamePanel = mainWindow.getGamePanel();
        nameWinUser = endedGameResponse.getNameWinUser();
            //
            System.out.println("IN EndedGameResponseHandler: nameWinUser" + nameWinUser );
            //

        gamePanel.changeTextInformationGamePanel(TypeValueCurrentStateGame.GAME_ENDED, nameWinUser);
        gamePanel.deleteListenersInGameTable();
        mainWindow.changePanelButtonToGame(false);
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
}
