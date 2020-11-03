import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class RadioListener {

    private static final byte[] HEADER = new byte[]{1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
    private static final long HEADER_BINARY = 0b1010101010101010;
    private static final long LAST_16_BITS_MASK = 0b1111111111111111;
    private static final int PAYLOAD_LENGTH = 16;
    private final List<Message> messageQueue = new LimitedQueue<>(10000);

    public List<Message> getMessagesStored() {
        return messageQueue;
    }

    /**
     * Listen to a stream from a radio frequency that uses biphase encoding.
     * Store messages until the stream stops sending correct messages.
     */
    public void readStream(InputStream pip) {
        //SOLUTION
        byte[] messageBytes = new byte[HEADER.length + PAYLOAD_LENGTH];
        System.arraycopy(HEADER, 0, messageBytes, 0, HEADER.length);
        long latest16Bits = 0;
        try (InputStream is = new BufferedInputStream(pip)) {
            while (true) {
                int in = is.read();
                boolean isValidInput = in == 0 || in == 1;
                if (!isValidInput) {
                    throw new RuntimeException("stream contains invalid bytes (only 0 and 1 are valid).");
                }
                // shift the previous bits to the left and add the latest bit
                latest16Bits = ((latest16Bits << 1) & LAST_16_BITS_MASK) | in;

                if (latest16Bits == HEADER_BINARY) {
                    if (is.read(messageBytes, HEADER.length, PAYLOAD_LENGTH) < PAYLOAD_LENGTH) {
                        return;
                    }
                    RadioSignalUtils.printBits(messageBytes);
                    messageQueue.add(new Message(decodeBiphaseStream(messageBytes), Instant.now()));
                    latest16Bits = 0;
                }
            }
        } catch (IOException ex) {
            // do nothing: when the radio stream is closed/ended, an implemented exception is thrown
        }
        //SOLUTION
    }

    /**
     * biphase encoding is 2 bits per chunk each chunk should change phase at the end of the chunk
     * a chunk that changes phase halfway the chunk is a 1, if it doesn't change it is a 0
     *
     * @param input byte array
     * @return output byte array
     */
    public static byte[] decodeBiphaseStream(byte[] input) {
        // <SOLUTION>
        byte[] output = new byte[input.length / 2];
        IntStream.range(0, output.length).forEach(i -> {
            output[i] = (byte) ((input[i * 2] == input[i * 2 + 1]) ? 0 : 1);
        });
        return output;
        // </SOLUTION>
    }


}
