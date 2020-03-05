import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class TestSimple {

	@Test
    public void test1WordHorizontal() {
		Clue c1 = new Clue("programming language", "java", true, 0, 0);
		assertTrue( CrosswordValidator.validate(Collections.singletonList(c1)));
    }

	@Test
    public void test1WordVertical() {
		Clue c1 = new Clue("programming language", "java", false, 0, 0);
		assertTrue( CrosswordValidator.validate(Collections.singletonList(c1)));
    }

	@Test
    public void test2WordsHorizontal() {
		Clue c1 = new Clue("programming language", "java", true, 0, 0);
		Clue c2 = new Clue("to make an assumption", "assert", true, 0, 1);
		assertTrue( CrosswordValidator.validate(Arrays.asList(c1,c2)));
    }

	@Test
    public void test2WordsVertical() {
		Clue c1 = new Clue("programming language", "java", false, 0, 0);
		Clue c2 = new Clue("to make an assumption", "assert", false, 1, 0);
		assertTrue( CrosswordValidator.validate(Arrays.asList(c1,c2)));
    }


}
