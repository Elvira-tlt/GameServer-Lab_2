package client.serverActionsHandlers;

import actionsFromServer.Action;
import actionsFromServer.ActionHandler;


public class ExitFromGameResponseHandler implements ActionHandler {

    @Override
    public Action handle() {
        System.out.println("ExitFromGameResponseHandler");
        return null;
    }

}
