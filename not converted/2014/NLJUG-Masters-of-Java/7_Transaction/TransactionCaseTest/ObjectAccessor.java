
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class ObjectAccessor {
	public static final String CANNOT_INSTANTIATE = "Cannot run this test until tests 1 to 4 are all passing.";

	private ObjectAccessor() {}
	
	public static <T> T instantiate(Class<T> type, Map<Class<?>, Object> prefabValues) throws Throwable {
		try {
			Constructor<?> constructor = type.getConstructors()[0];
			Class<?>[] parameterTypes = constructor.getParameterTypes();
			Object[] params = new Object[3];
			for (int i = 0; i < 3; i++) {
				params[i] = prefabValues.get(parameterTypes[i]);
			}
			@SuppressWarnings("unchecked")
			T result = (T)constructor.newInstance(params);
			return result;
		}
		catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public static Map<Class<?>, Object> createPrefabValues() {
		Map<Class<?>, Object> result = new HashMap<Class<?>, Object>();
		result.put(BigDecimal.class, new BigDecimal("42.42"));
		result.put(Calendar.class, new GregorianCalendar(2014, 5, 18));
		result.put(Date.class, new Date(1337));
		result.put(String.class, "Some description");
		return result;
	}
	
	@SuppressWarnings("serial")
	public static class InstantiatorException extends RuntimeException {
		public InstantiatorException(Throwable cause) {
			super(cause);
		}
	}
	
	public static BigDecimal getAmount(Object value) {
		return (BigDecimal)get("getAmount", value);
	}
	
	public static Object getDate(Object value) {
		return get("getDate", value);
	}
	
	public static String getDescription(Object value) {
		return (String)get("getDescription", value);
	}

	private static Object get(String getterName, Object value) {
		try {
			Class<?> type = value.getClass();
			Method getter = type.getMethod(getterName);
			return getter.invoke(value);
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public static Class<?> getTypeOfDate(Class<?> type) {
		try {
			return type.getDeclaredField("date").getType();
		}
		catch (Exception e) {
			return null;
		}
	}
}
