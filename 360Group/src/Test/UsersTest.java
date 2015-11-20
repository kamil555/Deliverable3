package Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import Main.Users;

/**
 * @author Stepan Adespya
 *
 */
public class UsersTest
{
	
	private Users u;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		u = new Users();
	}
	
	/**
	 * Test method for createUser.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public void testCreateUser() throws IOException, ParseException
	{
		assertTrue(u.createUser("bob", "Nonprofit"));
	}
	
	/**
	 * Test method for Login.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public void testLogin() throws IOException, ParseException
	{
		assertTrue(u.login("Stepanb"));
	}
	
}
