package client.serverActionsHandlers;

import client.view.GamePanel;
import client.view.TypeInformationTextGame;
import requests.ServerActionHandler;
import responses.MoveGameResponse;


public class MoveGameResponseHandler implements ServerActionHandler<MoveGameResponse> {
	 private GamePanel gamePanel;
	 private String[][] madeMovesFromServer;
    private String nameCurrentUser;


    @Override
    public void handle(MoveGameResponse moveGameResponse) {
    	madeMovesFromServer = moveGameResponse.getMadeMove();
        nameCurrentUser = moveGameResponse.getNameCurrentUser();
    	gamePanel.setMadeMovesToTable(madeMovesFromServer);
        gamePanel.changeTextInformationGamePanel(TypeInformationTextGame.CHANGE_CURRENT_PLAYER, nameCurrentUser);
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}
