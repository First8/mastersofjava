import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestFull {
	
	@Test
	public void testFull() {
		assertEquals( 1, new AreaCounter().count(TestCount.readImage("image2.txt")));
	}

}
