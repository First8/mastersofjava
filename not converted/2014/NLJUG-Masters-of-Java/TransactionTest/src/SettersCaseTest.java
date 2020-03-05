import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


public class SettersCaseTest extends TestBase {
	@Before
	public void setUp() {
		test = new SettersCase();
	}
	
	static class HasASetter {
		public void setAmount(BigDecimal amount) {}
	}
	@Test
	public void hasASetter() {
		assertTestFails(HasASetter.class, SettersCase.SETTER);
	}
}
