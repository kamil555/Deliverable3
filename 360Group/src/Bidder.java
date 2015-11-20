import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * 
 * @author Stepan Adespya
 * @since November 5, 2015
 */
public class Bidder
{
	
	/**
	 * Main menu for the Bidder User
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * 
	 */
	public Bidder(User u) throws IOException, ParseException
	{
		System.out.println("Welcome, " + u.userName);
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
		switch (input)
		{
			case 1:
				viewOpenAuctions(u);
				break;
			case 2:
				viewItemsBidded(u);
				System.out
						.println("Enter item ID to edit bid or -1 to go back: ");
				int select = reader.nextInt();
				Inventory i = new Inventory();
				Item it = i.getItemFromList(select);
				if (select == -1)
				{
					new Bidder(u);
				} else
				{
					System.out
							.println("Enter the amount you want to change? $");
					double money = reader.nextDouble();
					editBid(u, it, money);
					System.out.println("Bid changed");
					new Bidder(u);
				}
				
				break;
			case 3:
				new Users();
				break;
		}
		
	}
	
	/**
	 * Lets you bid on a given item.
	 * 
	 * @param u
	 * @param i
	 * @throws IOException
	 * @throws ParseException
	 */
	public void viewItem(User u, Item i) throws IOException, ParseException
	{
		System.out.println(i.toString());
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
		switch (input)
		{
			case 1:
				System.out.println("How much do you want to bid? $");
				double money = reader.nextDouble();
				Bid b = new Bid(u.userName, i.itemID, money);
				bidList bl = new bidList();
				bl.addBid(u, b);
				System.out.println("Bid entered");
				new Bidder(u);
				break;
			case 2:
				new Bidder(u);
				break;
		}
	}
	
	/**
	 * Allows you to change your bid on a given item.
	 * 
	 * @param u
	 * @param i
	 * @param money
	 * @throws IOException
	 */
	public void editBid(User u, Item i, double money) throws IOException
	{
		bidList b = new bidList();
		b.editBid(u, i, money);
	}
	
	/**
	 * Shows the User all open auctions.
	 * 
	 * @param u
	 * @throws ParseException
	 * @throws IOException
	 */
	public void viewOpenAuctions(User u) throws ParseException, IOException
	{
		CalendarAuctionCentral c = new CalendarAuctionCentral();
		System.out.println("Select Auction(Number) :");
		c.viewFutureAuctions();
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int select = reader.nextInt();
		Auction a = c.futureAuctionList.get(select);
		Inventory i = new Inventory();
		System.out.println("Select Item(Number) :");
		i.allItemsAuction(a);
		select = reader.nextInt();
		viewItem(u, i.listofItems.get(select));
	}
	
	/**
	 * Shows the all the Users bided items.
	 * 
	 * @param u
	 * @throws IOException
	 */
	private void viewItemsBidded(User u) throws IOException
	{
		Inventory i = new Inventory();
		i.allItemsBidder(u);
	}
	
}
