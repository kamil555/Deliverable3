package Main;
import java.io.IOException;
import java.text.ParseException;

import UserInterface.*;

/**
 * @author Stepan Adespya
 * @since November 5, 2015
 */
public class NonProfit{

	/**
	 * Main Menu for the Non Profit User, easier to navigate
	 * 
	 * @throws ParseException
	 * @throws IOException
	 */
	public NonProfit(User user) throws ParseException, IOException{
		NonProfitInterface npi = new NonProfitInterface(user);
		int input = npi.mainMenu();
		//selectOption(input, user);
		CalendarAuctionCentral c;
		Auction a;
		switch (input){
		case 1:
			String requestday = npi.enterAuctionDetails();
			Date request = new Date(requestday);
			int duration = npi.enterDuration();
			scheduleAuction(user, request, duration,npi);	
			break;
		case 2:
			c = new CalendarAuctionCentral();
			a = c.getAuction(user);
			this.editAuctionInfo(user, a,npi);
			break;
		case 3:
			c = new CalendarAuctionCentral();
			a = c.getAuction(user);
			this.addItemInfo(user, a,npi);
			new NonProfit(user);
			break;
		case 4:
			c = new CalendarAuctionCentral();
			a = c.getAuction(user);
			Inventory i = new Inventory();
			int item = npi.selectEditItem(i, a);
			this.editItemInfo(user, item, npi);
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
	 * Requests a Auction, checks if the schedule fits
	 * 
	 * @param u
	 * @param auctionDate
	 * @param duration
	 * @throws ParseException
	 * @throws IOException
	 */
	public void scheduleAuction(User u, Date auctionDate, int duration, NonProfitInterface npi)
			throws ParseException, IOException{
		CalendarAuctionCentral c = new CalendarAuctionCentral();
		if (c.checkRequestedDate(auctionDate))
		{
			addAuctionInfo(u, u.getOrganization(), auctionDate,
					duration,npi);
		} else
		{
			System.out.println("Sorry cannot Schedule on this Date Please try again");
			new NonProfit(u);
		}
	}

	/**
	 * Adds Information to the Auction class.
	 * 
	 * @param u
	 * @param profitName
	 * @param auctionDate
	 * @param auctionDuration
	 * @throws ParseException
	 * @throws IOException
	 */
	public void addAuctionInfo(User u, String orgName, Date auctionDate,
			int auctionDuration,NonProfitInterface npi) throws ParseException, IOException{
		Auction a = new Auction(orgName, auctionDate, auctionDuration);
		CalendarAuctionCentral c = new CalendarAuctionCentral();
		if (c.checkRequestedAuction(a)){
			c.addFutureAuction(a);
			System.out.println("Auction added");
			int input = 1;
			while (input == 1){
				input = npi.auctionAddMenu();
				if (input == 2)
				{
					new NonProfit(u);
				} else{
					addItemInfo(u, a,npi);
				}
			}
		} else{
			System.out.println("Sorry auction wasn't added");
			new NonProfit(u);
		}
	}

	/**
	 * Edits Auction Information.
	 * 
	 * @param u
	 * @param a
	 * @throws ParseException
	 * @throws IOException
	 */
	public void editAuctionInfo(User u, Auction a,NonProfitInterface npi) throws ParseException,
	IOException{
		int input = npi.editAuctionMenu();
		CalendarAuctionCentral c;
		switch (input){
		case 1:
			String editday = npi.enterAuctionDetails();
			Date newday = new Date(editday);
			c = new CalendarAuctionCentral();
			c.editAuctionDate(a, newday);
			System.out.println("Done editing");
			new NonProfit(u);
			break;
		case 2:
			int duration = npi.enterDuration();
			c = new CalendarAuctionCentral();
			c.editAuctionDuration(a, duration);
			System.out.println("Done editing");
			new NonProfit(u);
			break;
		}

	}

	/**
	 * Adds an Item to the selected auction.
	 * 
	 * @param u
	 * @param a
	 * @throws ParseException
	 * @throws IOException
	 */
	public void addItemInfo(User u, Auction a, NonProfitInterface npi) throws ParseException,
	IOException{
			Item i = npi.addItem(a);
			Inventory in = new Inventory();
			in.addItem(i);
			System.out.println("Item can now be bidded on");
	}

	/**
	 * Edits an items from selected auction.
	 * 
	 * @param u
	 * @param itemID
	 * @throws IOException
	 */
	public void editItemInfo(User u, int itemID, NonProfitInterface npi) throws IOException{
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
