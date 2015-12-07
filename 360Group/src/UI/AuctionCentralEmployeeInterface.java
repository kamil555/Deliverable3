/**
 * 
 */
package UI;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import main.*;

/**
 * This is the user interface of the AuctionCentralEmployee class.
 * 
 * @author Stepan Adespya
 * @edited by Mindy Huynh 12/5/2015
 * @since November 21, 2015
 */
public class AuctionCentralEmployeeInterface
{
	/**
	 * This is number representation of the calendar.
	 */
	private int CALENDAR = 1;
	
	/**
	 * This is number representation of the Auctions.
	 */
	private int AUCTIONS = 2;
	
	/**
	 * This is number representation of the logout.
	 */
	private int LOGOUT = 3;
	
	/**
	 * This is number representation of the return.
	 */
	private int RETURN = 1;
	
	/**
	 * This is the AuctionCentral Employee Interface constructor.
	 * 
	 * @param user current user.
	 * @throws ParseException
	 * @throws IOException
	 * 
	 */
	public AuctionCentralEmployeeInterface(User user)
	{
		System.out.println("Employee, " + user.getUserName());
	}
	
	/**
	 * This is the main menu of the AuctionCentral Employee User.
	 * 
	 * @return int of the menu selected
	 * @Pre nothing
	 * @Post int of menu selected
	 */
	public int mainMenu()
	{
		System.out.println("Press 1 to view calendar");
		System.out.println("Press 2 to view Auction Details");
		System.out.println("Press 3 log out");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int input = reader.nextInt();
		while (input != AUCTIONS && input != CALENDAR && input != LOGOUT)
		{
			System.out.println("Sorry wrong input, Please try again");
			input = reader.nextInt();
		}
		return input;
	}
	
	/**
	 * This method asks the user to Select a month.
	 * 
	 * @return int of month selected 
	 * @Pre nothing 
	 * @post int of month selected)
	 */
	public int enterMonth()
	{
		System.out.println("Enter the Month you want to view(1-12)");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int month = reader.nextInt();
		return month;
	}
	
	/**
	 * This method asks the user to Select a year.
	 * 
	 * @return int of year entered 
	 * @Pre nothing
	 * @Post int of year entered
	 */
	public int enterYear()
	{
		System.out.println("Enter the Year you want to view(EX. 2015)");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int year = reader.nextInt();
		return year;
	}
	
	/**
	 * This method asks the user to Select an Auction.
	 * 
	 * @param cal the calendar being used.
	 * @return int of Auction selected 
	 * @Pre nothing
	 * @Post int of Auction selected)
	 */
	public int selectAuction(CalendarAuctionCentral cal)
	{
		System.out.println("Select Auction(Number) :");
		CalendarUI cui = new CalendarUI();
		cui.viewFutureAuctions();
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int select = reader.nextInt();
		return select;
	}
	
	/**
	 * This method asks the user to return to main menu.
	 * 
	 * @return int to return to main menu.
	 * @Pre nothing
	 * @Post int to return to main menu)
	 */
	public int returnMainMenu(User u)
	{
		System.out.println("Press 1 to return to main menu");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int input = reader.nextInt();
		while (input != RETURN)
		{
			System.out.println("Sorry wrong input, Please try again");
			input = reader.nextInt();
		}
		return input;
	}
}
