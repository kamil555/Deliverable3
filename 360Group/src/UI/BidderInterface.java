package UI;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import main.*;

/**
 * This is the bidder interface class.
 * @author Stepan Adespya
 * @edited by Mindy Huynh 12/5/2015
 * @since November 21, 2015
 */
public class BidderInterface
{
	
	/**
	 * This is the constructor for the bidder interface.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * 
	 */
	public BidderInterface(User user) throws IOException, ParseException
	{
		System.out.println("Welcome, " + user.getUserName());
	}
	
	/**
	 * This is the main menu of the Bidder User.
	 * 
	 * @return int of the menu selected
	 * @Pre nothing
	 * @Post int of menu selected
	 */
	public int mainMenu()
	{
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
	 * This method asks to enter an item ID
	 * 
	 * @return int of item ID 
	 * @Pre nothing 
	 * @Post int of item selected
	 */
	public int enterItemID()
	{
		System.out.println("Enter item ID to edit bid or -1 to go back: ");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int select = reader.nextInt();
		return select;
	}
	
	/**
	 * This method asks the User what amount of bid he is changing to.
	 * 
	 * @return double of the amount he wants to change. 
	 * @Pre nothing
	 * @Post double amount bid changed to
	 */
	public double editBidAmount()
	{
		System.out.println("Enter the amount you want to change? $");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		double money = reader.nextDouble();
		return money;
	}
	
	/**
	 * This method asks the User how much he wants to bid on item.
	 * 
	 * @return double of bid amount 
	 * @Pre nothing
	 * @Post double amount bided.
	 */
	public double enterBidAmount()
	{
		System.out.println("How much do you want to bid? $");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		double money = reader.nextDouble();
		return money;
	}
	
	/**
	 * This is the menu when User selects an item.
	 * 
	 * @return int of what user wants to do next.
	 * @Pre nothing
	 * @Post int of menu selected
	 */
	public int viewItemMenu()
	{
		System.out.println("Press 1 to Bid");
		System.out.println("Press 2 to go back to menu");
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
	 * This method asks the User to select an Auction.
	 * 
	 * @param cal AuctionCentral calendar
	 * @return int of Auction selected. 
	 * @Pre nothing
	 * @Post int of Auction selected
	 */
	public int selectAuction(CalendarAuctionCentral cal)
	{
		if(cal.getFutureAuctions() > 0) {
			System.out.println("Select Auction(Number) :");
			CalendarUserInterface cui = new CalendarUserInterface();
			cui.viewFutureAuctions();
			@SuppressWarnings("resource")
			Scanner reader = new Scanner(System.in);
			int select = reader.nextInt();
			return select;
		} else {
			System.out.println("There are currently no auctions scheduled.");
			return -1;
		}
	}
	
	/**
	 * This method asks the User to select an item from the auction
	 * 
	 * @param item the item in the inventory.
	 * @param auction the auction being used.
	 * @return int of item selected. 
	 * @Pre nothing
	 * @Post int of item selected)
	 */
	public int selectItem(Inventory item, Auction auction)
	{
		System.out.println("Select Item(Number) :");
		ArrayList<Item> itemsForAuction = item.viewAllitems(auction);
		int counter = 1;
		for (Item itm: itemsForAuction) {
			System.out.println("\t(" + counter + ")");
			System.out.println("\t\tItem ID: " + itm.getItemID());
			System.out.println("\t\tItem Name: " + itm.getStartBid());
			System.out.println("\t\tItem Description: " + itm.getItemInfo());
			System.out.println("\t\tStarting Bid: $" + itm.getStartBid());
			System.out.println("");
			counter++;
		}
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int select = reader.nextInt();
		return select;
	}
	
}