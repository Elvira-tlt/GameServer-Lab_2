package requests;


import responses.Action;
import user.User;

public class StartGameRequest implements Action {
	private User thisPlayer;
	private User otherPlayer;


	public StartGameRequest(User thisPlayer) {
		this.thisPlayer = thisPlayer;

	}

	public StartGameRequest(User thisPlayer, User otherPlayer){

	}

	public User getThisPlayer() {
		return thisPlayer;
	}

	public User getOtherPlayer() {
		return otherPlayer;
	}
}
