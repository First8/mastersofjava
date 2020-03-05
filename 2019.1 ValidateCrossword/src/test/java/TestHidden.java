import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class TestHidden {

	@Test
    public void testValid() {
		Clue c1 = new Clue("programming language", "java", false, 2, 1);
		Clue c2 = new Clue("to make an assumption", "assert", true, 2, 4);
		Clue c3 = new Clue("best java company", "first8", false, 7, 0);
		assertTrue( CrosswordValidator.validate(Arrays.asList(c1,c2,c3)));
    }

	@Test
    public void testInvalid() {
		Clue c1 = new Clue("programming language", "java", false, 2, 1);
		Clue c2 = new Clue("to make an assumption", "assert", true, 1, 3);
		Clue c3 = new Clue("best java company", "first8", false, 7, 0);
		assertFalse( CrosswordValidator.validate(Arrays.asList(c1,c2,c3)));
    }

}
