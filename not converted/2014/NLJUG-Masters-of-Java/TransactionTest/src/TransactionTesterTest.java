import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TransactionTesterTest {
	private static final int TEST_COUNT = 10;
	
	private List<MojTest> tests = new ArrayList<MojTest>();
	private TransactionTester tester;

	@Before
	public void setUp() {
		for (int i = 0; i < TEST_COUNT; i++) {
			MojTest test = new StubMojTest("name" + i, "description" + i, i % 2 == 0);
			tests.add(test);
		}
		tester = new TransactionTester(Transaction.class, tests);
	}
	
	@Test
	public void testCount() {
		assertThat(tester.getTestCount(), is(TEST_COUNT));
	}
	
	@Test
	public void testName() {
		assertThat(tester.getTestName(4), is("name4"));
	}
	
	@Test
	public void testDescription() {
		assertThat(tester.getTestDescription(6), is("description6"));
	}
	
	@Test
	public void performTest() throws Throwable {
		assertThat(tester.performTest(2), is(true));
		assertThat(tester.performTest(7), is(false));
	}
}

class StubMojTest extends MojTest {
	private boolean testResult;

	public StubMojTest(String name, String description, boolean testResult) {
		super(name, description);
		this.testResult = testResult;
	}

	@Override
	public void runTests(Class<?> type) {
		if ( ! testResult) {
			provideErrorMessage("error");
		}
	}
}
