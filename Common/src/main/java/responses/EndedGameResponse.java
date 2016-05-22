package responses;
import user.User;

public class EndedGameResponse implements Action {
    private User winUser;
    private String nameConnectedUser;

	public EndedGameResponse(User winUser) {
        this.winUser = winUser;
    }

    public User getWinUser() {
    	return winUser;
    }
}
