import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TestUnbiphase {

	private void validateDecodingBiphaseStream(String expectedDecoded, String encoded) {
		assertArrayEquals(RadioSignalUtils.createBytesFromString(expectedDecoded), RadioListener.decodeBiphaseStream(RadioSignalUtils.createBytesFromString(encoded)));
	}

	@Test
	public void test1() {
		validateDecodingBiphaseStream("1111 1111", "10 10 10 10 10 10 10 10");
	}

	@Test
	public void test2() {
		validateDecodingBiphaseStream("0011 1100", "11 00 10 10 10 10 11 00");
	}
	@Test
	public void test3() {
		validateDecodingBiphaseStream("0100 0110", "11 01 00 11 00 10 10 11");
	}
}
