import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

/**
 * 
 * @author Mindy Huynh
 *
 */
public class Auction
{
	// auction name
	private String auctionName;
	// auction date
	private Date auctionStart;
	private Date auctionEnd;
	private int auctionDuration;
	private String profitName;
	
	public Auction(String profitName, Date auctionStartDate, int auctionDuration)
			throws ParseException
	{
		this.auctionStart = auctionStartDate;
		Date auctionEnd = auctionStart.clone();
		auctionEnd.addHours(auctionDuration);
		this.auctionEnd = auctionEnd;
		String date = getDate(auctionStartDate.getMonth(),
				auctionStartDate.getDay(), auctionStartDate.getYear());
		this.auctionName = profitName + "-" + date;
		this.profitName = profitName;
		this.auctionDuration = auctionDuration;
	}
	
	public String getAuctionName()
	{
		return this.auctionName;
	}
	
	public Date getAuctionStart()
	{
		return auctionStart;
	}
	
	public void setAuctionStart(Date auctionStart)
	{
		this.auctionStart = auctionStart;
	}
	
	public Date getAuctionEnd()
	{
		return auctionEnd;
	}
	
	public void setAuctionEnd(Date auctionEnd)
	{
		this.auctionEnd = auctionEnd;
	}
	
	public int getAuctionDuration()
	{
		return auctionDuration;
	}
	
	public void setAuctionDuration(int auctionDuration)
	{
		this.auctionDuration = auctionDuration;
	}
	
	public String getProfitName()
	{
		return profitName;
	}
	
	public void setProfitName(String profitName)
	{
		this.profitName = profitName;
	}
	
	public String toString()
	{
		
		return this.auctionName + "," + this.auctionStart + ","
				+ this.auctionDuration;
	}
	
	public void writeAuctionToFile(String fileName) throws IOException
	{
		String auctionString = new String();
		auctionString = this.toString();
		FileWriter fw = new FileWriter(fileName, true);
		PrintWriter pw = new PrintWriter(fw);
		// fw.append(System.lineSeparator());
		pw.write(auctionString + "\r\n");
		pw.close();
	}
	
	private String getDate(int month, int day, int year)
	{
		String[] monthName =
		{ "January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December" };
		String mm = monthName[month - 1];
		return mm + "-" + day + "-" + year;
	}
	
	public String printDetials()
	{
		return "Auction Name = " + this.auctionName + " Non-Profit Name = "
				+ this.profitName + " Auction Start Date = "
				+ this.auctionStart.toString();
	}
}