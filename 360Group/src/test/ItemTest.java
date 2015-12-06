package test;
import static org.junit.Assert.assertTrue;
import main.Item;

import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author kamil
 *
 */
public class ItemTest {
	Item item;

	/**
	 * test set up before class 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		item = new Item(123, "cakesale", 1.0, "cake","just a cake");
	}
	/**
	 * test tostring 
	 */
	@Test
	public void testToString() {
		System.out.println(item.toString());
		assertTrue(item.toString().equalsIgnoreCase("123,cakesale,1.0,cake,just a cake"));		
	}
	/**
	 * test getter for start bid
	 */
	@Test
	public void testGetStartBid() {
		assertTrue(item.getStartBid() == 1.0);
	}
	/**
	 * test setter for start bid
	 */
	@Test
	public void testSetStartBid() {
		item.setStartBid(2.2);
		assertTrue(item.getStartBid() == 2.2);
	}
	/**
	 * test getter for Item ID
	 */
	@Test
	public void testGetItemID() {
		assertTrue(item.getItemID() ==123);
	}
	/**
	 * test setter for Item ID
	 */
	@Test
	public void testSetItemID() {
		item.setItemID(234);
		assertTrue(item.getItemID() == 234);
	}
	/**
	 * test getter for Item name
	 */
	@Test
	public void testGetItemName() {
		assertTrue(item.getItemName().equalsIgnoreCase("cake"));
	}
	/**
	 * test setter for Item name
	 */
	@Test
	public void testSetItemName() {
		item.setItemName("pizza");
		assertTrue(item.getItemName().equalsIgnoreCase("pizza"));
	}
	
	/**
	 * test getter for Item information 
	 */
	@Test
	public void testGetItemInfo() {
		assertTrue(item.getItemInfo().equalsIgnoreCase("just a cake"));
	}
	/**
	 * test setter for item information
	 */
	@Test
	public void testSetItemInfo() {
		item.setItemInfo("it's a pizza");
		assertTrue(item.getItemInfo().equalsIgnoreCase("it's a pizza"));
	}
}
