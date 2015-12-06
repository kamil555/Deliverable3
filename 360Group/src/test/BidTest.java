package test;

import static org.junit.Assert.assertTrue;
import main.Bid;

import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Han
 *
 */
public class BidTest {
	Bid b;

	@Before
	/**
	 *  create a new bid
	 * @throws Exception
	 */
	public void setUp() throws Exception {
		
		b = new Bid("Han", 123, 4.2);
	}
	/**
	 * test tostring
	 */
	@Test
	public void testToString() {
		assertTrue(b.toString().equalsIgnoreCase("Han,123,4.2"));
	}
	/**
	 * test getter for item id
	 */
	@Test
	public void testGetItemID() {
		assertTrue(b.getItemID() == 123);
	}
	/**
	 * test setter for item id
	 */
	@Test
	public void testSetItemID() {
		b.setItemID(222);
		assertTrue(b.getItemID() == 222);
	}
	/**
	 * test getter for Bid amount
	 */
	@Test
	public void testGetBidAmount() {
		assertTrue(b.getBidAmount() == 4.2);
	}

	/**
	 * test setter for bid amount
	 */
	
	@Test
	public void testSetBidAmount() {
		b.setBidAmount(5.5);
		assertTrue(b.getBidAmount() == 5.5);
	}
	
	/**
	 * test getter for user's name 
	 */
	@Test
	public void testGetuserName() {
		assertTrue(b.getuserName().equalsIgnoreCase("Han"));
	}
	/**
	 * test setter for user's name 
	 */
	@Test
	public void testSetuserName() {
		b.setuserName("Li");
		assertTrue(b.getuserName().equalsIgnoreCase("Li"));
	}
}
