package main;

import java.io.IOException;

/**
 * This is the item class. 
 * It keeps track of all the items. 
 * @author Han Wang
 * @edited by Mindy Huynh 12/5/2015
 */
public class Item implements java.io.Serializable
{
	/**
	 * The unique item of the item.
	 */
	private int itemID;
	
	/**
	 * The starting bid of the item.
	 */
	private double startBid;
	
	/**
	 * The name of the item.
	 */
	private String itemName;
	
	/**
	 * The description of the item.
	 */
	private String itemInfo;
	
	/**
	 * The name of the auction that the item is in.
	 */
	private String auctionName;
	
	/**
	 * This is the constructor for the item class.
	 * 
	 * @param auctionName the name of the auction.
	 * @param startBid the start bid of the item.
	 * @param itemName the name of the item.
	 * @param itemInfo the item's description.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public Item(String auctionName, double startBid, String itemName, String itemInfo) throws ClassNotFoundException, IOException
	{
		// super();
		this.auctionName = auctionName;
		this.itemID = getID();
		this.startBid = startBid;
		this.itemName = itemName;
		this.itemInfo = itemInfo;
	}
	
	/**
	 * This is the second constructor for the item class.
	 * 
	 * @param itemID the ID of the item.
	 * @param auctionName the name of the auction.
	 * @param startBid the start bid of the item.
	 * @param itemName the name of the item.
	 * @param itemInfo the item's description.
	 */
	public Item(int itemID, String auctionName, double startBid, String itemName, String itemInfo)
	{
		// super();
		this.itemID = itemID;
		this.auctionName = auctionName;
		this.startBid = startBid;
		this.itemName = itemName;
		this.itemInfo = itemInfo;
	}
	
	/**
	 * This is the getter for auction name
	 * @return the name of the auction.
	 */
	public String getAuctionName()
	{
		return auctionName;
	}
	
	/**
	 * This is the setter for the auction name
	 * @param auctionName the new name of the auction.
	 */
	public void setAuctionName(String auctionName)
	{
		this.auctionName = auctionName;
	}
	
	/**
	 * This is the getter for start bid
	 * 
	 * @return startBid the start bid of the item.
	 */
	public double getStartBid()
	{
		return startBid;
	}
	
	/**
	 * This is the setter for start bid
	 * 
	 * @param startBid the new start bid for the item.
	 */
	public void setStartBid(double startBid)
	{
		this.startBid = startBid;
	}
	
	/**
	 * This is the getter for Item id
	 * 
	 * @return ItemID the ID of the item.
	 */
	public int getItemID()
	{
		return itemID;
	}
	
	/**
	 * This is the setter for item id
	 * 
	 * @param itemID the item's new ID
	 */
	public void setItemID(int itemID)
	{
		this.itemID = itemID;
	}
	
	/**
	 * This is the getter for itemName
	 * 
	 * @return itemName the name of the item.
	 */
	public String getItemName()
	{
		return itemName;
	}
	
	/**
	 * This is the setter for itemName
	 * 
	 * @param itemName the new name of the item.
	 */
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	
	/**
	 * This is the getter for item information
	 * 
	 * @return itemInfo the item's information.
	 */
	public String getItemInfo()
	{
		return itemInfo;
	}
	
	/**
	 * This is the setter for Item information
	 * 
	 * @param itemInfo the item's new info.
	 */
	public void setItemInfo(String itemInfo)
	{
		this.itemInfo = itemInfo;
	}
	
	
	/**
	 * This is the getter for itemID
	 * 
	 * @return index of arraylist
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private int getID() throws ClassNotFoundException, IOException
	{
		Inventory i = new Inventory();
		return i.listofItems.size();
	}
	
	/**
	 * This is the toString method to the item. 
	 * 
	 * @return the string representation of the item.
	 */
	@Override
	public String toString()
	{
		return itemID + "," + auctionName + "," + startBid + "," + itemName + "," + itemInfo;
	}
	
}