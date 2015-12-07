package UI;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import main.*;

/**
 * This is the nonprofit user interface.
 * @author Stepan Adespya
 * @edited by Mindy Huynh 12/5/2015
 * @since November 21, 2015
 */
public class NonProfitInterface
{
	
	/**
	 * This is the constructor and welcome message to the user. 
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	public NonProfitInterface(User user) throws ParseException, IOException
	{
		System.out.println("Welcome, " + user.getUserName());
		System.out.println("Organization: " + user.getOrganization());
	}
	
	/**
	 * This is the Main Menu for Non Profit User.
	 * 
	 * @return int of menu selected
	 * @Pre nothing
	 * @Post int of menu selected)
	 */
	public int mainMenu()
	{
		System.out.println("Press 1 to schedule an auction");
		System.out.println("Press 2 to edit auction information");
		System.out.println("Press 3 to enter add item to inventory ");
		System.out.println("Press 4 to edit inventory item information");
		System.out.println("Press 5 to Log out");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		while (!reader.hasNextInt())
		{
			System.out.println("Please enter a number between 1 and 5");
			reader = new Scanner(System.in);
		}
		int input = reader.nextInt();
		return input;
	}
	
	/**
	 * This method asks User to fill out the Auction schedule.
	 * 
	 * @return String of an Auction Date
	 * @Pre nothing
	 * @Post String date for auction)
	 */
	public String enterAuctionDetails()
	{
		System.out.println("Enter Auction Information: ");
		System.out.println("Enter Month(1-12): ");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int month = reader.nextInt();
		System.out.println("Enter Day(Integer): ");
		int day = reader.nextInt();
		System.out.println("Enter Year(Integer): ");
		int year = reader.nextInt();
		while (!isValidDate(month, day, year))
		{
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
	 * This is the menu for User after Auction is created.
	 * 
	 * @return int of menu selected 
	 * @Pre nothing
	 * @Post return int of menu selected.
	 */
	public int auctionAddMenu()
	{
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
	 * This is the menu of editing an Auction.
	 * 
	 * @return int of menu selected. 
	 * @Pre nothing
	 * @Post int of menu selected
	 */
	public int editAuctionMenu()
	{
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
	 * This is the menu of editing an item created.
	 * 
	 * @return int of menu selected.
	 * @Pre nothing
	 * @Post int of menu selected
	 */
	public int editItemMenu()
	{
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
	 * This method asks User to enter name for item
	 * 
	 * @return String of name for item. 
	 * @Pre nothing
	 * @Post String name for item.
	 */
	public String enterItemName()
	{
		System.out.println("Enter New name :");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		reader = new Scanner(System.in);
		String name = reader.nextLine();
		return name;
	}
	
	/**
	 * This method asks User what the start bid amount for item
	 * 
	 * @return double of start bid item
	 * @Pre nothing
	 * @Post double of start bid item.
	 */
	public double enterItemStartBid()
	{
		System.out.println("Enter New start Bid :");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		double startBid = reader.nextDouble();
		return startBid;
	}
	
	/**
	 * This method asks User to enter more information about item.
	 * 
	 * @return String about the item. 
	 * @Pre nothing
	 * @Post String about item.
	 */
	public String enterInfo()
	{
		System.out.println("Enter New Info :");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		String newInfo = reader.nextLine();
		return newInfo;
	}
	
	/**
	 * This method asks User the information about item to add.
	 * 
	 * @param auction the auction being used.
	 * @return Item to add. 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @Pre nothing
	 * @Post takes in the information and saves it.
	 */
	public Item addItem(Auction auction) throws ClassNotFoundException, IOException
	{
		System.out.println(auction.getAuctionName());
		System.out.println("Name of item :");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		String itemName = reader.nextLine();
		System.out.println("Starting bid of item :");
		double startBid = reader.nextDouble();
		System.out.println("More Information about item :");
		reader = new Scanner(System.in);
		String moreInfo = reader.nextLine();
		if (!itemName.isEmpty() && !moreInfo.isEmpty())
		{
			Item i = new Item(auction.getAuctionName(), startBid, itemName, moreInfo);
			return i;
		} else
		{
			System.out.println("One of the category is not correct.");
			this.addItem(auction);
		}
		return null;
	}
	
	/**
	 * This method asks User to select an item to edit.
	 * 
	 * @param inven the inventory the item is in
	 * @param auction the auction for the item.
	 * @return int of item selected
	 * @Pre Inventory and Auction where the item is stored
	 * @Post int of item selected
	 */
	public int selectEditItem(Inventory inven, Auction auction)
	{
		System.out.println("Enter the Number of item you want to edit");
		System.out.println(inven.allItemsAuction(auction));
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int item = reader.nextInt();
		return item;
	}
	
	/**
	 * This method asks User to enter the Duration of the Auction
	 * 
	 * @return int of duration 
	 * @Pre Inventory and Auction where the item is stored
	 * @Post int of duration.
	 */
	public int enterDuration()
	{
		System.out.println("Enter the Duration(Hours): ");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int duration = reader.nextInt();
		return duration;
	}
	
	/**
	 * This method checks if the date entered is correct;
	 * 
	 * @param month the month to check.
	 * @param day the date to check.
	 * @param year the year to check.
	 * @return true if date is valid and false if date doesn't exist
	 * @Pre int for month, day ,year
	 * @Post true or false
	 */
	private boolean isValidDate(int month, int day, int year)
	{
		if (year >= 2015)
		{
			int[] monthDay =
			{ 31, 28, 31, 30, 31, 31, 31, 30, 31, 30, 31, 30 };
			if (month < 13 && month > 0)
			{
				if (monthDay[month - 1] >= day)
				{
					return true;
				} else
				{
					return false;
				}
			} else
			{
				return false;
			}
		} else
		{
			return false;
		}
	}
}
