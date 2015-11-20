package Test;
import Main.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import Main.AuctionCentralEmployee;
import Main.User;

/**
 * 
 */

/**
 * @author Stepan Adespya
 * @since November 16, 2015
 */
public class AuctionCentralEmployeeTest {

	User u;
	AuctionCentralEmployee ace;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		u = new User("Stepan", "AuctionCentral Employee");
		
	}

	/**
	 * Test method for {@link AuctionCentralEmployee#AuctionCentralEmployee(User)}.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@Test
	public void testAuctionCentralEmployee() throws ParseException, IOException {
		ace = new AuctionCentralEmployee(u);
		ace.viewAuctionDetails(u);
		ace.viewCalendar(u, 11, 2015);
		assertNotNull(ace);
		assertNotNull(u);
		assertEquals("Stepan", u.userName);
		assertEquals("AuctionCentral Employee",u.user);
	}

	/**
	 * Test method for {@link AuctionCentralEmployee#viewAuctionDetails(User)}.
	 */
	@Test
	public void testViewAuctionDetails() {
		assertEquals("Stepan", u.userName);
		assertEquals("AuctionCentral Employee",u.user);
	}

	/**
	 * Test method for {@link AuctionCentralEmployee#viewCalendar(User, int, int)}.
	 */
	@Test
	public void testViewCalendar() {
		assertEquals("Stepan", u.userName);
		assertEquals("AuctionCentral Employee",u.user);
	}

}
