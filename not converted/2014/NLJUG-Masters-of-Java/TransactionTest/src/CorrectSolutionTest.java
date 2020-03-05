import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import solution_attempts.CorrectTransactionWithCalendar;
import solution_attempts.CorrectTransactionWithDate;
import solution_attempts.CorrectTransactionWithEqualsGetClass;
import solution_attempts.CorrectTransactionWithFinalEqualsMethod;
import solution_attempts.CorrectTransactionWithReorderedParameters;


public class CorrectSolutionTest {
	private TransactionTester tester;

	@Test
	public void testerConfirmsCorrectImplementationWithCalendar() throws Throwable {
		assertImplementationIsCorrect(CorrectTransactionWithCalendar.class);
	}

	@Test
	public void testerConfirmsCorrectImplementationWithDate() throws Throwable {
		assertImplementationIsCorrect(CorrectTransactionWithDate.class);
	}

	@Test
	public void testerConfirmsCorrectImplementationWithEqualsGetClass() throws Throwable {
		assertImplementationIsCorrect(CorrectTransactionWithEqualsGetClass.class);
	}

	@Test
	public void testerConfirmsCorrectImplementationWithFinalEqualsMethod() throws Throwable {
		assertImplementationIsCorrect(CorrectTransactionWithFinalEqualsMethod.class);
	}

	@Test
	public void testerConfirmsCorrectImplementationWithReorderedParameters() throws Throwable {
		assertImplementationIsCorrect(CorrectTransactionWithReorderedParameters.class);
	}
	
	@Test
	public void testerRejectsIncorrectImplementation() throws Throwable {
		tester = new TransactionTester();
		assertThat(performTests(), is(false));
	}

	private void assertImplementationIsCorrect(Class<?> type) throws Throwable {
		tester = new TransactionTester(type, MojTestHelper.buildListOfTests());
		assertThat(performTests(), is(true));
	}
	
	public boolean performTests() throws Throwable {
		for (int i = 0; i < tester.getTestCount(); i++) {
			if ( ! tester.performTest(i)) {
				return false;
			}
		}
		return true;
	}
}
