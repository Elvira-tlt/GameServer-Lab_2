package server;

import server.clientActionHandlers.IncorrectPasswordExeption;
import server.clientActionHandlers.NotFoundExeption;
import user.User;

import java.util.LinkedList;
import java.util.List;

public class UserDatabase {
	private List<User> identifiedUsersInDatabase = new LinkedList<User>();
	
	public UserDatabase(){
		String login = "1";
		String password = "1";
		User user = new User();
		user.setNameUser(login);
		user.setPasswordUser(password);
		identifiedUsersInDatabase.add(user);

		login = "пп";
		password = "пп";
		User user1 = new User();
		user1.setNameUser(login);
		user1.setPasswordUser(password);
		
		identifiedUsersInDatabase.add(user1);
	}
	
	private int countUsers =
			identifiedUsersInDatabase.size();
	//private int nextFreeId = ++countUsers;
	
    public void addUser(User user) {
        identifiedUsersInDatabase.add(user);
        
        countUsers++;
        
        /////////
        System.out.println("countUser: " + countUsers);
        ////
    }

   public List<User> getIdentidiedUsers() {
	   List<User> usersInDatabase = identifiedUsersInDatabase;
       return usersInDatabase;
    }
    
    public int getNextFreeID() {
    	int nextFreeID = countUsers+1;
    	return nextFreeID;
    }
    
    public User getUser(String nameForSearchUser, String passwordUser) throws NotFoundExeption, IncorrectPasswordExeption {
    	User userFromDatabase = null;

    	for(User userDatabaseForEquals: identifiedUsersInDatabase) {
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
