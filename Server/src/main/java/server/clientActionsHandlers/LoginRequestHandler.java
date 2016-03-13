package server.clientActionsHandlers;


import actionsFromServer.Action;
import actionsFromServer.ActionHandler;

public class LoginRequestHandler implements ActionHandler {
    @Override
    public Action handle() {
        System.out.println("LoginRequestHandler");
        return null;
    }
}
