package client.serverActionsHandlers;

import actionsFromServer.Action;
import actionsFromServer.ActionHandler;


public class FreeUsersResponseHandler implements ActionHandler {

    @Override
    public Action handle() {
        System.out.println("FreeUsersResponseHandler");
        return null;
    }

}
