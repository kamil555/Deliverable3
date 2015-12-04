package UI;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import main.*;

/**
 * 
 * @author Stepan Adespya
 * @since November 21, 2015
 */
public class BidderInterface{

	/**
	 * Main menu for the Bidder User Interface.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * 
	 */
	public BidderInterface(User u) throws IOException, ParseException{
		System.out.println("Welcome, " + u.getUserName());
	}

	/**
	 * The main menu of the Bidder User.
	 * @return int of the menu selected
	 * (Precondition nothing)
	 * (Postcondition int of menu selected)
	 */
	public int mainMenu(){
		System.out.println("Press 1 to see availiable auctions");
		System.out.println("Press 2 to see bidded items");
		System.out.println("Press 3 log out");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int input = reader.nextInt();
		while (input != 1 && input != 2 && input != 3)
		{
			System.out.println("Sorry wrong input, Please try again");
			input = reader.nextInt();
		}
		return input;
	}

	/**
	 * Asks to enter an item ID
	 * @return int of item ID
	 * (Precondition nothing)
	 * (Postcondition int of item selected)
	 */
	public int enterItemID(){
		System.out.println("Enter item ID to edit bid or -1 to go back: ");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int select = reader.nextInt();
		return select;
	}

	/**
	 * Asks the User what amount of bid he is changing to.
	 * @return double of the amount he wants to change.
	 * (Precondition nothing)
	 * (Postcondition double amount bid changed to)
	 */
	public double editBidAmount(){
		System.out.println("Enter the amount you want to change? $");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		double money = reader.nextDouble();
		return money;
	}
	
	/**
	 * Asks the User how much he wants to bid on item.
	 * @return double of bid amount
	 * (Precondition nothing)
	 * (Postcondition double amount bided)
	 */
	public double enterBidAmount(){
		System.out.println("How much do you want to bid? $");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		double money = reader.nextDouble();
		return money;
	}

	/**
	 * Menu when User selects an item.
	 * @return int of what user wants to do next.
	 * (Precondition nothing)
	 * (Postcondition int of menu selected)
	 */
	public int viewItemMenu(){
		System.out.println("Press 1 to Bid");
		System.out.println("Press 2 to go back to menu");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int input = reader.nextInt();
		while (input != 1 && input != 2 && input != 3){
			System.out.println("Sorry wrong input, Please try again");
			input = reader.nextInt();
		}
		return input;
	}
	
	/**
	 * Asks the User to select an Auction.
	 * @param c AuctionCentral calendar
	 * @return int of Auction selected.
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
	 * Asks the User to select an item from the auction
	 * @param i
	 * @param a
	 * @return int of item selected.
	 * (Precondition nothing)
	 * (Postcondition int of item selected)
	 */
	public int selectItem(Inventory i, Auction a){
		System.out.println("Select Item(Number) :");
		System.out.println(i.allItemsAuction(a));
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int select = reader.nextInt();
		return select;
	}

}