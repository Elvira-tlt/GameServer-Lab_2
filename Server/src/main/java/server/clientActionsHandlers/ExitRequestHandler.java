package server.clientActionsHandlers;


import actionsFromServer.Action;
import actionsFromServer.ActionHandler;

public class ExitRequestHandler implements ActionHandler {
    @Override
    public Action handle() {
        System.out.println("ExitRequestHandler");
        return null;
    }
}
