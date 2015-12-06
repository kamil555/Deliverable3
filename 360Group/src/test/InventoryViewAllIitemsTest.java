package test;

import static org.junit.Assert.*;

import java.text.ParseException;

import main.Inventory;
import main.Item;

import org.junit.Before;
import org.junit.Test;

public class InventoryViewAllIitemsTest
{
	
	private Inventory myInventory;
	private Item myItem1;
	private Item myItem2;
	private Item myItem3;
	private Item myItem4;
	
	@Before
	public void setUp() throws Exception
	{
		
		// setting up empty inventory list
		myInventory = new Inventory();
		
		myItem1 = new Item("NPName1-November-22-2015", 5.00, "Item1", "Description of item1");
		myItem2 = new Item("NPName1-November-22-2015", 5.00, "Item2", "Description of item2");
		myItem3 = new Item("NPName2-November-22-2015", 5.00, "Item3", "Description of item3");
		myItem4 = new Item("NPName3-November-22-2015", 5.00, "Item4", "Description of item4");
		
		myInventory.addItem(myItem1);
		myInventory.addItem(myItem2);
		myInventory.addItem(myItem3);
		myInventory.addItem(myItem4);
	}
	
	/*
	 * Note: "Inventory.txt" must be cleared and empty before running the test.
	 */
	@Test
	public void testAllAuctionItems() throws ParseException
	{
		// verify in console and text file
		myInventory.viewAllitems();
	}
	
}