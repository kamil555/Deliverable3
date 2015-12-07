package main;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This is inventory class for all items.
 * 
 * @author Stepan Adespya
 * @since November 9, 2015
 * @edited by Gabrielle 11/15/2015
 * @edited by Mindy Huynh 12/5/2015
 */
public class Inventory
{
	// Array list of all the items
	/**
	 * This is the ArrayList of all the items.
	 */
	public ArrayList<Item> listofItems;
	// size of the array list
	/**
	 * This is the size of the inventory.
	 */
	int size;
	
	String fileName = "Inventory.ser";
	
	/**
	 * This is the constructor for Inventory, reads log file of items.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public Inventory() throws ClassNotFoundException, IOException
	{
		listofItems = new ArrayList<Item>();
		readFile(fileName);
		size = listofItems.size();
	}
	
	/**
	 * This is the getter for the size.
	 * 
	 * @throws IOException
	 */
	public int getSize()
	{
		return this.size;
	}
	
	/**
	 * This method adds a item to the Array list.
	 * 
	 * @param item the item to be added
	 * @throws IOException
	 */
	public void addItem(Item item) throws IOException
	{
		listofItems.add(item);
		size = listofItems.size();
		writeToFile(fileName);
	}
	
	/**
	 * This method edits the Name of the Item in Array list and rewrites file.
	 * 
	 * @param itemID the ID of the item.
	 * @param newName the name of the item.
	 * @throws IOException
	 */
	public void editItemName(int itemID, String newName) throws IOException
	{
		for (int i = 0; i < listofItems.size(); i++)
		{
			if (listofItems.get(i).getItemID() == itemID)
			{
				listofItems.get(i).setItemName(newName);
				writeToFile(fileName);
			}
		}
		
	}
	
	/**
	 * This method edits the Starting Bid of the item selected.
	 * 
	 * @param itemID the ID of the item.
	 * @param startBid the starting bid of the item.
	 * @throws IOException
	 */
	public void editItemStartBid(int itemID, double startBid)
			throws IOException
	{
		for (int i = 0; i < listofItems.size(); i++)
		{
			if (listofItems.get(i).getItemID() == itemID)
			{
				listofItems.get(i).setStartBid(startBid);
				writeToFile(fileName);
			}
		}
		
	}
	
	/**
	 * This method edits the Information of an item selected.
	 * 
	 * @param itemID the Id of the item.
	 * @param info the information on the item.
	 * @throws IOException
	 */
	public void editItemInfo(int itemID, String info) throws IOException
	{
		for (int i = 0; i < listofItems.size(); i++)
		{
			if (listofItems.get(i).getItemID() == itemID)
			{
				listofItems.get(i).setItemInfo(info);
				writeToFile(fileName);
			}
		}
		
	}

	/**
	 * Shows all the items in a selected auction and returns how many. NOTE:
	 * Separate functions?
	 * 
	 * @param a
	 */
	public String allItemsAuction(Auction a)
	{
		String auctionItems = "";
		for (int i = 0; i < listofItems.size(); i++)
		{
			if (listofItems.get(i).getAuctionName().equals(a.getAuctionName()))
			{
				auctionItems += listofItems.get(i).toString() + "\n";
			}
		}
		return auctionItems;
	}
	
	/**
	 * Shows all the items the user has bided on.
	 * 
	 * @param u
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public void allItemsBidder(User u) throws IOException, ClassNotFoundException
	{
		BidList b = new BidList();
		for (int i = 0; i < b.Bidlist.size(); i++)
		{
			if (b.Bidlist.get(i).getuserName().endsWith(u.getUserName()))
			{
				System.out.println(b.Bidlist.get(i).toString());
			}
		}
	}
	
	/**
	 * shows all the items in the array list.
	 */
	public void viewAllitems()
	{
		int i = 0;
		for (Item itm : this.listofItems)
		{
			i++;
			System.out.println(i + ") " + itm.toString());
		}
	}
	
	/**
	 * Gives the item from id number.
	 * 
	 * @param ID
	 * @return
	 */
	public Item getItemFromList(int ID)
	{
		for (Item itm : this.listofItems)
		{
			if (itm.getItemID() == ID)
				return itm;
		}
		return null;
	}
	
	/**
	 * Reads all the items from a file.
	 * 
	 * @param fileName
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	private void readFile(String fileName) throws IOException, ClassNotFoundException
	{
		FileInputStream fileIn = new FileInputStream(fileName);
		try
		{
			ObjectInputStream in = new ObjectInputStream(fileIn);
			listofItems = (ArrayList<Item>) in.readObject();
			in.close();
		} catch (EOFException e)
		{
			listofItems = new ArrayList<Item>();
		}
		fileIn.close();
	}
	
	/**
	 * Writes in the file chosen(use for logs)
	 * 
	 * @param fileName
	 * @param contents
	 * @throws IOException
	 */
	private void writeToFile(String fileName)
			throws IOException
	{
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(listofItems);
		out.close();
		fileOut.close();
	}
}