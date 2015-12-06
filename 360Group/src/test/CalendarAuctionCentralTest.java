package test;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.IOException;
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
	CalendarAuctionCentral myCalendar;
	Auction pastAuctionTest1;
	
	@Before
	public void setUp() throws Exception
	{		
		String NPNameTest1 = "NPNAMETEST1";
		Date pastDateTest1 = new Date("06/02/2016 5:00:00");
		int pastDurationTest1 = 2;
		try {
			Auction pastAuctionTest1 = new Auction(NPNameTest1, pastDateTest1, pastDurationTest1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
	
	/**
	 * 
	 */
	@Test
	public void testCalendarConstructorOnNonExistentAuctionListFile()
	{
		myCalendar = new CalendarAuctionCentral();
		assertTrue(myCalendar.getAuctionList().isEmpty());
		assertTrue(myCalendar.getFutureAuctionList().isEmpty());
		assertEquals(0, myCalendar.getFutureAuctions());
	}		
	
	/**
	 * 
	 */
	@Test
	public void testCalendarConstructorOnEmptyAuctionListFile()
	{
		myCalendar = new CalendarAuctionCentral();
		assertTrue(myCalendar.getAuctionList().isEmpty());
		assertTrue(myCalendar.getFutureAuctionList().isEmpty());
		assertEquals(0, myCalendar.getFutureAuctions());
	}
	
	/**
	 * 
	 */
	@Test
	public void testCalendarConstructorOnAuctionListFileWithPastAuctionsOnly()
	{
		myCalendar = new CalendarAuctionCentral();
		try {
			myCalendar.writeAuctionListToFile("auctions.ser");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertFalse(myCalendar.getAuctionList().isEmpty());
		assertTrue(myCalendar.getFutureAuctionList().isEmpty());
		assertEquals(0, myCalendar.getFutureAuctions());
	}
	
	/**
	 * 
	 */
	@Test
	public void testCalendarConstructorOnAuctionListFileWithFutureAuctionsOnly()
	{
		myCalendar = new CalendarAuctionCentral();
		assertTrue(myCalendar.getAuctionList().isEmpty());
		assertTrue(myCalendar.getFutureAuctionList().isEmpty());
		assertEquals(0, myCalendar.getFutureAuctions());
	}
	
	/**
	 * 
	 */
	@Test
	public void testCalendarConstructorOnAuctionListFileWithBothPastAndFutureAuctions()
	{
		myCalendar = new CalendarAuctionCentral();
		assertTrue(myCalendar.getAuctionList().isEmpty());
		assertTrue(myCalendar.getFutureAuctionList().isEmpty());
		assertEquals(0, myCalendar.getFutureAuctions());
	}

}
