import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Compressor using LZW algorithm.
 */
public class LZWCompressor {

	private final int SIZE_12BIT = 4096;

	public void compress(DataInputStream in, DataOutputStream out) throws IOException {

		String temp = "";
		byte[] buffer = new byte[3];
		boolean onLeft = true;

		// initialize the encoding table with single characters to their
		// respective index as encoding value
		Map<String, Integer> table = new HashMap<String, Integer>();
		for (int i = 0; i < 256; i++) {
			table.put(Character.toString((char) i), i);
		}

		try {

			// read the first character
			temp = "" + read8BitsToChar(in);

			// Read progressively more characters
			while (true) {
				char c = read8BitsToChar(in);

				// check if we have a prefix for this entire string
				if (table.containsKey(temp + c)) {
					// We have this in our dictionary so continue reading, maybe
					// we can do a longer prefix!
					temp = temp + c;
				} else {
					// Nope, so emit the longest found string (temp without c)
					String s12 = to12bit(table.get(temp));

					// Since we're writing 12 bit words, we're either writing
					// 1.5 ("left") or 2.5 ("right") bytes.
					// Output only if we're "on the right".
					if (onLeft) {
						buffer[0] = (byte) Integer.parseInt(s12.substring(0, 8), 2);
						buffer[1] = (byte) Integer.parseInt(s12.substring(8, 12) + "0000", 2);
					} else {
						buffer[1] += (byte) Integer.parseInt(s12.substring(0, 4), 2);
						buffer[2] = (byte) Integer.parseInt(s12.substring(4, 12), 2);
						for (int b = 0; b < buffer.length; b++) {
							out.writeByte(buffer[b]);
							buffer[b] = 0;
						}
					}
					onLeft = !onLeft;

					// if we have room in our 12bit dictionary, add the newly
					// found prefix
					if (table.size() < SIZE_12BIT) {
						table.put(temp + c, table.size());
					}
					temp = "" + c;
				}
			}

		} catch (EOFException e) {

			// reached the end of the file, so write out the buffer
			String temp_12 = to12bit(table.get(temp));
			if (onLeft) {
				buffer[0] = (byte) Integer.parseInt(temp_12.substring(0, 8), 2);
				buffer[1] = (byte) Integer.parseInt(temp_12.substring(8, 12) + "0000", 2);
				out.writeByte(buffer[0]);
				out.writeByte(buffer[1]);
			} else {
				buffer[1] += (byte) Integer.parseInt(temp_12.substring(0, 4), 2);
				buffer[2] = (byte) Integer.parseInt(temp_12.substring(4, 12), 2);
				for (int b = 0; b < buffer.length; b++) {
					out.writeByte(buffer[b]);
					buffer[b] = 0;
				}
			}
			in.close();
			out.close();
		}

	}

	public static char read8BitsToChar(DataInputStream in) throws IOException {
		byte inputByte = in.readByte();
		int i = new Byte(inputByte).intValue();
		if (i < 0) {
			i += 256;
		}
		char c = (char) i;
		return c;
	}

	/** Convert 8 bit to 12 bit */
	public static String to12bit(int i) {
		String temp = Integer.toBinaryString(i);
		while (temp.length() < 12) {
			temp = "0" + temp;
		}
		return temp;
	}

}