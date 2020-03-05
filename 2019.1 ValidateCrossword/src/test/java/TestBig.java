import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class TestBig {

	@Test
    public void testValid() {
		Clue c1 = new Clue("programming language", "java", false, 0, 0);
		Clue c2 = new Clue("to make an assumption", "assert", true, 0, 1);
		Clue c3 = new Clue("verify", "test", false, 3, 0);
		Clue c4 = new Clue("work", "todo", true, 3, 3);
		assertTrue( CrosswordValidator.validate(Arrays.asList(c1,c2,c3,c4)));
    }

	@Test
    public void testInvalid() {
		Clue c1 = new Clue("programming language", "java", false, 0, 0);
		Clue c2 = new Clue("to make an assumption", "assert", true, 0, 1);
		Clue c3 = new Clue("verify", "test", false, 3, 0);
		Clue c4 = new Clue("work", "todo", true, 2, 3);
		assertFalse( CrosswordValidator.validate(Arrays.asList(c1,c2,c3,c4)));
    }

}
