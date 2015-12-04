package UserInterface;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import Main.*;
/**
 * @author Stepan Adespya
 * @since November 21, 2015
 */
public class UserInterface{
	// Keeps track of all Users
	private ArrayList<User> users;
	private int BIDDER = 1;
	private int EMPLOYEE = 2;
	private int NONPROFIT = 3;
	private String logUser = "User.ser";

	/**
	 * Users constructor, allows user to login or create account.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * @throws ClassNotFoundException 
	 */
	public UserInterface() throws IOException, ParseException, ClassNotFoundException{
		readFileToUsers(logUser);
		System.out.println("Hello Welcome to AuctionCentral");
		System.out.println("Press 1 to login\nPress 2 to create user\nPress 3 to exit");
		Scanner reader = new Scanner(System.in);

		//created a method that checks if its integer..keep prompting
		checkForInt(reader);
	}

	/**
	 * Checks it User selected the right menu item.
	 * @param reader
	 * @throws IOException
	 * @throws ParseException
	 * (Precondition nothing)
	 * (Postcondition int of menu selected)
	 * @throws ClassNotFoundException 
	 */
	private void checkForInt(Scanner reader) throws IOException, ParseException, ClassNotFoundException{
		while(!reader.hasNextInt()){
			System.out.println("Please enter an integer between 1 and 3.");
			reader = new Scanner(System.in);
		}
		selectOption(reader);		

	}

	/**
	 * Goes to the Right menu selected
	 * @param reader
	 * @throws IOException
	 * @throws ParseException
	 * (Precondition nothing)
	 * (Postcondition send the User to menu)
	 * @throws ClassNotFoundException 
	 */
	private void selectOption(Scanner reader) throws IOException, ParseException, ClassNotFoundException{
		int login = 1;
		int createUser = 2;
		int exit = 3;

		int input = reader.nextInt();
		while (input != login && input != createUser && input != exit){
			System.out.println("Sorry wrong input, Please try again");
			input = reader.nextInt();
		}
		String userName = null;
		switch (input){
		//login
		case 1:
			User userLogin = userLogin(reader, userName);
			if (userLogin.getUser().equalsIgnoreCase("Bidder")){
				new Bidder(userLogin);
				break;
			} else if (userLogin.getUser().equalsIgnoreCase("AuctionCentral Employee")){
				new AuctionCentralEmployee(userLogin);
				break;
			} else if (userLogin.getUser().equalsIgnoreCase("nonprofit")){
				new NonProfit(userLogin);
				break;
			}
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
			new UserInterface();
			break;

			//exit
		case 3:

			break;
		}
	}

	/**
	 * Gets the new User information
	 * @param userName
	 * @throws IOException
	 * @throws ParseException
	 * (Precondition User name is valid)
	 * (Postcondition creates new user)
	 */
	private void createNewUser(String userName) throws IOException, ParseException{
		System.out.println("Press 1 if you want to create an account as a Bidder"
				+ "\nPress 2 if you want to create an account as an Auction Central Employee"
				+ "\nPress 3 if you want to create an account as a Nonprofit Organization");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		while(!reader.hasNextInt()){
			System.out.println("Please enter a number between 1 and 3.");
			reader = new Scanner (System.in);
		}
		int inputUser = reader.nextInt();
		while (inputUser != BIDDER && inputUser != EMPLOYEE
				&& inputUser != NONPROFIT){
			System.out.println("Sorry wrong input, please try again.");
			inputUser = reader.nextInt();
		}
		User newUser = null;
		if (inputUser == BIDDER){
			newUser = createUser(userName, "Bidder");
		} else if (inputUser == EMPLOYEE){
			newUser = createUser(userName, "AuctionCentral Employee");
		} else if (inputUser == NONPROFIT){
			newUser = createUser(userName, "Nonprofit");
		}
		users.add(newUser);
		writeToFile(logUser);
		System.out.println("Created new User! Please Login with new User.");
	}

	/**
	 * Looks if new User is a NonProfit if he is asks for organization name.
	 * 
	 * @param username
	 * @param pass
	 * @param user
	 * @throws IOException
	 * @throws ParseException
	 * (Precondition User name and who is user)
	 * (Postcondition new user)
	 */
	public User createUser(String username, String user) throws IOException,
	ParseException{
		User per = new User(username, user);
		if (per.getUser().equalsIgnoreCase("nonprofit")){
			System.out.println("Enter Nonprofit Organization: ");
			@SuppressWarnings("resource")
			Scanner reader = new Scanner(System.in);
			String np = reader.nextLine();
			while (isOnePerOrg(np)){
				System.out.println("Please enter another organization name :");
				np = reader.nextLine();
			}
			per.setOrganization(np);
			writeToFile(logUser);

		}
		return per;
	}

	public User userLogin(Scanner reader, String userName) throws IOException, ParseException, ClassNotFoundException{
		System.out.print("Enter Username: ");
		userName = reader.next().toUpperCase();
		return login(userName);
	}

	/**
	 * Login for users.
	 * 
	 * @param userName
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 * (Precondition user name)
	 * (Postcondition go to right user class)
	 * @throws ClassNotFoundException 
	 */
	private User login(String userName) throws IOException, ParseException, ClassNotFoundException{
		userName.toUpperCase();
		for (int i = 0; i < users.size(); i++){
			if (users.get(i).getUserName().endsWith(userName)){	
				return users.get(i);
			}

		}
		System.out.println("Sorry could not login please try again");
		new UserInterface();
		return null;
	}

	/**
	 * Checks to see if anyone else has the same organization name. BR #7
	 * @param org (Organization name)
	 * @return true or false
	 * (Precondition organization name)
	 * (Postcondition checks to see if organization is already used)
	 */
	private boolean isOnePerOrg(String org){
		for (int i = 0; i < users.size(); i++){
			if (users.get(i).getOrganization() != null){
				if (users.get(i).getOrganization().equalsIgnoreCase(org)){
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
	 * (Precondition user name valid)
	 * (Postcondition check is username is already taken)
	 */
	private boolean isOneUserName(String username){
		for (int i = 0; i < users.size(); i++){
			if (users.get(i).getUserName().equalsIgnoreCase(username)){
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
	 * (Precondition string of file name to read off of)
	 * (Postcondition fill array of all users in system)
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	private void readFileToUsers(String fileName) throws ClassNotFoundException, IOException{
		FileInputStream fileIn = new FileInputStream(fileName);
		try{
			ObjectInputStream in = new ObjectInputStream(fileIn);
			users = (ArrayList<User>) in.readObject();
			in.close();
		}catch(EOFException e){
			users = new ArrayList<User>();
		}
        fileIn.close();
	}


	/**
	 * Writes in the file chosen(use for logs)
	 * 
	 * @param fileName
	 * @param contents
	 * @throws IOException
	 * (Precondition String of contents and file name)
	 * (Postcondition Writes contents to File)
	 */
	private void writeToFile(String fileName)
			throws IOException{
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(users);
		out.close();
		fileOut.close();
	}
}