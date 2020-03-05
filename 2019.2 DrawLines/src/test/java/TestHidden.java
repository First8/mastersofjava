import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TestHidden {

	@Test
    public void testDrawVertical() {
		boolean[][] expected = new boolean[][] { 
			{false, false, false},
			{false, false, false},
			{false, false, true},
			{false, false, true}
		};
		
		boolean[][] actual = Draw.drawLine(2,2,3,2);

		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}
	
	
	@Test
    public void testDrawHorizontal() {
		boolean[][] expected = new boolean[][] { 
			{false,false},
			{false, true},
			{false, true}
		};
		
		boolean[][] actual = Draw.drawLine(1,1,2,1);

		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}

	@Test
    public void testDrawDiagonal() {
		boolean[][] expected = new boolean[][] { 
			{false,false, false},
			{false, true, false},
			{false, false, true}
		};
		
		boolean[][] actual = Draw.drawLine(1,1,2,2);

		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}
	
	@Test
    public void testDrawDiagonal2() {
		boolean[][] expected = new boolean[][] {
			{false, false, false, false, false, false},
			{false, true, true, false, false, false},
			{false, false,false,true,true,true}
		};
		
		boolean[][] actual = Draw.drawLine(1,1,2,5);

		Print.print("expected", expected);
		Print.print("actual", actual);
		System.out.println("-------------");
		assertArrayEquals(expected, actual );
	}


}
