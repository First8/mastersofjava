import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class HiddenTests {
	private static String NOISE1 = "0000000000000001000000000000010000000000000000000000000000000000000000";
	private static String NOISE2 = "000000000100001000000010000010000000000000000000000000000000";
	private static String NOISE1_ENDING_1 = "00000000000000010000000000000100000000000000000000000000000000000000001";
	private static String NOISE1_ENDING_1001 = "00000000000000010000000000000100000000000000000000000000000000000000001001";

	@Test
	public void test() throws IOException {
		String msg1 = "1111 1111 0000 0000";
		String msg2 = "1111 1111 1111 1111";
		String msg3 = "1111 1111 0101 1100";
		String msg4 = "1111 1111 1101 1101";
		RadioListener l = RadioSignalUtils.runStreamTransformation(os -> {
			for (int i = 0; i < 5; i++) {
				RadioSignalUtils.writeRaw(os, NOISE1_ENDING_1);
				RadioSignalUtils.writeRaw(os, NOISE1_ENDING_1001);
				RadioSignalUtils.writeRaw(os, NOISE1_ENDING_1);
			}
			RadioSignalUtils.writeBiphase(os, msg1);
			for (int i = 0; i < 5; i++) {
				RadioSignalUtils.writeRaw(os, NOISE1);
			}
			RadioSignalUtils.writeBiphase(os, msg2);
			for (int i = 0; i < 7; i++) {
				RadioSignalUtils.writeRaw(os, NOISE2);
			}
			RadioSignalUtils.writeBiphase(os, msg3);
			for (int i = 0; i < 4; i++) {
				RadioSignalUtils.writeRaw(os, NOISE1_ENDING_1001);
			}
			RadioSignalUtils.writeBiphase(os, msg4);
			return null;
		});

		assertEquals(4, l.getMessagesStored().size());
		assertArrayEquals(RadioSignalUtils.createBytesFromString(msg1), l.getMessagesStored().get(0).data);
		assertArrayEquals(RadioSignalUtils.createBytesFromString(msg2), l.getMessagesStored().get(1).data);
		assertArrayEquals(RadioSignalUtils.createBytesFromString(msg3), l.getMessagesStored().get(2).data);
		assertArrayEquals(RadioSignalUtils.createBytesFromString(msg4), l.getMessagesStored().get(3).data);
	}

}
