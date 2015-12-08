package main;

import java.io.IOException;
import java.text.ParseException;

import UI.NonProfitInterface;
import UI.UserInterface;

/**
 * This is the nonprofit class.
 * It keeps track of all the non profits.
 * @author Stepan Adespya
 * @edited by Mindy Huynh 12/5/2015
 * @since November 5, 2015
 */
public class NonProfit
{
	
	/**
	 * This is the constructor for the Non Profit User, easier to navigate.
	 * 
	 * @pre User that logged in.
	 * @post Menu of all the options the Nonprofit can choose.
	 * 
	 * @throws ParseException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public NonProfit(User user) throws ParseException, IOException, ClassNotFoundException
	{
		NonProfitInterface npi = new NonProfitInterface(user);
		int input = npi.mainMenu();
		CalendarAuctionCentral c;
		Auction a;
		switch (input)
		{
			case 1:
				String requestday = npi.enterAuctionDetails();
				Date request = new Date(requestday);
				int duration = npi.enterDuration();
				scheduleAuction(user, request, duration, npi);
				break;
			case 2:
				c = new CalendarAuctionCentral();
				a = c.getAuction(user.getOrganization());
				if (a != null) {
					this.editAuctionInfo(user, a, npi);
				} else {
					System.out.println("There is no auction scheduled with your organization.\n");
					new NonProfit(user);
				}
				break;
			case 3:
				c = new CalendarAuctionCentral();
				a = c.getAuction(user.getOrganization());
				if (a != null) {
					this.addItemInfo(user, a, npi);
				} else {
					System.out.println("There is no auction scheduled with your organization.\n");
				}
				new NonProfit(user);
				break;
			case 4:
				c = new CalendarAuctionCentral();
				a = c.getAuction(user.getOrganization());
				if (a != null) {
					Inventory i = new Inventory();
					int item = npi.selectEditItem(i, a);
					this.editItemInfo(user, item, npi);
				} else {
					System.out.println("There is no auction scheduled with your organization.\n");
				}
				new NonProfit(user);
				break;
			case 5:
				new UserInterface();
				break;
			default:
				System.out.println("Sorry wrong input");
				new NonProfit(user);
				break;
		}
	}
	
	/**
	 * This method requests an Auction, checks if the schedule fits
	 * 
	 * @pre User that logged in, date that user wants to schedule auction, duration of auction in hours, NonProfit interface
	 * @post Checks to see if the date selected can be scheduled.
	 * 
	 * @param user the user that scheduled it.
	 * @param auctionDate the date of the auction.
	 * @param duration the duration of the auction.
	 * @throws ParseException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void scheduleAuction(User user, Date auctionDate, int duration, NonProfitInterface npi)
			throws ParseException, IOException, ClassNotFoundException
	{
		CalendarAuctionCentral c = new CalendarAuctionCentral();
		Auction a = new Auction(user.getOrganization(), auctionDate, duration);
		if (c.checkRequestedAuction(a))
		{
			addAuctionInfo(user, user.getOrganization(), auctionDate,
					duration, npi);
		} else
		{
			System.out.println("Sorry cannot Schedule on this Date Please try again");
			new NonProfit(user);
		}
	}
	
	/**
	 * This method adds Information to the Auction class.
	 * 
	 * @pre User that logged in, organization name of User, date schedule for auction, duration for auction, NonProfit interface.
	 * @post Gets more information about auction and adds to calendar.
	 * 
	 * @param user the user using the class.
	 * @param nonProfitName the name of the non profit
	 * @param auctionDate the date of the auction.
	 * @param auctionDuration the duration of the auction.
	 * @throws ParseException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void addAuctionInfo(User user, String orgName, Date auctionDate,
			int auctionDuration, NonProfitInterface npi) throws ParseException, IOException,
			ClassNotFoundException
	{
		Auction a = new Auction(orgName, auctionDate, auctionDuration);
		CalendarAuctionCentral c = new CalendarAuctionCentral();
		if (c.checkRequestedAuction(a))
		{
			c.addFutureAuction(a);
			System.out.println("Auction added");
			int input = 1;
			while (input == 1)
			{
				input = npi.auctionAddMenu();
				if (input == 2)
				{
					new NonProfit(user);
				} else
				{
					addItemInfo(user, a, npi);
				}
			}
		} else
		{
			System.out.println("Sorry auction wasn't added");
			new NonProfit(user);
		}
	}
	
	/**
	 * This method edits Auction Information.
	 * 
	 * @pre User that logged in, auction the User wants to edit, and NonProfit Interface.
	 * @post Editing whatever the User chooses.
	 * 
	 * @param user the user using the class.
	 * @param auction the auction to be added.
	 * @throws ParseException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void editAuctionInfo(User user, Auction auction, NonProfitInterface npi) throws ParseException,
			IOException, ClassNotFoundException
	{
		int input = npi.editAuctionMenu();
		CalendarAuctionCentral c;
		switch (input)
		{
			case 1:
				String editday = npi.enterAuctionDetails();
				Date newday = new Date(editday);
				c = new CalendarAuctionCentral();
				c.editAuctionDate(auction, newday);
				System.out.println("Done editing");
				new NonProfit(user);
				break;
			case 2:
				int duration = npi.enterDuration();
				c = new CalendarAuctionCentral();
				c.editAuctionDuration(auction, duration);
				System.out.println("Done editing");
				new NonProfit(user);
				break;
		}
		
	}
	
	/**
	 * This method adds an Item to the selected auction.
	 * 
	 * @pre User that logged in, auction to which item is selling, and NonProfit interface
	 * @post adds item to the auction selected by User.
	 * 
	 * @param user the user that's adding item info.
	 * @param auction the auction that the item belongs to.
	 * @throws ParseException
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public void addItemInfo(User user, Auction auction, NonProfitInterface npi) throws ParseException,
			IOException, ClassNotFoundException
	{
		Item i = npi.addItem(auction);
		Inventory in = new Inventory();
		in.addItem(i);
		System.out.println("Item can now be bidded on");
	}
	
	/**
	 * This method edits an items from selected auction.
	 * 
	 * @pre User that logged in, item the User wants edited, and NonProfit Interface.
	 * @post Item edited to what the User wants.
	 * 
	 * @param user the user using the class. 
	 * @param itemID the ID of the item.
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public void editItemInfo(User user, int itemID, NonProfitInterface npi) throws IOException, ClassNotFoundException
	{
		int input = npi.editItemMenu();
		Inventory i;
		switch (input)
		{
			case 1:
				String newName = npi.enterItemName();
				i = new Inventory();
				i.editItemName(itemID, newName);
				break;
			case 2:
				double startBid = npi.enterItemStartBid();
				i = new Inventory();
				i.editItemStartBid(itemID, startBid);
				break;
			case 3:
				String newInfo = npi.enterInfo();
				i = new Inventory();
				i.editItemInfo(itemID, newInfo);
				break;
		}
	}
}