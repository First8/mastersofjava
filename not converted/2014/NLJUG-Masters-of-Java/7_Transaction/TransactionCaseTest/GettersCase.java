import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Map;

public class GettersCase extends MojTest {
	public static final String PRESENCE = "All fields should have a corresponding getter.";
	public static final String CORRESPONDENCE = "The return types of the getters should match the types of the fields.";
	public static final String ROUND_TRIP = "The same value that enters the constructor, should be returned by the getter.";

	public GettersCase() {
		super("Getters", "Implement all appropriate getters.");
	}

	@Override
	public void runTests(Class<?> type) {
		provideErrorMessage(presenceOfGetters(type));
		provideErrorMessage(correspondenceOfGetters(type));
		provideErrorMessage(roundTrip(type));
	}

	public String presenceOfGetters(Class<?> type) {
		boolean result = hasGetterNamed(type, NAMES[0][1]) &&
				hasGetterNamed(type, NAMES[1][1]) &&
				hasGetterNamed(type, NAMES[2][1]);
		if (result) {
			return null;
		}
		return PRESENCE;
	}
	
	private boolean hasGetterNamed(Class<?> type, String name) {
		return getGetter(type, name) != null;
	}
	
	public String correspondenceOfGetters(Class<?> type) {
		for (String[] pair : NAMES) {
			Field f = getField(type, pair[0]);
			Method m = getGetter(type, pair[1]);
			if (f == null || m == null || ! f.getType().equals(m.getReturnType())) {
				return CORRESPONDENCE;
			}
		}
		return null;
	}
	
	public String roundTrip(Class<?> type) {
		try {
			Map<Class<?>, Object> prefabValues = ObjectAccessor.createPrefabValues();
			Object obj = ObjectAccessor.instantiate(type, prefabValues);
			
			if ( ! ObjectAccessor.getAmount(obj).equals(prefabValues.get(BigDecimal.class))) {
				return ROUND_TRIP;
			}
			if ( ! ObjectAccessor.getDate(obj).equals(prefabValues.get(ObjectAccessor.getTypeOfDate(type)))) {
				return ROUND_TRIP;
			}
			if ( ! ObjectAccessor.getDescription(obj).equals(prefabValues.get(String.class))) {
				return ROUND_TRIP;
			}
			return null;
		}
		catch (Throwable e) {
			return null;
		}
	}
	
	private static final String[][] NAMES = {
		{ "amount", "getAmount" },
		{ "date", "getDate" },
		{ "description", "getDescription" },
	};
	
	private static Method getGetter(Class<?> type, String name) {
		try {
			Method method = type.getMethod(name);
			if (Modifier.isPublic(method.getModifiers())) {
				return method;
			}
			return null;
		}
		catch (NoSuchMethodException e) {
			return null;
		}
		catch (SecurityException e) {
			return null;
		}
	}

	private static Field getField(Class<?> type, String name) {
		try {
			return type.getDeclaredField(name);
		}
		catch (SecurityException e) {
			return null;
		}
		catch (NoSuchFieldException e) {
			return null;
		}
	}
}
