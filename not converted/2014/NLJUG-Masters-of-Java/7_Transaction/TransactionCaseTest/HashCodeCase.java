import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;


public class HashCodeCase extends MojTest {
	public static final String HASHCODE_NOT_OVERRIDDEN = "It seems you didn't add a hashCode method.";
	public static final String ALL_FIELDS = "Changing one of the fields does not cause the hashCode to change!";
	public static final String FINAL_BALANCE = "If equals is final, hashCode should be too, and vice versa.";

	public HashCodeCase() {
		super("hashCode", "Implement a hashCode method that fulfills the contract for hashCode, but also provides a decent distribution.");
	}

	@Override
	public void runTests(Class<?> type) {
		if (failsToInstantiate(type)) {
			provideErrorMessage(ObjectAccessor.CANNOT_INSTANTIATE);
			return;
		}
		provideErrorMessage(hashCodeNotOverridden(type));
		provideErrorMessage(allFieldsUsed(type));
		provideErrorMessage(finalBalance(type));
	}
	
	private String hashCodeNotOverridden(Class<?> type) {
		Object red = instantiateRed(type);
		Object black = instantiateRed(type);
		if (red.hashCode() != black.hashCode()) {
			return HASHCODE_NOT_OVERRIDDEN;
		}
		return null;
	}
	
	private String allFieldsUsed(Class<?> type) {
		Object red = instantiateRed(type);
		Object black;
		Map<Class<?>, Object> prefabValues;

		// Amount
		prefabValues = ObjectAccessor.createPrefabValues();
		prefabValues.put(BigDecimal.class, new BigDecimal(1337.00));
		prefabValues.put(String.class, "red"); // Needed because 'red' is a non-standard description.
		black = instantiate(type, prefabValues);
		if (red.hashCode() == black.hashCode()) {
			return ALL_FIELDS;
		}

		// Date
		prefabValues = ObjectAccessor.createPrefabValues();
		prefabValues.put(Date.class, new Date(65535));
		prefabValues.put(Calendar.class, new GregorianCalendar(1999, 11, 31));
		prefabValues.put(String.class, "red"); // Needed because 'red' is a non-standard description.
		black = instantiate(type, prefabValues);
		if (red.hashCode() == black.hashCode()) {
			return ALL_FIELDS;
		}

		// Description
		black = instantiateBlack(type);
		if (red.hashCode() == black.hashCode()) {
			return ALL_FIELDS;
		}

		return null;
	}
	
	private String finalBalance(Class<?> type) {
		try {
			Method equals = type.getMethod("equals", Object.class);
			Method hashCode = type.getMethod("hashCode");
			if (Modifier.isFinal(equals.getModifiers()) != Modifier.isFinal(hashCode.getModifiers())) {
				return FINAL_BALANCE;
			}
			return null;
		}
		catch (Throwable e) {
			return null;
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
	
	private boolean failsToInstantiate(Class<?> type) {
		try {
			return ObjectAccessor.instantiate(type, ObjectAccessor.createPrefabValues()) == null;
		}
		catch (Throwable e) {
			return true;
		}
	}
}
