package UserInterface;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import Main.*;

/**
 * 
 * @author Stepan Adespya
 * @since November 5, 2015
 */
public class BidderInterface{

	/**
	 * Main menu for the Bidder User
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * 
	 */
	public BidderInterface(User u) throws IOException, ParseException{
		System.out.println("Welcome, " + u.getUserName());
	}

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

	public int enterItemID(){
		System.out.println("Enter item ID to edit bid or -1 to go back: ");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int select = reader.nextInt();
		return select;
	}

	public double editBidAmount(){
		System.out.println("Enter the amount you want to change? $");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		double money = reader.nextDouble();
		return money;
	}
	
	public double enterBidAmount(){
		System.out.println("How much do you want to bid? $");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		double money = reader.nextDouble();
		return money;
	}

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
	
	public int selectAuction(CalendarAuctionCentral c){
		System.out.println("Select Auction(Number) :");
		System.out.println(c.viewFutureAuctions());
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int select = reader.nextInt();
		return select;
	}
	
	public int selectItem(Inventory i, Auction a){
		System.out.println("Select Item(Number) :");
		i.allItemsAuction(a);
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int select = reader.nextInt();
		return select;
	}

}
