import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TestTrivial {

	@Test
    public void testDrawSinglePixel() {
		boolean[][] expected = new boolean[][] { 
			{true} 
		};
		
		boolean[][] actual = Draw.drawLine(0,0,0,0);

		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );

	}


}
