package test;

import static org.junit.Assert.*;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class CalendarAuctionCentralTest
{
	
	/**
	 * Constants specified by Auction Central business rules.
	 */
	private static int MAX_FUTURE_AUCTIONS = 25;
	private static int MAX_DAYS_OUT = 90;
	private static int MAX_AUCTIONS_ROLLING_PERIOD = 5;
	private static int MAX_AUCTIONS_SAME_DAY = 2;
	private static int MAX_HOURS_BTW_AUCTIONS = 2;
	private static int MAX_NP_AUCTIONS_PER_YEAR = 1;
	private static int DAYS_PER_ROLLING_PERIOD = 7;
	private static int DAYS_PER_YEAR = 365;
	private static String FILENAME = "Auctions.ser";
	
	CalendarAuctionCentral myCalendar;
	CalendarAuctionCentral calendarWithEmptyAuctionFile;
	CalendarAuctionCentral calendarWithPastAuctionsOnly;
	CalendarAuctionCentral calendarWithFutureAuctionsOnly;
	CalendarAuctionCentral calendarWithBothPastAndFutureAuctions;
	CalendarAuctionCentral calendarWithOneAuctionOnOneDayAndTwoOnAnother;	
	CalendarAuctionCentral calendarWithOneAuctionInPeriodAtBeginning;
	CalendarAuctionCentral calendarWithOneAuctionInPeriodAtEnd;		
	CalendarAuctionCentral calendarWithTwoAuctionsAtEachEndOfPeriod;
	CalendarAuctionCentral calendarWithOnFiveAuctionsInPeriod;
	CalendarAuctionCentral calendarWithOneAuctionAtBeginningForRollingPeriod;
	CalendarAuctionCentral calendarWithOneAuctionAtEndForRollingPeriod;
	CalendarAuctionCentral calendarWithTwoAuctionsAtEachEndForRollingPeriod;
	CalendarAuctionCentral calendarWithFourAuctionsAtEachEndForRollingPeriod;
	CalendarAuctionCentral calendarWithFourAuctionsAtBegAndFiveAtEndForRollingPeriod;
	CalendarAuctionCentral calendarWithFourAuctionsAtEndAndFiveAtBegForRollingPeriod;
	CalendarAuctionCentral calendarWithLessThanMaxFutureAuctions;
	CalendarAuctionCentral calendarWithNoFutureAuctions;
	CalendarAuctionCentral calendarWithMaxFutureAuctions;	
	
	Auction pastAuction1;
	Auction futureAuction1;
	Auction futureAuction2;
	Auction futureAuction3;
	Auction futureAuction4;
	Auction futureAuction5;
	
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
	ArrayList<Auction> OneAuctionOnOneDayAndTwoOnAnother;
	ArrayList<Auction> RollingPeriodHelperTestArray;
	ArrayList<Auction> maxFutureAuctionsList;
	
	@Before
	public void setUp() throws Exception
	{
		
		// Storing Auction file contents to prevent testing from
		// interfering with Auction Central setup.
		temperaryAuctionList = storeFileContentsAndClearFile();
		
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
		
		// Setup for testCalendarConstructorOnEmptyAuctionListFile()
		deleteFileContents();
		calendarWithEmptyAuctionFile = new CalendarAuctionCentral();
		
		// Setup for testCalendarConstructorOnAuctionListFileWithPastAuctionsOnly()
		String NPNameTest1 = "NPNAMETEST1";
		Date pastDateTest1 = new Date("06/02/2014 5:00:00");
		int pastDurationTest1 = 2;
		pastAuction1 = new Auction(NPNameTest1, pastDateTest1,
				pastDurationTest1);
		pastAuctionsOnlyList = new ArrayList<Auction>();
		pastAuctionsOnlyList.add(pastAuction1);
		serializeAuctions(pastAuctionsOnlyList);
		calendarWithPastAuctionsOnly = new CalendarAuctionCentral();
		
		// Setup for testCalendarConstructorOnAuctionListFileWithFutureAuctionsOnly()
		String NPNameTest2 = "NPNAMETEST2";
		Date futureDateTest1 = new Date("02/02/2016 5:00:00");
		int futureDurationTest1 = 2;
		futureAuction1 = new Auction(NPNameTest2, futureDateTest1,
				futureDurationTest1);		
		futureAuctionsOnlyList = new ArrayList<Auction>();
		futureAuctionsOnlyList.add(futureAuction1);
		serializeAuctions(futureAuctionsOnlyList);
		calendarWithFutureAuctionsOnly = new CalendarAuctionCentral();
		
		// Setup for testCalendarConstructorOnAuctionListFileWithBothPastAndFutureAuctions()
		// and testGetAuctionOnAuctionListWithNonProfitName()
		// and testGetAuctionOnAuctionListWithoutNonProfitName()
		// and testEditAuctionDateWithValidNewDate()
		// and testEditAuctionDateWithInvalidNewDate()
		String NPNameTest3 = "NPNAMETEST3";
		Date futureDateTest2 = new Date("02/01/2016 5:00:00");
		int futureDurationTest2 = 2;
		futureAuction2 = new Auction(NPNameTest3, futureDateTest2,
				futureDurationTest2);
		bothPastAndFutureAuctionsList = new ArrayList<Auction>();
		bothPastAndFutureAuctionsList.add(pastAuction1);
		bothPastAndFutureAuctionsList.add(futureAuction1);
		bothPastAndFutureAuctionsList.add(futureAuction2);
		serializeAuctions(bothPastAndFutureAuctionsList);
		calendarWithBothPastAndFutureAuctions = new CalendarAuctionCentral();

		// Setup for testEditAuctionDurationWithValidNewDuration()
		// and testEditAuctionDurationWithInvalidNewDuration()
		// and testCountAuctiionsOnDayOnDayWithZeroAuctions()
		// and testCountAuctiionsOnDayOnDayWithOneAuction()
		// and testCountAuctiionsOnDayOnDayWithTwoAuctions()
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
		String NPNameTest6 = "NPNAMETEST6";
		Date futureDateTest5 = new Date("01/22/2016 14:00:00");
		int futureDurationTest5 = 2;
		futureAuction5 = new Auction(NPNameTest6, futureDateTest5,
				futureDurationTest5);
		OneAuctionOnOneDayAndTwoOnAnother = new ArrayList<Auction>();
		OneAuctionOnOneDayAndTwoOnAnother.add(futureAuction3);
		OneAuctionOnOneDayAndTwoOnAnother.add(futureAuction4);
		OneAuctionOnOneDayAndTwoOnAnother.add(futureAuction5);
		serializeAuctions(OneAuctionOnOneDayAndTwoOnAnother);
		calendarWithOneAuctionOnOneDayAndTwoOnAnother = new CalendarAuctionCentral();		

		// Setup for testAtMaxAuctionsInRollingPeriodHelperOnOneAuctionInPeriodAtBeginning()
		ArrayList<Auction> OneAuctionInPeriodAtBeginning = new ArrayList<Auction>();
		OneAuctionInPeriodAtBeginning.add(AuctionRollingTest1);
		serializeAuctions(OneAuctionInPeriodAtBeginning);
		calendarWithOneAuctionInPeriodAtBeginning = new CalendarAuctionCentral();
		
		// Setup for testAtMaxAuctionsInRollingPeriodHelperOnOneAuctionInPeriodAtEnd()
		ArrayList<Auction> OneAuctionInPeriodAtEnd = new ArrayList<Auction>();
		OneAuctionInPeriodAtEnd.add(AuctionRollingTest7);
		serializeAuctions(OneAuctionInPeriodAtEnd);
		calendarWithOneAuctionInPeriodAtEnd = new CalendarAuctionCentral();		
		
		// Setup for testAtMaxAuctionsInRollingPeriodHelperOnTwoAuctionsAtEachEnd()
		ArrayList<Auction> TwoAuctionsAtEachEnd = new ArrayList<Auction>();
		TwoAuctionsAtEachEnd.add(AuctionRollingTest1);
		TwoAuctionsAtEachEnd.add(AuctionRollingTest2);
		TwoAuctionsAtEachEnd.add(AuctionRollingTest6);
		TwoAuctionsAtEachEnd.add(AuctionRollingTest7);
		serializeAuctions(TwoAuctionsAtEachEnd);
		calendarWithTwoAuctionsAtEachEndOfPeriod = new CalendarAuctionCentral();

		// Setup for testAtMaxAuctionsInRollingPeriodHelperOnFiveAuctions()
		ArrayList<Auction> FiveAuctionsPerPeriod = new ArrayList<Auction>();
		FiveAuctionsPerPeriod.add(AuctionRollingTest1);
		FiveAuctionsPerPeriod.add(AuctionRollingTest2);
		FiveAuctionsPerPeriod.add(AuctionRollingTest4);
		FiveAuctionsPerPeriod.add(AuctionRollingTest6);
		FiveAuctionsPerPeriod.add(AuctionRollingTest7);
		serializeAuctions(FiveAuctionsPerPeriod);
		calendarWithOnFiveAuctionsInPeriod = new CalendarAuctionCentral();

		// Setup for testAtMaxAuctionsInRollingPeriodOnOneAuctionAtBeginning()
		ArrayList<Auction> OneAuctionAtBeginning = new ArrayList<Auction>();
		OneAuctionAtBeginning.add(AuctionRollingTest1);
		serializeAuctions(OneAuctionAtBeginning);
		calendarWithOneAuctionAtBeginningForRollingPeriod = new CalendarAuctionCentral();
		
		// Setup for testAtMaxAuctionsInRollingPeriodOnOneAuctionAtEnd()
		ArrayList<Auction> OneAuctionAtEnd = new ArrayList<Auction>();
		OneAuctionAtEnd.add(AuctionRollingTest13);
		serializeAuctions(OneAuctionAtEnd);
		calendarWithOneAuctionAtEndForRollingPeriod = new CalendarAuctionCentral();
			
		// Setup for testAtMaxAuctionsInRollingPeriodOnTwoAuctionsAtEachEnd()
		ArrayList<Auction> TwoAuctionsAtEachEndforRollingPeriod = new ArrayList<Auction>();
		TwoAuctionsAtEachEnd.add(AuctionRollingTest1);
		TwoAuctionsAtEachEnd.add(AuctionRollingTest2);
		TwoAuctionsAtEachEnd.add(AuctionRollingTest12);
		TwoAuctionsAtEachEnd.add(AuctionRollingTest13);
		serializeAuctions(TwoAuctionsAtEachEndforRollingPeriod);
		calendarWithTwoAuctionsAtEachEndForRollingPeriod = new CalendarAuctionCentral();
		
		// Setup for testAtMaxAuctionsInRollingPeriodOnFourAuctionsAtEachEnd()
		ArrayList<Auction> FourAuctionsAtEachEnd = new ArrayList<Auction>();
		FourAuctionsAtEachEnd.add(AuctionRollingTest1);
		FourAuctionsAtEachEnd.add(AuctionRollingTest2);
		FourAuctionsAtEachEnd.add(AuctionRollingTest3);
		FourAuctionsAtEachEnd.add(AuctionRollingTest4);
		FourAuctionsAtEachEnd.add(AuctionRollingTest10);
		FourAuctionsAtEachEnd.add(AuctionRollingTest11);
		FourAuctionsAtEachEnd.add(AuctionRollingTest12);
		FourAuctionsAtEachEnd.add(AuctionRollingTest13);
		serializeAuctions(FourAuctionsAtEachEnd);
		calendarWithFourAuctionsAtEachEndForRollingPeriod = new CalendarAuctionCentral();
		
		//Setup for testAtMaxAuctionsInRollingPeriodOnFourAuctionsAtBegAndFiveAtEnd()
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
		serializeAuctions(FourAuctionsAtBegAndFiveAtEnd);
		calendarWithFourAuctionsAtBegAndFiveAtEndForRollingPeriod = new CalendarAuctionCentral();
		
		// Setup for testAtMaxAuctionsInRollingPeriodOnFourAuctionsAtEndAndFiveAtBeg()
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
		serializeAuctions(FourAuctionsAtEndAndFiveAtBeg);
		calendarWithFourAuctionsAtEndAndFiveAtBegForRollingPeriod = new CalendarAuctionCentral();		
		
		// Setup for testAtMaxFutureAuctionsOnMaxFutureAuction()
		serializeAuctions(pastAuctionsOnlyList);
		calendarWithNoFutureAuctions = new CalendarAuctionCentral();

		// Setup for testAtMaxFutureAuctionsOnLessThanMaxFutureAuction()
		serializeAuctions(bothPastAndFutureAuctionsList);
		calendarWithLessThanMaxFutureAuctions = new CalendarAuctionCentral();
		
		// Setup for testAtMaxFutureAuctionsOnMaxFutureAuction()
		maxFutureAuctionsList = new ArrayList<Auction>();
		Date now = new Date();
		int i;
		for (i = 0; i < MAX_FUTURE_AUCTIONS; i++) {
			now.addDays(2);
			maxFutureAuctionsList.add(new Auction("NPNAME" + i, now, 2));
		}
		serializeAuctions(maxFutureAuctionsList);
		calendarWithMaxFutureAuctions = new CalendarAuctionCentral();
		
		// Restoring original contents of Auction file
		restoreFileContents(temperaryAuctionList);		
	}
	
	@Test
	public void testCalendarConstructorOnEmptyAuctionListFile()
			throws IOException
	{
		assertTrue(calendarWithEmptyAuctionFile.getAuctionList().isEmpty());
		assertTrue(calendarWithEmptyAuctionFile.getFutureAuctionList().isEmpty());
		assertEquals(0, calendarWithEmptyAuctionFile.getFutureAuctions());
	}
	
	@Test
	public void testCalendarConstructorOnAuctionListFileWithPastAuctionsOnly()
			throws IOException
	{
		assertFalse(calendarWithPastAuctionsOnly.getAuctionList().isEmpty());
		assertTrue(calendarWithPastAuctionsOnly.getFutureAuctionList().isEmpty());
		assertEquals(0, calendarWithPastAuctionsOnly.getFutureAuctions());
	}
	
	@Test
	public void testCalendarConstructorOnAuctionListFileWithFutureAuctionsOnly()
			throws IOException
	{		
		assertFalse(calendarWithFutureAuctionsOnly.getAuctionList().isEmpty());
		assertFalse(calendarWithFutureAuctionsOnly.getFutureAuctionList().isEmpty());
		assertEquals(futureAuctionsOnlyList.size(),
				calendarWithFutureAuctionsOnly.getFutureAuctions());
	}
	
	@Test
	public void testCalendarConstructorOnAuctionListFileWithBothPastAndFutureAuctions()
			throws IOException
	{
		assertFalse(calendarWithBothPastAndFutureAuctions.getAuctionList().isEmpty());
		assertFalse(calendarWithBothPastAndFutureAuctions.getFutureAuctionList().isEmpty());
		assertEquals(calendarWithBothPastAndFutureAuctions.getFutureAuctionList().size(),
				calendarWithBothPastAndFutureAuctions.getFutureAuctions());
		assertEquals(bothPastAndFutureAuctionsList.size()
				, calendarWithBothPastAndFutureAuctions.getAuctionList().size());
	}
	
	@Test
	public void testGetAuctionOnAuctionListWithNonProfitName()
			throws IOException
	{		
		assertTrue(pastAuction1.toString().equals(
				calendarWithBothPastAndFutureAuctions.getAuction(pastAuction1.getNonProfitName()).toString()));
		assertTrue(futureAuction1.toString().equals(
				calendarWithBothPastAndFutureAuctions.getAuction(futureAuction1.getNonProfitName()).toString()));
	}
	
	@Test
	public void testGetAuctionOnAuctionListWithoutNonProfitName()
			throws IOException
	{
		assertNull(calendarWithBothPastAndFutureAuctions.getAuction("NonExistentNPName"));
	}

	@Test
	public void testEditAuctionDateWithValidNewDate() throws IOException, ParseException {
		
		// additional setup for this test
		Date newDate = new Date("01/03/2016 5:00:00");
		calendarWithBothPastAndFutureAuctions.editAuctionDate(futureAuction2, newDate);
		
		assertEquals(newDate, futureAuction2.getAuctionStart());
	}

	@Test
	public void testEditAuctionDateWithInvalidNewDate() throws IOException,
			ParseException {

		// additional setup for this test
		Date newDate = new Date("01/03/2017 5:00:00");
		Date copyDate = futureAuction1.getAuctionStart().clone();
		calendarWithBothPastAndFutureAuctions.editAuctionDate(futureAuction1,
				newDate);

		assertTrue(copyDate.toString().equals(
				calendarWithBothPastAndFutureAuctions
						.getAuction(futureAuction1.getNonProfitName())
						.getAuctionStart().toString()));
	}
	
	@Test
	public void testEditAuctionDurationWithValidNewDuration() throws IOException, ParseException {
		
		// additional setup for this test
		calendarWithOneAuctionOnOneDayAndTwoOnAnother.editAuctionDuration(futureAuction3, 4);
		Date auctionEnd = new Date("1/20/2016 9:0:0");
		
		assertEquals(4, futureAuction3.getAuctionDuration());
		assertEquals(auctionEnd.toString(), futureAuction3.getAuctionEnd().toString());
	}
	
//	@Test
//	public void testEditAuctionDurationWithInvalidNewDuration() throws IOException,
//			ParseException {
//
//		// additional setup for this test
//		calendarWithTwoFutureAuctionsOnOneDay.editAuctionDuration(futureAuction3, 8);
//		
//		// shows auction duration doesn't change
//		assertEquals(2, futureAuction3.getAuctionDuration());
//	}
//	
//	@Test
//	public void testAddFutureAuctionOnPastAuction()
//			throws IOException
//	{
//		// TODO
//	}
//	
//	@Test
//	public void testAddFutureAuctionOnGoodFutureAuction()
//			throws IOException
//	{
//		// TODO
//	}
//	
//	@Test
//	public void testAddFutureAuctionOnBadFutureAuction()
//			throws IOException
//	{		
//		// TODO
//	}
	
	@Test
	public void testCountAuctiionsOnDayOnDayWithZeroAuctions()
			throws IOException, ParseException
	{			
		assertEquals(0, calendarWithOneAuctionOnOneDayAndTwoOnAnother.countAuctionsOnDay(new Date("12/31/2015 5:00:00")));
	}
	
	@Test
	public void testCountAuctiionsOnDayOnDayWithOneAuction()
			throws IOException, ParseException
	{		
		assertEquals(1, calendarWithOneAuctionOnOneDayAndTwoOnAnother.countAuctionsOnDay(futureAuction5.getAuctionStart()));
	}
	
	@Test
	public void testCountAuctiionsOnDayOnDayWithTwoAuctions()
			throws IOException, ParseException
	{		
		assertEquals(2, calendarWithOneAuctionOnOneDayAndTwoOnAnother.countAuctionsOnDay(futureAuction3.getAuctionStart()));
	}
		
	@Test
	public void testAtMaxAuctionsInRollingPeriodHelperOnOneAuctionInPeriodAtBeginning()
			throws IOException, ParseException
	{
		assertFalse(calendarWithOneAuctionInPeriodAtBeginning.atMaxAuctionsInRollingPeriodHelper(new Date("01/21/2016 14:00:00")));
	}
	
	@Test
	public void testAtMaxAuctionsInRollingPeriodHelperOnOneAuctionInPeriodAtEnd()
			throws IOException, ParseException
	{
		assertFalse(calendarWithOneAuctionInPeriodAtEnd.atMaxAuctionsInRollingPeriodHelper(new Date("01/21/2016 14:00:00")));
	}
	
	@Test
	public void testAtMaxAuctionsInRollingPeriodHelperOnTwoAuctionsAtEachEnd()
			throws IOException, ParseException
	{
		assertFalse(calendarWithTwoAuctionsAtEachEndOfPeriod.atMaxAuctionsInRollingPeriodHelper(new Date("01/21/2016 14:00:00")));
	}
	
	@Test
	public void testAtMaxAuctionsInRollingPeriodHelperOnFiveAuctions()
			throws IOException, ParseException
	{
		assertTrue(calendarWithOnFiveAuctionsInPeriod.atMaxAuctionsInRollingPeriodHelper(new Date("01/21/2016 14:00:00")));
	}
	
	@Test
	public void testAtMaxAuctionsInRollingPeriodOnOneAuctionAtBeginning()
			throws IOException, ParseException
	{
		assertFalse(calendarWithOneAuctionAtBeginningForRollingPeriod.atMaxAuctionsInRollingPeriodHelper(new Date("01/27/2016 14:00:00")));
	}
	
	@Test
	public void testAtMaxAuctionsInRollingPeriodOnOneAuctionAtEnd()
			throws IOException, ParseException
	{
		assertFalse(calendarWithOneAuctionAtEndForRollingPeriod.atMaxAuctionsInRollingPeriod(new Date("01/27/2016 14:00:00")));
	}
	
	@Test
	public void testAtMaxAuctionsInRollingPeriodOnTwoAuctionsAtEachEnd()
			throws IOException, ParseException
	{
		assertFalse(calendarWithTwoAuctionsAtEachEndForRollingPeriod.atMaxAuctionsInRollingPeriod(new Date("01/27/2016 14:00:00")));
	}
	
	@Test
	public void testAtMaxAuctionsInRollingPeriodOnFourAuctionsAtEachEnd()
			throws IOException, ParseException
	{
		assertFalse(calendarWithFourAuctionsAtEachEndForRollingPeriod.atMaxAuctionsInRollingPeriod(new Date("01/27/2016 14:00:00")));
	}
	
	@Test
	public void testAtMaxAuctionsInRollingPeriodOnFourAuctionsAtBegAndFiveAtEnd()
			throws IOException, ParseException
	{		
		assertTrue(calendarWithFourAuctionsAtBegAndFiveAtEndForRollingPeriod.atMaxAuctionsInRollingPeriod(new Date("01/27/2016 14:00:00")));
	}
	
	@Test
	public void testAtMaxAuctionsInRollingPeriodOnFourAuctionsAtEndAndFiveAtBeg() throws ParseException
	{
		assertTrue(calendarWithFourAuctionsAtEndAndFiveAtBegForRollingPeriod.atMaxAuctionsInRollingPeriod(new Date("01/27/2016 14:00:00")));
	}
	
	@Test
	public void testAtMaxFutureAuctionsOnNoFutureAuctions()
			throws IOException, ParseException
	{		
		assertFalse(calendarWithNoFutureAuctions.atMaxFutureAuctions());
	}
	
	@Test
	public void testAtMaxFutureAuctionsOnLessThanMaxFutureAuction()
			throws IOException, ParseException
	{		
		assertFalse(calendarWithLessThanMaxFutureAuctions.atMaxFutureAuctions());
	}
	
	@Test
	public void testAtMaxFutureAuctionsOnMaxFutureAuction()
			throws IOException, ParseException
	{		
		assertEquals(MAX_FUTURE_AUCTIONS, calendarWithMaxFutureAuctions.getFutureAuctionList().size());
		assertTrue(calendarWithMaxFutureAuctions.atMaxFutureAuctions());
	}
	
	@Test
	public void testInDateRangeOnAuctionInPast()
			throws IOException, ParseException
	{		
		
	}
	
	@Test
	public void testInDateRangeOnAuctionInDateRange()
			throws IOException, ParseException
	{		
		
	}
	
	@Test
	public void testInDateRangeOnAuctionInFuture()
			throws IOException, ParseException
	{		
		
	}		
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Auction> deserializeAuctions(String fileName)
			throws IOException
	{
		ArrayList<Auction> auctionList = null;
		FileInputStream fileIn = new FileInputStream(fileName);
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
		return auctionList;
	}
	
	/**
	 * 
	 * @param fileName
	 * @param auctionList
	 * @throws IOException
	 */
	public void serializeAuctions(ArrayList<Auction> auctionList) throws IOException
	{		
		deleteFileContents();
		FileOutputStream fileOut = new FileOutputStream(FILENAME);
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
	public void deleteFileContents() throws IOException
	{
		FileOutputStream file = new FileOutputStream(FILENAME);
		file.close();
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Auction> storeFileContentsAndClearFile() throws IOException
	{
		ArrayList<Auction> auctionList = deserializeAuctions(FILENAME);
		deleteFileContents();
		return auctionList;
	}
	
	/**
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void restoreFileContents(ArrayList<Auction> auctionList)
			throws IOException
	{
		deleteFileContents();
		serializeAuctions(auctionList);
	}
	
}