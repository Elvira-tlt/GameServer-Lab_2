package server;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.clientActionHandlers.IncorrectPasswordExeption;
import server.clientActionHandlers.NotFoundException;
import user.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class UserDatabase {
	private static final Logger LOG = LoggerFactory.getLogger(UserDatabase.class);
	private List<User> identifiedUsersInDatabase = new LinkedList<User>();
	private int countUsers ;
	private static final String FILENAME = "IdentifiedUsersInDatabase.xml";
	private Document document;
	private FileOutputStream fileOutputStream;

	public UserDatabase(){
		countUsers = identifiedUsersInDatabase.size();
		identifiedUsersInDatabase = loadingUsersFromFile();
	}

    public void addUser(User user) {
        identifiedUsersInDatabase.add(user);
		savingTasksToFile(identifiedUsersInDatabase);
		countUsers++;
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
					LOG.info("UserDatabase: user {} is Found!", userFromDatabase);
				} else {
					LOG.info("UserDatabase: user {} is Found! PASSWORD NOT SACCESSFUL", userFromDatabase);
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

	private List<User> loadingUsersFromFile(){
		SAXBuilder saxBuilder = new SAXBuilder();
		File xmlFile = new File(FILENAME);

		if (xmlFile.exists()){
			try {
				document = saxBuilder.build(xmlFile);
				Element users = document.getRootElement();
				List<Element> allUsersFromFile = users.getChildren("user");
				for (Element nextUser : allUsersFromFile) {
					String name = nextUser.getChild("name").getValue();
					String password = nextUser.getChild("password").getValue();
					String idUserToString = nextUser.getChild("idUser").getValue();
					int idUser = Integer.parseInt(idUserToString);

					User userFromFile = new User(idUser);
					userFromFile.setNameUser(name);
					userFromFile.setPasswordUser(password);

					synchronized (identifiedUsersInDatabase) {
						identifiedUsersInDatabase.add(userFromFile);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return identifiedUsersInDatabase;
	}

	private void savingTasksToFile (List<User> usersForSaving) {
		File xmlFile = new File(FILENAME);
		Element users = new Element("users");
		document = new Document(users);
		XMLOutputter xmlOutputter = new XMLOutputter();
		xmlOutputter.setFormat(Format.getPrettyFormat());

		synchronized (usersForSaving) {
			if (!usersForSaving.isEmpty()) {
				for (User user : usersForSaving) {
					Element userElement = new Element("user");
					userElement.addContent(new Element("name").setText(user.getNameUser()));
					userElement.addContent(new Element("password").setText(user.getPassword()));
					int idUserForSaving = user.getidUser();
					userElement.addContent(new Element("idUser").setText(String.valueOf(idUserForSaving)));
					users.addContent(userElement);
				}
			}
		}
		try {
			fileOutputStream = new FileOutputStream(xmlFile);
			xmlOutputter.output(document, fileOutputStream);
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
