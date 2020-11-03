import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class TestNoiseBeforeHeader {

	static String NOISE1 				= "0000000000000001000000000000010000000000000000000000000000000000000000";
	static String NOISE1_ENDING_1 		= "00000000000000010000000000000100000000000000000000000000000000000000001";
	static String NOISE1_ENDING_1001 	= "00000000000000010000000000000100000000000000000000000000000000000000001001";

	@Test
	public void testMessageWithHeaderNoise() throws IOException {
		String msg1 = "1111 1111 1001 0101";
		String msg2 = "1111 1111 1100 0011";
		RadioListener listener = RadioSignalUtils.runStreamTransformation(os -> {
			RadioSignalUtils.writeRaw(os, NOISE1_ENDING_1);
			RadioSignalUtils.writeBiphase(os, msg1);
			RadioSignalUtils.writeRaw(os, NOISE1_ENDING_1001);
			RadioSignalUtils.writeBiphase(os, msg2);
			RadioSignalUtils.writeRaw(os, NOISE1);
			return null;
		});

		// validate the retrieval of the 2 messages
		assertEquals(2, listener.getMessagesStored().size());
		assertArrayEquals(RadioSignalUtils.createBytesFromString(msg1), listener.getMessagesStored().get(0).data);
		assertArrayEquals(RadioSignalUtils.createBytesFromString(msg2), listener.getMessagesStored().get(1).data);
	}
}
