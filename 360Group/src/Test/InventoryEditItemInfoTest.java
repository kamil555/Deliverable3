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
public class InventoryEditItemInfoTest {
	
	private static final double TOLERANCE = .0001;
	
	private Inventory myInventory;

	private Item myItem1;
	private Item myItem2;

	@Before
	public void setUp() throws Exception {
		
		// setting up empty inventory list
		myInventory = new Inventory();

		myItem1 = new Item("Auction1Name", 5.00, "Item1", "Description of item1");		
		myItem2 = new Item("Auction1Name", 5.00, "Item2", "Description of item2");
		
		myInventory.addItem(myItem1);
		myInventory.addItem(myItem2);
	}

	/*
	 * Note: "Inventory.txt" must be cleared and empty
	 * before running the test.
	 */
	@Test
	public void testEditItemStartBid() throws IOException {
		myInventory.editItemInfo(myItem2.getItemID(), "New description of item2");
		assertEquals("New description of item2", myItem2.getItemInfo());
		myInventory.editItemInfo(myItem1.getItemID(), "New description of item1");
		assertEquals("New description of item1", myItem1.getItemInfo());
	
	}
}
