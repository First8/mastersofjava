import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestHidden {
	
	@Test
	public void test1() {
		assertEquals( 2, new AreaCounter().count(TestCount.readImage("hiddentest1.txt")));
	}

	@Test
	public void test2() {
		assertEquals( 5, new AreaCounter().count(TestCount.readImage("hiddentest2.txt")));
	}

	

}
