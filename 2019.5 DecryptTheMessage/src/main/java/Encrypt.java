import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Contains all measurement logic and accelerator control mechanisms. Main
 * purpose is to classify a measured particle.
 */
public class Encrypt {

	public static void main(String[] args) throws UnsupportedEncodingException {

		String sInput1 = "Sorry, I forgot to attach the assignments. Will send them next week.";
		byte[] bInput1 = sInput1.getBytes(Charset.forName("UTF-8"));

		byte[] eInput = encrypt((byte) 201, bInput1);
		String eb64Output = Base64.getEncoder().encodeToString(eInput);
		System.out.println("encrypted b64: " + eb64Output);

		byte[] ebOutput = Base64.getDecoder().decode(eb64Output);
		byte[] bOutput = encrypt((byte) 201, ebOutput);
		String output = new String(bOutput, "UTF-8");
		System.out.println(output);

		String b64 = Base64.getEncoder().encodeToString(sInput1.getBytes("UTF-8"));
		String plaintext = new String(Base64.getDecoder().decode(b64), "UTF-8");
		System.out.println(plaintext);

	}

	public static byte[] encrypt(byte key, byte[] bInput) {
		byte[] bKey = fill(key, bInput.length);
		byte[] xOutput = xor(bInput, bKey);
		return xOutput;
	}

	public static byte[] xor(byte[] a, byte[] b) {
		if (a.length != b.length)
			throw new RuntimeException("xor buffers not equal size");
		byte[] output = new byte[a.length];

		for (int i = 0; i < a.length; i++) {
			output[i] = (byte) (a[i] ^ b[i]);
		}
		return output;
	}

	private static byte[] fill(byte b, int length) {
		byte[] output = new byte[length];
		for (int i = 0; i < output.length; i++) {
			output[i] = b;
		}
		return output;
	}

}
