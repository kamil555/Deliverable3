package UI;

import java.text.ParseException;

import main.CalendarAuctionCentral;

public class CalendarUserInterface
{	
	/**
	 * 
	 * @param month
	 * @param year
	 * @return
	 * @throws ParseException
	 */
	public String printCalendarMonthly(int month, int year) throws ParseException
	{
		CalendarAuctionCentral myCalendar = new CalendarAuctionCentral();
		int counter = 1;
		String calendar = "";
		String[] monthName =
		{ "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		int[] monthDay =
		{ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		for (int i = 1; i < monthDay[month - 1] + 1; i++)
		{
			System.out.println(monthName[month - 1] + "," + i + ", " + year + ": ");
			System.out.println("");
			for (int j = 0; j < myCalendar.getAuctionList().size(); j++)
			{
				if (myCalendar.getAuctionList().get(j).getAuctionStart().getMonth() == month
						&& myCalendar.getAuctionList().get(j).getAuctionStart().getDay() == i
						&& myCalendar.getAuctionList().get(j).getAuctionStart().getYear() == year)
				{
					calendar += myCalendar.getAuctionList().get(j).toString();
					System.out.println("   (" + counter + ")"); 
					System.out.println("\tAuction Name:\t" + myCalendar.getAuctionList().get(j).getAuctionName());
					System.out.println("\tOrganization:\t" + myCalendar.getAuctionList().get(j).getNonProfitName());
					System.out.println("\tStart Time:\t" + myCalendar.getAuctionList().get(j).getAuctionStart());
					System.out.println("\tEnd Time:\t" + myCalendar.getAuctionList().get(j).getAuctionEnd());
					System.out.println("");
					
					counter++;
				}
			}
		}
		return calendar;
	}
	
	/**
	 * 
	 */
	public void viewFutureAuctions()
	{
		CalendarAuctionCentral myCalendar = new CalendarAuctionCentral();
		for (int i = 0; i < myCalendar.getFutureAuctionList().size(); i++)
		{
			System.out.println("   (" + (i + 1) + ")"); 
			System.out.println("\tAuction Name:\t" + myCalendar.getFutureAuctionList().get(i).getAuctionName());
			System.out.println("");
		}
		
	}
	
}
