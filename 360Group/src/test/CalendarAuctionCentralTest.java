package test;

import static org.junit.Assert.*;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;

import main.Auction;
import main.CalendarAuctionCentral;
import main.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Gabrielle Glynn
 * @since December 5, 2015
 */
public class CalendarAuctionCentralTest {
	CalendarAuctionCentral myCalendar;
	
	Auction pastAuction1;
	Auction futureAuction1;
	Auction futureAuction2;
	Auction futureAuction3;
	Auction futureAuction4;
	
	Auction AuctionRollingTest1;
	Auction AuctionRollingTest2;
	Auction AuctionRollingTest3;
	Auction AuctionRollingTest4;
	Auction AuctionRollingTest5;
	Auction AuctionRollingTest6;
	Auction AuctionRollingTest7;
	Auction AuctionRollingTest8;
	Auction AuctionRollingTest9;
	Auction AuctionRollingTest10;
	Auction AuctionRollingTest11;
	Auction AuctionRollingTest12;
	Auction AuctionRollingTest13;
	
	ArrayList<Auction> temperaryAuctionList;
	ArrayList<Auction> pastAuctionsOnlyList;
	ArrayList<Auction> futureAuctionsOnlyList;
	ArrayList<Auction> bothPastAndFutureAuctionsList;
	ArrayList<Auction> bothPastAndFutureAuctionsList2;
	ArrayList<Auction> noAuctions;
	ArrayList<Auction> oneAuctionOnDay;
	ArrayList<Auction> twoAuctionsOnDay;
	ArrayList<Auction> RollingPeriodHelperTestArray;

	@Before
	public void setUp() throws Exception {

		// myCalendar = new CalendarAuctionCentral();

		String NPNameTest1 = "NPNAMETEST1";
		Date pastDateTest1 = new Date("06/02/2014 5:00:00");
		int pastDurationTest1 = 2;
		pastAuction1 = new Auction(NPNameTest1, pastDateTest1,
				pastDurationTest1);

		String NPNameTest2 = "NPNAMETEST2";
		Date futureDateTest1 = new Date("02/02/2016 5:00:00");
		int futureDurationTest1 = 2;
		futureAuction1 = new Auction(NPNameTest2, futureDateTest1,
				futureDurationTest1);

		String NPNameTest3 = "NPNAMETEST3";
		Date futureDateTest2 = new Date("02/01/2016 5:00:00");
		int futureDurationTest2 = 2;
		futureAuction2 = new Auction(NPNameTest3, futureDateTest2,
				futureDurationTest2);

		String NPNameTest4 = "NPNAMETEST4";
		Date futureDateTest3 = new Date("01/20/2016 5:00:00");
		int futureDurationTest3 = 2;
		futureAuction3 = new Auction(NPNameTest4, futureDateTest3,
				futureDurationTest3);

		String NPNameTest5 = "NPNAMETEST5";
		Date futureDateTest4 = new Date("01/20/2016 14:00:00");
		int futureDurationTest4 = 2;
		futureAuction4 = new Auction(NPNameTest5, futureDateTest4,
				futureDurationTest4);

		String NPNameRollingTest1 = "NPNAMERHTEST1";
		Date DateRollingTest1 = new Date("01/21/2016 14:00:00");
		int DurationRollingTest1 = 2;
		AuctionRollingTest1 = new Auction(NPNameRollingTest1, DateRollingTest1,
				DurationRollingTest1);

		String NPNameRollingTest2 = "NPNAMERHTEST2";
		Date DateRollingTest2 = new Date("01/22/2016 14:00:00");
		int DurationRollingTest2 = 2;
		AuctionRollingTest2 = new Auction(NPNameRollingTest2, DateRollingTest2,
				DurationRollingTest2);

		String NPNameRollingTest3 = "NPNAMERHTEST3";
		Date DateRollingTest3 = new Date("01/23/2016 14:00:00");
		int DurationRollingTest3 = 2;
		AuctionRollingTest3 = new Auction(NPNameRollingTest3, DateRollingTest3,
				DurationRollingTest3);

		String NPNameRollingTest4 = "NPNAMERHTEST4";
		Date DateRollingTest4 = new Date("01/24/2016 14:00:00");
		int DurationRollingTest4 = 2;
		AuctionRollingTest4 = new Auction(NPNameRollingTest4, DateRollingTest4,
				DurationRollingTest4);

		String NPNameRollingTest5 = "NPNAMERHTEST5";
		Date DateRollingTest5 = new Date("01/25/2016 14:00:00");
		int DurationRollingTest5 = 2;
		AuctionRollingTest5 = new Auction(NPNameRollingTest5, DateRollingTest5,
				DurationRollingTest5);

		String NPNameRollingTest6 = "NPNAMERHTEST6";
		Date DateRollingTest6 = new Date("01/26/2016 14:00:00");
		int DurationRollingTest6 = 2;
		AuctionRollingTest6 = new Auction(NPNameRollingTest6, DateRollingTest6,
				DurationRollingTest6);

		String NPNameRollingTest7 = "NPNAMERHTEST7";
		Date DateRollingTest7 = new Date("01/27/2016 14:00:00");
		int DurationRollingTest7 = 2;
		AuctionRollingTest7 = new Auction(NPNameRollingTest7, DateRollingTest7,
				DurationRollingTest7);

		String NPNameRollingTest8 = "NPNAMERHTEST8";
		Date DateRollingTest8 = new Date("01/28/2016 14:00:00");
		int DurationRollingTest8 = 2;
		AuctionRollingTest8 = new Auction(NPNameRollingTest8, DateRollingTest8,
				DurationRollingTest8);

		String NPNameRollingTest9 = "NPNAMERHTEST9";
		Date DateRollingTest9 = new Date("01/29/2016 14:00:00");
		int DurationRollingTest9 = 2;
		AuctionRollingTest9 = new Auction(NPNameRollingTest9, DateRollingTest9,
				DurationRollingTest9);

		String NPNameRollingTest10 = "NPNAMERHTEST10";
		Date DateRollingTest10 = new Date("01/30/2016 14:00:00");
		int DurationRollingTest10 = 2;
		AuctionRollingTest10 = new Auction(NPNameRollingTest10, DateRollingTest10,
				DurationRollingTest10);

		String NPNameRollingTest11 = "NPNAMERHTEST11";
		Date DateRollingTest11 = new Date("01/31/2016 14:00:00");
		int DurationRollingTest11 = 2;
		AuctionRollingTest11 = new Auction(NPNameRollingTest11, DateRollingTest11,
				DurationRollingTest11);

		String NPNameRollingTest12 = "NPNAMERHTEST12";
		Date DateRollingTest12 = new Date("02/01/2016 14:00:00");
		int DurationRollingTest12 = 2;
		AuctionRollingTest12 = new Auction(NPNameRollingTest12, DateRollingTest12,
				DurationRollingTest12);

		String NPNameRollingTest13 = "NPNAMERHTEST13";
		Date DateRollingTest13 = new Date("02/02/2016 14:00:00");
		int DurationRollingTest13 = 2;
		AuctionRollingTest13 = new Auction(NPNameRollingTest13, DateRollingTest13,
				DurationRollingTest13);

		pastAuctionsOnlyList = new ArrayList<Auction>();
		pastAuctionsOnlyList.add(pastAuction1);

		futureAuctionsOnlyList = new ArrayList<Auction>();
		futureAuctionsOnlyList.add(futureAuction1);

		bothPastAndFutureAuctionsList = new ArrayList<Auction>();
		bothPastAndFutureAuctionsList.add(pastAuction1);
		bothPastAndFutureAuctionsList.add(futureAuction1);

		bothPastAndFutureAuctionsList2 = new ArrayList<Auction>();
		bothPastAndFutureAuctionsList2.add(pastAuction1);
		bothPastAndFutureAuctionsList2.add(futureAuction1);
		bothPastAndFutureAuctionsList2.add(futureAuction2);

		noAuctions = new ArrayList<Auction>();

		oneAuctionOnDay = new ArrayList<Auction>();
		oneAuctionOnDay.add(futureAuction3);

		twoAuctionsOnDay = new ArrayList<Auction>();
		twoAuctionsOnDay.add(futureAuction3);
		twoAuctionsOnDay.add(futureAuction4);

	}


	/**
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 */
	@Test
	public void testCalendarConstructorOnEmptyAuctionListFile()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		myCalendar = new CalendarAuctionCentral();

		assertTrue(myCalendar.getAuctionList().isEmpty());
		assertTrue(myCalendar.getFutureAuctionList().isEmpty());
		assertEquals(0, myCalendar.getFutureAuctions());
		
		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 */
	@Test
	public void testCalendarConstructorOnAuctionListFileWithPastAuctionsOnly()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", pastAuctionsOnlyList);
		myCalendar = new CalendarAuctionCentral();

		assertFalse(myCalendar.getAuctionList().isEmpty());
		assertTrue(myCalendar.getFutureAuctionList().isEmpty());
		assertEquals(0, myCalendar.getFutureAuctions());

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testCalendarConstructorOnAuctionListFileWithFutureAuctionsOnly()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", futureAuctionsOnlyList);
		myCalendar = new CalendarAuctionCentral();

		assertFalse(myCalendar.getAuctionList().isEmpty());
		assertFalse(myCalendar.getFutureAuctionList().isEmpty());
		assertEquals(futureAuctionsOnlyList.size(),
				myCalendar.getFutureAuctions());

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testCalendarConstructorOnAuctionListFileWithBothPastAndFutureAuctions()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList);
		myCalendar = new CalendarAuctionCentral();

		assertFalse(myCalendar.getAuctionList().isEmpty());
		assertFalse(myCalendar.getFutureAuctionList().isEmpty());
		assertEquals(myCalendar.getFutureAuctionList().size(),
				myCalendar.getFutureAuctions());
		assertEquals(bothPastAndFutureAuctionsList.size(), myCalendar
				.getAuctionList().size());

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testGetAuctionOnAuctionListWithNonProfitName()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList);
		myCalendar = new CalendarAuctionCentral();
		
		assertTrue(pastAuction1.toString().equals(myCalendar.getAuction(pastAuction1.getNonProfitName()).toString()));
		assertTrue(futureAuction1.toString().equals(myCalendar.getAuction(futureAuction1.getNonProfitName()).toString()));
		
		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testGetAuctionOnAuctionListWithoutNonProfitName()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList);
		myCalendar = new CalendarAuctionCentral();

		assertNull(myCalendar.getAuction("NonExistentNPName"));

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
//	@Test
//	public void testEditAuctionDateWithValidNewDate()
//			throws IOException {
//		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
//		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList2);
//		myCalendar = new CalendarAuctionCentral();
//
//		myCalendar.editAuctionDate(futureAuction2, new Date("01/03/2016 5:00:00"));
//		
//		//TODO
////		System.out.println(myCalendar.getAuctionList());
////		System.out.println(myCalendar.getFutureAuctionList());
//
//		restoreFileContents("Auctions.ser", temperaryAuctionList);
//		temperaryAuctionList = null;
//		myCalendar = null;
//		
//		
//	}

//	/**
//	 * @throws IOException
//	 * 
//	 */
//	@Test
//	public void testEditAuctionDateWithInvalidNewDate()
//			throws IOException {
//		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
//		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList2);
//		myCalendar = new CalendarAuctionCentral();
//
//		try {
//			myCalendar.editAuctionDate(futureAuction2, new Date("01/03/2017 5:00:00"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		//TODO
//		System.out.println(myCalendar.getAuctionList());
//		System.out.println(myCalendar.getFutureAuctionList());
//
//		restoreFileContents("Auctions.ser", temperaryAuctionList);
//		temperaryAuctionList = null;
//		myCalendar = null;
//	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testAddFutureAuctionOnPastAuction()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList);
		myCalendar = new CalendarAuctionCentral();

		//TODO		

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testAddFutureAuctionOnGoodFutureAuction()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList);
		myCalendar = new CalendarAuctionCentral();

		//TODO		

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testAddFutureAuctionOnBadFutureAuction()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList);
		myCalendar = new CalendarAuctionCentral();

		//TODO

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testCountAuctiionsOnDayOnDayWithZeroAuctions()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", noAuctions);
		myCalendar = new CalendarAuctionCentral();

		assertEquals(myCalendar.getAuctionList().size(), noAuctions.size());

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testCountAuctiionsOnDayOnDayWithOneAuction()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", oneAuctionOnDay);
		myCalendar = new CalendarAuctionCentral();

		assertEquals(myCalendar.getAuctionList().size(), oneAuctionOnDay.size());

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testCountAuctiionsOnDayOnDayWithTwoAuctions()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", twoAuctionsOnDay);
		myCalendar = new CalendarAuctionCentral();

		assertEquals(myCalendar.getAuctionList().size(), twoAuctionsOnDay.size());

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * @throws ParseException 
	 * 
	 */
	@Test
	public void testAtMaxAuctionsInRollingPeriodHelperOnOneAuctionInPeriodAtBeginning()
			throws IOException, ParseException {
		ArrayList<Auction> OneAuctionInPeriodAtBeginning = new ArrayList<Auction>();
		OneAuctionInPeriodAtBeginning.add(AuctionRollingTest1);
		
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", OneAuctionInPeriodAtBeginning);
		myCalendar = new CalendarAuctionCentral();
		
		assertFalse(myCalendar.atMaxAuctionsInRollingPeriodHelper(new Date("01/21/2016 14:00:00")));

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * @throws ParseException 
	 * 
	 */
	@Test
	public void testAtMaxAuctionsInRollingPeriodHelperOnOneAuctionInPeriodAtEnd()
			throws IOException, ParseException {
		ArrayList<Auction> OneAuctionInPeriodAtEnd = new ArrayList<Auction>();
		OneAuctionInPeriodAtEnd.add(AuctionRollingTest7);
		
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", OneAuctionInPeriodAtEnd);
		myCalendar = new CalendarAuctionCentral();
		
		assertFalse(myCalendar.atMaxAuctionsInRollingPeriodHelper(new Date("01/21/2016 14:00:00")));

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * @throws ParseException 
	 * 
	 */
	@Test
	public void testAtMaxAuctionsInRollingPeriodHelperOnTwoAuctionsAtEachEnd()
			throws IOException, ParseException {
		ArrayList<Auction> TwoAuctionsAtEachEnd = new ArrayList<Auction>();
		TwoAuctionsAtEachEnd.add(AuctionRollingTest1);
		TwoAuctionsAtEachEnd.add(AuctionRollingTest2);
		TwoAuctionsAtEachEnd.add(AuctionRollingTest6);
		TwoAuctionsAtEachEnd.add(AuctionRollingTest7);
		
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", TwoAuctionsAtEachEnd);
		myCalendar = new CalendarAuctionCentral();
		
		assertFalse(myCalendar.atMaxAuctionsInRollingPeriodHelper(new Date("01/21/2016 14:00:00")));

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * @throws ParseException 
	 * 
	 */
	@Test
	public void testAtMaxAuctionsInRollingPeriodHelperOnFiveAuctions()
			throws IOException, ParseException {
		ArrayList<Auction> FiveAuctionsPerPeriod = new ArrayList<Auction>();
		FiveAuctionsPerPeriod.add(AuctionRollingTest1);
		FiveAuctionsPerPeriod.add(AuctionRollingTest2);
		FiveAuctionsPerPeriod.add(AuctionRollingTest4);
		FiveAuctionsPerPeriod.add(AuctionRollingTest6);
		FiveAuctionsPerPeriod.add(AuctionRollingTest7);
		
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", FiveAuctionsPerPeriod);
		myCalendar = new CalendarAuctionCentral();
		
		assertTrue(myCalendar.atMaxAuctionsInRollingPeriodHelper(new Date("01/21/2016 14:00:00")));

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * @throws ParseException 
	 * 
	 */
	@Test
	public void testAtMaxAuctionsInRollingPeriodOnOneAuctionAtBeginning()
			throws IOException, ParseException {
		ArrayList<Auction> OneAuctionAtBeginning = new ArrayList<Auction>();
		OneAuctionAtBeginning.add(AuctionRollingTest1);
		
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", OneAuctionAtBeginning);
		myCalendar = new CalendarAuctionCentral();
		
		assertFalse(myCalendar.atMaxAuctionsInRollingPeriodHelper(new Date("01/27/2016 14:00:00")));

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * @throws ParseException 
	 * 
	 */
	@Test
	public void testAtMaxAuctionsInRollingPeriodOnOneAuctionAtEnd()
			throws IOException, ParseException {
		ArrayList<Auction> OneAuctionAtEnd = new ArrayList<Auction>();
		OneAuctionAtEnd.add(AuctionRollingTest13);
		
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", OneAuctionAtEnd);
		myCalendar = new CalendarAuctionCentral();
		
		assertFalse(myCalendar.atMaxAuctionsInRollingPeriod(new Date("01/27/2016 14:00:00")));

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * @throws ParseException 
	 * 
	 */
	@Test
	public void testAtMaxAuctionsInRollingPeriodOnTwoAuctionsAtEachEnd()
			throws IOException, ParseException {
		ArrayList<Auction> TwoAuctionsAtEachEnd = new ArrayList<Auction>();
		TwoAuctionsAtEachEnd.add(AuctionRollingTest1);
		TwoAuctionsAtEachEnd.add(AuctionRollingTest2);
		TwoAuctionsAtEachEnd.add(AuctionRollingTest12);
		TwoAuctionsAtEachEnd.add(AuctionRollingTest13);
		
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", TwoAuctionsAtEachEnd);
		myCalendar = new CalendarAuctionCentral();
		
		assertFalse(myCalendar.atMaxAuctionsInRollingPeriod(new Date("01/27/2016 14:00:00")));

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * @throws ParseException 
	 * 
	 */
	@Test
	public void testAtMaxAuctionsInRollingPeriodOnFourAuctionsAtEachEnd()
			throws IOException, ParseException {
		ArrayList<Auction> FourAuctionsAtEachEnd = new ArrayList<Auction>();
		FourAuctionsAtEachEnd.add(AuctionRollingTest1);
		FourAuctionsAtEachEnd.add(AuctionRollingTest2);
		FourAuctionsAtEachEnd.add(AuctionRollingTest3);
		FourAuctionsAtEachEnd.add(AuctionRollingTest4);
		FourAuctionsAtEachEnd.add(AuctionRollingTest10);
		FourAuctionsAtEachEnd.add(AuctionRollingTest11);
		FourAuctionsAtEachEnd.add(AuctionRollingTest12);
		FourAuctionsAtEachEnd.add(AuctionRollingTest13);
		
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", FourAuctionsAtEachEnd);
		myCalendar = new CalendarAuctionCentral();
		
		assertFalse(myCalendar.atMaxAuctionsInRollingPeriod(new Date("01/27/2016 14:00:00")));

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * @throws ParseException 
	 * 
	 */
	@Test
	public void testAtMaxAuctionsInRollingPeriodOnFourAuctionsAtBegAndFiveAtEnd()
			throws IOException, ParseException {
		ArrayList<Auction> FourAuctionsAtBegAndFiveAtEnd = new ArrayList<Auction>();
		FourAuctionsAtBegAndFiveAtEnd.add(AuctionRollingTest1);
		FourAuctionsAtBegAndFiveAtEnd.add(AuctionRollingTest2);
		FourAuctionsAtBegAndFiveAtEnd.add(AuctionRollingTest3);
		FourAuctionsAtBegAndFiveAtEnd.add(AuctionRollingTest4);
		FourAuctionsAtBegAndFiveAtEnd.add(AuctionRollingTest9);
		FourAuctionsAtBegAndFiveAtEnd.add(AuctionRollingTest10);
		FourAuctionsAtBegAndFiveAtEnd.add(AuctionRollingTest11);
		FourAuctionsAtBegAndFiveAtEnd.add(AuctionRollingTest12);
		FourAuctionsAtBegAndFiveAtEnd.add(AuctionRollingTest13);
		
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", FourAuctionsAtBegAndFiveAtEnd);
		myCalendar = new CalendarAuctionCentral();
		
		assertTrue(myCalendar.atMaxAuctionsInRollingPeriod(new Date("01/27/2016 14:00:00")));

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * @throws ParseException 
	 * 
	 */
	@Test
	public void testAtMaxAuctionsInRollingPeriodOnFourAuctionsAtEndAndFiveAtBeg()
			throws IOException, ParseException {
		ArrayList<Auction> FourAuctionsAtEndAndFiveAtBeg = new ArrayList<Auction>();
		FourAuctionsAtEndAndFiveAtBeg.add(AuctionRollingTest1);
		FourAuctionsAtEndAndFiveAtBeg.add(AuctionRollingTest2);
		FourAuctionsAtEndAndFiveAtBeg.add(AuctionRollingTest3);
		FourAuctionsAtEndAndFiveAtBeg.add(AuctionRollingTest4);
		FourAuctionsAtEndAndFiveAtBeg.add(AuctionRollingTest5);
		FourAuctionsAtEndAndFiveAtBeg.add(AuctionRollingTest10);
		FourAuctionsAtEndAndFiveAtBeg.add(AuctionRollingTest11);
		FourAuctionsAtEndAndFiveAtBeg.add(AuctionRollingTest12);
		FourAuctionsAtEndAndFiveAtBeg.add(AuctionRollingTest13);
		
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", FourAuctionsAtEndAndFiveAtBeg);
		myCalendar = new CalendarAuctionCentral();
		
		assertTrue(myCalendar.atMaxAuctionsInRollingPeriod(new Date("01/27/2016 14:00:00")));

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * @throws ParseException 
	 * 
	 */
	@Test
	public void testAtMaxAuctionsFutureAuctionsOnNoFutureAuctions()
			throws IOException, ParseException {
		
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", pastAuctionsOnlyList);
		CalendarAuctionCentral calendarWithNoFutureAuctions = new CalendarAuctionCentral();
		
		assertFalse(calendarWithNoFutureAuctions.atMaxFutureAuctions());

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		calendarWithNoFutureAuctions = null;
	}

	/**
	 * @throws IOException
	 * @throws ParseException 
	 * 
	 */
	@Test
	public void testAtMaxAuctionsFutureAuctionsOnOneFutureAuction()
			throws IOException, ParseException {		
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList);
		CalendarAuctionCentral calendarWithLessThanMaxFutureAuctions = new CalendarAuctionCentral();
		
		assertFalse(calendarWithLessThanMaxFutureAuctions.atMaxFutureAuctions());

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		calendarWithLessThanMaxFutureAuctions = null;
	}

//	/**
//	 * @throws IOException
//	 * @throws ParseException 
//	 * NOTFINISHED
//	 */
//	@Test
//	public void testAtMaxAuctionsFutureAuctionsOnMaxFutureAuctions()
//			throws IOException, ParseException {		
//		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
//		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList);
//		CalendarAuctionCentral calendarWithMaxFutureAuctions = new CalendarAuctionCentral();
//		
//		assertFalse(calendarWithMaxFutureAuctions.atMaxFutureAuctions());
//
//		restoreFileContents("Auctions.ser", temperaryAuctionList);
//		temperaryAuctionList = null;
//		calendarWithMaxFutureAuctions = null;
//	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Auction> deserializeAuctions(String fileName)
			throws IOException {
		ArrayList<Auction> auctionList = null;
		FileInputStream fileIn = new FileInputStream(fileName);
		try {
			ObjectInputStream in = new ObjectInputStream(fileIn);
			try {
				auctionList = (ArrayList<Auction>) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			in.close();
		} catch (EOFException e) {
			auctionList = new ArrayList<Auction>();
		}
		fileIn.close();
		return auctionList;
	}

	/**
	 * 
	 * @param fileName
	 * @param auctionList
	 * @throws IOException
	 */
	public void serializeAuctions(String fileName,
			ArrayList<Auction> auctionList) throws IOException {

		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(auctionList);
		out.close();
		fileOut.close();
	}

	/**
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void deleteFileContents(String fileName) throws IOException {
		FileOutputStream file = new FileOutputStream(fileName);
		file.close();
	}
	
	/**
	 * 
	 * @param fileName
	 * @return 
	 * @throws IOException
	 */
	public ArrayList<Auction> storeFileContentsAndClearFile(String fileName) throws IOException {
		ArrayList<Auction> auctionList = deserializeAuctions(fileName);
		deleteFileContents(fileName);
		return auctionList;
	}
	
	/**
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void restoreFileContents(String fileName, ArrayList<Auction> auctionList) throws IOException {
		deleteFileContents(fileName);
		serializeAuctions(fileName, auctionList);
	}

}
