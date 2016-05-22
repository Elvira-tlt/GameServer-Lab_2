package client.serverActionsHandlers;

import client.view.GamePanel;
import client.view.TypeInformationText;
import client.view.TypeInformationTextGame;
import requests.ServerActionHandler;
import responses.EndedGameResponse;
import responses.MoveGameResponse;
import user.User;


public class EndedGameResponseHandler implements ServerActionHandler<EndedGameResponse> {
    private String nameWinUser = new String();
    private User winUser;
    private GamePanel gamePanel;


    @Override
    public void handle(EndedGameResponse endedGameResponse) {
        winUser = endedGameResponse.getWinUser();
        if(winUser != null) {
           nameWinUser = winUser.getNameUser();
        }
        gamePanel.changeTextInformationGamePanel(TypeInformationTextGame.GAME_ENDED, nameWinUser);
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}
