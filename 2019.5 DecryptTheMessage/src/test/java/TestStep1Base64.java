import static org.junit.Assert.assertArrayEquals;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class TestStep1Base64 {

	@Test
    public void decodeA() {
    	testDecode("YQ==", new byte[] {'a'});
    }

    @Test
    public void decodeAa() {
    	testDecode("YWE=", new byte[] {'a', 'a'});
    }

    @Test
    public void decodeAaa() {
    	testDecode("YWFh", new byte[] {'a', 'a', 'a'});
    }

    @Test
    public void decodeHelloWorld() throws UnsupportedEncodingException {
    	testDecode("SGVsbG8gd29ybGQh", "Hello world!".getBytes("UTF-8"));
    }

    private void testDecode(String b64In, byte[] expected) {
    	Decrypt d = new Decrypt();
    	byte[] bOutput = d.base64Decode(b64In);
    	assertArrayEquals("arrays are not equal", expected, bOutput);
    }



}
