package main;

/**
 * This is the User class. 
 * It keeps track of all the users. 
 * @author Stepan Adespya
 * @edited by Mindy Huynh 12/5/2015
 * @since November 5, 2015
 */
@SuppressWarnings("serial")
public class User implements java.io.Serializable
{
	// UserName of person
	/**
	 * This is the user's name.
	 */
	private String userName;
	
	// Whether a person is a bidder,employee, or nonprofit
	/**
	 * This is the the status of the user.
	 */
	private String userStatus;
	
	// If nonprofit, Organization name
	/**
	 * This is the name of the organization if the user is a non profit.
	 */
	private String organization;
	
	/**
	 * This method is the constructor for user.
	 * 
	 * @param username the name of the user
	 * @param userStatus the status of the user.
	 */
	public User(String username, String userStatus)
	{
		this.userName = username;
		this.userStatus = userStatus;
	}
	
	/**
	 * This is the getter for userName.
	 * 
	 * @return Username the name of the user. 
	 * @Pre nothing
	 * @post returns userName
	 */
	public String getUserName()
	{
		return userName;
	}
	
	/**
	 * This method is the setter for user.
	 * 
	 * @param userName the name of the user.
	 * @Pre passed is not NULL
	 * @Post replaces the Username
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	/**
	 * This is the getter for userStatus.
	 * 
	 * @return The status of the user.
	 * @Pre nothing
	 * @Post returns state of User
	 */
	public String getUserStatus()
	{
		return userStatus;
	}
	
	/**
	 * This is the setter for userStatus
	 * 
	 * @param userStatus
	 * @Pre string is not NULL
	 * @Post User changes its status.
	 */
	public void setUserStatus(String userStatus)
	{
		this.userStatus = userStatus;
	}
	
	/**
	 * This is a getter for organization.
	 * 
	 * @return String the name of the organization.
	 * @Pre User is NonProfit
	 * @Post returns Organization Name
	 */
	public String getOrganization()
	{
		return organization;
	}
	
	/**
	 * This is the Setter for organization name.
	 * 
	 * @param organization
	 * @Pre String is not Null, User is a non profit.
	 * @Post Postcondition Organization name to the right User
	 */
	public void setOrganization(String organization)
	{
		this.organization = organization;
	}
	
	@Override
	/**
	 * This is the string representation of the user class.
	 */
	public String toString()
	{
		return userName + ", " + userStatus;
	}
	
}