package test;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;


public class AuctionTest
{
	Auction auction;
	@Before
	public void setUp() throws Exception
	{
		Date day = new Date("06/02/2015 5:00:00");
		auction = new Auction("Auction", day, 2);
	}
	
	@Test
	public void test()
	{ 
//		System.out.println(auction.getAuctionName());
//		assertEquals("AuctionName", "Auction-June-2-2015", auction.getAuctionName());
	}
	
	/**
	 * The resetAuctionName method is supposed to be called at every single change.
	 * Testing if the auction name gets reset after non profit name is changed.
	 * @throws ParseException 
	 */
	@Test
	public void testResetAuctionNameChangeName() throws ParseException
	{
		//Make sure the name before is correct.
		assertEquals("AuctionName", "Auction-June-2-2015", auction.getAuctionName());
		auction.setNonProfitName("Mindy");
		assertEquals("New AuctionName", "Mindy-June-2-2015", auction.getAuctionName());
	}
	
	/**
	 * The resetAuctionName method is supposed to be called at every single change
	 * Testing if the auction name gets reset after start date is changed.
	 * @throws ParseException 
	 */
	@Test
	public void testResetAuctionNameChangeDate() throws ParseException
	{
		//Make sure the name before is correct.
		assertEquals("AuctionName", "Auction-June-2-2015", auction.getAuctionName());
		
		//change month
		Date day = new Date("12/02/2015 5:00:00");
		auction.setAuctionStart(day);
		assertEquals("New start month", "Auction-December-2-2015", auction.getAuctionName());
		
		//change date
		day = new Date("12/8/2015 5:00:00");
		auction.setAuctionStart(day);
		//System.out.println(auction.getAuctionName());
		assertEquals("New start year", "Auction-December-8-2015", auction.getAuctionName());
	}
	
	@Test
	public void testResetAuctionEndChangeDuration()
	{
		//Checking to make sure it ends right at first.
		Date endDate = new Date("06/02/2015 7:00:00");
		
		//Weird format
		System.out.println(auction.getAuctionEnd());
		System.out.println(endDate);
		
		assertEquals("Original Duration", auction.getAuctionEnd(), endDate);
	}
	
	
}
