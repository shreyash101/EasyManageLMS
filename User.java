import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialversionUID =
            129348938L; // used to identify if sender and receiver have compatible classes for working with 
						// serialized and deserialized objects
	
	private String userId;
	private String username;
	private String password;
	private String role;
	
	public User(String userId, String username, String password, String role) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", role=" + role + "]";
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
