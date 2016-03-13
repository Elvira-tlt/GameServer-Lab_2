package client.serverActionsHandlers;

import actionsFromServer.Action;
import actionsFromServer.ActionHandler;


public class ConnectingResponseHandler implements ActionHandler {

    @Override
    public Action handle() {
        System.out.println("ConnectingResponseHandler");
        return null;
    }

}
