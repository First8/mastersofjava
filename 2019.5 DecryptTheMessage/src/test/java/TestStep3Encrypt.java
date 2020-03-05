import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.junit.Test;

public class TestStep3Encrypt {

	
    @Test
    public void test() throws UnsupportedEncodingException {
    	test((byte)34, "first8".getBytes("UTF-8"), "REtQUVYa");
    }
    
    private void test(byte key, byte[] bInput, String b64Expected) {
		Decrypt d = new Decrypt();
		byte[] bOutput = d.encrypt(key, bInput);
		String b64Output = Base64.getEncoder().encodeToString(bOutput);
		assertEquals( b64Output, b64Expected); 
    }

	
}
