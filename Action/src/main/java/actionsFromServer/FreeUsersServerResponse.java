package actionsFromServer;

import server.User;

import java.util.List;


public class FreeUsersServerResponse implements Action {
    private List<User> freeUsers;

    public FreeUsersServerResponse(List<User> freeUsers) {
        this.freeUsers = freeUsers;
    }

    public List<User> getPlayers() {
        return freeUsers;
    }
}
