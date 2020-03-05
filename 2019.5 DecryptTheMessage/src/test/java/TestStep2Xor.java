import static org.junit.Assert.assertEquals;

import java.nio.charset.Charset;
import java.util.Base64;

import org.junit.Test;

public class TestStep2Xor {

	
    @Test
    public void test() {
    	test("first8", "nljug!", "CAUYBhMZ");
    }
    
    private void test(String sInput1, String sInput2, String b64Output) {
		byte[] bInput1 = sInput1.getBytes(Charset.forName("UTF-8"));
		byte[] bInput2 = sInput2.getBytes(Charset.forName("UTF-8"));

		Decrypt d = new Decrypt();
		
		byte[] xor = d.xor(bInput1, bInput2);
		String b64Xor = Base64.getEncoder().encodeToString(xor);
		assertEquals( b64Output, b64Xor); 
    }

	
}
