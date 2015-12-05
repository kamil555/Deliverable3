package main;

import java.io.IOException;
import java.text.ParseException;

import UI.*;

/**
 * 
 * @author Stepan Adespya
 * @since November 5, 2015
 */
public class Bidder{
	
	/**
	 * Main menu for the Bidder User
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * @throws ClassNotFoundException 
	 * 
	 */
	public Bidder(User u) throws IOException, ParseException, ClassNotFoundException{
		BidderInterface bi = new BidderInterface(u);
		int input = bi.mainMenu();
		switch (input){
			case 1:
				this.viewOpenAuctions(u,bi);
				break;
			case 2:
				this.viewItemsBidded(u);
				int select = bi.enterItemID();
				Inventory i = new Inventory();
				Item it = i.getItemFromList(select);
				if (select == -1){
					new Bidder(u);
				}else{
					double money = bi.editBidAmount();
					editBid(u, it, money);
					new Bidder(u);
				}
				
				break;
			case 3:
				new UserInterface();
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
	 * @throws ClassNotFoundException 
	 */
	public void viewItem(User u, Item i,BidderInterface bi) throws IOException, ParseException, ClassNotFoundException{
		System.out.println(i.toString());
		int input = bi.viewItemMenu();
		switch (input){
			case 1:
				double money = bi.enterBidAmount();
				Bid b = new Bid(u.getUserName(), i.getItemID(), money);
				BidList bl = new BidList();
				bl.addBid(u, b);
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
	public void editBid(User u, Item i, double money) throws IOException{
		bidList b = new bidList();
		b.editBid(u, i, money);
	}
	
	/**
	 * Shows the User all open auctions.
	 * 
	 * @param u
	 * @throws ParseException
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public void viewOpenAuctions(User u, BidderInterface bi) throws ParseException, IOException, ClassNotFoundException{
		CalendarAuctionCentral c = new CalendarAuctionCentral();
		int selectAuction = bi.selectAuction(c);
		Auction a = c.getfutureAuctionList().get(selectAuction);
		Inventory i = new Inventory();
		int selectItem = bi.selectItem(i, a);
		viewItem(u, i.listofItems.get(selectItem),bi);
	}
	
	/**
	 * Shows the all the Users bided items.
	 * 
	 * @param u
	 * @throws IOException
	 */
	private void viewItemsBidded(User u) throws IOException{
		Inventory i = new Inventory();
		i.allItemsBidder(u);
	}
	
}