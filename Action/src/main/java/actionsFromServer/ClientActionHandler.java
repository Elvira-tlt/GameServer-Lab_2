package actionsFromServer;


import server.ClientConnector;
import actionsFromServer.Action;

public interface ClientActionHandler<ActionClass extends Action> {
    public void handle(ActionClass action, ClientConnector connector);
}
