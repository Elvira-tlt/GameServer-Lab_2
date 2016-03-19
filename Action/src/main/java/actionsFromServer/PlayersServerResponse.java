package actionsFromServer;
import java.util.List;

import server.User;


public class PlayersServerResponse implements Action {
    private List<User> players;

    public PlayersServerResponse(List<User> players) {
        this.players = players;
    }

    public List<User> getPlayers() {
        return players;
    }
}
