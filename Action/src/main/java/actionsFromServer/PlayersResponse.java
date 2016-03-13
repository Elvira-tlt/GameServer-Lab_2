package actionsFromServer;
import java.util.List;

import server.User;


public class PlayersResponse implements Action {
    private List<User> players;

    public PlayersResponse(List<User> players) {
        this.players = players;
    }

    public List<User> getPlayers() {
        return players;
    }
}
