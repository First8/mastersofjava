import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import solution_attempts.CorrectTransactionWithCalendar;
import solution_attempts.CorrectTransactionWithDate;
import solution_attempts.CorrectTransactionWithReorderedParameters;

public class ObjectAccessorTest {
	private BigDecimal amount;
	private Calendar cal;
	private Date date;
	private String description;
	private Object value;
	private Map<Class<?>, Object> prefabValues;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() {
		amount = new BigDecimal("123.45");
		cal = new GregorianCalendar(2014, 11, 25);
		date = cal.getTime();
		description = "hi there!";
		value = new CorrectTransactionWithCalendar(amount, cal, description);
		prefabValues = new HashMap<Class<?>, Object>();
		prefabValues.put(BigDecimal.class, amount);
		prefabValues.put(Calendar.class, cal);
		prefabValues.put(Date.class, date);
		prefabValues.put(String.class, description);
	}

	@Test
	public void instantiateWithCalendar() throws Throwable {
		CorrectTransactionWithCalendar expected = new CorrectTransactionWithCalendar(amount, cal, description);
		CorrectTransactionWithCalendar actual = ObjectAccessor.instantiate(CorrectTransactionWithCalendar.class, prefabValues);
		
		assertThat(actual, is(expected));
	}

	@Test
	public void instantiateWithDate() throws Throwable {
		CorrectTransactionWithDate expected = new CorrectTransactionWithDate(amount, date, description);
		CorrectTransactionWithDate actual = ObjectAccessor.instantiate(CorrectTransactionWithDate.class, prefabValues);
		
		assertThat(actual, is(expected));
	}
	
	@Test
	public void instantiateReordered() throws Throwable {
		CorrectTransactionWithReorderedParameters expected = new CorrectTransactionWithReorderedParameters(cal, description, amount);
		CorrectTransactionWithReorderedParameters actual = ObjectAccessor.instantiate(CorrectTransactionWithReorderedParameters.class, prefabValues);
		
		assertThat(actual, is(expected));
	}
	
	static class Thrower {
		public Thrower(BigDecimal amount, Calendar date, String description) {
			throw new IllegalStateException("hello");
		}
	}
	@Test
	public void instantiatePassesThroughConstructorExceptions() throws Throwable {
		thrown.expect(IllegalStateException.class);
		thrown.expectMessage("hello");
		ObjectAccessor.instantiate(Thrower.class, prefabValues);
	}
	
	@Test
	public void getAmount() {
		BigDecimal actual = ObjectAccessor.getAmount(value);
		assertThat(actual, is(amount));
	}
	
	@Test
	public void getDateAsCalendar() {
		Calendar actual = (Calendar)ObjectAccessor.getDate(value);
		assertThat(actual, is(cal));
	}
	
	@Test
	public void getDateAsDate() {
		value = new CorrectTransactionWithDate(amount, date, description);
		Date actual = (Date)ObjectAccessor.getDate(value);
		assertThat(actual, is(date));
	}
	
	@Test
	public void getDescription() {
		String actual = ObjectAccessor.getDescription(value);
		assertThat(actual, is(description));
	}
	
	@Test
	public void getTypeOfDateIsDate() {
		Class<?> typeOfDate = ObjectAccessor.getTypeOfDate(CorrectTransactionWithDate.class);
		assertEquals(typeOfDate, Date.class);
	}
	
	@Test
	public void getTypeOfDateIsCalendar() {
		Class<?> typeOfDate = ObjectAccessor.getTypeOfDate(CorrectTransactionWithCalendar.class);
		assertEquals(typeOfDate, Calendar.class);
	}
}
