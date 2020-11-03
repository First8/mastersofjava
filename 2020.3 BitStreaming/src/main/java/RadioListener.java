import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
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
					// possible header byte found, so parse and read message if possible
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
		
		// ...
		
		return output;
	}


	/**
	 * First wait until we have retrieved enough bytes for a message.
	 * Then check if the incoming stream has the standard header.
	 * If standard header, then stream is decoded and stored as message
	 * @param fin the inputstream
	 * @return if header is valid
	 * @throws java.io.IOException
	 */
	private boolean tryStoreValidMessage(InputStream fin) throws IOException {

		// wait until enough bytes in buffer for message
		// note that we've already read possible bit from the message
		while (fin.available() < MAX_MESSAGE_SIZE - 1) {
			Thread.yield();
		}
		boolean isWithMatchingHeader = false;

		//...
		
		messageQueue.add(new Message(null,null));
		
		return isWithMatchingHeader;
	}

}
