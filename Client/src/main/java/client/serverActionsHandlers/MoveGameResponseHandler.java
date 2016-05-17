package client.serverActionsHandlers;

import client.view.MainWindow;
import requests.ServerActionHandler;
import responses.MoveGameResponse;


public class MoveGameResponseHandler implements ServerActionHandler<MoveGameResponse> {
    private MainWindow mainWindow;


    @Override
    public void handle(MoveGameResponse moveGameResponse) {
        System.out.println("MoveGameResponseClient");

        //TODO

    }

    public void setMainWindow(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

}
