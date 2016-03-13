package server.clientActionsHandlers;


import actionsFromServer.Action;
import actionsFromServer.ActionHandler;

public class FreeUsersRequestHandler implements ActionHandler {
    @Override
    public Action handle() {
        System.out.println("FreeUsersRequestHandler");
        return null;
    }
}
