package client.serverActionsHandlers;

import actionsFromServer.Action;
import actionsFromServer.ActionHandler;


public class LoginResponseHandler implements ActionHandler {

    @Override
    public Action handle() {
        System.out.println("LoginResponseHandler");
        return null;
    }

}
