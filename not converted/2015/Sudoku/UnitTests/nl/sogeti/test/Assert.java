package nl.sogeti.test;

public class Assert {

	public static void isTrue(final String msg, final boolean boolValue) {
		if (boolValue != true) {
			throw new TestException("Assertion for true failed : " + msg);
		}
	}

	public static void isFalse(final String msg, final boolean boolValue) {
		if (boolValue != false) {
			throw new TestException("Assertion for false failed : " + msg);
		}
	}
}
