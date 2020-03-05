import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TestHorizontal {

	@Test
    public void testDrawHorizontal1() {
		boolean[][] expected = new boolean[][] { 
			{true},{true},{true},{true},{true},{true} 
		};
		
		boolean[][] actual = Draw.drawLine(0,0,5,0);
		
		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}

	@Test
    public void testDrawHorizontal2() {
		boolean[][] expected = new boolean[][] { 
			{true},
			{true}
		};
		
		boolean[][] actual = Draw.drawLine(0,0,1,0);

		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}
	
	@Test
    public void testDrawHorizontal3() {
		boolean[][] expected = new boolean[][] { 
			{false,false},
			{false, false},
			{false, true},
			{false, true}
		};
		
		boolean[][] actual = Draw.drawLine(2,1,3,1);

		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}

}
