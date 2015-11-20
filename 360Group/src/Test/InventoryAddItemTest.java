package Test;
import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;

import Main.Inventory;
import Main.Item;

/**
 * Testing for inventory class.
 * 
 * @author Gabrielle Glynn
 * @since November 14, 2015
 */
public class InventoryAddItemTest {
	
	private static final double TOLERANCE = .0001;
	
	private Inventory myInventory;
	private Inventory myNewInventory;
	private Item myItem1;
	private Item myItem2;

	@Before
	public void setUp() throws Exception {
		
		// setting up empty inventory list
		myInventory = new Inventory();

		myItem1 = new Item("Auction1Name", 5.00, "Item1", "Description of item1");		
		myItem2 = new Item("Auction1Name", 5.00, "Item2", "Description of item2");
	}

	/*
	 * Note: "Inventory.txt" must be cleared and empty
	 * before running the test.
	 */
	@Test
	public void testAddItem() throws IOException {
		// Testing that size reads zero and that array
		// list is empty since size is set with the
		// array list.
		assertEquals(0, myInventory.getSize());
		
		// Testing size and array are updated.
		myInventory.addItem(myItem1);
		assertEquals(1, myInventory.getSize());

		// Testing size updated again, and contents
		// of the array are as expected.
		myInventory.addItem(myItem2);
		assertEquals(2, myInventory.getSize());
		assertEquals(2, myInventory.listofItems.size());
		for(int i = 0; i < myInventory.listofItems.size(); i++) {
			assertEquals(i+1, myInventory.listofItems.get(i).itemID);
			assertEquals("Auction1Name", myInventory.listofItems.get(i).auctionName);	
			assertEquals(5.00, myInventory.listofItems.get(i).startBid, TOLERANCE);		
		}
		assertEquals("Item1", myInventory.listofItems.get(0).itemName);
		assertEquals("Description of item1", myInventory.listofItems.get(0).itemInfo);
		assertEquals("Item2", myInventory.listofItems.get(1).itemName);
		assertEquals("Description of item2", myInventory.listofItems.get(1).itemInfo);
		
		// testing if new inventory will read the two auctions
		// that were added from myInventory additions. This will
		// show that they were written to "Inventory.txt" when added
		// as well as that the constructor read the file.
		myNewInventory = new Inventory();

		assertEquals(2, myNewInventory.getSize());
		assertEquals(2, myNewInventory.listofItems.size());
		for(int i = 0; i < myNewInventory.listofItems.size(); i++) {
			assertEquals(i+1, myNewInventory.listofItems.get(i).itemID);
			assertEquals("Auction1Name", myNewInventory.listofItems.get(i).auctionName);	
			assertEquals(5.00, myNewInventory.listofItems.get(i).startBid, TOLERANCE);		
		}
		assertEquals("Item1", myNewInventory.listofItems.get(0).itemName);
		assertEquals("Description of item1", myNewInventory.listofItems.get(0).itemInfo);
		assertEquals("Item2", myNewInventory.listofItems.get(1).itemName);
		assertEquals("Description of item2", myNewInventory.listofItems.get(1).itemInfo);

	}

}
