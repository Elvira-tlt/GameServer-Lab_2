package server.clientActionsHandlers;


import actionsFromServer.Action;
import actionsFromServer.ActionHandler;

public class PlayersRequestHandler implements ActionHandler {
    @Override
    public Action handle() {
        System.out.println("PlayersRequestHandler");
        return null;
    }
}
