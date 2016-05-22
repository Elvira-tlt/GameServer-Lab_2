package responses;
import java.util.ArrayList;
import java.util.List;
import user.User;



public class UsersServerResponse implements Action {
	private List<User> users;
	
	public UsersServerResponse (List<User> onlineUsers){
        try {
            users = new ArrayList<>();
            for (User user : onlineUsers) {
                User newUser = null;
                newUser = user.clone();
                users.add(newUser);
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
	
    public List<User> getOnlineUsers() {
        return users;
    }
    
    
    
    
}
