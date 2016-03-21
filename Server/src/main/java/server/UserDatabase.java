package server;

import server.clientActionHandlers.IncorrectPasswordExeption;
import server.clientActionHandlers.NotFoundExeption;

import java.util.LinkedList;
import java.util.List;

public class UserDatabase {
	private List<User> identifiedUserInDatabase = new LinkedList<User>();
	
	private int countUsers = identifiedUserInDatabase.size();
	//private int nextFreeId = ++countUsers;
	
    public void addUser(User user) {
        identifiedUserInDatabase.add(user);
        
        countUsers++;
        
        /////////
        System.out.println("countUser: " + countUsers);
        ////
    }

   /* public List<User> getIdentidiedUsers() {
        return identifiedUserinDatabase;
    }*/
    
    public int getNextFreeID() {
    	int nextFreeID = ++countUsers;
    	return nextFreeID;
    }
    
    public User getUser(String nameForSearchUser, String passwordUser) throws NotFoundExeption, IncorrectPasswordExeption {
    	User userFromDatabase = null;

    	for(User userDatabaseForEquals: identifiedUserInDatabase) {
    		String nameUserFromDatabase = userDatabaseForEquals.getNameUser();
			String passwordUserFromDatabase = userDatabaseForEquals.getPassword();
    		if(nameForSearchUser.equals(nameUserFromDatabase)){
				if (passwordUser.equals(passwordUserFromDatabase)) {
					userFromDatabase = userDatabaseForEquals;
					/////
					System.out.println("UserDatabase: user is Found! :)");
					//
				} else {
					/////
					System.out.println("UserDatabase: user is Found! PASSWORD NOT SACCESSFUL)");
					//
					throw new IncorrectPasswordExeption();
				}
    			break;
    		}
    	}
		if (userFromDatabase == null) {
			throw new NotFoundExeption();
		}
    	return userFromDatabase;
    }
}
