package actionsFromClient;


import actionsFromServer.Action;
import server.ClientConnector;

public interface ServerActionHandler<ActionClass extends Action> {
    public void handle(ActionClass action);
}
