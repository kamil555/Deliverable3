package main;

/**
 * This is the bid class. It keeps track of the bids by the user. 
 * 
 * @author Han Wang
 * @edited by Mindy Huynh 12/5/2015
 */
public class Bid implements java.io.Serializable
{
	
	/**
	 * toString for print out
	 * 
	 */
	@Override
	public String toString()
	{
		return userName + "," + itemID + "," + bidAmount;
	}
	
	/**
	 * The userName of the bidder.
	 */
	private String userName;
	
	/**
	 * The itemID of the item being bid on.
	 */
	private int itemID;
	
	/**
	 * The amount the user wants to bid. 
	 */
	private double bidAmount;
	
	/**
	 * This is the getter for item ID
	 * 
	 * @return item's ID to keep it different from other items. 
	 */
	public int getItemID()
	{
		return itemID;
	}
	
	/**
	 * This is the setter for itemID. 
	 * So that we can change the ID of the item.
	 * 
	 * @param itemID changes the itemID
	 */
	public void setItemID(int itemID)
	{
		this.itemID = itemID;
	}
	
	/**
	 * This is the getter for bidAmount.
	 * 
	 * @return bidAmount the bidAmount for an item.
	 */
	public double getBidAmount()
	{
		return bidAmount;
	}
	
	/**
	 * 
	 * @param bidAmount
	 *            how much money that bider want to bid
	 */
	public void setBidAmount(double bidAmount)
	{
		this.bidAmount = bidAmount;
	}
	
	/**
	 * 
	 * @return user name
	 */
	public String getuserName()
	{
		return userName;
	}
	
	/**
	 * This is the setter for userName.
	 * 
	 * @param userName
	 *            user's login name
	 */
	public void setuserName(String userName)
	{
		this.userName = userName;
	}
	
	/**
	 * This is the bid constructor. 
	 * To bid on an item, it needs the users login, itemID and bidAmount.
	 * @param userName
	 *            user's login name
	 * @param itemID
	 *            item's id
	 * @param bidAmount
	 *            bid amount
	 */
	public Bid(String userName, int itemID, double bidAmount)
	{
		this.bidAmount = bidAmount;
		this.userName = userName;
		this.itemID = itemID;
		
	}
}