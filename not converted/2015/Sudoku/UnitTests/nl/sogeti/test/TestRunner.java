package nl.sogeti.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestRunner {
	private static Logger LOGGER = Logger.getLogger(TestRunner.class
			.getName());

	private Map<String, Exception> exceptions = new HashMap<>();

	private Map<String, Class<?>> testCases = new HashMap<>();
	private Map<String, Method> testMethods = new HashMap<>();

	public static void main(String[] args) {
		System.out.println("Starting tester ...");
		configureLogger();

		TestRunner test = new TestRunner();
		test.run();
	}

	private static void configureLogger() {
		// Setting the first root logger's handler to all
		Handler handler = LOGGER.getLogger("").getHandlers()[0];
		handler.setLevel(Level.ALL);
		LOGGER.setLevel(Level.ALL);

		LOGGER.info("Logger configured ...");
	}

	public void run() {
		LOGGER.info("Starting tests");

		exceptions.clear();

		indexTestClassess(SudokuSolverTest.class, SudokuValidatorTest.class);

		for (Entry<String, Class<?>> testCase : testCases.entrySet()) {
			final Class<?> testCaseClass = testCase.getValue();
			final String testCaseName = testCase.getKey();

//			System.out.println("RUN testcase: " + testCaseName);
			
			Object testCaseObject = instantiateTestCase(testCaseClass);
			
			indexTestMethods(testCaseClass);

			for (Entry<String, Method> test : testMethods.entrySet()) {
				boolean success = invokeTest(testCaseObject, testCaseName,
						test.getKey(), test.getValue());
				System.out.print((success) ? "." : "x");
			}
			
		}
		System.out.print("\n");

		logReasons(exceptions);
	}

	private Object instantiateTestCase(Class<?> testCase) {
		Object testCaseObject = null;
		try {
			testCaseObject = testCase.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return testCaseObject;
	}

	private void indexTestClassess(Class<?>... testCaseClasses) {
		testCases.clear();

		for (Class<?> clazz : testCaseClasses) {
			String className = clazz.getName();
			Package packagge = clazz.getPackage();
			if (className.endsWith("Test")) {
				String caseName = className
						.substring(0, className.length() - 4);
				testCases.put(caseName, clazz);
			}
		}
	}

	private Vector<Class> getAllClasses() {
		Vector<Class> classes;
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();

		Field f;
		try {
			f = ClassLoader.class.getDeclaredField("classes");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		final boolean access = f.isAccessible();
		try {
			f.setAccessible(true);
			classes = (Vector<Class>) f.get(classLoader);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			f.setAccessible(access);
		}

		return classes;
	}

	private void indexTestMethods(Class<?> testClass) {
		testMethods.clear();
		for (Method method : testClass.getDeclaredMethods()) {
			String methodName = method.getName();
			if (methodName.startsWith("test")) {
				final String testName = methodName.substring(4);
				if (!testName.isEmpty() && testName.matches("^[\\p{Upper}].*")) {
					testMethods.put(testName, method);
				}
			}
		}

	}

	private boolean invokeTest(Object testCase, String caseName,
			String testName, Method testMethod) {
		boolean success = true;
		try {
			testMethod.invoke(testCase);
		} catch (InvocationTargetException e) {
			success = false;
			exceptions.put(testName, (Exception) e.getCause());
		} catch (IllegalAccessException e) {
			success = false;
			exceptions.put(caseName + ":" + testName,
					new Exception("Not allowed to access test method ["
							+ testMethod.getName() + "]"));
		} catch (IllegalArgumentException e) {
			success = false;
			exceptions.put(caseName + ":" + testName, new Exception(
					"Test method [" + testMethod.getName()
							+ "] should not require arguments"));
		}
		return success;
	}

	private void logReasons(Map<String, Exception> reasons) {
		for (Entry<String, Exception> reason : reasons.entrySet()) {
			Exception e = reason.getValue();
			if (e instanceof TestException) {
				LOGGER.log(Level.INFO, "Test [" + reason.getKey()
						+ "] failed: " + reason.getValue().getMessage());
			} else {
				LOGGER.log(Level.WARNING, "Test  [" + reason.getKey()
						+ "] in error: ", reason.getValue());
			}
		}

	}
}
