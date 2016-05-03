package requests;


import responses.Action;
import server.ClientConnector;

public interface ServerActionHandler<ActionClass extends Action> {
    public void handle(ActionClass action);
}
