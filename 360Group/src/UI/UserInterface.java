package UI;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import main.*;

/**
 * This is the UserInterface class. 
 * It combines all interfaces and puts everything together.
 * 
 * @author Stepan Adespya
 * @edited by Mindy Huynh 12/5/2015
 * @since November 21, 2015
 */
public class UserInterface
{
	// Keeps track of all Users
	/**
	 * This is the list of user. 
	 * It keeps track of all the users.
	 */
	private ArrayList<User> users;
	
	/**
	 * This is the bidder option number.
	 */
	private int BIDDER = 1;
	
	/**
	 * This is the employee option number.
	 */
	private int EMPLOYEE = 2;
	
	/**
	 * This is the nonprofit option number.
	 */
	private int NONPROFIT = 3;
	
	/**
	 * This is the string that represents the file name to be used later.
	 */
	private String logUser = "Users.ser";
	
	/**
	 * This is the Users class constructor, allows user to login or create account.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 */
	public UserInterface() throws IOException, ParseException, ClassNotFoundException
	{
		readFileToUsers(logUser);
		System.out.println("Hello Welcome to AuctionCentral");
		System.out.println("Press 1 to login\nPress 2 to create user\nPress 3 to exit");
		Scanner reader = new Scanner(System.in);
		
		// created a method that checks if its integer..keep prompting
		checkForInt(reader);
	}
	
	/**
	 * This method checks it User selected the right menu item.
	 * 
	 * @param reader the reader from scanner that is used read files.
	 * @throws IOException
	 * @throws ParseException
	 *             (Precondition nothing) (Postcondition int of menu selected)
	 * @throws ClassNotFoundException
	 */
	private void checkForInt(Scanner reader) throws IOException, ParseException,
			ClassNotFoundException
	{
		while (!reader.hasNextInt())
		{
			System.out.println("Please enter an integer between 1 and 3.");
			reader = new Scanner(System.in);
		}
		selectOption(reader);
		
	}
	
	/**
	 * This method goes to the Right menu selected.
	 * 
	 * @param reader the reader from scanner that is used read files.
	 * @throws IOException
	 * @throws ParseException
	 *             (Precondition nothing) (Postcondition send the User to menu)
	 * @throws ClassNotFoundException
	 */
	private void selectOption(Scanner reader) throws IOException, ParseException,
			ClassNotFoundException
	{
		int login = 1;
		int createUser = 2;
		int exit = 3;
		
		int input = reader.nextInt();
		while (input != login && input != createUser && input != exit)
		{
			System.out.println("Sorry wrong input, Please try again");
			input = reader.nextInt();
		}
		String userName = null;
		switch (input)
		{
		// login
			case 1:
				User userLogin = userLogin(reader, userName);
				if (userLogin.getUserStatus().equalsIgnoreCase("Bidder"))
				{
					new Bidder(userLogin);
					break;
				} else if (userLogin.getUserStatus().equalsIgnoreCase("AuctionCentral Employee"))
				{
					new AuctionCentralEmployee(userLogin);
					break;
				} else if (userLogin.getUserStatus().equalsIgnoreCase("nonprofit"))
				{
					new NonProfit(userLogin);
					break;
				}
				break;
			
			// createUser
			case 2:
				System.out.println("Create Username: ");
				userName = reader.next().toUpperCase();
				while (isOneUserName(userName))
				{
					System.out.println("This Username is taken, please enter another Username: ");
					userName = reader.next();
				}
				createNewUser(userName);
				new UserInterface();
				break;
			
			// exit
			case 3:
				
				break;
		}
	}
	
	/**
	 * This method gets the new User information
	 * 
	 * @param userName the name of the user.
	 * @throws IOException
	 * @throws ParseException
	 *             (Precondition User name is valid) (Postcondition creates new
	 *             user)
	 */
	private void createNewUser(String userName) throws IOException, ParseException
	{
		System.out.println("Press 1 if you want to create an account as a Bidder"
				+ "\nPress 2 if you want to create an account as an Auction Central Employee"
				+ "\nPress 3 if you want to create an account as a Nonprofit Organization");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		while (!reader.hasNextInt())
		{
			System.out.println("Please enter a number between 1 and 3.");
			reader = new Scanner(System.in);
		}
		int inputUser = reader.nextInt();
		while (inputUser != BIDDER && inputUser != EMPLOYEE
				&& inputUser != NONPROFIT)
		{
			System.out.println("Sorry wrong input, please try again.");
			inputUser = reader.nextInt();
		}
		User newUser = null;
		if (inputUser == BIDDER)
		{
			newUser = createUser(userName, "Bidder");
		} else if (inputUser == EMPLOYEE)
		{
			newUser = createUser(userName, "AuctionCentral Employee");
		} else if (inputUser == NONPROFIT)
		{
			newUser = createUser(userName, "Nonprofit");
		}
		users.add(newUser);
		writeToFile(logUser);
		System.out.println("Created new User! Please Login with new User.");
	}
	
	/**
	 * This method looks if new User is a NonProfit if he is asks for organization name.
	 * 
	 * @param username the name of the user using the system.
	 * @param userType the type of user linked to that user name.
	 * @throws IOException
	 * @throws ParseException
	 *             (Precondition User name and who is user) (Postcondition new
	 *             user)
	 */
	public User createUser(String username, String userType) throws IOException,
			ParseException
	{
		User per = new User(username, userType);
		if (per.getUserStatus().equalsIgnoreCase("nonprofit"))
		{
			System.out.println("Enter Nonprofit Organization: ");
			@SuppressWarnings("resource")
			Scanner reader = new Scanner(System.in);
			String np = reader.nextLine();
			while (isOnePerOrg(np))
			{
				System.out.println("Please enter another organization name :");
				np = reader.nextLine();
			}
			per.setOrganization(np);
			writeToFile(logUser);
			
		}
		return per;
	}
	
	/**
	 * This is the method for the user to log in.
	 * 
	 * @param reader the reader that reads the user name.
	 * @param userName the name of the user being stored.
	 * @return the login of the user.
	 * @throws IOException
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 */
	public User userLogin(Scanner reader, String userName) throws IOException, ParseException,
			ClassNotFoundException
	{
		System.out.print("Enter Username: ");
		userName = reader.next().toUpperCase();
		return login(userName);
	}
	
	/**
	 * This method logs users in.
	 * 
	 * @param userName the name of the user trying to be logged in.
	 * @return the user trying to log in.
	 * @throws IOException
	 * @throws ParseException
	 *             (Precondition user name) (Postcondition go to right user
	 *             class)
	 * @throws ClassNotFoundException
	 */
	private User login(String userName) throws IOException, ParseException, ClassNotFoundException
	{
		userName.toUpperCase();
		for (int i = 0; i < users.size(); i++)
		{
			if (users.get(i).getUserName().endsWith(userName))
			{
				return users.get(i);
			}
			
		}
		System.out.println("Sorry could not login please try again");
		new UserInterface();
		return null;
	}
	
	/**
	 * This method checks to see if anyone else has the same organization name. BR #7
	 * 
	 * @param org the organization the nonprofit belogs to.
	 *            (Organization name)
	 * @return true or false (Precondition organization name) (Postcondition
	 *         checks to see if organization is already used)
	 */
	private boolean isOnePerOrg(String org)
	{
		for (int i = 0; i < users.size(); i++)
		{
			if (users.get(i).getOrganization() != null)
			{
				if (users.get(i).getOrganization().equalsIgnoreCase(org))
				{
					System.out
							.println("Sorry only one person can represent the Nonprofit Organization");
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * This method checks if there is the same username
	 * 
	 * @param username the name of the user trying to log in.
	 * @return true or false (Precondition user name valid) (Postcondition check
	 *         is username is already taken)
	 */
	private boolean isOneUserName(String username)
	{
		for (int i = 0; i < users.size(); i++)
		{
			if (users.get(i).getUserName().equalsIgnoreCase(username))
			{
				System.out.println("Sorry the Username is already taken");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Reads the file selected to Users arraylist
	 * 
	 * @param fileName the name of the file we're trying to access.
	 * @Pre string of file name to read off of
	 * @Post fill array of all users in system
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private void readFileToUsers(String fileName) throws ClassNotFoundException, IOException
	{
		FileInputStream fileIn = new FileInputStream(fileName);
		try
		{
			ObjectInputStream in = new ObjectInputStream(fileIn);
			users = (ArrayList<User>) in.readObject();
			in.close();
		} catch (EOFException e)
		{
			users = new ArrayList<User>();
		}
		fileIn.close();
	}
	
	/**
	 * This method writes in the file chosen(use for logs)
	 * 
	 * @param fileName
	 * @param contents
	 * @throws IOException
	 *             (Precondition String of contents and file name)
	 *             (Postcondition Writes contents to File)
	 */
	private void writeToFile(String fileName)
			throws IOException
	{
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(users);
		out.close();
		fileOut.close();
	}
}