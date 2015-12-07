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
 * 
 * @author Han
 * @edited by Stepan
 */
public class BidList{
	public ArrayList<Bid> Bidlist;
	
	/**
	 * Print out what bidlist having now
	 */
	@Override
	public String toString() {
		return "BidList =" + Bidlist;
	}
	/**
	 * create a arraylist for bid and read from Bid.txt
	 * @throws IOException
	 */
	public BidList() throws IOException{
		Bidlist = new ArrayList<Bid>();
		readFileToBid("Bids.txt");
	}
	/**
	 * add a new bid info to arraylist and save to Bids.txt
	 * @param user: get the user info from bider class
	 * @param bid: create a new Bid list 
	 * @throws IOException
	 */

	public void addBid(User user, Bid b) throws IOException{
		Bid per = new Bid(user.getUserName(), b.getItemID(), b.getBidAmount());
		Inventory i = new Inventory();
		Item it = i.getItemFromList(b.getItemID());
		boolean bidedOnItem = false;
		for(int j = 0; j < Bidlist.size(); j++){
			if(Bidlist.get(j).getItemID() == b.getItemID()){
				bidedOnItem = true;
			}
		}
		if(bidedOnItem){
			System.out.println("You have already made a Bid on this Item.");
		}else{
			if (b.getBidAmount() >= it.startBid){
				Bidlist.add(per);
				String blist = "" + user.getUserName() + "," + b.getItemID() + ","
						+ b.getBidAmount();
				writeToFile("Bids.txt", blist);
				System.out.println("Bid entered");
			}else{
				System.out.println("Sorry you didnt enter a bid over the starting bid.");
			}
		}
	}
	/**
	 * edit the previews bid price
	 * @param user: get the user info from bider class
	 * @param bid: create a new Bid list 
	 * @param bidAmount: bider's bid price 
	 * @throws IOException
	 */
	public void editBid(User user, Item item, double bidAmount)throws IOException{
		for (int i = 0; i < Bidlist.size(); i++)
		{
			if (Bidlist.get(i).getuserName().endsWith(user.getUserName())){
				if(item.itemID == Bidlist.get(i).getItemID()){
					if(bidAmount > item.startBid){
						Bidlist.get(i).setBidAmount(bidAmount);
						clearFile("Bids.txt");
						writeAllItemsToFile("Bids.txt");
						System.out.println("Bid Changed");
					}else{
						System.out.println("Sorry you didnt enter a bid over the starting bid.");
					}
				}
			}

		}
	}
	/**
	 * Cancel the bid for this Auction
	 * @param user: get the user info from bider class
	 * @param item: which item bid that user want to cancel
	 * @throws IOException
	 */
	public void cancelBid(User user, Item item) throws IOException{
		for (int i = 0; i < Bidlist.size(); i++)
		{
			if (Bidlist.get(i).getuserName().equalsIgnoreCase(user.getUserName())
					&& item.itemID == Bidlist.get(i).getItemID())
			{
				Bidlist.remove(i);
				clearFile("Bids.txt");
				writeAllItemsToFile("Bids.txt");
			}
		}
	}
	/**
	 * check the winning bid price and username for the item
	 * @param item item information 
	 * @return user's name and bid amount
	 */
	public String isWinBid(Item item) {
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
	 * write this array list to the Bid.txt
	 * @param string write to the file
	 * @param blist the array list
	 * @throws IOException
	 */
	private void writeToFile(String fileName, String content) throws IOException {
		// TODO Auto-generated method stub
		FileWriter fw = new FileWriter(fileName, true);
		PrintWriter pw = new PrintWriter(fw);
		if (Files.size(Paths.get(fileName)) == 0)
		{
			pw.write(content);
		} else
		{
			pw.write("\r\n" + content);
		}
		pw.close();
	}
	/**
	 * write all items to Bid.txt
	 * @param string
	 * @throws IOException
	 */

	private void writeAllItemsToFile(String string) throws IOException
	{
		// TODO Auto-generated method stub
		FileWriter fw = new FileWriter(string);
		PrintWriter pw = new PrintWriter(fw);
		for (int i = 0; i < Bidlist.size(); i++)
		{
			pw.write(Bidlist.get(i) + "\r\n");
		}
		pw.close();
	}
	/**
	 * Clear the file
	 * @param string an empty string
	 * @throws IOException
	 */
	private void clearFile(String string) throws IOException
	{
		FileWriter fw = new FileWriter(string, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.print("");
		pw.close();
	}
	/**
	 * read file to the array list
	 * @param fileName file' name 
	 */
	private void readFileToBid(String fileName)
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
				String[] split = line.split(",", 3);
				String userName = split[0];
				String itemID = split[1];
				String bidAmount = split[2];
				int id = Integer.parseInt(itemID);
				double money = Double.parseDouble(bidAmount);
				Bidlist.add(new Bid(userName, id, money));

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
}