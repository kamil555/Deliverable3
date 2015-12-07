package main;

/**
 * 
 * @author Stepan Adespya
 * @edited by Mindy Huynh 12/5/2015
 * @since November 5, 2015
 */
@SuppressWarnings("serial")
public class User implements java.io.Serializable
{
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
	public User(String username, String user)
	{
		this.userName = username;
		this.user = user;
	}
	
	/**
	 * gives the User's Username
	 * 
	 * @return Username (Precondition nothing) (Postcondition returns Username)
	 */
	public String getUserName()
	{
		return userName;
	}
	
	/**
	 * Replaces the Username
	 * 
	 * @param userName
	 *            (Precondition String passed is not NULL) (Postcondition
	 *            replaces the Username)
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	/**
	 * States what the user is(Bidder,NonProfit, or Employee)
	 * 
	 * @return String of what the User is (Precondition nothing) (Postcondition
	 *         returns state of User)
	 */
	public String getUser()
	{
		return user;
	}
	
	/**
	 * Sets the User to correct state.
	 * 
	 * @param user
	 *            (Precondition String is not NULL) (Postcondition User becomes
	 *            in right state)
	 */
	public void setUser(String user)
	{
		this.user = user;
	}
	
	/**
	 * Gives the Organization name if User is Nonprofit
	 * 
	 * @return String Organization name (Precondition User is NonProfit)
	 *         (Postcondition returns Organization Name)
	 */
	public String getOrganization()
	{
		return organization;
	}
	
	/**
	 * Puts a new Organization name.
	 * 
	 * @param organization
	 *            (Precondition String is not Null) (Postcondition Organization
	 *            name to the right User)
	 */
	public void setOrganization(String organization)
	{
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