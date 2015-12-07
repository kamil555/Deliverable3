package main;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This is the bidList class. 
 * It has all the bids.
 * @author Han Wang
 * @edited by Stepan & Mindy Huynh 12/5/2015
 */
public class BidList
{
	/**
	 * This the the list of bids.
	 */
	public ArrayList<Bid> Bidlist;
	private String fileName = "Bids.ser";
	
	/**
	 * This is the constructor of bidList.
	 * It creates an arraylist for bid and read from Bid.txt
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	public BidList() throws IOException, ClassNotFoundException
	{
		Bidlist = new ArrayList<Bid>();
		readFileToBid(fileName);
	}
	
	/**
	 * This method adds a new bid info to arraylist and save to Bids.txt.
	 * 
	 * @param user
	 *            the user's info from bider class
	 * @param bid
	 *            the bid that creates a new Bid list
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	
	public void addBid(User user, Bid b) throws IOException, ClassNotFoundException
	{
		Bid per = new Bid(user.getUserName(), b.getItemID(), b.getBidAmount());
		Inventory i = new Inventory();
		Item it = i.getItemFromList(b.getItemID());
		boolean bidedOnItem = false;
		for (int j = 0; j < Bidlist.size(); j++)
		{
			if (Bidlist.get(j).getItemID() == b.getItemID())
			{
				bidedOnItem = true;
			}
		}
		if (bidedOnItem)
		{
			System.out.println("You have already made a Bid on this Item.");
		} else
		{
			if (b.getBidAmount() >= it.getStartBid())
			{
				Bidlist.add(per);
				writeToFile(fileName);
				System.out.println("Bid entered");
			} else
			{
				System.out.println("Sorry you didnt enter a bid over the starting bid.");
			}
		}
	}
	
	/**
	 * This method edits the previews bid price.
	 * 
	 * @param user
	 *            the user to get the user info from bider class
	 * @param bid
	 *           the bid creates a new Bid list
	 * @param bidAmount
	 *           bider's bid price
	 * @throws IOException
	 */
	public void editBid(User user, Item item, double bidAmount) throws IOException
	{
		for (int i = 0; i < Bidlist.size(); i++)
		{
			if (Bidlist.get(i).getuserName().endsWith(user.getUserName()))
			{
				if (item.getItemID() == Bidlist.get(i).getItemID())
				{
					if (bidAmount > item.getStartBid())
					{
						Bidlist.get(i).setBidAmount(bidAmount);
						writeToFile(fileName);
						System.out.println("Bid Changed");
					} else
					{
						System.out.println("Sorry you didnt enter a bid over the starting bid.");
					}
				}
			}
			
		}
	}
	
	/**
	 * This class cancels the bid for this Auction.
	 * 
	 * @param user
	 *           the user is used get the user info from bider class.
	 * @param item
	 *           the item that user want to cancel their bid on.
	 * @throws IOException
	 */
	public void cancelBid(User user, Item item) throws IOException
	{
		for (int i = 0; i < Bidlist.size(); i++)
		{
			if (Bidlist.get(i).getuserName().equalsIgnoreCase(user.getUserName())
					&& item.getItemID() == Bidlist.get(i).getItemID())
			{
				Bidlist.remove(i);
				writeToFile(fileName);
			}
		}
	}
	
	/**
	 * This method checks the winning bid price and username for the item
	 * 
	 * @param item
	 *            being checked on's information
	 * @return user's name and bid amount
	 */
	public String isWinBid(Item item)
	{
		int winner = 0;
		for (int i = 0; i < Bidlist.size(); i++)
		{
			if (Bidlist.get(winner).getBidAmount() < Bidlist.get(i)
					.getBidAmount())
			{
				winner = i;
			}
		}
		return "userName is:" + Bidlist.get(winner).getuserName()
				+ " Amount is:" + Bidlist.get(winner).getBidAmount();
		
	}
	
	/**
	 * This method writes all items to Bid.ser
	 * 
	 * @param string the string being written to.
	 * @throws IOException
	 */
	private void writeToFile(String string) throws IOException
	{
		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(Bidlist);
		out.close();
		fileOut.close();
	}
	/**
	 * This method reads the file to the array list
	 * 
	 * @param fileName
	 *            the file's name to read from.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	private void readFileToBid(String fileName) throws IOException, ClassNotFoundException
	{
		FileInputStream fileIn = new FileInputStream(fileName);
		try
		{
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Bidlist = (ArrayList<Bid>) in.readObject();
			in.close();
		} catch (EOFException e)
		{
			Bidlist = new ArrayList<Bid>();
		}
		fileIn.close();
	}
}