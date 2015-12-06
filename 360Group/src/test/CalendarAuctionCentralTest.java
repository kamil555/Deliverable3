package test;

import static org.junit.Assert.*;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;

import main.Auction;
import main.CalendarAuctionCentral;
import main.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Gabrielle Glynn
 * @since December 5, 2015
 */
public class CalendarAuctionCentralTest {
	CalendarAuctionCentral myCalendar;
	Auction pastAuction1;
	Auction futureAuction1;
	Auction futureAuction2;
	ArrayList<Auction> temperaryAuctionList;
	ArrayList<Auction> pastAuctionsOnlyList;
	ArrayList<Auction> futureAuctionsOnlyList;
	ArrayList<Auction> bothPastAndFutureAuctionsList;
	ArrayList<Auction> bothPastAndFutureAuctionsList2;

	@Before
	public void setUp() throws Exception {

		// myCalendar = new CalendarAuctionCentral();

		String NPNameTest1 = "NPNAMETEST1";
		Date pastDateTest1 = new Date("06/02/2014 5:00:00");
		int pastDurationTest1 = 2;
		pastAuction1 = new Auction(NPNameTest1, pastDateTest1,
				pastDurationTest1);

		String NPNameTest2 = "NPNAMETEST2";
		Date futureDateTest1 = new Date("02/02/2016 5:00:00");
		int futureDurationTest1 = 2;
		futureAuction1 = new Auction(NPNameTest2, futureDateTest1,
				futureDurationTest1);

		String NPNameTest3 = "NPNAMETEST3";
		Date futureDateTest2 = new Date("02/01/2016 5:00:00");
		int futureDurationTest2 = 2;
		futureAuction2 = new Auction(NPNameTest3, futureDateTest2,
				futureDurationTest2);

		pastAuctionsOnlyList = new ArrayList<Auction>();
		pastAuctionsOnlyList.add(pastAuction1);

		futureAuctionsOnlyList = new ArrayList<Auction>();
		futureAuctionsOnlyList.add(futureAuction1);

		bothPastAndFutureAuctionsList = new ArrayList<Auction>();
		bothPastAndFutureAuctionsList.add(pastAuction1);
		bothPastAndFutureAuctionsList.add(futureAuction1);

		bothPastAndFutureAuctionsList2 = new ArrayList<Auction>();
		bothPastAndFutureAuctionsList2.add(pastAuction1);
		bothPastAndFutureAuctionsList2.add(futureAuction1);
		bothPastAndFutureAuctionsList2.add(futureAuction2);

	}


	/**
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 */
	@Test
	public void testCalendarConstructorOnEmptyAuctionListFile()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		myCalendar = new CalendarAuctionCentral();

		assertTrue(myCalendar.getAuctionList().isEmpty());
		assertTrue(myCalendar.getFutureAuctionList().isEmpty());
		assertEquals(0, myCalendar.getFutureAuctions());
		
		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * 
	 */
	@Test
	public void testCalendarConstructorOnAuctionListFileWithPastAuctionsOnly()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", pastAuctionsOnlyList);
		myCalendar = new CalendarAuctionCentral();

		assertFalse(myCalendar.getAuctionList().isEmpty());
		assertTrue(myCalendar.getFutureAuctionList().isEmpty());
		assertEquals(0, myCalendar.getFutureAuctions());

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testCalendarConstructorOnAuctionListFileWithFutureAuctionsOnly()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", futureAuctionsOnlyList);
		myCalendar = new CalendarAuctionCentral();

		assertFalse(myCalendar.getAuctionList().isEmpty());
		assertFalse(myCalendar.getFutureAuctionList().isEmpty());
		assertEquals(futureAuctionsOnlyList.size(),
				myCalendar.getFutureAuctions());

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testCalendarConstructorOnAuctionListFileWithBothPastAndFutureAuctions()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList);
		myCalendar = new CalendarAuctionCentral();

		assertFalse(myCalendar.getAuctionList().isEmpty());
		assertFalse(myCalendar.getFutureAuctionList().isEmpty());
		assertEquals(myCalendar.getFutureAuctionList().size(),
				myCalendar.getFutureAuctions());
		assertEquals(bothPastAndFutureAuctionsList.size(), myCalendar
				.getAuctionList().size());

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testGetAuctionOnAuctionListWithNonProfitName()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList);
		myCalendar = new CalendarAuctionCentral();
		
		assertTrue(pastAuction1.toString().equals(myCalendar.getAuction(pastAuction1.getNonProfitName()).toString()));
		assertTrue(futureAuction1.toString().equals(myCalendar.getAuction(futureAuction1.getNonProfitName()).toString()));
		
		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testGetAuctionOnAuctionListWithoutNonProfitName()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList);
		myCalendar = new CalendarAuctionCentral();

		assertNull(myCalendar.getAuction("NonExistentNPName"));

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testEditAuctionDateWithValidNewDate()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList2);
		myCalendar = new CalendarAuctionCentral();

		try {
			myCalendar.editAuctionDate(futureAuction2, new Date("01/03/2016 5:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//TODO
//		System.out.println(myCalendar.getAuctionList());
//		System.out.println(myCalendar.getFutureAuctionList());

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
		
		
	}

//	/**
//	 * @throws IOException
//	 * 
//	 */
//	@Test
//	public void testEditAuctionDateWithInvalidNewDate()
//			throws IOException {
//		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
//		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList2);
//		myCalendar = new CalendarAuctionCentral();
//
//		try {
//			myCalendar.editAuctionDate(futureAuction2, new Date("01/03/2017 5:00:00"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		//TODO
//		System.out.println(myCalendar.getAuctionList());
//		System.out.println(myCalendar.getFutureAuctionList());
//
//		restoreFileContents("Auctions.ser", temperaryAuctionList);
//		temperaryAuctionList = null;
//		myCalendar = null;
//	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testAddFutureAuctionOnPastAuction()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList);
		myCalendar = new CalendarAuctionCentral();

		//TODO		

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testAddFutureAuctionOnGoodFutureAuction()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList);
		myCalendar = new CalendarAuctionCentral();

		//TODO		

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	/**
	 * @throws IOException
	 * 
	 */
	@Test
	public void testAddFutureAuctionOnBadFutureAuction()
			throws IOException {
		temperaryAuctionList = storeFileContentsAndClearFile("Auctions.ser");
		serializeAuctions("Auctions.ser", bothPastAndFutureAuctionsList);
		myCalendar = new CalendarAuctionCentral();

		//TODO

		restoreFileContents("Auctions.ser", temperaryAuctionList);
		temperaryAuctionList = null;
		myCalendar = null;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Auction> deserializeAuctions(String fileName)
			throws IOException {
		ArrayList<Auction> auctionList = null;
		FileInputStream fileIn = new FileInputStream(fileName);
		try {
			ObjectInputStream in = new ObjectInputStream(fileIn);
			try {
				auctionList = (ArrayList<Auction>) in.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			in.close();
		} catch (EOFException e) {
			auctionList = new ArrayList<Auction>();
		}
		fileIn.close();
		return auctionList;
	}

	/**
	 * 
	 * @param fileName
	 * @param auctionList
	 * @throws IOException
	 */
	public void serializeAuctions(String fileName,
			ArrayList<Auction> auctionList) throws IOException {

		FileOutputStream fileOut = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(auctionList);
		out.close();
		fileOut.close();
	}

	/**
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void deleteFileContents(String fileName) throws IOException {
		FileOutputStream file = new FileOutputStream(fileName);
		file.close();
	}
	
	/**
	 * 
	 * @param fileName
	 * @return 
	 * @throws IOException
	 */
	public ArrayList<Auction> storeFileContentsAndClearFile(String fileName) throws IOException {
		ArrayList<Auction> auctionList = deserializeAuctions(fileName);
		deleteFileContents(fileName);
		return auctionList;
	}
	
	/**
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void restoreFileContents(String fileName, ArrayList<Auction> auctionList) throws IOException {
		deleteFileContents(fileName);
		serializeAuctions(fileName, auctionList);
	}

}
