package UI;

import java.text.ParseException;

public class CalendarUI {
	
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
			for (int j = 0; j < getAuctionList.size(); j++) {
				if (auctionList.get(j).getAuctionStart().getMonth() == month
						&& auctionList.get(j).getAuctionStart().getDay() == i
						&& auctionList.get(j).getAuctionStart().getYear() == year) {
					System.out.println(auctionList.get(j).toString());
				}
			}
		}
	}


	/**
	 * 
	 */
	public void viewFutureAuctions() {
		for (int i = 0; i < futureAuctionList.size(); i++) {
			System.out.println(i + ") " + futureAuctionList.get(i).toString());
		}

	}

}

