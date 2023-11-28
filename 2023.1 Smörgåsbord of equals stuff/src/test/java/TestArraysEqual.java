import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestArraysEqual {
	
	
	Object[] a1 = new Object[] { "master", null, new Object[] {"of", "java" }};
	Object[] a2 = new Object[] { "master", null, new String[] {"of", "java" } };

	Object[] b1 = new Object[] { "master", null, null };
	Object[] c1 = new Object[] { "master", null, new String[] {"of", "dotnet" } };

	Object[] d1 = new Object[][] { {"master", "of", "java"}, {"master", "of", "dotnet"} };
	Object[] d2 = new Object[][] { {"master", "of", "java"}, {"master", "of", "dotnet"} };
	
    @Test
	public void testArraysShouldBeEqualNested() {
		assertTrue(WeirdEqualsBugs.equals(a1,a2));
	}

    @Test
	public void testArraysShouldBeEqualMultiDimensional() {
		assertTrue(WeirdEqualsBugs.equals(d1,d2));
	}
    
    @Test
	public void testArraysShouldNotBeEqual() {
		assertFalse(WeirdEqualsBugs.equals(a1,b1));
		assertFalse(WeirdEqualsBugs.equals(a1,c1));
		assertFalse(WeirdEqualsBugs.equals(a1,d1));
	}

}
