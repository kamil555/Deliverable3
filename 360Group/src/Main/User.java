package Main;

/**
 * 
 * @author Stepan Adespya
 * @since November 5, 2015
 */
public class User{
	// UserName of person
	private String userName;
	// Whether a person is a bidder,employee, or nonprofit
	private String user;
	// If nonprofit, Organization name
	private String organization;
	
	/**
	 * Saves a User.
	 * 
	 * @param username
	 * @param user
	 */
	public User(String username, String user){
		this.userName = username;
		this.user = user;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	/**
	 * overrides the toString method
	 */
	public String toString()
	{
		return userName + ", " + user;
	}
	
}
