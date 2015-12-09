package main;

import java.io.IOException;
import java.text.ParseException;

import UI.AuctionCentralEmployeeInterface;
import UI.CalendarUserInterface;
import UI.UserInterface;

/**
 * This is the AuctionCentralEmployee class.
 * It keeps track of all the auctionCentral employees.
 * 
 * @author Stepan Adespya
 * @edited by Mindy Huynh 12/5/2015
 * @since November 5, 2015
 */
public class AuctionCentralEmployee
{	
	/**
	 * The Main menu for the AuctionCentral Employee.
	 * 
	 * @pre User that logged in.
	 * @post User interface of all the Menu options.
	 * 
	 * @param user the person currently logged in.
	 * @throws ParseException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public AuctionCentralEmployee(User user) throws ParseException, IOException,
			ClassNotFoundException
	{
		AuctionCentralEmployeeInterface acei = new AuctionCentralEmployeeInterface(user);
		int input = acei.mainMenu();
		switch (input)
		{
			case 1:
				int month = acei.enterMonth();
				int year = acei.enterYear();
				System.out.println(viewCalendar(user, month, year));
				input = acei.returnMainMenu(user);
				if (input == 1)
				{
					new AuctionCentralEmployee(user);
				}
				break;
			case 2:
				System.out.println(viewAuctionDetails(user, acei));
				input = acei.returnMainMenu(user);
				if (input == 1)
				{
					new AuctionCentralEmployee(user);
				}
				break;
			case 3:
				new UserInterface();
		}
		
	}
	
	/**
	 * Shows the User the details of an up and coming auction.
	 * 
	 * @pre User that logged in, the Interface used.
	 * @post Printing out to the console information about the auction.
	 * 
	 * @param user the person currently logged in.
	 * @param acei the auction central employeee calling to view auction.
	 * @throws ParseException
	 * @throws IOException
	 */
	public String viewAuctionDetails(User user, AuctionCentralEmployeeInterface acei)
			throws ParseException, IOException
	{
		CalendarAuctionCentral cal = new CalendarAuctionCentral();
		int select = acei.selectAuction(cal);
		Auction a = cal.getAuctionList().get(select-1);
		return a.toString();
	}
	
	/**
	 * Show the User a monthly calendar of auctions.
	 * 
	 * @pre User that logged in, the month and year the User would like to see as an integer.
	 * @post a Calendar showing the month that the User selected.
	 * 
	 * @param user the person currently logged in.
	 * @param month the month the user wants to set up in the calendar
	 * @param year the year the user wants to set up in the calendar
	 * @throws ParseException
	 * @throws IOException
	 */
	public String viewCalendar(User user, int month, int year)
			throws ParseException, IOException
	{
		CalendarUserInterface cui = new CalendarUserInterface();
		return cui.printCalendarMonthly(month, year);
	}
	
}