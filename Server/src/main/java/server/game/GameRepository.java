package server.game;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import user.User;

public class GameRepository {	
	private Map<User, Game> users2game = new HashMap<>();

	public Collection<Game> getActualGames() {
		Collection <Game> actualGames = users2game.values();
		return actualGames;
	}

	public void setPlayer2Game(User user, Game newGame) {
		users2game.put(user, newGame);
	}
	
	public Game getGameThisPlayer(User player){
		Game gameThisPlayer = users2game.get(player);
		return gameThisPlayer;
	}
}
