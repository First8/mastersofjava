import java.util.Arrays;

/**
 * Build a simple xor decryption algorithm.
 */
public class Decrypt {

	/**
	 * Step 1: decode the given base64 input to the represented bytes.
	 * 
	 * @param input a base64 string
	 * @return the encoded bytes
	 */
	public byte[] base64Decode(String input) {
		return null;
	}

	/**
	 * Step 2: given two equal sized byte arrays, xor them bytewise.
	 * 
	 * @param a first byte array
	 * @param b second byte array
	 * @return byte-by-byte xored array
	 */
	public byte[] xor(byte[] a, byte[] b) {
		return null;
	}

	/**
	 * Step 3: encrypt the input by xor'ing the input with the key.
	 * 
	 * @param key    the key to encrypt with
	 * @param bInput the input to encrypt
	 * @return the encrypted byte[]
	 */
	public byte[] encrypt(byte key, byte[] bInput) {
		return null;
	}

	/**
	 * Step 3: decrypt is the same as encrypt.
	 */
	public byte[] decrypt(byte key, byte[] bInput) {
		return encrypt(key, bInput);
	}

	private byte[] fill(byte b, int length) {
		byte[] output = new byte[length];
		for (int i = 0; i < output.length; i++) {
			output[i] = b;
		}
		return output;
	}

	/**
	 * Step 4: decrypt a given encrypted text.
	 * 
	 * @param b64EncryptedText a base64 encoded, single byte encrypted UTF-8 text.
	 * @return the decrypted text
	 */
	public String decrypt(String b64EncryptedText) {
		return null;
	}

	/**
	 * Score a given plaintext to indicate if it is a english text. Sums the
	 * total length of all characters in found common english words and compares the average number of capital letters to the 3% norm.
	 * 
	 * @param plainText
	 * @return score number of common dutch words in the plaintext
	 */
	public long score(String plainText) {
		String wordsOnly = plainText.replaceAll("[^A-Za-z]+", " ").toLowerCase();
		int commonWordScore = Arrays.stream(wordsOnly.split(" ")).map(String::trim).filter(Words.WORDS::contains)
				.mapToInt(String::length).sum();
		
		int capitalLetters = (int) plainText.chars().filter( Character::isUpperCase).count();
		float capitalLetterNorm = 0.03f;
		float capitalLetterDiff = Math.abs( 10.0f * (capitalLetterNorm - (float) capitalLetters / plainText.length()));
		return (long) (commonWordScore - capitalLetterDiff);
	}

}
