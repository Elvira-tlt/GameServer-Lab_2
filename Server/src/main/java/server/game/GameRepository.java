package server.game;


import java.util.*;

import server.ConnectedUsers;
import user.User;

public class GameRepository {
	public ConnectedUsers connectedUsers;

	private Map<User, Game> users2game = new HashMap<>();

/*	public Collection<Game> getActualGames() {
		Collection <Game> actualGames = users2game.values();
		return actualGames;
	}*/

	public void setPlayer2Game(User user, Game newGame) {
		newGame.setGameRepository(this);
		users2game.put(user, newGame);
	}

	public Game getGameThisPlayer(User player){
		Game gameThisPlayer = users2game.get(player);
		return gameThisPlayer;
	}

	public void removeGame(Game gameForRemove){

		int amountFoundGame = 0;
		Set<User> users = new HashSet<>();
		users.addAll(this.users2game.keySet());
		for (User user: users){
			User currentIterationUser = user;
			Game gameCurrentIterationUser = this.users2game.get(currentIterationUser);
			if(gameCurrentIterationUser.equals(gameForRemove)){
				this.users2game.remove(currentIterationUser, gameForRemove);
				changeStatusPlayerInUser(currentIterationUser);
				amountFoundGame++;
				if(amountFoundGame == 2){
					break;
				}
			}
		}
	}

	private void changeStatusPlayerInUser(User userToStatusFree){
		connectedUsers.changeStatusPlayerTo(userToStatusFree,false);
	}

	public void setConnectedUsers(ConnectedUsers connectedUsers) {
		this.connectedUsers = connectedUsers;
	}
}
