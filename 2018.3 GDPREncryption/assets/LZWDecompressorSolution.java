import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

public class LZWDecompressor {

	private final int SIZE_12BIT = 4096;

	/**
	 * Extract the 12 bit key from 2 bytes and get the int value of the key
	 * 
	 * @param b1     byte 1
	 * @param b2     byte 2
	 * @param onLeft whether we are on the left or on the right
	 * @return an Integer which holds the value of the key
	 */
	public int getValue(byte b1, byte b2, boolean onLeft) {
		String temp1 = Integer.toBinaryString(b1);
		String temp2 = Integer.toBinaryString(b2);
		while (temp1.length() < 8) {
			temp1 = "0" + temp1;
		}
		if (temp1.length() == 32) {
			temp1 = temp1.substring(24, 32);
		}
		while (temp2.length() < 8) {
			temp2 = "0" + temp2;
		}
		if (temp2.length() == 32) {
			temp2 = temp2.substring(24, 32);
		}

		if (onLeft) {
			return Integer.parseInt(temp1 + temp2.substring(0, 4), 2);
		} else {
			return Integer.parseInt(temp1.substring(4, 8) + temp2, 2);
		}

	}

	public void decompress(DataInputStream in, DataOutputStream out) throws IOException {
		int count = 256;
		String[] dictionary = new String[SIZE_12BIT];
		int currentWord, previousWord;
		byte[] buffer = new byte[3];
		boolean onLeft = true;

		// initialize the first 256 entries to single byte entries
		for (int i = 0; i < count; i++) {
			dictionary[i] = Character.toString((char) i);
		}

		try {

			// read the first 12-bit word
			buffer[0] = in.readByte();
			buffer[1] = in.readByte();

			previousWord = getValue(buffer[0], buffer[1], onLeft);
			onLeft = !onLeft;

			// and output it immediately. (When compressing, in the beginning
			// there are only 1-byte entries in the dictionary.
			out.writeBytes(dictionary[previousWord]);

			/**
			 * Read every 3 bytes and generate a corresponding characters - 2 character
			 */
			while (true) {

				// now, read the next two 1.5 bytes into the buffer
				if (onLeft) {
					buffer[0] = in.readByte();
					buffer[1] = in.readByte();
					currentWord = getValue(buffer[0], buffer[1], onLeft);
				} else {
					buffer[2] = in.readByte();
					currentWord = getValue(buffer[1], buffer[2], onLeft);
				}
				onLeft = !onLeft;

				// So now we have the next 12bit word in 'currentWord' and the
				// previous 12bit word in 'previousWord'.
				if (currentWord < count) {
					// If we have an entry in our dictionary for 'currentWord',
					// output the entry. And add a new entry (if we still have
					// room)
					// to the dictionary, just as the compressor would.
					if (count < 4096)
						dictionary[count] = dictionary[previousWord] + dictionary[currentWord].charAt(0);
					count++;
					out.writeBytes(dictionary[currentWord]);
				} else {
					// Apparently we don't have 'currentWord' in the dictionary.
					// How could that happen? Well, that means that the
					// compressor
					// will add it this round, but we don't know what that is!
					// But we do know that there was some overlap in the string
					// being compressed, otherwise it wouldn't have triggered
					// the dictionary so soon. Specifically, that overlap is
					// that the string we're looking for has the same last
					// character as it's first character.
					// So, add that to the dictionary and output it!
					if (count < 4096)
						dictionary[count] = dictionary[previousWord] + dictionary[previousWord].charAt(0);
					count++;
					out.writeBytes(dictionary[previousWord] + dictionary[previousWord].charAt(0));
				}
				previousWord = currentWord;
			}

		} catch (EOFException e) {
			in.close();
			out.close();
		}

	}

}