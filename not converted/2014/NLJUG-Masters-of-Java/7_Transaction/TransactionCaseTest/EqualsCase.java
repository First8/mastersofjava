import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

public class EqualsCase extends MojTest {
	public static final String REFLEXIVE = "Your Transaction's equals method is not reflexive: an instance is not equal to itself.";
	public static final String SYMMETRIC = "Your Transaction's equals method is not symmetric.";
	public static final String TRANSITIVE = "Your Transaction's equals method is not transitive.";
	public static final String NON_NULL = "Your Transaction's equals method does not satisfy the 'non-null' requirement.";
	public static final String ALL_FIELDS = "Not all your fields are tested by your equals method";
	public static final String NON_FINAL = "I could override your class and mess up your equals method, breaking the contract in various ways...";
	
	private Class<?> type;
	private Object red;
	private Object black;
	
	public EqualsCase() {
		super("equals", "Implement an equals method that fulfills the contract for equality.");
	}

	@Override
	public void runTests(Class<?> type) {
		if (failsToInstantiate(type)) {
			provideErrorMessage(ObjectAccessor.CANNOT_INSTANTIATE);
			return;
		}

		this.type = type;
		this.red = instantiateRed(type);
		this.black = instantiateBlack(type);

		try {
			provideErrorMessage(reflexive());
			provideErrorMessage(symmetric());
			provideErrorMessage(transitive());
			provideErrorMessage(nonNull());
			provideErrorMessage(allFieldsUsed());
			provideErrorMessage(finality());
		}
		catch (IllegalStateException e) {
			Throwable cause = e.getCause();
			if (cause instanceof InstantiationError) {
				provideErrorMessage(cause.getCause().toString());
			}
			else {
				provideErrorMessage(cause.toString());
			}
		}
	}
	
	private String reflexive() {
		Object alsoRed = instantiateRed(type);
		if ( ! red.equals(red)) {
			return REFLEXIVE;
		}
		if ( ! red.equals(alsoRed)) {
			return REFLEXIVE;
		}
		return null;
	}
	
	private String symmetric() {
		if (red.equals(black) != black.equals(red)) {
			return SYMMETRIC;
		}
		return null;
	}
	
	private String transitive() {
		Map<Class<?>, Object> prefabValues = ObjectAccessor.createPrefabValues();
		// Make a class that has the same description as RED, but where all other fields are different.
		prefabValues.put(String.class, "red");
		prefabValues.put(BigDecimal.class, new BigDecimal("1337.00"));
		prefabValues.put(Date.class, new Date(65535));
		prefabValues.put(Calendar.class, new GregorianCalendar(1999, 11, 31));
		Object indigo = instantiate(type, prefabValues);
		
		boolean x = red.equals(black);
		boolean y = black.equals(indigo);
		boolean z = red.equals(indigo);
		
		if (countFalses(x, y, z) == 1) {
			return TRANSITIVE;
		}
		return null;
	}
	
	private int countFalses(boolean... bools) {
		int result = 0;
		for (boolean b : bools) {
			if (!b) {
				result++;
			}
		}
		return result;
	}
	
	private String nonNull() {
		try {
			if (red.equals(null)) {
				return NON_NULL;
			}
			return null;
		}
		catch (Throwable e) {
			return NON_NULL;
		}
	}
	
	private String allFieldsUsed() {
		Map<Class<?>, Object> prefabValues;

		// Amount
		prefabValues = ObjectAccessor.createPrefabValues();
		prefabValues.put(BigDecimal.class, new BigDecimal(1337.00));
		prefabValues.put(String.class, "red"); // Needed because 'red' is a non-standard description.
		black = instantiate(type, prefabValues);
		if (red.equals(black)) {
			return ALL_FIELDS;
		}

		// Date
		prefabValues = ObjectAccessor.createPrefabValues();
		prefabValues.put(Date.class, new Date(65535));
		prefabValues.put(Calendar.class, new GregorianCalendar(1999, 11, 31));
		prefabValues.put(String.class, "red"); // Needed because 'red' is a non-standard description.
		black = instantiate(type, prefabValues);
		if (red.equals(black)) {
			return ALL_FIELDS;
		}

		// Description
		black = instantiateBlack(type);
		if (red.equals(black)) {
			return ALL_FIELDS;
		}
		return null;
	}
	
	private String finality() {
		if (Modifier.isFinal(type.getModifiers())) {
			return null;
		}
		try {
			Method m = type.getMethod("equals", Object.class);
			if (Modifier.isFinal(m.getModifiers())) {
				return null;
			}
		}
		catch (Throwable e) {
			return null;
		}
		return NON_FINAL;
	}
	
	private boolean failsToInstantiate(Class<?> type) {
		try {
			return ObjectAccessor.instantiate(type, ObjectAccessor.createPrefabValues()) == null;
		}
		catch (Throwable e) {
			return true;
		}
	}

	private <T> T instantiateRed(Class<T> type) {
		Map<Class<?>, Object> prefabValues = ObjectAccessor.createPrefabValues();
		prefabValues.put(String.class, "red");
		return instantiate(type, prefabValues);
	}
	
	private <T> T instantiateBlack(Class<T> type) {
		Map<Class<?>, Object> prefabValues = ObjectAccessor.createPrefabValues();
		prefabValues.put(String.class, "black");
		return instantiate(type, prefabValues);
	}
	
	private <T> T instantiate(Class<T> type, Map<Class<?>, Object> prefabValues) {
		try {
			return ObjectAccessor.instantiate(type, prefabValues);
		}
		catch (Throwable e) {
			throw new IllegalStateException(e);
		}
	}
}
