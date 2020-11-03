import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class RadioListener {

	private static final byte[] HEADER = new byte[] { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 };
	private static final int MAX_MESSAGE_SIZE = 32;
	private final List<Message> messageQueue = new LimitedQueue<>(10000);

	public List<Message> getMessagesStored() {
		return messageQueue;
	}

	/**
	 * Listen to a stream from a radio frequency that uses biphase encoding.
	 * Store messages until the stream stops sending correct messages.
	 */
	public void readStream(InputStream pip) {
		try (InputStream is = new BufferedInputStream(pip)){
			while (true) {
				int in = is.read();

				boolean isValidInput = in == 0 || in == 1;
				if (!isValidInput) {
					throw new RuntimeException("stream contains invalid bytes (only 0 and 1 are valid).");
				}
				if (in == 1) {
					// header byte found, so parse and read message if possible
					is.mark(MAX_MESSAGE_SIZE);
					if (!tryStoreValidMessage(is)) {
						is.reset();
					}
				}
			}
		} catch (IOException ex) {
			// do nothing: when the radio stream is closed/ended, an implemented exception is thrown
		}
	}

	/**
	 * biphase encoding is 2 bits per chunk each chunk should change phase at the end of the chunk
	 * a chunk that changes phase halfway the chunk is a 1, if it doesn't change it is a 0
     * @param input byte array
     * @return output byte array
	 */
	public static byte[] decodeBiphaseStream(byte[] input) {
		byte[] output = new byte[input.length / 2];
		for (int i = 0; i < output.length; i++) {
			if (input[i * 2] == input[i * 2 + 1]) // no phase change
                output[i] = 0;
			else
                output[i] = 1;
		}
		return output;
	}


	/**
	 * First wait until the incoming stream is completely retrieved.
	 * Then check if the incoming stream has the standard header.
	 * If standard header, then stream is decoded and stored as message
	 * @param fin inputstream
	 * @return if header is valid
	 * @throws java.io.IOException
	 */
	private boolean tryStoreValidMessage(InputStream fin) throws IOException {
		// wait until all is retrieved
		while (fin.available() < MAX_MESSAGE_SIZE - 1) {
			Thread.yield();
		}
		boolean isWithMatchingHeader = false;
		byte[] bytesFromInputStream = new byte[MAX_MESSAGE_SIZE];
		bytesFromInputStream[0] = 1;
		int readForBufferIndication = fin.read(bytesFromInputStream, 1, MAX_MESSAGE_SIZE - 1);
		if (readForBufferIndication < MAX_MESSAGE_SIZE - 1) {
			System.out.println("Not enough buffer to read, should not happen since we yielded until buffer was full");
		}

		RadioSignalUtils.printBits(bytesFromInputStream);
		isWithMatchingHeader = Arrays.equals(bytesFromInputStream, 0, HEADER.length, HEADER, 0, HEADER.length);
		if (isWithMatchingHeader) {
			messageQueue.add(new Message(decodeBiphaseStream(bytesFromInputStream), Instant.now()));
		}
		return isWithMatchingHeader;
	}

}
