/**
 * 
 */
package UserInterface;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import Main.*;

/**
 * @author Stepan Adespya
 * @since November 5, 2015
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
	
	public int enterMonth(){
		System.out.println("Enter the Month you want to view(1-12)");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int month = reader.nextInt();
		return month;
	}
	
	public int enterYear(){
		System.out.println("Enter the Year you want to view(EX. 2015)");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int year = reader.nextInt();
		return year;
	}
	
	public int selectAuction(CalendarAuctionCentral c){
		System.out.println("Select Auction(Number) :");
		System.out.println(c.viewFutureAuctions());
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int select = reader.nextInt();
		return select;
	}
	
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

