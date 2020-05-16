package users;
import java.util.ArrayList;
import java.util.UUID;

public abstract class User {
public User() {
	// assign an unique ID to each user
	this.userID = UUID.randomUUID().toString().substring(0,8);
	
}

private String userID;
private String username;
private String password;

// public void setUserID(String ID) {
// 	this.userID=ID;
// }

public String getUserID() {
	return userID;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}
}