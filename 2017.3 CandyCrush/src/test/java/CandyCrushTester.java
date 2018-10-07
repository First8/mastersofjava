import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CandyCrushTester {

	private CandyCrush instance;

	@Before
	public void setup() {
		instance = new CandyCrush();
	}

	@Test
	public void testGameBoard_canMove2_2() {
		char[][] gameboard = { { 'R', 'Y', 'G', 'Y' }, { 'Y', 'P', 'G', 'R' }, { 'P', 'O', 'O', 'Y' },
				{ 'B', 'O', 'B', 'O' } };
		assertTrue(instance.hasMovableFields(gameboard));
	}
	@Test
	public void testSimpleStaticBoard() {
		char[][] gameboard = { { 'R', 'Y', 'G', 'Y' }, { 'Y', 'P', 'G', 'R' }, { 'P', 'O', 'O', 'Y' },
				{ 'B', 'O', 'O', 'B' } };
		assertFalse(instance.hasMovableFields(gameboard));
	}
	@Test
	public void testGameBoard_canMove1_2() {
		char[][] gameboard = { { 'R', 'Y', 'G', 'Y' }, { 'Y', 'P', 'O', 'R' }, { 'P', 'O', 'G', 'Y' },
				{ 'B', 'O', 'O', 'B' } };
		assertTrue(instance.hasMovableFields(gameboard));
	}
	@Test
	public void testSmallStaticRowBoard() {
		char[][] gameboard = { { 'R', 'B', 'P', 'Y' } };
		assertFalse(instance.hasMovableFields(gameboard));
	}
	@Test
	public void testSmallStaticColumnBoard() {
		char[][] gameboard = { { 'R' }, { 'P' }, { 'B' }, { 'Y' } };
		assertFalse(instance.hasMovableFields(gameboard));
	}
	@Test
	public void testGameBoard_canMove3() {
		char[][] gameboard = { { 'R', 'Y', 'G', 'Y' }, { 'Y', 'P', 'G', 'O' }, { 'G', 'R', 'O', 'Y' },
				{ 'B', 'P', 'B', 'O' } };
		assertTrue(instance.hasMovableFields(gameboard));
	}

}