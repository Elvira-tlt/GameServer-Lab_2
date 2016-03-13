package server;

import java.util.LinkedList;
import java.util.List;

public class UserDatabase {
    private List<User> identifiedUser = new LinkedList<User>();

    public void addUser(User user) {
        identifiedUser.add(user);
    }

    public List getIdentidiedUsers() {
        return identifiedUser;
    }


}
