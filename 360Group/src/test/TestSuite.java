package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
   AuctionTest.class,
   BidListTest.class,
   BidTest.class,
   CalendarAuctionCentralTest.class,
   DateTest.class,
   InventoryTest.class,
   ItemTest.class
})
public class TestSuite {   
}  