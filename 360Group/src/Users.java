import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Stepan Adespya and Mindy Huynh
 * @since November 5, 2015
 */

class User
{
	// UserName of person
	protected String userName;
	// Whether a person is a bidder,employee, or nonprofit
	protected String user;
	// If nonprofit, Organization name
	protected String organization;
	
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

public class Users
{
	// Keeps track of all Users
	private ArrayList<User> users;
	private int BIDDER = 1;
	private int EMPLOYEE = 2;
	private int NONPROFIT = 3;
	
	/**
	 * Users constructor, allows user to login or create account.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public Users() throws IOException, ParseException
	{
		users = new ArrayList<User>();
		readFileToUsers("Logs.txt");
		readFileToOrg("NonProfit.txt");
		System.out.println("Hello Welcome to AuctionCentral");
		System.out.println("Press 1 to login\nPress 2 to create user\nPress 3 to exit");
		Scanner reader = new Scanner(System.in);
		
		//created a method that checks if its integer..keep prompting
		checkForInt(reader);
	}
	
	private void checkForInt(Scanner reader) throws IOException, ParseException
	{
		while(!reader.hasNextInt())
		{
			System.out.println("Please enter an integer between 1 and 3.");
			reader = new Scanner(System.in);
		}
		selectOption(reader);		
		
	}
	
	private void selectOption(Scanner reader) throws IOException, ParseException
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
			//login
			case 1:
				userLogin(reader, userName);
				break;
			
			//createUser
			case 2:
				System.out.println("Create Username: ");
				userName = reader.next().toUpperCase();
				while (isOneUserName(userName))
				{
					System.out.println("This Username is taken, please enter another Username: ");
					userName = reader.next();
				}
				createNewUser(userName);
				break;
			
			//exit
			case 3:
				
				break;
		}
	}
	
	private void createNewUser(String userName) throws IOException, ParseException
	{
		System.out.println("Press 1 if you want to create an account as a Bidder"
				+ "\nPress 2 if you want to create an account as an Auction Central Employee"
				+ "\nPress 3 if you want to create an account as a Nonprofit Organization");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		while(!reader.hasNextInt())
		{
			System.out.println("Please enter a number between 1 and 3.");
			reader = new Scanner (System.in);
		}
		int inputUser = reader.nextInt();
		while (inputUser != BIDDER && inputUser != EMPLOYEE
				&& inputUser != NONPROFIT)
		{
			System.out.println("Sorry wrong input, please try again.");
			inputUser = reader.nextInt();
		}
		if (inputUser == BIDDER)
		{
			createUser(userName, "Bidder");
		} else if (inputUser == EMPLOYEE)
		{
			createUser(userName, "AuctionCentral Employee");
		} else if (inputUser == NONPROFIT)
		{
			createUser(userName, "Nonprofit");
		}
	}
	
	private void userLogin(Scanner reader, String userName) throws IOException, ParseException
	{
		System.out.print("Enter Username: ");
		userName = reader.next().toUpperCase();
		login(userName);
	}
	
	/**
	 * Creates a User.
	 * 
	 * @param username
	 * @param pass
	 * @param user
	 * @throws IOException
	 * @throws ParseException
	 */
	public boolean createUser(String username, String user) throws IOException,
			ParseException
	{
		User per = new User(username, user);
		if (per.user.equalsIgnoreCase("nonprofit"))
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
			per.organization = np;
			String org = username + "," + np;
			writeToFile("NonProfit.txt", org);
			
		}
		String contents = "" + username + "," + user;
		writeToFile("Logs.txt", contents);
		users.add(per);
		System.out.println("done");
		new Users();
		return true;
	}
	
	/**
	 * Login for users.
	 * 
	 * @param userName
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public boolean login(String userName) throws IOException, ParseException
	{
		userName.toUpperCase();
		for (int i = 0; i < users.size(); i++)
		{
			if (users.get(i).userName.endsWith(userName))
			{	
				if (users.get(i).user.equalsIgnoreCase("Bidder"))
				{
					Bidder b = new Bidder(users.get(i));
					break;
				} else if (users.get(i).user.equalsIgnoreCase("AuctionCentral Employee"))
				{
					AuctionCentralEmployee a = new AuctionCentralEmployee(users.get(i));
					break;
				} else if (users.get(i).user.equalsIgnoreCase("nonprofit"))
				{
					NonProfit p = new NonProfit(users.get(i));
					break;
				}
			}
			if (i == users.size() - 1)
			{
				System.out.println("Sorry could not login please try again");
				new Users();
			}
		}
		return true;
		
	}
	
	/**
	 * Checks to see if anyone else has the same organization name. BR #7
	 * 
	 * @param org
	 *            (Organization name)
	 * @return true or false
	 */
	private boolean isOnePerOrg(String org)
	{
		
		for (int i = 0; i < users.size(); i++)
		{
			if (users.get(i).organization != null)
			{
				if (users.get(i).organization.equalsIgnoreCase(org))
				{
					System.out.println("Sorry only one person can represent the Nonprofit Organization");
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if there is the same username
	 * 
	 * @param username
	 * @return true or false
	 */
	private boolean isOneUserName(String username)
	{
		for (int i = 0; i < users.size(); i++)
		{
			if (users.get(i).userName.equalsIgnoreCase(username))
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
	 * @param fileName
	 */
	private void readFileToUsers(String fileName)
	{
		String line = null;
		try
		{
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);
			
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while ((line = bufferedReader.readLine()) != null)
			{
				String[] split = line.split(",", 2);
				String username = split[0];
				String user = split[1];
				users.add(new User(username, user));
				
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex)
		{
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex)
		{
			System.out.println("Error reading file '" + fileName + "'");
		}
	}
	
	/**
	 * Reads the file for nonprofit organizations
	 * 
	 * @param fileName
	 */
	private void readFileToOrg(String fileName)
	{
		String line = null;
		try
		{
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);
			
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while ((line = bufferedReader.readLine()) != null)
			{
				String[] split = line.split(",", 2);
				String username = split[0];
				String orgname = split[1];
				for (int i = 0; i < users.size(); i++)
				{
					if (users.get(i).userName.equalsIgnoreCase(username))
					{
						users.get(i).organization = orgname;
					}
				}
				
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex)
		{
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex)
		{
			System.out.println("Error reading file '" + fileName + "'");
		}
	}
	
	/**
	 * Writes in the file chosen(use for logs)
	 * 
	 * @param fileName
	 * @param contents
	 * @throws IOException
	 */
	private void writeToFile(String fileName, String contents)
			throws IOException
	{
		FileWriter fw = new FileWriter(fileName, true);
		PrintWriter pw = new PrintWriter(fw);
		if (Files.size(Paths.get(fileName)) == 0)
		{
			pw.write(contents);
		} else
		{
			pw.write("\n" + contents);
		}
		pw.close();
	}
}