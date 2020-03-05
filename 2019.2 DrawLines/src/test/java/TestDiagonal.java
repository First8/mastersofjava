import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TestDiagonal {

	
	@Test
    public void testDrawTrueDiagonal1() {
		boolean[][] expected = new boolean[][] { 
			{true, false},
			{false, true}
		};
		
		boolean[][] actual = Draw.drawLine(0,0,1,1);
		
		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}

	@Test
    public void testDrawTrueDiagonal2() {
		boolean[][] expected = new boolean[][] { 
			{false, false, false, true},
			{false, false, true, false},
			{false, true, false, false},
			{true, false, false, false},
		};
		
		boolean[][] actual = Draw.drawLine(0,3,3,0);

		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}
	
	@Test
    public void testDrawDiagonal3() {
		boolean[][] expected = new boolean[][] { 
			{true, true, false, false, false},
			{false,false,true,true,true}
		};
		
		boolean[][] actual = Draw.drawLine(0,0,1,4);

		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}
	
	@Test
    public void testDrawDiagonal4() {
		boolean[][] expected = new boolean[][] { 
			{false, false},
			{false, true},
			{true, false},
			{true, false}
		};
		
		boolean[][] actual = Draw.drawLine(3,0,1,1);

		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}

	@Test
    public void testDrawTrueDiagonal5() {
		boolean[][] expected = new boolean[][] { 
			{false, false, false, true, true},
			{false, true, true, false, false},
			{true, false, false, false, false},
		};
		
		boolean[][] actual = Draw.drawLine(0,4,2,0);
		
		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}
	

}
