package main;

import java.io.IOException;
import java.text.ParseException;

import UI.BidderInterface;
import UI.UserInterface;

/**
 * This is the bidder class.
 * It keeps track of all the bidders.
 * @author Stepan Adespya
 * @edited by Mindy Huynh
 * @since November 5, 2015
 */
public class Bidder
{
	
	/**
	 * Main menu for the Bidder User.
	 * 
	 * @pre User that logged in.
	 * @post Menu options for the Bidder that logged in.
	 * 
	 * @param user the username of the user. 
	 * @throws IOException
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 * 
	 */
	public Bidder(User user) throws IOException, ParseException, ClassNotFoundException
	{
		BidderInterface bi = new BidderInterface(user);
		int input = bi.mainMenu();
		switch (input)
		{
			case 1:
				this.viewOpenAuctions(user, bi);
				break;
			case 2:
				this.viewItemsBidded(user);
				int select = bi.enterItemID();
				Inventory i = new Inventory();
				Item it = i.getItemFromList(select);
				if (select == -1)
				{
					new Bidder(user);
				} else
				{
					double money = bi.editBidAmount();
					editBid(user, it, money);
					new Bidder(user);
				}
				
				break;
			case 3:
				new UserInterface();
				break;
		}
		
	}
	
	/**
	 * This method allows the bidder to view the item and bid on the item they like.
	 * 
	 * @pre User that logged in, the item to view, and interface for the bidder.
	 * @post Details of the item selected.
	 * 
	 * @param user the bidder.
	 * @param item the item being bid on.
	 * @throws IOException
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 */
	public void viewItem(User user, Item item, BidderInterface bidInterface) throws IOException, ParseException,
			ClassNotFoundException
	{
		System.out.println(item.toString());
		int input = bidInterface.viewItemMenu();
		switch (input)
		{
			case 1:
				double amount = bidInterface.enterBidAmount();
				Bid b = new Bid(user.getUserName(), item.getItemID(), amount);
				BidList bl = new BidList();
				bl.addBid(user, b);
				new Bidder(user);
				break;
			case 2:
				new Bidder(user);
				break;
		}
	}
	
	/**
	 * This method allows bidder to change his/her bid on a given item.
	 * 
	 * @pre User that logged in, item to edit bid, and the amount he wants edited to.
	 * @post Bid changes if the rules are accepted.
	 * 
	 * @param user the bidder interested in the item.
	 * @param item the item that is being bid on.
	 * @param money the amount they want to pay.
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public void editBid(User user, Item item, double money) throws IOException, ClassNotFoundException
	{
		BidList b = new BidList();
		b.editBid(user, item, money);
	}
	
	/**
	 * This method shows the bidder all open auction.
	 * 
	 * @pre User that logged in, bidder interface.
	 * @post Options to all the auctions available to the bidder.
	 * 
	 * @param user the bidder that is looking to view the auction.
	 * @throws ParseException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void viewOpenAuctions(User user, BidderInterface bidderInterface) throws ParseException, IOException,
			ClassNotFoundException
	{
		CalendarAuctionCentral c = new CalendarAuctionCentral();
		int selectAuction = bidderInterface.selectAuction(c);
		Auction a = c.getAuctionList().get(selectAuction);
		Inventory i = new Inventory();
		int selectItem = bidderInterface.selectItem(i, a);
		viewItem(user, i.listofItems.get(selectItem), bidderInterface);
	}
	
	/**
	 * This method shows the user what item is being bidded on.
	 * 
	 * @pre User that logged in.
	 * @post View all the items the bidder bided on.
	 * 
	 * @param user the bidder for the item.
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	private void viewItemsBidded(User user) throws IOException, ClassNotFoundException
	{
		Inventory i = new Inventory();
		i.allItemsBidder(user);
	}
	
}