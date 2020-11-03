import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class TestSimple {

	private static String NOISE1 = "0000000000000001000000000000010000000000000000000000000000000000000000";
	private static String NOISE2 = "000000000100001000000010000010000000000000000000000000000000";

	@Test
	public void testEmptyStream() throws IOException, InterruptedException {
		RadioListener l = RadioSignalUtils.runStreamTransformation(os -> {
			RadioSignalUtils.writeRaw(os, NOISE1);
			return null;
		});

		assertEquals(0, l.getMessagesStored().size());
	}

	@Test
	public void testSingleMessage() throws IOException {
		String msg = "1111 1111 1111 1111";
		RadioListener l = RadioSignalUtils.runStreamTransformation(os -> {
			RadioSignalUtils.writeRaw(os, NOISE2);
			RadioSignalUtils.writeBiphase(os, msg);
			RadioSignalUtils.writeRaw(os, NOISE1);
			return null;
		});

		assertEquals(1, l.getMessagesStored().size());
		assertArrayEquals(RadioSignalUtils.createBytesFromString(msg), l.getMessagesStored().get(0).data);
	}

	@Test
	public void testMultipleMessages() throws IOException {
		String msg1 = "1111 1111 1111 1111";
		String msg2 = "1111 1111 0011 1100";
		RadioListener l = RadioSignalUtils.runStreamTransformation(os -> {
			RadioSignalUtils.writeRaw(os, NOISE1);
			RadioSignalUtils.writeBiphase(os, msg1);
			RadioSignalUtils.writeRaw(os, NOISE1);
			RadioSignalUtils.writeRaw(os, NOISE1);
			RadioSignalUtils.writeBiphase(os, msg2);
			RadioSignalUtils.writeRaw(os, NOISE1);
			return null;
		});

		assertEquals(2, l.getMessagesStored().size());
		assertArrayEquals(RadioSignalUtils.createBytesFromString(msg1), l.getMessagesStored().get(0).data);
		assertArrayEquals(RadioSignalUtils.createBytesFromString(msg2), l.getMessagesStored().get(1).data);
	}

}
