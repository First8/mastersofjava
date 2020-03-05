import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;


public class CompareToCase extends MojTest {
	public static final String TOO_MANY_INTERFACES = "You don't really need all those interfaces!";
	public static final String DOES_NOT_IMPLEMENT_INTERFACE = "This class is not comparable.";
	public static final String WRONG_GENERIC_PARAMETER = "Generic parameter is incorrect.";
	public static final String WRONG_ORDER = "Transactions should be ordered by descending date.";
	public static final String CONSISTENT_WITH_EQUALS = "compareTo is not consistent with equals: (x.compareTo(y) == 0) == x.equals(y) in all cases.";
	
	public CompareToCase() {
		super("compareTo", "Implement a compareTo method that orders Transactions based on the date in descending order. It should also be consistent with equals.");
	}

	@Override
	public void runTests(Class<?> type) {
		if (failsToInstantiate(type)) {
			provideErrorMessage(ObjectAccessor.CANNOT_INSTANTIATE);
			return;
		}
		String r = implementsInterface(type);
		if (r != null) {
			provideErrorMessage(r);
			return;
		}
		provideErrorMessage(ordering(type));
		provideErrorMessage(consistentWithEquals(type));
	}
	
	private String implementsInterface(Class<?> type) {
		Type[] interfaces = type.getGenericInterfaces();
		if (interfaces.length > 1) {
			return TOO_MANY_INTERFACES;
		}
		if (interfaces.length == 0) {
			return DOES_NOT_IMPLEMENT_INTERFACE;
		}
		if (interfaces[0] instanceof Class) {
			Class<?> c = (Class<?>)interfaces[0];
			if (c.equals(Comparable.class)) {
				return WRONG_GENERIC_PARAMETER;
			}
		}
		if ( ! (interfaces[0] instanceof ParameterizedType)) {
			return DOES_NOT_IMPLEMENT_INTERFACE;
		}
		ParameterizedType interfaceType = (ParameterizedType)interfaces[0];
		if ( ! interfaceType.getRawType().equals(Comparable.class)) {
			return DOES_NOT_IMPLEMENT_INTERFACE;
		}
		Type genericType = interfaceType.getActualTypeArguments()[0];
		if ( ! (genericType instanceof Class)) {
			return WRONG_GENERIC_PARAMETER;
		}
		Class<?> genericClass = (Class<?>)genericType;
		if ( ! genericClass.equals(type)) {
			return WRONG_GENERIC_PARAMETER;
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String ordering(Class<?> type) {
		Comparable a = instantiate(type, new GregorianCalendar(2014, 5, 23));
		Comparable b = instantiate(type, new GregorianCalendar(2014, 5, 22));
		Comparable c = instantiate(type, new GregorianCalendar(2014, 6, 23));
		
		if (a.compareTo(b) >= 0) {
			return WRONG_ORDER;
		}
		if (a.compareTo(c) <= 0) {
			return WRONG_ORDER;
		}
		if (b.compareTo(c) <= 0) {
			return WRONG_ORDER;
		}
		
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	private String consistentWithEquals(Class<?> type) {
		Calendar date = new GregorianCalendar(2014, 5, 23);
		Comparable a = instantiate(type, new BigDecimal("1.0"), date, "some desciption");
		Comparable b = instantiate(type, new BigDecimal("2.0"), date, "some desciption");
		Comparable c = instantiate(type, new BigDecimal("1.0"), date, "another desciption");
		
		if (consistent(a, b) && consistent(a, c) && consistent(b, c)) {
			return null;
		}
		return CONSISTENT_WITH_EQUALS;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean consistent(Comparable x, Comparable y) {
		return (x.compareTo(y) == 0) == x.equals(y);
	}

	@SuppressWarnings("rawtypes")
	private Comparable instantiate(Class<?> type, Calendar date) {
		return instantiate(type, new BigDecimal("42.00"), date, "some description");
	}
	
	@SuppressWarnings("rawtypes")
	private Comparable instantiate(Class<?> type, BigDecimal amount, Calendar date, String description) {
		Map<Class<?>, Object> prefabValues = new HashMap<Class<?>, Object>();
		prefabValues.put(BigDecimal.class, amount);
		prefabValues.put(Calendar.class, date);
		prefabValues.put(Date.class, date.getTime());
		prefabValues.put(String.class, description);
		try {
			return (Comparable)ObjectAccessor.instantiate(type, prefabValues);
		}
		catch (Throwable e) {
			return null;
		}
	}
	
	private boolean failsToInstantiate(Class<?> type) {
		try {
			return ObjectAccessor.instantiate(type, ObjectAccessor.createPrefabValues()) == null;
		}
		catch (Throwable e) {
			return true;
		}
	}
}
