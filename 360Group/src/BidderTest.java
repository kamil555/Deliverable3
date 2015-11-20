import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Stepan Adespya
 * @since November 16, 2015
 */
public class BidderTest {
	
	User u;
	Bidder b;
	Item i;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		u = new User("Stepan","Bidder");
		i = new Item(200,"Stepan",4.00,"pencil","nice pencil");
	}

	/**
	 * Test method for {@link Bidder#Bidder(User)}.
	 * @throws ParseException 
	 * @throws IOException 
	 */
	@Test
	public void testBidder() throws IOException, ParseException {
		b = new Bidder(u);
		b.viewItem(u, i);
		b.editBid(u, i, 5.0);
		b.viewOpenAuctions(u);
		assertNotNull(b);
		assertNotNull(u);
		assertEquals("Stepan", u.userName);
		assertEquals("Bidder",u.user);
	}

	/**
	 * Test method for {@link Bidder#viewItem(User, Item)}.
	 * @throws ParseException 
	 * @throws IOException 
	 */
	@Test
	public void testViewItem() throws IOException, ParseException {
		assertEquals(200, i.itemID);
		assertEquals("Stepan", i.getAuctionName());
		assertEquals("pencil", i.getItemName());
		assertEquals("nice pencil", i.getItemInfo());
		assertEquals("Stepan", u.userName);
		assertEquals("Bidder",u.user);
	}

	/**
	 * Test method for {@link Bidder#editBid(User, Item, double)}.
	 * @throws IOException 
	 */
	@Test
	public void testEditBid() throws IOException {
		assertEquals(200, i.itemID);
		assertEquals("Stepan", i.getAuctionName());
		assertEquals("pencil", i.getItemName());
		assertEquals("nice pencil", i.getItemInfo());
		assertEquals("Stepan", u.userName);
		assertEquals("Bidder",u.user);
	}

	/**
	 * Test method for {@link Bidder#viewOpenAuctions(User)}.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void testViewOpenAuctions() throws ParseException, IOException {
		assertEquals("Stepan", u.userName);
		assertEquals("Bidder",u.user);
	}

}
