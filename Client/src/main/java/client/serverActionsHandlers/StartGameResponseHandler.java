package client.serverActionsHandlers;

import client.view.GamePanel;
import requests.ServerActionHandler;
import responses.ExitFromGameServerResponse;
import responses.StartGameResponse;


public class StartGameResponseHandler implements ServerActionHandler<StartGameResponse> {
    private GamePanel gamePanel;

    @Override
    public void handle(StartGameResponse startGameResponse) {
        System.out.println("StartGameResponseHandler");
        gamePanel.display();
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}
