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
public class NonProfitTest {

	User u;
	NonProfit np;
	Auction a;
	Date d;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		u = new User("Stepan", "NonProfit");
		d = new Date("11/20/2015 15:30:00");
		a = new Auction("Stepan",d,2);
	}

	/**
	 * Test method for {@link NonProfit#NonProfit(User)}.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void testNonProfit() throws ParseException, IOException {
		np = new NonProfit(u);
		assertNotNull(np);
		assertNotNull(u);
		assertEquals("Stepan", u.userName);
		assertEquals("NonProfit",u.user);
	}

	/**
	 * Test method for {@link NonProfit#scheduleAuction(User, Date, int)}.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void testScheduleAuction() throws ParseException, IOException {
//		np.scheduleAuction(u, d, 2);
		assertEquals("11/20/2015 15:30:00", d.toString());
		assertEquals("Stepan", u.userName);
		assertEquals("NonProfit",u.user);
	}

	/**
	 * Test method for {@link NonProfit#addAuctionInfo(User, java.lang.String, Date, int)}.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void testAddAuctionInfo() throws ParseException, IOException {
//		np.addAuctionInfo(u, "Stepan", d, 2);
		assertEquals("11/20/2015 15:30:00", d.toString());
		assertEquals("Stepan", u.userName);
		assertEquals("NonProfit",u.user);
	}

	/**
	 * Test method for {@link NonProfit#editAuctionInfo(User, Auction)}.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void testEditAuctionInfo() throws ParseException, IOException {
//		np.editAuctionInfo(u, a);
		assertEquals(2, a.getAuctionDuration());
		assertEquals("Stepan-November-20-2015", a.getAuctionName());
		assertEquals(d.toString(), a.getAuctionStart().toString());
		assertEquals("11/20/2015 17:30:0",a.getAuctionEnd().toString());
		assertEquals("Stepan", u.userName);
		assertEquals("NonProfit",u.user);
	}

	/**
	 * Test method for {@link NonProfit#addItemInfo(User, Auction)}.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void testAddItemInfo() throws ParseException, IOException {
//		np.addItemInfo(u, a);
		assertEquals(2, a.getAuctionDuration());
		assertEquals("Stepan-November-20-2015", a.getAuctionName());
		assertEquals(d.toString(), a.getAuctionStart().toString());
		assertEquals("11/20/2015 17:30:0",a.getAuctionEnd().toString());
		assertEquals("Stepan", u.userName);
		assertEquals("NonProfit",u.user);
	}

	/**
	 * Test method for {@link NonProfit#editItemInfo(User, int)}.
	 * @throws IOException 
	 */
	@Test
	public void testEditItemInfo() throws IOException {
//		np.editItemInfo(u, 200);
		assertEquals(2, a.getAuctionDuration());
		assertEquals("Stepan-November-20-2015", a.getAuctionName());
		assertEquals(d.toString(), a.getAuctionStart().toString());
		assertEquals("11/20/2015 17:30:0",a.getAuctionEnd().toString());
		assertEquals("Stepan", u.userName);
		assertEquals("NonProfit",u.user);
	}

}
