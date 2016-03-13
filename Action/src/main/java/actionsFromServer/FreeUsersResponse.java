package actionsFromServer;

import server.User;

import java.util.List;


public class FreeUsersResponse implements Action {
    private List<User> freeUsers;

    public FreeUsersResponse(List<User> freeUsers) {
        this.freeUsers = freeUsers;
    }

    public List<User> getPlayers() {
        return freeUsers;
    }
}
