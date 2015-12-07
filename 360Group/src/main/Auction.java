package main;

import java.text.ParseException;

/**
 * This is the Auction class. This class takes in the non profits request for an
 * auction.
 * 
 * @author Mindy Huynh
 *
 */
@SuppressWarnings("serial")
public class Auction implements java.io.Serializable {
	// auction name
	/**
	 * This is the name of the auction, it will be automatically generated via
	 * the the non profits name and the date of the auction.
	 */
	private String auctionName;

	/**
	 * This is the date in which the auction start. It includes date, month,
	 * year as well as hour and minute of the auction start.
	 */
	// auction date
	private Date auctionStart;

	/**
	 * This is the date in which the auction end. It includes date, month, year
	 * as well as hour and minute of the auction end. It will be automatically
	 * generated via the duration and auction start.
	 */
	private Date auctionEnd;

	/**
	 * This is how long the auction will last. It will be automatically
	 * generated via the auction start and end.
	 */
	private int auctionDuration;

	/**
	 * This is the non profit's name.
	 */
	private String nonprofitName;

	/**
	 * This is the constructor to the Auction class. It requires the non-profit
	 * user to fill out profitName. auctionStartDAte, auctionDuration.
	 * 
	 * @param nonprofitName
	 *            The name of the nonprofit company
	 * @param auctionStartDate
	 *            when the auction starts.
	 * @param auctionDuration
	 *            when the auction ends.
	 * @throws ParseException
	 */
	public Auction(String nonprofitName, Date auctionStartDate,
			int auctionDuration) throws ParseException {
		this.nonprofitName = nonprofitName;
		this.auctionStart = auctionStartDate;
		this.auctionDuration = auctionDuration;

		resetAuctionEnd();
		resetAuctionName();
	}

	/**
	 * This is the getter for auctionName.
	 * 
	 * @return auctionName the name of the auction.
	 */
	public String getAuctionName() {
		return this.auctionName;
	}

	/**
	 * This is the method to reset the auction name. Since the start date or the
	 * non profit name may change, I want the auction name to change according
	 * to the new non profit name.
	 * 
	 * @throws ParseException
	 */
	public void resetAuctionName() throws ParseException {
		// "calculating" auction name
		String date = getDate(auctionStart.getMonth(), auctionStart.getDay(),
				auctionStart.getYear());

		this.auctionName = this.nonprofitName + "-" + date;
	}

	/**
	 * This is the getter for auction start.
	 * 
	 * @return auctionStart the time and date of when the auction is scheduled
	 *         to start.
	 */
	public Date getAuctionStart() {
		return auctionStart;
	}

	/**
	 * This is the setter for auction start
	 * 
	 * @param auctionStart
	 *            the new date and time for when the auction starts.
	 * @throws ParseException
	 */
	public void setAuctionStart(Date auctionStart) throws ParseException {
		this.auctionStart = auctionStart;
		resetAuctionName();
	}

	/**
	 * This is the getter for the end of the auction.
	 * 
	 * @return when the auction is scheduled to end.
	 */
	public Date getAuctionEnd() {
		return auctionEnd;
	}

	/**
	 * This is the method to reset the auction end. Since the start date or
	 * duration may change, I want the auction end to change according to the
	 * duration and start date.
	 * 
	 * @throws ParseException
	 */
	public void resetAuctionEnd() throws ParseException {
		Date auctionEnd = auctionStart.clone();
		auctionEnd.addHours(auctionDuration);
		this.auctionEnd = auctionEnd;
	}

	/**
	 * This is the getter for auctionDuration.
	 * 
	 * @return auctionDuration how the long auction is supposed to last.
	 */
	public int getAuctionDuration() {
		return auctionDuration;
	}

	/**
	 * This is the setter for auction duration.
	 * 
	 * @param auctionDuration
	 *            the new duration of the auction.
	 */
	public void setAuctionDuration(int auctionDuration) {
		this.auctionDuration = auctionDuration;
	}

	/**
	 * This is the getter for the nonprofit name.
	 * 
	 * @return nonprofitname the name of the non profit
	 */
	public String getNonProfitName() {
		return nonprofitName;
	}

	/**
	 * This is the setter for the non profitname
	 * 
	 * @param profitName
	 *            the new name of the non profit.
	 * @throws ParseException
	 */
	public void setNonProfitName(String profitName) throws ParseException {
		this.nonprofitName = profitName;
		resetAuctionName();
	}

	/**
	 * This is the toString of the class.
	 * 
	 * @return the string representation all the information saved in the
	 *         auction class.
	 */
	public String toString() {
		return "Auction Name = " + this.auctionName + "Non-Profit Name = "
				+ this.nonprofitName + "Auction Start Date = "
				+ this.auctionStart.toString();

		// return this.auctionName + "," + this.auctionStart + ","
		// + this.auctionDuration;
	}
	
	public Auction clone()
	{
		try {
			return new Auction(this.nonprofitName, this.auctionStart, this.auctionDuration);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// public String printDetails()
	// {
	// return "Auction Name = " + this.auctionName + "Non-Profit Name = "
	// + this.nonprofitName + "Auction Start Date = "
	// + this.auctionStart.toString();
	// }

	/*
	 * public void writeAuctionToFile(String fileName) throws IOException {
	 * String auctionString = new String(); auctionString = this.toString();
	 * FileWriter fw = new FileWriter(fileName, true); PrintWriter pw = new
	 * PrintWriter(fw); // fw.append(System.lineSeparator());
	 * pw.write(auctionString + "\r\n"); pw.close(); }
	 */

	/**
	 * Returns the string representation of the date class.
	 * 
	 * @param month
	 *            the month we want to change to string
	 * @param day
	 *            the date we want in the string
	 * @param year
	 *            the year we want in the string.
	 * @return
	 */
	private String getDate(int month, int day, int year) {
		String[] monthName = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		String mm = monthName[month - 1];
		return mm + "-" + day + "-" + year;
	}

}