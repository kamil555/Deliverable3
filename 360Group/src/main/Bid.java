package main;

public class Bid {

	@Override
	public String toString() {
		return userName + "," + itemID + "," + bidAmount ;
	}

	private String userName;
	private int itemID;
	private double bidAmount;

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public double getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public Bid(String userName,  int itemID, double bidAmount) {
		this.bidAmount = bidAmount;
		this.userName = userName;
		this.itemID = itemID;

	}
}