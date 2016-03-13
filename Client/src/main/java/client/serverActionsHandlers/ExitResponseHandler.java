package client.serverActionsHandlers;

import actionsFromServer.Action;
import actionsFromServer.ActionHandler;


public class ExitResponseHandler implements ActionHandler {

    @Override
    public Action handle() {
        System.out.println("ExitResponseHandler");
        return null;
    }

}
