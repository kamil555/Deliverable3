package main;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * The AuctionCentralCalendar class creates a single calendar and coordinates
 * auctions and the business rules that govern their scheduling. Calendar named
 * so as not to interfere with java's Calendar class.
 * 
 * @author Gabrielle Glynn and Stepan Adespya
 * @edited by Mindy Huynh 12/5/2015
 */
public class CalendarAuctionCentral
{
	
	/**
	 * The variable futureAuctions specifies the number of auctions currently
	 * scheduled.
	 */
	private int futureAuctions;
	
	/**
	 * The variable auctionList keeps track of all auctions overtime, either past or
	 * present.
	 */
	private ArrayList<Auction> auctionList;
	
	/**
	 * The variable futureAuctionList keeps track of the auctions scheduled into the
	 * future.
	 */
	private ArrayList<Auction> futureAuctionList;
	
	/**
	 * The constant specified by Auction Central business rules.
	 * The maximum number of auctions MAX_DAYS_OUT days into the future.
	 */
	private static int MAX_FUTURE_AUCTIONS = 25;
	
	/**
	 * The constant specified by Auction Central business rules.
	 * The number of days into the future in which an auction can be scheduled.
	 */
	private static int MAX_DAYS_OUT = 90;
	
	/**
	 * The constant specified by Auction Central business rules.
	 * The maximum number of auction per period.
	 */
	private static int MAX_AUCTIONS_ROLLING_PERIOD = 5;
	
	/**
	 * The constant specified by Auction Central business rules.
	 * The maximum number of auctions in a day.
	 */
	private static int MAX_AUCTIONS_SAME_DAY = 2;
	
	/**
	 * The constant specified by Auction Central business rules.
	 * The maximum number of hours per auction.
	 */
	private static int MAX_HOURS_BTW_AUCTIONS = 2;
	
	/**
	 * The constant specified by Auction Central business rules.
	 * The maximum number of auctions per year.
	 */
	private static int MAX_NP_AUCTIONS_PER_YEAR = 1;
	
	/**
	 * The constant specified by Auction Central business rules.
	 * The number of days per rolling period.
	 */
	private static int DAYS_PER_ROLLING_PERIOD = 7;
	
	/**
	 * The number of days per year.
	 */
	private static int DAYS_PER_YEAR = 365;
	
	/**
	 * The file name being used in this class.
	 */
	private static String FILENAME = "Auctions.ser";
	
	
	/**
	 * This is the constructor.
	 * It is currently reading auctions from an existing file.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public CalendarAuctionCentral()
	{
		
		try
		{
			deserializeAuctions();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		futureAuctionList = new ArrayList<Auction>();
		
		if (!auctionList.isEmpty())
		{
			for (Auction auction : auctionList)
			{
				Date now = new Date();
				try
				{
					if (now.before(auction.getAuctionStart()))
					{
						futureAuctionList.add(auction);
					}
				} catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		futureAuctions = futureAuctionList.size();
	}
	
	/**
	 * Sets the number of scheduled Auctions. Used primarily for testing.
	 * 
	 */
	public void setFutureAuctions()
	{
		futureAuctions = futureAuctionList.size();
	}
	
	/**
	 * This is a getter for futureAuctions.
	 * 
	 * @return futureAuctions the number of scheduled auctions.
	 */
	public int getFutureAuctions()
	{
		return futureAuctions;
	}
	
	/**
	 * This is a getter for auction list
	 * 
	 * @return auctionList list of all auctions.
	 */
	public ArrayList<Auction> getAuctionList()
	{
		return auctionList;
	}
	
	/**
	 * This is the getter for futureAuctionList 
	 * 
	 * @return futureAuctionList a list of future auctions.
	 */
	public ArrayList<Auction> getFutureAuctionList()
	{
		return futureAuctionList;
	}
	
	/**
	 * This is the setter for futureAuctionList.
	 * 
	 * @throws ParseException
	 */
	public void setFutureAuctionList() throws ParseException
	{
		ArrayList<Auction> newFutureAuctionList = new ArrayList<Auction>();
		for (Auction auction : auctionList)
		{
			Date now = new Date();
			if (now.before(auction.getAuctionStart()))
			{
				newFutureAuctionList.add(auction);
			}
		}
		futureAuctionList = newFutureAuctionList;
		setFutureAuctions();
	}
	
	/**
	 * This is the getter for Auction
	 * @param nonProfitOrganization the nonprofit with the scheduled auction.
	 * @return
	 */
	public Auction getAuction(String nonProfitOrganization)
	{
		for (Auction auction : auctionList)
		{
			if (auction.getNonProfitName().equals(
					nonProfitOrganization))
			{
				return auction;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * This is the method to editAuctionDate.
	 * Note, will only edit future auctions.
	 * 
	 * @param auctionToEdit which auction we need to edit.
	 * @param newDate the new date of the auction.
	 * @throws ParseException
	 */
	public void editAuctionDate(Auction auctionToEdit, Date newDate)
			throws ParseException
	{
		Auction originalAuction = null;
		Auction auctionToRemove = null;
		for (Auction auction : futureAuctionList)
		{
			if (auction.toString().equals(auctionToEdit.toString()))
			{
				auctionToRemove = auction;
				originalAuction = auction.clone();
			}
		}
		if (originalAuction == null)
		{
			System.out.println("Auction " + auctionToEdit.getAuctionName()
					+ " not found in scheduled auctions.");
			return;
		}
		
		System.out.println("OA, clone of auction: " + originalAuction);
		
		futureAuctionList.remove(auctionToRemove);
		setFutureAuctions();
		auctionList.remove(auctionToRemove);
		
		auctionToEdit.setAuctionStart(newDate);
		auctionToEdit.resetAuctionEnd();
		auctionToEdit.resetAuctionName();
		
		if (checkRequestedAuction(auctionToEdit))
		{
			addFutureAuction(auctionToEdit);
			System.out
					.println("Auction with new requested date has been added.");
		} else
		{
			addFutureAuction(originalAuction);
			System.out
					.println("Auction with new requested date could not be added.");
		}
	}
	
	/**
	 * This is the method to change auctionDuration.
	 * Note: will only edit future auctions.
	 * 
	 * @param auctionToEdit the auction we want to change the duration to.
	 * @param newDuration the new duration.
	 * @throws ParseException
	 */
	public void editAuctionDuration(Auction auctionToEdit, int newDuration)
			throws ParseException
	{
		Auction originalAuction = null;
		Auction auctionToRemove = null;
		for (Auction auction : futureAuctionList)
		{
			if (auction.toString().equals(auctionToEdit.toString()))
			{
				auctionToRemove = auction;
				originalAuction = auction.clone();
			}
		}
		if (originalAuction == null)
		{
			System.out.println("Auction " + auctionToEdit.getAuctionName()
					+ " not found in scheduled auctions.");
			return;
		}
		
		System.out.println("OA, clone of auction: " + originalAuction);
		
		futureAuctionList.remove(auctionToRemove);
		setFutureAuctions();
		auctionList.remove(auctionToRemove);
		
		auctionToEdit.setAuctionDuration(newDuration);
		auctionToEdit.resetAuctionEnd();
		
		if (checkRequestedAuction(auctionToEdit))
		{
			addFutureAuction(auctionToEdit);
			System.out
					.println("Auction with new duration has been added.");
		} else
		{
			addFutureAuction(originalAuction);
			System.out
					.println("Auction with new duration could not be added.");
		}
	}
	
	/**
	 * This is a method to add more auctions. 
	 * @param reqAuction the auction we want to add.
	 * @throws ParseException
	 */
	public void addFutureAuction(Auction reqAuction) throws ParseException {
		System.out.println(reqAuction);
		auctionList.add(reqAuction);
		futureAuctionList.add(reqAuction);
		try {
			serializeAuctions();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		futureAuctions += 1;
		System.out.println(auctionList);
	}
	
	/**
	 * This is the method that checks if auction meets requirements, true mean accepted, false means
	 * denied
	 * 
	 * @param reqAuction the auction we want to schedule. 
	 * @return numberOfFails how many auctions failed to meet requirements.
	 * @throws ParseException
	 */
	public boolean checkRequestedAuction(Auction reqAuction)
			throws ParseException
	{
		int numberOfFails = 0;
		
		if (atMaxFutureAuctions())
		{
			System.out
					.println("Requested auction cannot be added; the maximum "
							+ MAX_FUTURE_AUCTIONS
							+ " future auctions have already been scheduled.");
			numberOfFails++;
		}
		if (atMaxAuctionPerNonProfitPerYear(reqAuction.getNonProfitName(),
				reqAuction.getAuctionStart()))
		{
			System.out.println("Requested auction cannot be added; "
					+ reqAuction.getNonProfitName() + " may only schedule "
					+ MAX_NP_AUCTIONS_PER_YEAR + " auction per year.");
			numberOfFails++;
		}
		if (!inDateRange(reqAuction.getAuctionStart()))
		{
			System.out
					.println("Requested auction cannot be added; the date is not within "
							+ MAX_DAYS_OUT + " days from current date.");
			numberOfFails++;
		}
		if (atMaxAuctionsPerDay(reqAuction.getAuctionStart()))
		{
			System.out
					.println("Requested auction cannot be added; no more than "
							+ MAX_AUCTIONS_SAME_DAY
							+ " auctions may be scheduled on the same day.");
			numberOfFails++;
		}
		if (atMaxAuctionsInRollingPeriod(reqAuction.getAuctionStart()))
		{
			System.out
					.println("Requested auction cannot be added; no more than "
							+ MAX_AUCTIONS_ROLLING_PERIOD
							+ " may be added to any 7-day rolling period.");
			numberOfFails++;
		}
		if (is2HoursBetween(reqAuction))
		{
			System.out
					.println("Requested auction cannot be added; there must be at least "
							+ MAX_HOURS_BTW_AUCTIONS
							+ " between the end of one auction and the start"
							+ "of another.");
			numberOfFails++;
		}
		
		return numberOfFails == 0;
	}
	
	// /**
	// * add auction passed to it without any error checking; used in function
	// * that add past auctions to an arraylist
	// *
	// * @param auctionList
	// * @param newAuction
	// * @param fileName
	// * @throws IOException
	// */
	// public void addAuction(ArrayList<Auction> auctionList, Auction
	// newAuction) {
	// auctionList.add(newAuction);
	// }
	
	/**
	 * This is the method that checks the business rule of
	 * no more than 5 auctions in a rolling 7 day period
	 * 
	 * @param requestedDate the date at which we want to to schedule the auction
	 * @return if auctioncentral is at its max auctions in a rolling period.
	 * @throws ParseException
	 */
	public boolean atMaxAuctionsInRollingPeriod(Date requestedDate)
			throws ParseException
	{
		requestedDate.addDays(-(DAYS_PER_ROLLING_PERIOD - 1));
		for (int i = 0; i < DAYS_PER_ROLLING_PERIOD; i++)
		{
			if (atMaxAuctionsInRollingPeriodHelper(requestedDate))
			{
				return true;
			}
			requestedDate.addDays(1);
		}
		return false;
	}
	
	public boolean atMaxAuctionsInRollingPeriodHelper(Date begOfPeriodDate)
			throws ParseException
	{
		int counter = 0;
		for (int i = 0; i < DAYS_PER_ROLLING_PERIOD; i++)
		{
			counter += countAuctionsOnDay(begOfPeriodDate);
			begOfPeriodDate.addDays(1);
		}
		return counter >= MAX_AUCTIONS_ROLLING_PERIOD;
	}
	
	/**
	 * This is the method that counts how many auctions within a date. 
	 * @param requestedDate the date the user want to schedule the auction.
	 * @return counter the number of auctions that start on a specified date.
	 * @throws ParseException
	 */
	public int countAuctionsOnDay(Date requestedDate) throws ParseException
	{
		int counter = 0;
		for (Auction auction : auctionList)
		{
			if (auction.getAuctionStart().getYear() == (requestedDate.getYear())
					&& auction.getAuctionStart().getMonth() == (requestedDate
							.getMonth())
					&& auction.getAuctionStart().getDay() == (requestedDate
							.getDay()))
			{
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * This is the method that checks the business rul of
	 * no more than 25 auctions may be scheduled into the future
	 * 
	 * @return if auctionCentral has reached its max future auctions.
	 */
	public boolean atMaxFutureAuctions()
	{
		return (futureAuctions >= MAX_FUTURE_AUCTIONS);
	}
	
	/**
	 * This is the method that checks the business rule of
	 * an auction may not be more than 90 days from the current date
	 * 
	 * @param requestedDate the date the user want to schedule the auction
	 * @return if the date is into the future and if it's within the 90 period.
	 * @throws ParseException
	 */
	public boolean inDateRange(Date requestedDate) throws ParseException
	{
		Date currentDate = new Date();
		Date inMaxDaysFromCurrentDate = new Date();
		inMaxDaysFromCurrentDate.addDays(MAX_DAYS_OUT);
		return currentDate.before(requestedDate)
				&& requestedDate.before(inMaxDaysFromCurrentDate);
	}
	
	/**
	 * This is the method that checks the business rule of
	 * no more than 2 auctions in the same day
	 * 
	 * @param requestedDate the date the user want to schedule the auction
	 * @return if auctionCentral is at its limit number of auctions per day.
	 * @throws ParseException
	 */
	public boolean atMaxAuctionsPerDay(Date requestedDate)
			throws ParseException
	{
		int auctionOnRequestedDate = 0;
		for (Auction auction : futureAuctionList)
		{
			if (auction.getAuctionStart().getMonth() == requestedDate
					.getMonth()
					&& auction.getAuctionStart().getDay() == requestedDate
							.getDay()
					&& auction.getAuctionStart().getYear() == requestedDate
							.getYear())
			{
				auctionOnRequestedDate++;
			}
		}
		return (auctionOnRequestedDate >= MAX_AUCTIONS_SAME_DAY);
	}
	
	/**
	 * This is the method that checks the business rule of
	 * start time of 2nd auction/day must be two hours after end time of he
	 * first
	 * 
	 * @param requestedDate the date the user want to schedule the auction
	 * @return if the scheduled auction is at least 2 hours before and after the next auction.
	 * @throws ParseException
	 */
	public boolean is2HoursBetween(Auction requestedAuction)
			throws ParseException
	{
		int index = oneAuctionforDay(requestedAuction);
		if (index == -1)
		{
			return false;
		} else if (requestedAuction.getAuctionStart().before(
				futureAuctionList.get(index).getAuctionEnd()))
		{
			return (requestedAuction.getAuctionEnd().getDiffHours(
					futureAuctionList.get(index).getAuctionEnd()) < MAX_HOURS_BTW_AUCTIONS);
		} else
		{
			return (futureAuctionList.get(index).getAuctionEnd()
					.getDiffHours(requestedAuction.getAuctionStart()) < MAX_HOURS_BTW_AUCTIONS);
		}
	}
	
	/**
	 * This is the method that checks the business rule of
	 * returns the index of an auction if it is scheduled on the same date as
	 * the requested Date of a new auction; if not returns -1.
	 * 
	 * @param requestedDate the date the user want to schedule the auction
	 * @return checks if there's another auction happening at the same date as requestedDate.
	 * @throws ParseException
	 */
	public int oneAuctionforDay(Auction requestedAuction) throws ParseException
	{
		for (int j = 0; j < futureAuctionList.size(); j++)
		{
			if (futureAuctionList.get(j).getAuctionStart().getMonth() == requestedAuction
					.getAuctionStart().getMonth()
					&& futureAuctionList.get(j).getAuctionStart().getDay() == requestedAuction
							.getAuctionStart().getDay()
					&& futureAuctionList.get(j).getAuctionStart().getYear() == requestedAuction
							.getAuctionStart().getYear())
			{
				return j;
			}
		}
		return -1;
	}
	
	/**
	 * This is the method that checks the business rule of
	 * no more than one auction per year per nonprofit organization can be
	 * scheduled.
	 * 
	 * @param nonprofit the nonprofit we are scheduling this for. 
	 * @param requestedDate the date the user want to schedule the auction
	 * @return if the nonprofit has scheduled another auction within a 365 days a year.
	 * @throws ParseException
	 */
	public boolean atMaxAuctionPerNonProfitPerYear(
			String nonProfitOrganization, Date requestedDate)
			throws ParseException
	{
		int auctionsWithinLastYear = 0;
		for (Auction auction : auctionList)
		{
			if (auction.getNonProfitName().equalsIgnoreCase(
					nonProfitOrganization)
					&& auction.getAuctionEnd().getDiffDay(requestedDate) < DAYS_PER_YEAR)
			{
				auctionsWithinLastYear++;
			}
		}
		return (auctionsWithinLastYear >= MAX_NP_AUCTIONS_PER_YEAR);
	}
	
	/**
	 * This method deserializes auctions.
	 * @throws ClassNotFoundException
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void deserializeAuctions()
			throws IOException
	{
		FileInputStream fileIn = new FileInputStream(FILENAME);
		try
		{
			
			ObjectInputStream in = new ObjectInputStream(fileIn);
			try
			{
				auctionList = (ArrayList<Auction>) in.readObject();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			in.close();
		} catch (EOFException e)
		{
			auctionList = new ArrayList<Auction>();
		}
		fileIn.close();
	}
	
	public void serializeAuctions() throws IOException
	{
		deleteAuctionFileContents();
		FileOutputStream fileOut = new FileOutputStream(FILENAME);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(auctionList);
		out.close();
		fileOut.close();
	}	
	
	/**
	 * This method deletes auction file contents.
	 * @throws IOException
	 */
	public void deleteAuctionFileContents() throws IOException
	{
		FileOutputStream file = new FileOutputStream(FILENAME);
		file.close();
	}
	
}