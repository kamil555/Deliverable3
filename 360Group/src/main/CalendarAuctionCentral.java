package main;

import java.text.ParseException;
import java.util.ArrayList;


/**
 * The AuctionCentralCalendar class creates a single calendar
 * and coordinates auctions and the business rules that
 * govern their scheduling. Calendar named so as not to interfere
 * with java's Calendar class.
 * 
 * @author Gabrielle Glynn and Stepan Adespya
 * 
 */
public class CalendarAuctionCentral {
	
	/**
	 * Variable futureAuctions specifies the number of 
	 * auctions currently scheduled. 
	 */
	private int futureAuctions;
	
	/**
	 * Variable auctionList keeps track of all auctions
	 * overtime, either past or present.
	 */
	private ArrayList<Auction> auctionList;
	
	/**
	 * Variable futureAuctionList keeps track of 
	 * the auctions scheduled into the future.
	 */
	ArrayList<Auction> futureAuctionList;


	/**
	 * Constants specified by Auction Central business rules.
	 */
	private static int MAX_FUTURE_AUCTIONS = 25;
	private static int MAX_DAYS_OUT = 90;
	private static int MAX_AUCTIONS_ROLLING_PERIOD = 5;
	private static int MAX_AUCTIONS_SAME_DAY = 2;
	private static int MAX_HOURS_BTW_AUCTIONS = 2;
	private static int MAX_NP_AUCTIONS_PER_YEAR = 1;
	private static int DAYS_PER_YEAR = 365;
	private static int DAYS_PER_ROLLING_PERIOD = 7;

	/**
	 * Makes class CalendarAuctionCentral a singleton.
	 */
	private static CalendarAuctionCentral sCalendar;

	public static CalendarAuctionCentral getCalendar() throws ParseException {
		if (sCalendar == null)
			sCalendar = new CalendarAuctionCentral();
		return sCalendar;
	}

	/**
	 * Constructor, currently reading auctions from existing file.
	 * NOTE: NEED TO IMPLEMENT OBJECT SERIALIZATION.
	 */
	public CalendarAuctionCentral()  {
		auctionList = new ArrayList<Auction>();
		//auctionList = readAuctionsFromFile("auctionList.ser");

		futureAuctionList = new ArrayList<Auction>();

		for (Auction auction : auctionList) {
			Date now = new Date();
			try {
				if (now.before(auction.getAuctionStart())) {
					futureAuctionList.add(auction);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		futureAuctions = futureAuctionList.size();
	}
	

	/**
	 * Sets the number of scheduled Auctions. Used primarily for testing.
	 * @param auctions
	 */
	public void setFutureAuctions() {
		this.futureAuctions = this.futureAuctionList.size();
	}	

	/**
	 * @return number of scheduled auctions.
	 */
	public int getFutureAuctions() {
		return this.futureAuctions;
	}

	/**
	 * Returns a list of all auctions.
	 * @return ArrayList of all auctions.
	 */
	public ArrayList<Auction> getAuctionList() {
		return this.auctionList;
	}	

	/**
	 * 
	 * Return array list of future auctions
	 * @return
	 */
	public ArrayList<Auction> getFutureAuctionList() {
		return this.futureAuctionList;
	}
	
	/**
	 * 
	 * @return
	 * @throws ParseException 
	 */
	public void setFutureAuctionList() throws ParseException {
		ArrayList<Auction> newFutureAuctionList = new ArrayList<Auction>();
		for (Auction auction : this.auctionList) {
			Date now = new Date();
			if (now.before(auction.getAuctionStart())) {				
				newFutureAuctionList.add(auction);
			} 
		}
		this.futureAuctionList = newFutureAuctionList;
		this.setFutureAuctions();
	}

	/**
	 * 
	 * @param nonProfitOrganization
	 * @return
	 */
	public Auction getAuction(String nonProfitOrganization) {
		for (Auction auction : auctionList) {
			if (auction.getNonProfitName()
					.equalsIgnoreCase(nonProfitOrganization)) {
				return auction;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param auctionToEdit
	 * @param newDate
	 * @throws ParseException
	 * @throws IOException
	 */
	public void editAuctionDate(Auction auctionToEdit, Date newDate) throws ParseException {
		for (Auction auction : auctionList) {
			if (auction.getAuctionName()
					.equalsIgnoreCase(auctionToEdit.getAuctionName())) {
				auction.setAuctionStart(newDate);
				auction.resetAuctionEnd();
			}
		}
	}
	
	/**
	 * 
	 * @param auctionToEdit
	 * @param newDuration
	 * @throws IOException
	 * @throws ParseException
	 */
	public void editAuctionDuration(Auction auctionToEdit, int newDuration) throws ParseException {
		for (Auction auction : auctionList) {
			if (auction.getAuctionName()
					.equalsIgnoreCase(auctionToEdit.getAuctionName())) {
				auction.setAuctionDuration(newDuration);
				auction.resetAuctionEnd();
			}
		}
	}


	/**
	 * 
	 * @param reqAuction
	 * @throws IOException
	 * @throws ParseException
	 */
	public void addFutureAuction(Auction reqAuction) throws ParseException {
		if (checkRequestedAuction(reqAuction)) {
			futureAuctionList.add(reqAuction);
			futureAuctions += 1;
		}
	}


	/**
	 * check if auction meets requirements, true mean accepted, false means denied
	 * @param reqAuction
	 * @return
	 * @throws ParseException
	 */
	public boolean checkRequestedAuction(Auction reqAuction) throws ParseException {
		int numberOfFails = 0;
		
		if (atMaxFutureAuctions()) {
			System.out.println("Requested auction cannot be added; the maximum "
					+ MAX_FUTURE_AUCTIONS + " future auctions have already been scheduled.");
			numberOfFails++;
		}
		
		if (atMaxAuctionPerNonProfitPerYear(reqAuction.getNonProfitName(),
				reqAuction.getAuctionStart())) {
			System.out.println("Requested auction cannot be added; " + reqAuction.getNonProfitName() 
					+ " may only schedule " + MAX_NP_AUCTIONS_PER_YEAR + " auction per year.");
			numberOfFails++;
		}
		
		if (!inDateRange(reqAuction.getAuctionStart())) {
			System.out.println("Requested auction cannot be added; the date is not within " 
					+ MAX_DAYS_OUT + " days from current date.");
			numberOfFails++;
		}
			
		if (atMaxAuctionsPerDay(reqAuction.getAuctionStart())) {
			System.out.println("Requested auction cannot be added; no more than " 
					+ MAX_AUCTIONS_SAME_DAY + " auctions may be scheduled on the same day.");
			numberOfFails++;
		}
		
		if (atMaxAuctionsInRollingPeriod(reqAuction.getAuctionStart())) {
			System.out.println("Requested auction cannot be added; no more than " 
					+ MAX_AUCTIONS_ROLLING_PERIOD + " may be added to any 7-day rolling period.");
			numberOfFails++;
		}
		
		if (is2HoursBetween(reqAuction)) {
			System.out.println("Requested auction cannot be added; there must be at least "
					+ MAX_HOURS_BTW_AUCTIONS + " between the end of one auction and the start" 
					+ "of another.");
			numberOfFails++;
		}
		
		return numberOfFails == 0;
	}

	/**
	 * add auction passed to it without any error checking; used in function
	 * that add past auctions to an arraylist
	 * @param auctionList
	 * @param newAuction
	 * @param fileName
	 * @throws IOException
	 */
	public void addAuction(ArrayList<Auction> auctionList, Auction newAuction) {
		auctionList.add(newAuction);
	}

	/**
	 * no more than 5 auctions in a rolling 7 day period
	 * @param requestedDate
	 * @return
	 * @throws ParseException
	 */
	public boolean atMaxAuctionsInRollingPeriod(Date requestedDate) throws ParseException {
		requestedDate.addDays(-(DAYS_PER_ROLLING_PERIOD - 1));
		for (int i = 0; i < DAYS_PER_ROLLING_PERIOD; i++) {
			if(atMaxAuctionsInRollingPeriodHelper(requestedDate)) {
				return true;
			}
			requestedDate.addDays(1);
		}		
		return false;
	}
	
	public boolean atMaxAuctionsInRollingPeriodHelper(Date requestedDate) throws ParseException {
		int counter = 0;
		for (int i = 0; i < DAYS_PER_ROLLING_PERIOD; i++) {
			counter += countAuctionsOnDay(requestedDate);
			requestedDate.addDays(1);
		}		
		return counter >= MAX_AUCTIONS_ROLLING_PERIOD;
	}
	
	/**
	 * 
	 * @param requestedDate
	 * @return the number of auctions that start on a specified date
	 * @throws ParseException 
	 */
	public int countAuctionsOnDay(Date requestedDate) throws ParseException {
		int counter = 0;
		for (Auction auction : auctionList) {
			if (auction.getAuctionStart().getYear() == (requestedDate.getYear())
					&& auction.getAuctionStart().getMonth() == (requestedDate.getMonth())
					&& auction.getAuctionStart().getDay() == (requestedDate.getDay())) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * no more than 25 auctions may be scheduled into the future
	 * @return
	 */
	public boolean atMaxFutureAuctions() {
		return (futureAuctions >= MAX_FUTURE_AUCTIONS);
	}

	/**
	 * an auction may not be more than 90 days from the current date
	 * @param requestedDate
	 * @return
	 * @throws ParseException
	 */
	public boolean inDateRange(Date requestedDate) throws ParseException {
		Date currentDate = new Date();
		Date inMaxDaysFromCurrentDate = new Date();
		inMaxDaysFromCurrentDate.addDays(MAX_DAYS_OUT);
		return currentDate.before(requestedDate)
				&& !(inMaxDaysFromCurrentDate.before(requestedDate));
	}

	/**
	 * no more than 2 auctions in the same day
	 * @param requestedDate
	 * @return
	 * @throws ParseException
	 */
	public boolean atMaxAuctionsPerDay(Date requestedDate) throws ParseException {
		int auctionOnRequestedDate = 0;
		for (Auction auction : futureAuctionList) {
			if (auction.getAuctionStart().getMonth() == requestedDate.getMonth()
					&& auction.getAuctionStart().getDay() == requestedDate.getDay()
					&& auction.getAuctionStart().getYear() == requestedDate.getYear()) {
				auctionOnRequestedDate++;
			}
		}
		return (auctionOnRequestedDate >= MAX_AUCTIONS_SAME_DAY);
	}

	/**
	 * start time of 2nd auction/day must be two hours after end time of he
	 * first
	 * @param requestedDate
	 * @return
	 * @throws ParseException
	 */
	public boolean is2HoursBetween(Auction requestedAuction) throws ParseException {
		int index = this.oneAuctionforDay(requestedAuction);
		if (index == -1) {
			return false;
		} else if (requestedAuction.getAuctionStart().before(futureAuctionList.get(index)
					.getAuctionEnd())) {
			return (requestedAuction.getAuctionEnd().getDiffHours(futureAuctionList.get(index)
					.getAuctionEnd()) < MAX_HOURS_BTW_AUCTIONS);
		} else {
			return (futureAuctionList.get(index).getAuctionEnd()
					.getDiffHours(requestedAuction.getAuctionStart()) < MAX_HOURS_BTW_AUCTIONS);
		}
	}

	/**
	 * returns the index of an auction if it is scheduled on the same date as the 
	 * requested Date of a new auction; if not returns -1
	 * @param requestedDate
	 * @return
	 * @throws ParseException
	 */
	public int oneAuctionforDay(Auction requestedAuction) throws ParseException {
		for (int j = 0; j < futureAuctionList.size(); j++) {
			if (futureAuctionList.get(j).getAuctionStart().getMonth() == requestedAuction.getAuctionStart().getMonth()
					&& futureAuctionList.get(j).getAuctionStart().getDay() == requestedAuction.getAuctionStart().getDay()
					&& futureAuctionList.get(j).getAuctionStart().getYear() == requestedAuction.getAuctionStart().getYear()) {
				return j;
			}
		}
		return -1;
	}

	/**
	 * no more than one auction per year per nonprofit organization can be
	 * scheduled
	 * @param nonprofit
	 * @param requestedDate
	 * @return
	 * @throws ParseException
	 */
	public boolean atMaxAuctionPerNonProfitPerYear(String nonProfitOrganization, Date requestedDate)
			throws ParseException {
		int auctionsWithinLastYear = 0;
		for (Auction auction : auctionList) {
			if (auction.getNonProfitName().equalsIgnoreCase(nonProfitOrganization) 
					&& auction.getAuctionEnd().getDiffDay(requestedDate) < DAYS_PER_YEAR) {
				auctionsWithinLastYear++;
			}
		}
		return (auctionsWithinLastYear >= MAX_NP_AUCTIONS_PER_YEAR);
	}
}