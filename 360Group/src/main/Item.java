package main;
/**
 * 
 * @author Han
 *
 */

public class Item {
	/**
	 * to string method
	 */
	@Override
	public String toString() {
		return itemID + "," +auctionName+","+ startBid + "," + itemName + "," + itemInfo;
	}
	int itemID;
	double startBid;
	String itemName;
	String itemInfo;
	public String auctionName;
	/**
	 *  getter for auction Name
	 * @return auctionName
	 */
	public String getAuctionName() {
		return auctionName;
	}
	/**
	 * setter for auction name
	 * @param auctionName: auction's name
	 */
	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
	}
	/**
	 * getter for start bid
	 * @return startBid
	 */
	public double getStartBid() {
		return startBid;
	}
	/**
	 * setter for start bid
	 * @param startBid: start bid
	 */
	public void setStartBid(double startBid) {
		this.startBid = startBid;
	}
	
	/**
	 * getter for Item id
	 * @return ItemID
	 */
	public int getItemID() {
		return itemID;
	}
	/**
	 * setter for item id
	 * @param itemID item's id 
	 */
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	/**
	 *  getter for itemName
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * setter for itemName
	 * @param itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * getter for item information
	 * @return itemInfo
	 */
	public String getItemInfo() {
		return itemInfo;
	}
	/**
	 * setter for Item information
	 * @param itemInfo
	 */
	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
	}
	/**
	 * Construction of item class 
	 * @param auctionName
	 * @param startBid
	 * @param itemName
	 * @param itemInfo
	 */
	public Item(String auctionName,double startBid, String itemName, String itemInfo) {
		//super();
		this.auctionName = auctionName;
		this.itemID = getID();
		this.startBid = startBid;
		this.itemName = itemName;
		this.itemInfo =itemInfo;
	}
	/**
	 * Construction of item class 
	 * @param itemID
	 * @param auctionName
	 * @param startBid
	 * @param itemName
	 * @param itemInfo
	 */
	public Item(int itemID,String auctionName,double startBid, String itemName, String itemInfo) {
		//super();
		this.itemID = itemID;
		this.auctionName = auctionName;
		this.startBid = startBid;
		this.itemName = itemName;
		this.itemInfo =itemInfo;
	}
	
	/**
	 * get the index for Inventory list
	 * @return index of arraylist
	 */
	private int getID(){
		Inventory i = new Inventory();
		return i.listofItems.size();
	}
	

}
