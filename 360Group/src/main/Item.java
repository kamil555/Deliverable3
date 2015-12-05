package main;
/**
 * 
 * @author Han 
 *
 */
public class Item
{
	
	@Override
	public String toString()
	{
		return itemID + "," + auctionName + "," + startBid + "," + itemName
				+ "," + itemInfo;
	}
	
	public int itemID;
	public double startBid;
	public String itemName;
	public String itemInfo;
	// Double winBid;
	// String winUser;
	public String auctionName;
	
	public String getAuctionName()
	{
		return this.auctionName;
	}
	
	public double getStartBid()
	{
		return startBid;
	}
	
	public void setStartBid(double startBid)
	{
		this.startBid = startBid;
	}
	
	public int getItemID()
	{
		return this.itemID;
	}
	
	public void setItemID(int itemID)
	{
		this.itemID = itemID;
	}
	
	public String getItemName()
	{
		return itemName;
	}
	
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	
	public String getItemInfo()
	{
		return itemInfo;
	}
	
	public void setItemInfo(String itemInfo)
	{
		this.itemInfo = itemInfo;
	}
	
	public Item(String auctionName, double startBid, String itemName,
			String itemInfo)
	{
		// super();
		this.auctionName = auctionName;
		this.itemID = createID();
		this.startBid = startBid;
		this.itemName = itemName;
		this.itemInfo = itemInfo;
	}
	
	public Item(int itemID, String auctionName, double startBid,
			String itemName, String itemInfo)
	{
		// super();
		this.itemID = itemID;
		this.auctionName = auctionName;
		this.startBid = startBid;
		this.itemName = itemName;
		this.itemInfo = itemInfo;
	}
	
	private int createID()
	{		
		Inventory in = new Inventory();
		return in.getSize();
	}
	
}