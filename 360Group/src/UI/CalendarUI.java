package UI;

import java.text.ParseException;

import main.CalendarAuctionCentral;

public class CalendarUI {
	
	CalendarAuctionCentral myCalendar = new CalendarAuctionCentral();
	
	/**
	 * 
	 * @param month
	 * @param year
	 * @throws ParseException
	 */
	public void printCalendarMonthly(int month, int year) throws ParseException {
		String[] monthName = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		int[] monthDay = { 31, 28, 31, 30, 31, 31, 31, 30, 31, 30, 31, 30 };
		for (int i = 1; i < monthDay[month - 1] + 1; i++) {
			System.out.println(monthName[month - 1] + "," + i + ", " + year
					+ ": ");
			for (int j = 0; j < myCalendar.getAuctionList().size(); j++) {
				if (myCalendar.getAuctionList().get(j).getAuctionStart().getMonth() == month
						&& myCalendar.getAuctionList().get(j).getAuctionStart().getDay() == i
						&& myCalendar.getAuctionList().get(j).getAuctionStart().getYear() == year) {
					System.out.println(myCalendar.getAuctionList().get(j).toString());
				}
			}
		}
	}


	/**
	 * 
	 */
	public void viewFutureAuctions() {
		for (int i = 0; i < myCalendar.getAuctionList().size(); i++) {
			System.out.println(i + ") " + myCalendar.getAuctionList().get(i).toString());
		}

	}

}

