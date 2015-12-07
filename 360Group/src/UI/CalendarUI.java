package UI;

import java.text.ParseException;

import main.CalendarAuctionCentral;

/**
 * 
 * @author Gabrielle Glynn
 * @edited by Mindy Huynh 12/5/2015
 *
 */
public class CalendarUI
{
	
	CalendarAuctionCentral myCalendar = new CalendarAuctionCentral();
	
	/**
	 * 
	 * @param month
	 * @param year
	 * @throws ParseException
	 */
	public String printCalendarMonthly(int month, int year) throws ParseException
	{

		System.out.println(myCalendar.getAuctionList());
		String calendar = "";
		String[] monthName =
		{ "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		int[] monthDay =
		{ 31, 28, 31, 30, 31, 31, 31, 30, 31, 30, 31, 30 };
		for (int i = 1; i < monthDay[month - 1] + 1; i++)
		{
			System.out.println(monthName[month - 1] + "," + i + ", " + year
					+ ": ");
			for (int j = 0; j < myCalendar.getAuctionList().size(); j++)
			{
				if (myCalendar.getAuctionList().get(j).getAuctionStart().getMonth() == month
						&& myCalendar.getAuctionList().get(j).getAuctionStart().getDay() == i
						&& myCalendar.getAuctionList().get(j).getAuctionStart().getYear() == year)
				{
					calendar += myCalendar.getAuctionList().get(j).toString();
				}
			}
		}
		//System.out.print(calendar);
		return calendar;
	}
	
	/**
	 * 
	 */
	public void viewFutureAuctions()
	{
		for (int i = 0; i < myCalendar.getAuctionList().size(); i++)
		{
			System.out.println(i + ") " + myCalendar.getAuctionList().get(i).toString());
		}
		
	}
	
	// /*
	// * Returns auction if it matches a Nonprofit's name.
	// */
	// public Auction getAuction(User u) throws ParseException, IOException {
	// System.out
	// .println("Select your Auction or Press -1 to go back to main menu:");
	// for (int i = 0; i < auctionList.size(); i++) {
	// if (auctionList.get(i).getProfitName()
	// .equalsIgnoreCase(u.organization)) {
	// System.out.println(i + ")" + auctionList.get(i).toString());
	// }
	// }
	// @SuppressWarnings("resource")
	// Scanner reader = new Scanner(System.in);
	// int select = reader.nextInt();
	// if (select == -1) {
	// new NonProfit(u);
	// }
	// return auctionList.get(select);
	// }
	
}
