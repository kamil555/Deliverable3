package Main;

/**
 * 
 * @author Stepan Adespya
 * @since November 5, 2015
 */
public class User
{
	// UserName of person
	public String userName;
	// Whether a person is a bidder,employee, or nonprofit
	public String user;
	// If nonprofit, Organization name
	public String organization;
	
	/**
	 * Saves a User.
	 * 
	 * @param username
	 * @param user
	 */
	public User(String username, String user)
	{
		this.userName = username;
		this.user = user;
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
