
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class RsaObjectCrypter {

	private Cipher deCipher;
	private Cipher enCipher;
	private SecretKeySpec key;
	private IvParameterSpec ivSpec;

	public RsaObjectCrypter(byte[] keyBytes, byte[] ivBytes) {
		// wrap key data in Key/IV specs to pass to cipher

		ivSpec = new IvParameterSpec(ivBytes);
		// create the cipher with the algorithm you choose
		// see javadoc for Cipher class for more info, e.g.
		try {
			DESKeySpec dkey = new DESKeySpec(keyBytes);
			key = new SecretKeySpec(dkey.getKey(), "DES");         // SOLUTION
			deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding"); // SOLUTION
			enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding"); // SOLUTION
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}

	public byte[] encrypt(String obj) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException,
			IllegalBlockSizeException, ShortBufferException, BadPaddingException {
		byte[] input = convertToByteArray(obj);
		enCipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

		return enCipher.doFinal(input);

	}

	public Object decrypt(byte[] encrypted) throws InvalidKeyException, InvalidAlgorithmParameterException,
			IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException {
		deCipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

		return convertFromByteArray(deCipher.doFinal(encrypted));

	}

	private Object convertFromByteArray(byte[] byteObject) throws IOException, ClassNotFoundException {

		ByteArrayInputStream bais = new ByteArrayInputStream(byteObject);
		ObjectInputStream  in = new ObjectInputStream(bais);
		Object o = in.readObject();
		in.close();
		return o;

	}

	private byte[] convertToByteArray(String complexObject) throws IOException {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		ObjectOutputStream out = new ObjectOutputStream(baos);

		out.writeObject(complexObject);

		out.close();

		return baos.toByteArray();

	}

}