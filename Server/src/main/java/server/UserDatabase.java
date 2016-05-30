package server;

import server.clientActionHandlers.IncorrectPasswordExeption;
import server.clientActionHandlers.NotFoundException;
import user.User;

import javax.swing.text.Document;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

public class UserDatabase {
	private List<User> identifiedUsersInDatabase = new LinkedList<User>();
	private int countUsers ;

	public UserDatabase(){
		countUsers = identifiedUsersInDatabase.size();

		String login = "1";
		String password = "1";
		User user = new User(10);
		user.setNameUser(login);
		user.setPasswordUser(password);
		identifiedUsersInDatabase.add(user);

		login = "пп";
		password = "пп";
		User user1 = new User(20);
		user1.setNameUser(login);
		user1.setPasswordUser(password);
		identifiedUsersInDatabase.add(user1);

		login = "2";
		password = "2";
		User user2 = new User(30);
		user2.setNameUser(login);
		user2.setPasswordUser(password);
		identifiedUsersInDatabase.add(user2);
	}

    public void addUser(User user) {
        identifiedUsersInDatabase.add(user);
        countUsers++;
		//
		System.out.println("Count user: " + countUsers);
		//
    }

   public List<User> getIdentidiedUsers() {
	   List<User> usersInDatabase = identifiedUsersInDatabase;
       return usersInDatabase;
    }
    
    public int getNextFreeID() {
    	int nextFreeID = countUsers+1;
    	return nextFreeID;
    }
    
    public User getUser(String nameForSearchUser, String passwordUser) throws NotFoundException, IncorrectPasswordExeption {
    	User userFromDatabase = null;

    	for(User userDatabaseForEquals: identifiedUsersInDatabase) {
    		String nameUserFromDatabase = userDatabaseForEquals.getNameUser();
			String passwordUserFromDatabase = userDatabaseForEquals.getPassword();
    		if(nameForSearchUser.equals(nameUserFromDatabase)){
				if (passwordUser.equals(passwordUserFromDatabase)) {
					userFromDatabase = userDatabaseForEquals;
					/////
					//System.out.println("UserDatabase: user is Found! :)");
					//
				} else {
					/////
					//System.out.println("UserDatabase: user is Found! PASSWORD NOT SACCESSFUL)");
					//
					throw new IncorrectPasswordExeption();
				}
    			break;
    		}
    	}
		if (userFromDatabase == null) {
			throw new NotFoundException();
		}
    	return userFromDatabase;
    }
}
