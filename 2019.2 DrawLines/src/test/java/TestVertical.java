import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TestVertical {

	@Test
    public void testDrawVertical1() {
		boolean[][] expected = new boolean[][] { 
			{true,true,true,true,true} 
		};
		
		boolean[][] actual = Draw.drawLine(0,0,0,4);
		
		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}

	@Test
    public void testDrawVertical2() {
		boolean[][] expected = new boolean[][] { 
			{true, true}
		};
		
		boolean[][] actual = Draw.drawLine(0,0,0,1);

		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}
	
	@Test
    public void testDrawVertical3() {
		boolean[][] expected = new boolean[][] { 
			{false, false},
			{false, true},
			{false, true}
		};
		
		boolean[][] actual = Draw.drawLine(1,1,2,1);

		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}


}
