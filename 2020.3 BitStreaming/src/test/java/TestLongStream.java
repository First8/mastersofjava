import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class TestLongStream {

	private static final String NOISE1 = "0000000000000001000000000000010000000000000000000000000000000000000000";
	private static final String NOISE2 = "000000000100001000000010000010000000000000000000000000000000";
	private static final String NOISE1_ENDING_1 = "00000000000000010000000000000100000000000000000000000000000000000000001";
	private static final String NOISE1_ENDING_1001 = "00000000000000010000000000000100000000000000000000000000000000000000001001";

	@Test
	public void testMessageWithMuchNoise() throws IOException {
		final String msg1 = "1111 1111 0000 0000";
		RadioListener l = RadioSignalUtils.runStreamTransformation(os -> {
			StringBuilder noise = new StringBuilder();
			for (int i = 0; i < 5; i++) {
				noise.append(NOISE1_ENDING_1);
				noise.append(NOISE1_ENDING_1001);
				noise.append(NOISE2);
			}
			RadioSignalUtils.writeRaw(os, noise.toString());
			RadioSignalUtils.writeBiphase(os, msg1);
			RadioSignalUtils.writeRaw(os, NOISE1);
			return null;
		});

		assertEquals(1, l.getMessagesStored().size());
		assertArrayEquals(RadioSignalUtils.createBytesFromString(msg1), l.getMessagesStored().get(0).data);
	}

}
