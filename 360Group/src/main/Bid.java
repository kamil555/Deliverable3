package main;

/**
 * 
 * @author Han
 *
 */
public class Bid
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
	
	private String userName;
	private int itemID;
	private double bidAmount;
	
	/**
	 * getItemID
	 * 
	 * @return item's ID
	 */
	public int getItemID()
	{
		return itemID;
	}
	
	/**
	 * 
	 * @param itemID
	 *            set Item ID
	 */
	public void setItemID(int itemID)
	{
		this.itemID = itemID;
	}
	
	/**
	 * 
	 * @return bidAmount
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
	 * 
	 * @param userName
	 *            user's login name
	 */
	public void setuserName(String userName)
	{
		this.userName = userName;
	}
	
	/**
	 * 
	 * @param userName
	 *            user's login name
	 * @param itemID
	 *            item's id
	 * @param bidAmount
	 *            bid money
	 */
	public Bid(String userName, int itemID, double bidAmount)
	{
		this.bidAmount = bidAmount;
		this.userName = userName;
		this.itemID = itemID;
		
	}
}