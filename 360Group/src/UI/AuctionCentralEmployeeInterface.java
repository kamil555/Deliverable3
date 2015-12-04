/**
 * 
 */
package UI;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import main.*;

/**
 * @author Stepan Adespya
 * @since November 21, 2015
 */
public class AuctionCentralEmployeeInterface{
	private int CALENDAR = 1;
	private int AUCTIONS = 2;
	private int LOGOUT = 3;
	private int RETURN = 1;
	
	/**
	 * AuctionCentral Employee Interface constructor;
	 * 
	 * @throws ParseException
	 * @throws IOException 
	 * 
	 */
	public AuctionCentralEmployeeInterface(User u) {
		System.out.println("Employee, " + u.getUserName());
	}
	
	/**
	 * The main menu of the AuctionCentral Employee User.
	 * @return int of the menu selected
	 * (Precondition nothing)
	 * (Postcondition int of menu selected)
	 */
	public int mainMenu(){
		System.out.println("Press 1 to view calendar");
		System.out.println("Press 2 to view Auction Details");
		System.out.println("Press 3 log out");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int input = reader.nextInt();
		while (input != AUCTIONS && input != CALENDAR && input != LOGOUT){
			System.out.println("Sorry wrong input, Please try again");
			input = reader.nextInt();
		}
		return input;
	}
	
	/**
	 * Asks the user to Select a month.
	 * @return int of month selected
	 * (Precondition nothing)
	 * (Postcondition int of month selected)
	 */
	public int enterMonth(){
		System.out.println("Enter the Month you want to view(1-12)");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int month = reader.nextInt();
		return month;
	}
	
	/**
	 * Asks the user to Select a year.
	 * @return int of year selected
	 * (Precondition nothing)
	 * (Postcondition int of year selected)
	 */
	public int enterYear(){
		System.out.println("Enter the Year you want to view(EX. 2015)");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int year = reader.nextInt();
		return year;
	}
	
	/**
	 * Asks the user to Select a Auction.
	 * @return int of Auction selected
	 * (Precondition nothing)
	 * (Postcondition int of Auction selected)
	 */
	public int selectAuction(CalendarAuctionCentral c){
		System.out.println("Select Auction(Number) :");
		System.out.println(c.viewFutureAuctions());
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int select = reader.nextInt();
		return select;
	}
	
	/**
	 * Asks the user to return to main menu.
	 * @return int to return to main menu.
	 * (Precondition nothing)
	 * (Postcondition int to return to main menu)
	 */
	public int returnMainMenu(User u){
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
