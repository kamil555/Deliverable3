package Main;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * @author Stepan Adespya
 * @since November 5, 2015
 */
public class AuctionCentralEmployee
{
	private int CALENDAR = 1;
	private int AUCTIONS = 2;
	
	/**
	 * The Main menu for the AuctionCentral Employee.
	 * 
	 * @throws ParseException
	 * @throws IOException 
	 * 
	 */
	public AuctionCentralEmployee(User u) throws ParseException, IOException
	{
		System.out.println("Employee, " + u.userName);
		System.out.println("Press 1 to view calendar");
		System.out.println("Press 2 to view Auction Details");
		System.out.println("Press 3 log out");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int input = reader.nextInt();
		while (input != AUCTIONS && input != CALENDAR && input != 3)
		{
			System.out.println("Sorry wrong input, Please try again");
			input = reader.nextInt();
		}
		switch (input)
		{
			case 1:
				System.out.println("Enter the Month you want to view(1-12)");
				int month = reader.nextInt();
				System.out.println("Enter the Year you want to view(EX. 2015)");
				int year = reader.nextInt();
				viewCalendar(u, month, year);
				break;
			case 2:
				viewAuctionDetails(u);
				break;
			case 3:
				new Users();
		}
		
	}
	
	/**
	 * Shows the User the details of an up and coming auction.
	 * 
	 * @param u
	 * @throws ParseException
	 * @throws IOException 
	 */
	public void viewAuctionDetails(User u) throws ParseException, IOException
	{
		CalendarAuctionCentral c = new CalendarAuctionCentral();
		System.out.println("Select Auction(Number) :");
		c.viewFutureAuctions();
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int select = reader.nextInt();
		Auction a = c.futureAuctionList.get(select);
		System.out.println(a.printDetials());
		System.out.println("Press 1 to return to main menu");
		int input = reader.nextInt();
		
		//Note: need to fix eventually to be not "1"
		while (input != 1)
		{
			System.out.println("Sorry wrong input, Please try again");
			input = reader.nextInt();
		}
		if (input == 1)
		{
			new AuctionCentralEmployee(u);
		}
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
	public void viewCalendar(User u, int month, int year) throws ParseException, IOException
	{
		CalendarAuctionCentral c = new CalendarAuctionCentral();
		c.printCalendarMonthly(month, year);
		System.out.println("Press 1 to return to main menu");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int input = reader.nextInt();
		
		//Note: need to fix eventually to be not "1"
		while (input != 1)
		{
			System.out.println("Sorry wrong input, Please try again");
			input = reader.nextInt();
		}
		if (input == 1)
		{
			new AuctionCentralEmployee(u);
		}
	}
	
}
