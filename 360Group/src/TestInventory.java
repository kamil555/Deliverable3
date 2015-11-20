import java.io.IOException;

import Main.Bid;
import Main.Inventory;
import Main.Item;
import Main.User;
import Main.bidList;

public class TestInventory
{
	
	public static void main(String[] args) throws IOException
	{
		User u1 = new User("Andrey", "Bidder");
		System.out.println(u1.toString());
		Item i = new Item("Adespya", 5.0, "pencil", "Special pencil");
		System.out.println(i.toString());
		System.out.println();
		Inventory in = new Inventory();
		in.viewAllitems();
		in.addItem(i);
		System.out.println(in.listofItems.get(0));
		Bid b = new Bid(u1.userName, i.itemID, 6.0);
		System.out.println(b.toString());
		bidList bl = new bidList();
		bl.addBid(u1, b);
		bl.editBid(u1, i, 7.0);
		System.out.println(bl.isWinBid(i));
		bl.cancelBid(u1, i);
		
	}
	
}