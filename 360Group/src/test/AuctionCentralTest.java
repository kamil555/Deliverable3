package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Gabrielle Glynn
 * @since November 15, 2015
 */
public class CalendarAuctionCentralTest {

	CalendarAuctionCentral myCalendar;
	
	/* 
	 * Make sure auctionList.txt is empty before running this file
	 */
	@Before
	public void setUp() throws Exception {
		myCalendar = new CalendarAuctionCentral();
	}
	
	@Test
	public void testAddFutureAuction() throws ParseException, IOException {
		Auction testAuc1 = new Auction("npName1", new Date("11/20/2015 16:00:00"), 2);
		myCalendar.addFutureAuction(testAuc1);
		assertEquals(1, myCalendar.getAuctionList().size());
		assertEquals(1, myCalendar.getFutureAuctionList().size());
		assertEquals(1, myCalendar.getNumberOfFutureAuctions());
		
		Auction testAuc2 = new Auction("npName2", new Date("11/20/2015 20:00:00"), 2);
		myCalendar.addFutureAuction(testAuc2);
		assertEquals(2, myCalendar.getAuctionList().size());
		assertEquals(2, myCalendar.getFutureAuctionList().size());
		assertEquals(2, myCalendar.getNumberOfFutureAuctions());
		
		Auction testAuc3 = new Auction("npName3", new Date("11/25/2015 20:00:00"), 2);
		myCalendar.addFutureAuction(testAuc3);
		assertEquals(3, myCalendar.getAuctionList().size());
		assertEquals(3, myCalendar.getFutureAuctionList().size());
		assertEquals(3, myCalendar.getNumberOfFutureAuctions());
	}
	

	/* 
	 * testing the oneAuctionforDay method which returns index
	 * of array list with auctions if a requested date is on a day with
	 * at least one other auction, and -1 otherwise
	 */
	@Test
	public void testOneAuctionforDay() throws ParseException, IOException {
		
		Auction testAuc = new Auction("npName1", new Date("11/20/2015 16:00:00"), 2);
		myCalendar.addFutureAuction(testAuc);
		
		// returns correct index if on same day?
		Date reqDate1 = new Date("11/20/2015 16:00:00");
		assertEquals(0, myCalendar.oneAuctionforDay(reqDate1));
		
		// returns -1 if on diff day?
		Date reqDate2 = new Date("11/30/2015 16:00:00");
		assertEquals(-1, myCalendar.oneAuctionforDay(reqDate2));
	}
	
	/* 
	 * testing 
	 */
	@Test
	public void testInDateRange() throws ParseException, IOException {
	
		Date reqDate1 = new Date("02/13/2016 16:00:00");
		assertTrue(myCalendar.inDateRange(reqDate1));
	
		Date reqDate2 = new Date("02/20/2016 16:00:00");
		assertFalse(myCalendar.inDateRange(reqDate2));
	}
	
	/* 
	 * testing 
	 */
	@Test
	public void testOneAuctionPerYear() throws ParseException, IOException {
	
		Auction testPastAucWithinYear = new Auction("npName1", new Date("10/20/2015 16:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(), testPastAucWithinYear, "auctionList.txt");
		Date reqDate1 = new Date("11/20/2015 16:00:00");
		assertTrue(myCalendar.oneAuctionPerYear("npName1", reqDate1));
	
		Auction testPastAucOutsideYear = new Auction("npNameDiff", new Date("10/20/2013 16:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(), testPastAucOutsideYear, "auctionList.txt");
		Date reqDate2 = new Date("11/30/2015 16:00:00");
		assertFalse(myCalendar.oneAuctionPerYear("npNameDiff", reqDate2));
	}
	
	/* 
	 * testing 
	 */
	@Test
	public void testIs2HoursBeforeStart() throws ParseException, IOException {
		Auction testAucA = new Auction("npNameA", new Date("12/20/2015 16:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(), testAucA, "auctionList.txt");
		Date reqDate1 = new Date("12/20/2015 19:00:00");
		assertTrue(myCalendar.is2HoursBeforeStart(reqDate1));
	
		Auction testPastAucOutsideYear = new Auction("npNameB", new Date("12/15/2015 14:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(), testPastAucOutsideYear, "auctionList.txt");
		Date reqDate2 = new Date("12/15/2015 19:00:00");
		assertFalse(myCalendar.is2HoursBeforeStart(reqDate2));
	}
	
	/* 
	 * testing 
	 */
	@Test
	public void testAtMaxAuctionsPerDay() throws ParseException, IOException {
		Auction testAucA1 = new Auction("npNameA1", new Date("12/01/2015 16:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(), testAucA1, "auctionList.txt");
		Auction testAucA2 = new Auction("npNameA2", new Date("12/01/2015 20:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(), testAucA2, "auctionList.txt");
		Date reqDate1 = new Date("12/01/2015 10:00:00");
		assertTrue(myCalendar.atMaxAuctionsPerDay(reqDate1));
	

		Auction testAucB1 = new Auction("npNameB1", new Date("12/05/2015 16:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(), testAucB1, "auctionList.txt");
		Date reqDate2 = new Date("12/05/2015 10:00:00");
		assertFalse(myCalendar.atMaxAuctionsPerDay(reqDate2));
	}	
	
	/*
	 * testing
	 */
	@Test
	public void testAtMaxAuctions7day() throws ParseException, IOException {
		Date req1 = new Date("01/23/2015 16:00:00");
		Date req2 = new Date("01/23/2015 20:00:00");
		assertFalse(myCalendar.atMaxAuctions7day(req1));
		
		Auction newAuc1 = new Auction("NewnpName1", new Date("01/23/2015 16:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(), newAuc1, "auctionList.txt");
		Auction newAuc2 = new Auction("NewnpName2", new Date("01/22/2015 16:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(), newAuc2, "auctionList.txt");
		Auction newAuc3 = new Auction("NewnpName3", new Date("01/24/2015 16:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(), newAuc3, "auctionList.txt");
		Auction newAuc4 = new Auction("NewnpName4", new Date("01/24/2015 20:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(), newAuc4, "auctionList.txt");
		Auction newAuc5 = new Auction("NewnpName5", new Date("01/22/2015 20:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(), newAuc5, "auctionList.txt");
		assertTrue(myCalendar.atMaxAuctions7day(req2));
	}
	
	/* 
	 * testing the addAuction function which should add an auction
	 * to auctionList ArrayList without error checking.
	 * Note: numbers are cumulative in this test so it is looking
	 * at all the auctions added to the text file.
	 */
	@Test
	public void testAddAuction() throws ParseException, IOException {
		Auction testAuc1 = new Auction("npName1", new Date("11/01/2015 16:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(),testAuc1, "auctionList.txt");
		System.out.println(myCalendar.getAuctionList().size());
		System.out.println(myCalendar.getFutureAuctionList().size());
		assertEquals(9, myCalendar.getAuctionList().size());
		assertEquals(6, myCalendar.getFutureAuctionList().size());
		assertEquals(6, myCalendar.getNumberOfFutureAuctions());
		
		Auction testAuc2 = new Auction("npName2", new Date("11/20/2014 20:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(),testAuc2, "auctionList.txt");
		assertEquals(10, myCalendar.getAuctionList().size());
		assertEquals(6, myCalendar.getFutureAuctionList().size());
		assertEquals(6, myCalendar.getNumberOfFutureAuctions());
		
		// This particular function shouldn't update futureAuctionList: confirming
		Auction testAuc3 = new Auction("npName3", new Date("11/26/2015 20:00:00"), 2);
		myCalendar.addAuction(myCalendar.getAuctionList(),testAuc3, "auctionList.txt");
		assertEquals(11, myCalendar.getAuctionList().size());
		assertEquals(6, myCalendar.getFutureAuctionList().size());
		assertEquals(6, myCalendar.getNumberOfFutureAuctions());
	}
	
	/*
	 * we have already verified that the futureAuctions counter is
	 * working, so since we made the calendar a singleton and can't
	 * create different versions we are going to set future auctions 
	 * and test.
	 */
	@Test
	public void atMaxFutureAuctions() throws ParseException, IOException {
		myCalendar.setFutureAuctions(1);
		assertFalse(myCalendar.atMaxFutureAuctions());
		
		myCalendar.setFutureAuctions(25);
		assertTrue(myCalendar.atMaxFutureAuctions());
	}
}


/*
import java.io.IOException;
import java.text.ParseException;

public class CalendarTest
{
	
	public static void main(String[] args) throws ParseException, IOException
	{
//		
//		CalendarAuctionCentral c = new CalendarAuctionCentral();
//		Date d1 = new Date("11/16/2015 02:30:00");
//		Auction a1 = new Auction("Stepan", d1, 3);
//		Date d2 = new Date("11/16/2015 05:45:00");
//		Auction a2 = new Auction("Computer", d2, 2);
//		c.addFutureAuction(a1);
//		c.addFutureAuction(a2);
//		c.printCalendarMonthly(11, 2015);
		
	}
	
}
*/