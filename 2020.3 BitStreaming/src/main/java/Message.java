import java.time.Instant;

class Message {
	final byte[] data;
	final Instant time;

	public Message(byte[] data, Instant time) {
		this.data = data;
		this.time = time;
	}
}