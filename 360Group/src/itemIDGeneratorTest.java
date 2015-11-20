import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class itemIDGeneratorTest {

	@Test
	public void testNextID() {
		assertEquals(0, itemIDGenerator.getCurrentID());
		assertEquals(1, itemIDGenerator.nextID());
		assertEquals(1, itemIDGenerator.getCurrentID());
		assertEquals(2, itemIDGenerator.nextID());
		assertEquals(2, itemIDGenerator.getCurrentID());
	}

}
