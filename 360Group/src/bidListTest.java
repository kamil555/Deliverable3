import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class bidListTest
{
	
	private static final double TOLERANCE = .0001;
	
	private bidList bidlist;
	private User user;
	private Item item;
	private Bid bid;
	
	@Before
	public void setUp() throws Exception
	{
		
		bidlist = new bidList();
		
		user = new User("Han", "User");
		// item = new Item(1.00, "cake","just an cake");
		bid = new Bid(user.userName, 0, 4.2);
		
	}
	
	@Test
	public void testBidList()
	{
		assertTrue(bidlist.Bidlist != null);
	}
	
	@Test
	public void testAddBid() throws Exception
	{
		// bidlist.addBid(user, item, 4.2);
		assertEquals("add name fail", "Han", user.userName);
		assertEquals("add ID fail", 123, item.itemID);
		assertEquals("add Amount fail", 4.2, bid.getBidAmount(), TOLERANCE);
	}
	
	@Test
	public void testEditBid() throws Exception
	{
		// bidlist.editBid(user, item, 5.5, 123);
		assertEquals("edit name fail", "Han", user.userName);
		assertEquals("add ID fail", 123, item.itemID);
		assertEquals("edit Amount fail", 5.5, bid.getBidAmount(), TOLERANCE);
	}
	
	@Test
	public void testCancelBid()
	{
		fail("Not yet implemented");
	}
	
	@Test
	public void testIsWinBid()
	{
		bidlist.isWinBid(item);
		assertEquals("error", "userName is:Han Amount is:4.2", bidlist
				.isWinBid(item).toString());
	}
	
}
