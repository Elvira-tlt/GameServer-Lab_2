package responses;
import java.util.List;
import user.User;



public class UsersServerResponse implements Action {
	private List<User> users;

    public UsersServerResponse(List<User> onlineUsers) {
        this.users = onlineUsers;
    }

    public List<User> getOnlineUsers() {
        return users;
    }
}
