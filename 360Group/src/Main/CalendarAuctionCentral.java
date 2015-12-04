package Main;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Gabrielle Glynn and Stepan Adespya
 * @since November 8, 2015
 * @updated November 14, 2015
 */
public class CalendarAuctionCentral
{
	private int futureAuctions;
	private ArrayList<Auction> auctionList;
	private ArrayList<Auction> futureAuctionList;
	private ArrayList<Auction> pastAuctionList;

	private static int MAX_FUTURE_AUCTIONS = 25;
	private static int MAX_DAYS_OUT = 90;
	private static int MAX_AUCTIONS_ROLLING_7DAY = 5;
	private static int MAX_AUCTIONS_SAME_DAY = 2;
	private static int MAX_HOURS_BTW_AUCTIONS = 2;
	private static int MAX_NP_AUCTIONS_PER_DAY = 365;

	// needs to check textFile
	public CalendarAuctionCentral() throws ParseException
	{
		auctionList = new ArrayList<Auction>();
		auctionList = readAuctionsFromFile("auctionList.txt");

		futureAuctionList = new ArrayList<Auction>();
		pastAuctionList = new ArrayList<Auction>();
		for (Auction a : auctionList)
		{
			Date now = new Date();
			if (now.before(a.getAuctionEnd()))
			{
				futureAuctionList.add(a);
			} else
			{
				pastAuctionList.add(a);
			}
		}

		futureAuctions = futureAuctionList.size();
	}

	// Make calendar a singleton.
	// http://www.tutorialspoint.com/java/java_using_singleton.htm
	private static CalendarAuctionCentral sCalendar;

	public static CalendarAuctionCentral getCalendar() throws ParseException
	{
		if (sCalendar == null)
			sCalendar = new CalendarAuctionCentral();
		return sCalendar;
	}

	// return array list of all auctions: past and future
	public ArrayList<Auction> getAuctionList()
	{
		return this.auctionList;
	}

	public Auction getAuction(User u) throws ParseException, IOException, ClassNotFoundException
	{
		System.out.println("Select your Auction or Press -1 to go back to main menu:");
		for (int i = 0; i < auctionList.size(); i++)
		{
			if (auctionList.get(i).getProfitName().equalsIgnoreCase(u.getOrganization()))
			{
				System.out.println(i + ")" + auctionList.get(i).toString());
			}
		}
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int select = reader.nextInt();
		if (select == -1)
		{
			new NonProfit(u);
		}
		return auctionList.get(select);
	}

	public void editAuctionDate(Auction a, Date newDate) throws ParseException, IOException{
		for(int i =0;i < auctionList.size(); i++){
			if(auctionList.get(i).getAuctionName().endsWith(a.getAuctionName())){
				auctionList.get(i).setAuctionStart(newDate);
				Date newEnd = newDate.clone();
				newEnd.addHours(auctionList.get(i).getAuctionDuration());
				auctionList.get(i).setAuctionEnd(newEnd);
				writeAuctionsToFile("auctionList.txt");
			}
		}
	}

	public void editAuctionDuration(Auction a, int newDuration) throws IOException{
		for(int i =0;i < auctionList.size(); i++){
			if(auctionList.get(i).getAuctionName().endsWith(a.getAuctionName())){
				auctionList.get(i).setAuctionDuration(newDuration);
				writeAuctionsToFile("auctionList.txt");
			}
		}

	}
	// return array list of future auctions
	public ArrayList<Auction> getfutureAuctionList()
	{
		return this.futureAuctionList;
	}

	public void addFutureAuction(Auction reqAuction) throws IOException,
	ParseException
	{
		if (checkRequestedAuction(reqAuction))
		{
			addAuction(this.auctionList, reqAuction, "auctionList.txt");
			futureAuctionList.add(reqAuction);
			futureAuctions += 1;
		}
	}

	// check if Date meets requirements, true mean accepted, false means denied
	public boolean checkRequestedDate(Date reqDate) throws ParseException
	{
		if (!atMaxFutureAuctions())
		{
			if (inDateRange(reqDate))
			{
				if (!atMaxAuctionsPerDay(reqDate))
				{
					if (!atMaxAuctions7day(reqDate))
					{
						if (!is2HoursBeforeStart(reqDate))
						{
							return true;
						} else
						{
							System.out.println("Sorry, we have another auction within 2 hours of your requested auction.");
							return false;
						}
					} 
					else
					{
						System.out.println("Sorry, we are at our limits for the requested week.");
						return false;
					}
				} else
				{
					System.out.println("Sorry, we are at our limits for the requested date.");
					return false;
				}
			} else
			{
				System.out.println("Date not in range (must be within 90 days from today)");
				return false;
			}
		} else
		{
			System.out.println("At Max future Auctions.");
			return false;
		}
	}

	// check if auction meets requirements, true mean accepted, false means
	// denied
	public boolean checkRequestedAuction(Auction reqAuction)
			throws ParseException
			{
		if (!atMaxFutureAuctions())
		{
			if (inDateRange(reqAuction.getAuctionEnd()))
			{
				if (!oneAuctionPerYear(reqAuction.getProfitName(),
						reqAuction.getAuctionStart()))
				{
					if (!atMaxAuctionsPerDay(reqAuction.getAuctionStart()))
					{
						if (!atMaxAuctions7day(reqAuction.getAuctionStart()))
						{
							if (!is2HoursBeforeStart(reqAuction
									.getAuctionStart()))
							{
								return true;
							} else
							{
								System.out.println("2 hour before start");
								return false;
							}
						} else
						{
							System.out.println("At max 7 day");
							return false;
						}
					} else
					{
						System.out.println("at max auction per day.");
						return false;
					}
				} else
				{
					System.out.println("One Auction per year");
					return false;
				}
			} else
			{
				System.out.println("Not in date range.");
				return false;
			}
		} else
		{
			System.out.println("At max future auctions");
			return false;
		}
			}

	// add auction passed to it without any error checking; used in function
	// that add past auctions to an arraylist
	private void addAuction(ArrayList<Auction> auctionList, Auction newAuction,
			String fileName) throws IOException
			{
		auctionList.add(newAuction);
		writeOneAuctionToFile(newAuction, fileName);
			}

	// check if future list is current; if not remove out of date auctions and
	// append them to past auction list

	// no more than 5 auctions in a rolling 7 day period
	private boolean atMaxAuctions7day(Date requestedDate) throws ParseException
	{
		int jobsAfter;
		int jobsBefore;
		if (oneAuctionforDay(requestedDate) == -1)
		{
			jobsAfter = 0;
			jobsBefore = 0;
		} else
		{
			if (auctionList.get(oneAuctionforDay(requestedDate))
					.getAuctionStart().before(requestedDate))
			{
				jobsAfter = 1;
				jobsBefore = 0;
			} else
			{
				jobsAfter = 0;
				jobsBefore = 1;
			}
		}
		Date tempA = requestedDate.clone();
		Date tempB = requestedDate.clone();
		for (int i = 0; i < 7; i++)
		{
			tempA.addDays(1);
			tempB.addDays(-1);
			for (int j = 0; j < auctionList.size(); j++)
			{
				if (auctionList.get(j).getAuctionStart().getMonth() == tempA.getMonth()
						&& auctionList.get(j).getAuctionStart().getDay() == tempA.getDay()
						&& auctionList.get(j).getAuctionStart().getYear() == tempA.getYear())
				{
					jobsAfter++;
				}
				if (auctionList.get(j).getAuctionStart().getMonth() == tempB.getMonth()
						&& auctionList.get(j).getAuctionStart().getDay() == tempB.getDay()
						&& auctionList.get(j).getAuctionStart().getYear() == tempB.getYear())
				{
					jobsBefore++;
				}
			}
		}
		if (jobsAfter > MAX_AUCTIONS_ROLLING_7DAY
				|| jobsBefore > MAX_AUCTIONS_ROLLING_7DAY)
		{
			return true;
		} else
		{
			return false;
		}

	}

	// no more than 25 auctions may be scheduled into the future
	private boolean atMaxFutureAuctions()
	{
		return (futureAuctions >= MAX_FUTURE_AUCTIONS);
	}

	// an auction may not be more than 90 days from the current date
	private boolean inDateRange(Date requestedDate) throws ParseException
	{
		Date currentDate = new Date();
		Date inNintyDays = new Date();
		inNintyDays.addDays(MAX_DAYS_OUT);
		return currentDate.before(requestedDate) && !(inNintyDays.before(requestedDate));

	}

	// no more than 2 auctions in the same day
	private boolean atMaxAuctionsPerDay(Date requestedDate)
			throws ParseException
			{
		int auctions = 0;
		for (int j = 0; j < auctionList.size(); j++)
		{
			if (auctionList.get(j).getAuctionStart().getMonth() == requestedDate.getMonth() // same
					// month
					&& auctionList.get(j).getAuctionStart().getDay() == requestedDate.getDay() // same
					// date
					&& auctionList.get(j).getAuctionStart().getYear() == requestedDate.getYear()) // same
				// year
			{
				auctions++;
				if (auctions > MAX_AUCTIONS_SAME_DAY)
				{
					return true;
				}
			}
		}
		return false;
			}

	// start time of 2nd auction/day must be two hours after end time of he
	// first
	private boolean is2HoursBeforeStart(Date requestedDate)
			throws ParseException
			{
		if (this.oneAuctionforDay(requestedDate) == -1)
		{
			return false;
		} else
		{
			if (auctionList.get(this.oneAuctionforDay(requestedDate))
					.getAuctionEnd().getDiffHours(requestedDate) < MAX_HOURS_BTW_AUCTIONS)
			{
				return true;
			} else
			{
				return false;
			}
		}
			}

	// no more than one auction per year per nonprofit organization can be
	// scheduled
	private boolean oneAuctionPerYear(String nonprofit, Date requestedDate)
			throws ParseException
			{
		for (int i = 0; i < auctionList.size(); i++)
		{
			if (auctionList.get(i).getProfitName() == nonprofit)
			{
				if (auctionList.get(i).getAuctionEnd()
						.getDiffDay(requestedDate) < MAX_NP_AUCTIONS_PER_DAY)
				{
					return true;
				} else
				{
					return false;
				}
			}
		}
		return false;
			}

	// returns the index of the auction if it has the same requestDate if not
	// returns -1
	private int oneAuctionforDay(Date requestedDate) throws ParseException
	{
		for (int j = 0; j < auctionList.size(); j++)
		{
			if (auctionList.get(j).getAuctionStart().getMonth() == requestedDate
					.getMonth()
					&& auctionList.get(j).getAuctionStart().getDay() == requestedDate
					.getDay()
					&& auctionList.get(j).getAuctionStart().getYear() == requestedDate
					.getYear())
			{
				return j;
			}
		}
		return -1;
	}

	public String printCalendarMonthly(int month, int year) throws ParseException{
		String Calendar = "Calendar";
		String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
				"August", "September", "October", "November", "December" };
		int[] monthDay = { 31, 28, 31, 30, 31, 31, 31, 30, 31, 30, 31, 30 };
		for (int i = 1; i < monthDay[month - 1] + 1; i++){
			Calendar += monthName[month - 1] + "," + i + ", " + year
					+ ": \n";
			for (int j = 0; j < auctionList.size(); j++)
			{
				if (auctionList.get(j).getAuctionStart().getMonth() == month
						&& auctionList.get(j).getAuctionStart().getDay() == i
						&& auctionList.get(j).getAuctionStart().getYear() == year){
					Calendar += auctionList.get(j).toString()+"\n";
				}
			}
		}
		return Calendar;
	}

	// read file into array list of auctions, will use to construct auctionList,
	// futureAuctionList and pastAuctionList
	private ArrayList<Auction> readAuctionsFromFile(String fileName)
			throws ParseException
			{
		String line = null;
		try
		{			
			ArrayList<Auction> auctionList = new ArrayList<Auction>();
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null)
			{	
				String[] split = line.split(",", 3);
				String auctionName = split[0];
				Date auctionDate = new Date(split[1]);
				String auctionDuration = split[2];
				int duration = Integer.parseInt(auctionDuration);
				String[] splitName = auctionName.split("-", 2);
				String nonProfitName = splitName[0];

				auctionList.add(new Auction(nonProfitName, auctionDate,
						duration));
			}

			bufferedReader.close();

			/*
			 * //for debugging System.out.println(auctionList.size());
			 * System.out.println(auctionList);
			 * 
			 * for(Auction a:auctionList) { System.out.println(a.toString()); //
			 * prints [Tommy, tiger] }
			 */

			return auctionList;
		} catch (FileNotFoundException ex)
		{
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex)
		{
			System.out.println("Error reading file '" + fileName + "'");
		}
		return null;
			}

	@SuppressWarnings("unused")
	private void removeOneAuctionFromFile(Auction auctionToRemove,
			String fileName) throws IOException
			{
		for (Auction a : this.auctionList)
		{
			if (auctionToRemove.getAuctionName() == a.getAuctionName())
			{
				this.auctionList.remove(a);
				writeAuctionsToFile(fileName);
			}
		}
		for (Auction a : this.futureAuctionList)
		{
			if (auctionToRemove.getAuctionName() == a.getAuctionName())
			{
				this.futureAuctionList.remove(a);
				futureAuctions -= 1;
			}
		}
			}

	private void clearFile(String fileName) throws IOException
	{
		FileWriter fw = new FileWriter(fileName);
		PrintWriter pw = new PrintWriter(fw);
		pw.print("");
		pw.close();
	}

	private void writeAuctionsToFile(String fileName) throws IOException
	{
		clearFile(fileName);
		for (int i = 0; i < auctionList.size();i++){
			writeOneAuctionToFile(auctionList.get(i), fileName);

		}
	}

	private void writeOneAuctionToFile(Auction auctionToWrite, String fileName)
			throws IOException
			{
		String auctionString = new String();
		auctionString = auctionToWrite.toString();
		FileWriter fw = new FileWriter(fileName, true);
		PrintWriter pw = new PrintWriter(fw);
		if (Files.size(Paths.get(fileName)) == 0)
		{
			pw.write(auctionString);
		} else
		{
			pw.write("\r\n" + auctionString);
		}
		pw.close();
			}

	public String viewFutureAuctions(){
		String futureAuctions = "Here are the future Auctions: \n";
		for (int i = 0; i < futureAuctionList.size(); i++){
			futureAuctions += i + ") " + futureAuctionList.get(i).toString()+"\n";
		}
		return futureAuctions;
	}

}