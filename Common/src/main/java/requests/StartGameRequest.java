package requests;


import responses.Action;
import user.User;

public class StartGameRequest implements Action {
	private User otherPlayer;

	public StartGameRequest(){
		
	}
	
	public StartGameRequest(User otherPlayer){
		this.otherPlayer = otherPlayer;
	}
	
	public User getOtherPlayer() {
		return otherPlayer;
	}
}
