package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import main.Bid;
import main.BidList;
import main.Item;
import main.User;

import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Han
 *
 */
public class BidListTest {

	private static final double TOLERANCE = .0001;

	private BidList bidlist;
	private User user;
	private Item item;
	private Bid bid;
	//	private Inventory inv;


	/**
	 * set up before test
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		bidlist = new BidList();
		user = new User("Han","User");
		item = new Item(123, "123", 1.0, "cake", "cake");
		bid = new Bid(user.getUserName(), 123, 4.2);
	}
	
	/**
	 * test Bidlist
	 * 
	 */
	@Test
	public void testBidList() {
		assertTrue(bidlist.Bidlist != null);
	}

	/**
	 * test addBid
	 * @throws Exception
	 */
	@Test
	public void testAddBid() throws Exception {
		bidlist.addBid(user, bid);
		assertEquals("add name fail", "Han", user.getUserName());
		assertEquals("add ID fail", 123, item.getItemID());
		assertEquals("add Amount fail", 4.2, bid.getBidAmount(),TOLERANCE);
	}
	/**
	 * test edit bid
	 * @throws Exception
	 */
	@Test
	public void testEditBid() throws Exception {
		assertEquals("edit name fail", "Han", user.getUserName());
		assertEquals("add ID fail", 123, item.getItemID());
		assertEquals("edit Amount fail", 4.2, bid.getBidAmount(),TOLERANCE);
	}
	/**
	 * test cancel bid
	 */
	@Test
	public void testCancelBid() {
		assertTrue(bid.getuserName().equalsIgnoreCase(user.getUserName()));
		assertTrue(bid.getItemID() == item.getItemID());
	}
	/**
	 * test who is winning the bid
	 */
	@Test
	public void testIsWinBid() {
		bidlist.isWinBid(item);
		assertEquals("error", "userName is:Han Amount is:4.2", bidlist.isWinBid(item).toString());
	}

}
