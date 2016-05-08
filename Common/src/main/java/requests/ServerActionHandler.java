package requests;


import responses.Action;

public interface ServerActionHandler<ActionClass extends Action> {
    public void handle(ActionClass action);
}
