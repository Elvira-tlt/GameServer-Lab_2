package server.game;


import java.util.ArrayList;
import java.util.List;

public class GameRepository {	
	private List<Game> actualGames = new ArrayList<Game>();

	public List<Game> getActualGames() {
		return actualGames;
	}

	public void setGame(Game newGame) {
		actualGames.add(newGame);
	}
}
