/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import main.Date;

/**
 * @author Stepan Adespya
 *
 */
public class DateTest {
	
	Date d;
	Date d2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		d = new Date("12/25/2015 08:30:00");
		d2 = new Date("12/26/2015 08:30:00");
	}


	/**
	 * Test method for {@link Main.Date#getMonth()}.
	 * @throws ParseException 
	 */
	@Test
	public void testGetMonth() throws ParseException {
		assertEquals(d.getMonth(),12);
	}

	/**
	 * Test method for {@link Main.Date#getDay()}.
	 * @throws ParseException 
	 */
	@Test
	public void testGetDay() throws ParseException {
		assertEquals(d.getDay(),25);
	}

	/**
	 * Test method for {@link Main.Date#getYear()}.
	 * @throws ParseException 
	 */
	@Test
	public void testGetYear() throws ParseException {
		assertEquals(d.getYear(),2015);
	}

	/**
	 * Test method for {@link Main.Date#getHour()}.
	 * @throws ParseException 
	 */
	@Test
	public void testGetHour() throws ParseException {
		assertEquals(d.getHour(),8);
	}

	/**
	 * Test method for {@link Main.Date#getMinutes()}.
	 * @throws ParseException 
	 */
	@Test
	public void testGetMinutes() throws ParseException {
		assertEquals(d.getMinutes(),30);
	}

	/**
	 * Test method for {@link Main.Date#getSeconds()}.
	 * @throws ParseException 
	 */
	@Test
	public void testGetSeconds() throws ParseException {
		assertEquals(d.getSeconds(),0);
	}

	/**
	 * Test method for {@link Main.Date#setMonth(int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testSetMonth() throws ParseException {
		d.setMonth(1);
		assertEquals(d.getMonth(),1);
	}

	/**
	 * Test method for {@link Main.Date#setDay(int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testSetDay() throws ParseException {
		d.setDay(1);
		assertEquals(d.getDay(),1);
	}

	/**
	 * Test method for {@link Main.Date#setYear(int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testSetYear() throws ParseException {
		d.setYear(2016);;
		assertEquals(d.getYear(),2016);
	}

	/**
	 * Test method for {@link Main.Date#setHour(int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testSetHour() throws ParseException {
		d.setHour(0);;
		assertEquals(d.getHour(),0);
	}

	/**
	 * Test method for {@link Main.Date#setMinutes(int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testSetMinutes() throws ParseException {
		d.setMinutes(45);
		assertEquals(d.getMinutes(),45);
	}

	/**
	 * Test method for {@link Main.Date#setSeconds(int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testSetSeconds() throws ParseException {
		d.setSeconds(30);;
		assertEquals(d.getSeconds(),30);
	}

	/**
	 * Test method for {@link Main.Date#getDiffHours(Main.Date)}.
	 * @throws ParseException 
	 */
	@Test
	public void testGetDiffHours() throws ParseException {
		assertEquals(d.getDiffHours(d2),24);
	}

	/**
	 * Test method for {@link Main.Date#getDiffDay(Main.Date)}.
	 * @throws ParseException 
	 */
	@Test
	public void testGetDiffDay() throws ParseException {
		assertEquals(d.getDiffDay(d2),1);
	}

	/**
	 * Test method for {@link Main.Date#before(Main.Date)}.
	 * @throws ParseException 
	 */
	@Test
	public void testBefore() throws ParseException {
		assertTrue(d.before(d2));
	}

	/**
	 * Test method for {@link Main.Date#addDays(int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testAddDays() throws ParseException {
		d.addDays(1);
		assertEquals(d.getDay(),26);
	}

	/**
	 * Test method for {@link Main.Date#addHours(int)}.
	 * @throws ParseException 
	 */
	@Test
	public void testAddHours() throws ParseException {
		d.addHours(2);
		assertEquals(d.getHour(),10);
	}


}
