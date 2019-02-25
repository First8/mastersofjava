

import static org.junit.Assert.*;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testWithX() {
		final Position pos = new Position(1, 2);
		final Position pos2 = pos.withX(2);
		
		assertEquals(3, pos2.getX());
		assertEquals(2, pos2.getY());
	}
	
	@Test
	public void testWithY() {
		final Position pos = new Position(1, 2);
		final Position pos2 = pos.withY(2);
		
		assertEquals(4, pos2.getY());
		assertEquals(1, pos2.getX());
		
	}
	
	@Test
	public void testEqualsAndHashCode() { 
		
		final Position position = new Position(1, 2);
		
		//Reflexive & consistency
		for(int i = 0; i < 2; i++) { 
			assertEquals(position, position);
			assertEquals(position.hashCode(), position.hashCode());
		}
		
		//Symmetry
		final Position secondPosition = new Position(1, 2);
		assertEquals(position, secondPosition);
		assertEquals(position.hashCode(), secondPosition.hashCode());
	
		//Transitive
		final Position thirdPosition = new Position(1, 2);
		assertEquals(secondPosition, thirdPosition);
		assertEquals(secondPosition.hashCode(), thirdPosition.hashCode());
		
		assertNotEquals(position, null);
		
		final Position positionDifferentYValue = new Position(1, 3);
		assertNotEquals(position, positionDifferentYValue);
		assertNotEquals(position.hashCode(), positionDifferentYValue.hashCode());
		
		final Position positionDifferentXValue = new Position(2, 2);
		assertNotEquals(position, positionDifferentXValue);
		assertNotEquals(position.hashCode(), positionDifferentXValue.hashCode());
	}

}
