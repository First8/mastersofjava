import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestEmpty {
	
	@Test
	public void testEmpty() {
		assertEquals( 0, new AreaCounter().count(TestCount.readImage("image1.txt")));
	}

}
