package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import main.Bid;
import main.BidList;
import main.Inventory;
import main.Item;
import main.User;

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
	private Inventory in;
	private Item item;
	private Bid bid;

	/**
	 * test addBid
	 * @throws Exception
	 */
	@Test
	public void testAddBid() throws Exception {
		bidlist = new BidList();
		in = new Inventory();
		user = new User("Han","User");
		item = new Item(123, "123", 1.0, "cake", "cake");
		bid = new Bid(user.getUserName(), 123, 4.2);
		in.addItem(item);
		
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
		bidlist = new BidList();
		in = new Inventory();
		user = new User("Han","User");
		item = new Item(123, "123", 1.0, "cake", "cake");
		bid = new Bid(user.getUserName(), 123, 4.2);
		in.addItem(item);
		
		bidlist.addBid(user, bid);
		bidlist.editBid(user, item, 5.5);
		
		assertEquals("edit Amount fail", "BidList =[Han,123,5.5]", bidlist.toString());
		
	}
	/**
	 * test cancel bid
	 * @throws IOException 
	 */
	@Test
	public void testCancelBid() throws IOException {
		bidlist = new BidList();
		in = new Inventory();
		user = new User("Han","User");
		item = new Item(123, "123", 1.0, "cake", "cake");
		bid = new Bid(user.getUserName(), 123, 4.2);
		in.addItem(item);
		
		bidlist.cancelBid(user, item);
		
		assertTrue(bid.getuserName().equalsIgnoreCase(user.getUserName()));
		assertTrue(bid.getItemID() == item.getItemID());
	}
	/**
	 * test who is winning the bid
	 * 
	 * if there no Bids.txt,  run it again, it will be pass the test
	 * 
	 * @throws IOException 
	 */
	@Test
	public void testIsWinBid() throws IOException {
		bidlist = new BidList();
		in = new Inventory();
		user = new User("Han","User");
		item = new Item(123, "123", 1.0, "cake", "cake");
		bid = new Bid(user.getUserName(), 123, 4.2);
		in.addItem(item);
		bidlist.editBid(user, item, 5.5);
		bidlist.isWinBid(item);
		assertEquals("error", "userName is:Han Amount is:5.5", bidlist.isWinBid(item).toString());
	}

}
