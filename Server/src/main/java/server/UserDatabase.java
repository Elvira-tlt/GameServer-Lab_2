package server;

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
    
    public User getUserOfName(String nameForSearchUser){
    	User userFromDatabase = null;
    	
    	for(User userDatabaseForEquals: identifiedUserInDatabase) {
    		String nameUserFromDatabase = userDatabaseForEquals.getNameUser();
    		if(nameForSearchUser.equals(nameUserFromDatabase)){
    			userFromDatabase = userDatabaseForEquals;
    			
    			/////
    			System.out.println("UserDatabase: user is Found! :)");
    			//
    			
    			break;
    		}
    	}
    	return userFromDatabase;
    }
    
    


}
