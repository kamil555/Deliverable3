import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;


public class CalendarAuctionCentralTest {

	CalendarAuctionCentral myCalendar;
	
	@Before
	public void setUp() throws Exception {
		myCalendar = new CalendarAuctionCentral();	
	}

	@Test
	public void testaddFutureAuction() throws ParseException, IOException {
		Auction testAuc1 = new Auction("npName1", new Date("11/16/2015 16:00:00"), 2);
		//assertTrue(myCalendar.addFutureAuction(testAuc1));
	}
}

