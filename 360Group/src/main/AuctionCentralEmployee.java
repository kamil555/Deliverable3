package main;
import java.io.IOException;
import java.text.ParseException;

import UI.*;

/**
 * @author Stepan Adespya
 * @since November 5, 2015
 */
public class AuctionCentralEmployee{
	
	/**
	 * The Main menu for the AuctionCentral Employee.
	 * 
	 * @throws ParseException
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	public AuctionCentralEmployee(User u) throws ParseException, IOException, ClassNotFoundException{
		AuctionCentralEmployeeInterface acei = new AuctionCentralEmployeeInterface(u);
		int input = acei.mainMenu();
		switch (input){
			case 1:
				int month = acei.enterMonth();
				int year = acei.enterYear();
				System.out.println(viewCalendar(u, month, year));
				input = acei.returnMainMenu(u);
				if (input == 1)
				{
					new AuctionCentralEmployee(u);
				}
				break;
			case 2:
				System.out.println(viewAuctionDetails(u, acei));
				input = acei.returnMainMenu(u);
				if (input == 1){
					new AuctionCentralEmployee(u);
				}
				break;
			case 3:
				new UserInterface();
		}
		
	}
	
	/**
	 * Shows the User the details of an up and coming auction.
	 * 
	 * @param u
	 * @throws ParseException
	 * @throws IOException 
	 */
	public String viewAuctionDetails(User u,AuctionCentralEmployeeInterface acei) throws ParseException, IOException{
		CalendarAuctionCentral c = new CalendarAuctionCentral();
		int select = acei.selectAuction(c);
		Auction a = c.getAuctionList().get(select);
		return a.toString();
	}
	
	/**
	 * Show the User a monthly calendar of auctions.
	 * 
	 * @param u
	 * @param month
	 * @param year
	 * @throws ParseException
	 * @throws IOException 
	 */
	public String viewCalendar(User u, int month, int year) 
			throws ParseException, IOException{
		CalendarUI cui = new CalendarUI();
		return cui.printCalendarMonthly(month, year);
	}
	
}