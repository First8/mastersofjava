import java.lang.reflect.Method;


public class SettersCase extends MojTest {
	public static final String SETTER = "Is it appropriate for an immutable class to have setters? :)";
	
	public SettersCase() {
		super("Setters", "Implement all appropriate setters.");
	}

	@Override
	public void runTests(Class<?> type) {
		provideErrorMessage(classHasSetters(type));
	}
	
	private String classHasSetters(Class<?> type) {
		try {
			Method[] methods = type.getDeclaredMethods();
			for (Method m : methods) {
				if (m.getName().startsWith("set")) {
					return SETTER;
				}
			}
			return null;
		}
		catch (SecurityException e) {
			return null;
		}
	}
}
