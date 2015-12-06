package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Inventory class for all items.
 * 
 * @author Stepan Adespya
 * @since November 9, 2015
 * @edited by Gabrielle 11/15/2015
 */
public class Inventory implements java.io.Serializable
{
	// Array list of all the items
	public ArrayList<Item> listofItems;
	// size of the array list
	int size;
	
	/**
	 * Constructor for Inventory, reads log file of items.
	 */
	public Inventory()
	{
		listofItems = new ArrayList<Item>();
		readFile("Inventory.txt");
		size = listofItems.size();
	}
	
	/**
	 * Gives the size of Array list.
	 * 
	 * @param i
	 * @throws IOException
	 */
	public int getSize()
	{
		return this.size;
	}
	
	/**
	 * Adds a item to the Array list.
	 * 
	 * @param i
	 * @throws IOException
	 */
	public void addItem(Item i) throws IOException
	{
		listofItems.add(i);
		size = listofItems.size();
		writeToFile("Inventory.txt", i.toString());
	}
	
	/**
	 * Edits the Name of the Item in Array list and rewrites file.
	 * 
	 * @param itemID
	 * @param newName
	 * @throws IOException
	 */
	public void editItemName(int itemID, String newName) throws IOException
	{
		for (int i = 0; i < listofItems.size(); i++)
		{
			if (listofItems.get(i).getItemID() == itemID)
			{
				listofItems.get(i).setItemName(newName);
				writeAllItemsToFile("Inventory.txt");
			}
		}
		
	}
	
	/**
	 * Edits the Starting Bid of the item selected.
	 * 
	 * @param itemID
	 * @param startBid
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
				writeAllItemsToFile("Inventory.txt");
			}
		}
		
	}
	
	/**
	 * Edits the Information of an item selected.
	 * 
	 * @param itemID
	 * @param info
	 * @throws IOException
	 */
	public void editItemInfo(int itemID, String info) throws IOException
	{
		for (int i = 0; i < listofItems.size(); i++)
		{
			if (listofItems.get(i).getItemID() == itemID)
			{
				listofItems.get(i).setItemInfo(info);
				writeAllItemsToFile("Inventory.txt");
			}
		}
		
	}
	
	/**
	 * writes all the items in the array list to file.
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	private void writeAllItemsToFile(String fileName) throws IOException
	{
		clearFile(fileName);
		for (int i = 0; i < listofItems.size(); i++)
		{
			writeToFile(fileName, listofItems.get(i).toString());
		}
		
	}
	
	/**
	 * clears a file, if needed.
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	private void clearFile(String fileName) throws IOException
	{
		FileWriter fw = new FileWriter(fileName);
		PrintWriter pw = new PrintWriter(fw);
		pw.print("");
		pw.close();
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
	 */
	public void allItemsBidder(User u) throws IOException
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
	 */
	private void readFile(String fileName)
	{
		String line = null;
		try
		{
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);
			
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while ((line = bufferedReader.readLine()) != null)
			{
				String[] split = line.split(",", 5);
				String sitemID = split[0];
				int itemID = Integer.parseInt(sitemID);
				String auctionName = split[1];
				String start = split[2];
				double startbid = Double.parseDouble(start);
				String itemName = split[3];
				String itemInfo = split[4];
				listofItems.add(new Item(itemID, auctionName, startbid,
						itemName, itemInfo));
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex)
		{
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex)
		{
			System.out.println("Error reading file '" + fileName + "'");
		}
	}
	
	/**
	 * Writes in the file chosen(use for logs)
	 * 
	 * @param fileName
	 * @param contents
	 * @throws IOException
	 */
	private void writeToFile(String fileName, String contents)
			throws IOException
	{
		FileWriter fw = new FileWriter(fileName, true);
		PrintWriter pw = new PrintWriter(fw);
		if (Files.size(Paths.get(fileName)) == 0)
		{
			pw.write(contents);
		} else
		{
			pw.write("\r\n" + contents);
		}
		pw.close();
	}
}