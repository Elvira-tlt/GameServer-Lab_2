package server.clientActionsHandlers;


import actionsFromServer.Action;
import actionsFromServer.ActionHandler;

public class ExitFromGameRequestHandler implements ActionHandler {
    @Override
    public Action handle() {
        System.out.println("ExitFromGameRequestHandler");
        return null;
    }
}
