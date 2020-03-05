import static org.junit.Assert.assertFalse;

import java.util.Arrays;

import org.junit.Test;

public class TestNotValid {

	@Test
    public void test2Words1() {
		Clue c1 = new Clue("programming language", "java", true, 0, 0);
		Clue c2 = new Clue("to make an assumption", "assert", false, 0, 0);
		assertFalse( CrosswordValidator.validate(Arrays.asList(c1,c2)));
    }

	@Test
    public void test2Words2() {
		Clue c1 = new Clue("programming language", "java", false, 0, 0);
		Clue c2 = new Clue("to make an assumption", "assert", true, 0, 0);
		assertFalse( CrosswordValidator.validate(Arrays.asList(c1,c2)));
    }

	@Test
    public void test2Words3() {
		Clue c1 = new Clue("programming language", "java", false, 0, 0);
		Clue c2 = new Clue("to make an assumption", "assert", false, 0, 0);
		assertFalse( CrosswordValidator.validate(Arrays.asList(c1,c2)));
    }

	@Test
    public void test2Words4() {
		Clue c1 = new Clue("programming language", "java", true, 0, 0);
		Clue c2 = new Clue("to make an assumption", "assert", true, 0, 0);
		assertFalse( CrosswordValidator.validate(Arrays.asList(c1,c2)));
    }

	@Test
    public void test2Words5() {
		Clue c1 = new Clue("programming language", "java", true, 1, 0);
		Clue c2 = new Clue("to make an assumption", "assert", true, 0, 0);
		assertFalse( CrosswordValidator.validate(Arrays.asList(c1,c2)));
    }

	@Test
    public void test2Words6() {
		Clue c1 = new Clue("programming language", "java", true, 0, 2);
		Clue c2 = new Clue("to make an assumption", "assert", false, 0, 0);
		assertFalse( CrosswordValidator.validate(Arrays.asList(c1,c2)));
    }

	@Test
    public void test2Words7() {
		Clue c1 = new Clue("programming language", "java", false, 3, 0);
		Clue c2 = new Clue("to make an assumption", "assert", true, 0, 0);
		assertFalse( CrosswordValidator.validate(Arrays.asList(c1,c2)));
    }
}
