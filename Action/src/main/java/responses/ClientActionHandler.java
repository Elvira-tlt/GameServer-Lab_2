package responses;


import responses.Action;
import server.ClientConnector;

public interface ClientActionHandler<ActionClass extends Action> {
    public void handle(ActionClass action, ClientConnector connector);
}
