import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;

/**
 * Build a simple xor decryption algorithm.
 */
public class Decrypt {

	/**
	 * Step 1: encode the given input into a base64 string.
	 * 
	 * @param input the bytes to encode
	 * @return a base64 string
	 */
	public String base64Encode(byte[] input) {
		return Base64.getEncoder().encodeToString(input);
	}

	/**
	 * Step 1: decode the given base64 input to the represented bytes.
	 * 
	 * @param input a base64 string
	 * @return the encoded bytes
	 */
	public byte[] base64Decode(String input) {
		return Base64.getDecoder().decode(input);
	}

	/**
	 * Step 2: given two equal sized byte arrays, xor them bytewise.
	 * 
	 * @param a first byte array
	 * @param b second byte array
	 * @return byte-by-byte xored array
	 */
	public byte[] xor(byte[] a, byte[] b) {
		if (a.length != b.length)
			throw new RuntimeException("xor buffers not equal size");
		byte[] output = new byte[a.length];

		for (int i = 0; i < a.length; i++) {
			output[i] = (byte) (a[i] ^ b[i]);
		}
		return output;
	}

	/**
	 * Step 3: encrypt the input by xor'ing the input with the key.
	 * 
	 * @param key    the key to encrypt with
	 * @param bInput the input to encrypt
	 * @return the encrypted byte[]
	 */
	public byte[] encrypt(byte key, byte[] bInput) {
		byte[] bKey = fill(key, bInput.length);
		byte[] xOutput = xor(bInput, bKey);
		return xOutput;
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
	 * Step 4: score a given plaintext to indicate if it is a english text. Sums the
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

	/**
	 * Step 4: decrypt a given encrypted text.
	 * 
	 * @param b64EncryptedText a base64 encoded, single byte encrypted UTF-8 text
	 * @return the decrypted text
	 */
	public String decrypt(String b64EncryptedText) {
		String bestPlainText = "";
		long bestScore = -1;
		int bestKey = -1;
		
		byte[] bEncrypted = base64Decode(b64EncryptedText);
		for (int k = 0; k <= 255; k++) {
			byte[] bDecrypted = decrypt((byte) k, bEncrypted);
			try {
				String plainText = new String(bDecrypted, "UTF-8");
				long score = score(plainText);
				if (score >= bestScore) {
					bestScore = score;
					bestPlainText = plainText;
					bestKey = k;
					if (score>1) {
						System.out.println("new best score: " + bestScore + ", with decryption result: " + plainText);
					}
				}
			} catch (Exception e) {
				System.out.println("exception while interpreting decrypted text as plaintext: " + bDecrypted);
			}
		}

		System.out.println("decrypted with key " + bestKey + ", with score " + bestScore + ": " + bestPlainText );
		
		return bestPlainText;
	}

}
