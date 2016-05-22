package client.serverActionsHandlers;

import client.view.GamePanel;
import requests.ServerActionHandler;
import responses.MoveGameResponse;


public class MoveGameResponseHandler implements ServerActionHandler<MoveGameResponse> {
	 private GamePanel gamePanel;
	 private String[][] madeMovesFromServer;


    @Override
    public void handle(MoveGameResponse moveGameResponse) {
    	madeMovesFromServer = moveGameResponse.getMadeMove();
    	gamePanel.setMadeMovesToTable(madeMovesFromServer);
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

}
