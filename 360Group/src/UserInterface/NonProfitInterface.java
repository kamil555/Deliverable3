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
 * @since November 21, 2015
 */
public class NonProfitInterface{
	
	/**
	 * Welcome message to User.
	 * @throws ParseException
	 * @throws IOException
	 */
	public NonProfitInterface(User user) throws ParseException, IOException{
		System.out.println("Welcome, " + user.getUserName());
		System.out.println("Organization: " + user.getOrganization());
	}
	
	/**
	 * Main Menu for Non Profit User.
	 * @return int of menu selected
	 * (Precondition nothing)
	 * (Postcondition int of menu selected)
	 */
	public int mainMenu(){
		System.out.println("Press 1 to schedule an auction");
		System.out.println("Press 2 to edit auction information");
		System.out.println("Press 3 to enter add item to inventory ");
		System.out.println("Press 4 to edit inventory item information");
		System.out.println("Press 5 to Log out");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		while (!reader.hasNextInt()){
			System.out.println("Please enter a number between 1 and 5");
			reader = new Scanner(System.in);
		}
		int input = reader.nextInt();
		return input;
	}
	
	/**
	 * Asks User to fill out the Auction schedule.
	 * @return String of an Auction Date
	 * (Precondition nothing)
	 * (Postcondition String of date for auction)
	 */
	public String enterAuctionDetails(){
		System.out.println("Enter Auction Information: ");
		System.out.println("Enter Month(1-12): ");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int month = reader.nextInt();
		System.out.println("Enter Day(Integer): ");
		int day = reader.nextInt();
		System.out.println("Enter Year(Integer): ");
		int year = reader.nextInt();
		while(!isValidDate(month, day, year)){
			System.out.println("Invalid date.");
			System.out.println("Enter Auction Information: ");
			System.out.println("Enter Month(1-12): ");
			month = reader.nextInt();
			System.out.println("Enter Day(Integer): ");
			day = reader.nextInt();
			System.out.println("Enter Year(Integer): ");
			year = reader.nextInt();
		}
		System.out.println("Enter Hour Auction to Start(0-23): ");
		int hour = reader.nextInt();
		System.out.println("Enter Minute Auction to Start(0-59): ");
		int minute = reader.nextInt();
		int second = 0;
		String requestday = month + "/" + day + "/" + year + " "
				+ hour + ":" + minute + ":" + second;
		return requestday;
	}
	
	/**
	 * Menu for User after Auction is created.
	 * @return int of menu selected
	 * (Precondition nothing)
	 * (Postcondition int of menu selected)
	 */
	public int auctionAddMenu(){
		System.out.println("Press 1 to add items");
		System.out.println("Press 2 to go back to main menu");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int input = reader.nextInt();
		while (input != 1 && input != 2)
		{
			System.out.println("Sorry wrong input, Please try again");
			input = reader.nextInt();
		}
		return input;
	}
	
	/**
	 * Menu of editing an Auction.
	 * @return int of menu selected.
	 * (Precondition nothing)
	 * (Postcondition int of menu selected)
	 */
	public int editAuctionMenu(){
		System.out.println("Press 1 to edit Start of Auction");
		System.out.println("Press 2 to edit The duration of Auction");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int input = reader.nextInt();
		while (input != 1 && input != 2)
		{
			System.out.println("Sorry wrong input, Please try again");
			input = reader.nextInt();
		}
		return input;
	}
	
	/**
	 * Menu of editing an item created.
	 * @return int of menu selected.
	 * (Precondition nothing)
	 * (Postcondition int of menu selected)
	 */
	public int editItemMenu(){
		System.out.println("Press 1 to edit Item Name");
		System.out.println("Press 2 to edit Start Bid");
		System.out.println("Press 3 to edit Item Info");
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
	 * Asks User to enter name for item
	 * @return String of name for item.
	 * (Precondition nothing)
	 * (Postcondition String for item name)
	 */
	public String enterItemName(){
		System.out.println("Enter New name :");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		reader = new Scanner(System.in);
		String name = reader.nextLine();
		return name;
	}
	
	/**
	 * Asks User what the start bid amount for item
	 * @return double of start bid item
	 * (Precondition nothing)
	 * (Postcondition double for start bid)
	 */
	public double enterItemStartBid(){
		System.out.println("Enter New start Bid :");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		double startBid = reader.nextDouble();
		return startBid;
	}
	
	/**
	 * Asks User to enter more information about item.
	 * @return String about the item.
	 * (Precondition nothing)
	 * (Postcondition String about item)
	 */
	public String enterInfo(){
		System.out.println("Enter New Info :");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		String newInfo = reader.nextLine();
		return newInfo;
	}
	
	/**
	 * Asks User the information about item to add.
	 * @param a
	 * @return Item to add.
	 * (Precondition Auction to add item to)
	 * (Postcondition Item with information)
	 */
	public Item addItem(Auction a){
		System.out.println(a.getAuctionName());
		System.out.println("Name of item :");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		String itemName = reader.nextLine();
		System.out.println("Starting bid of item :");
		double startBid = reader.nextDouble();
		System.out.println("More Information about item :");
		reader = new Scanner(System.in);
		String moreInfo = reader.nextLine();
		if(!itemName.isEmpty() && !moreInfo.isEmpty()){
			Item i = new Item(a.getAuctionName(), startBid, itemName, moreInfo);
			return i;
		}else{
			System.out.println("One of the category is not correct.");
			this.addItem(a);
		}
		return null;
	}
	
	/**
	 * Asks User to select an item to edit.
	 * @param i
	 * @param a
	 * @return int of item selected
	 * (Precondition Inventory and Auction where the item is stored)
	 * (Postcondition int of item selected)
	 */
	public int selectEditItem(Inventory i, Auction a){
		System.out.println("Enter the Number of item you want to edit");
		System.out.println(i.allItemsAuction(a));
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int item = reader.nextInt();
		return item;
	}
	
	/**
	 * Asks User to enter the Duration of the Auction
	 * @return int of duration
	 * (Precondition nothing)
	 * (Postcondition int of auction duration)
	 */
	public int enterDuration(){
		System.out.println("Enter the Duration(Hours): ");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int duration = reader.nextInt();
		return duration;
	}
	
	/**
	 * Checks if the date entered is correct;
	 * 
	 * @param month
	 * @param day
	 * @param year
	 * @return true if date is valid and false if date doesn't exist
	 * (Precondition int for month, day ,year)
	 * (Postcondition true or false)
	 */
	private boolean isValidDate(int month, int day, int year){
		if (year >= 2015){
			int[] monthDay =
			{ 31, 28, 31, 30, 31, 31, 31, 30, 31, 30, 31, 30 };
			if (month < 13 && month > 0){
				if (monthDay[month - 1] >= day){
					return true;
				} else{
					return false;
				}
			} else{
				return false;
			}
		} else{
			return false;
		}
	}
}

