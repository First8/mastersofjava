import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class TestStep4Decrypt {

	
    @Test
    public void test1() throws UnsupportedEncodingException {
    	test("X343cmFyZW54eXI2N1J5dHt4ZHJzN254Yjd0dnk3cX55czdjf3I3Z2V4Z3hkcnM3dmRkfnB5enJ5Y2Q3cXhlN3lyb2M3bnJ2ZWQ3WnZkY3JlZDd4cTdddmF2Ng==", "Hi everyone! Enclosed you can find the proposed assignments for next years Masters of Java!");
    }

    @Test
    public void test2() throws UnsupportedEncodingException {
    	test("KxcWDF8WDF8eXwkaDQZfDBocDRoLXwsaBwtfDBBfEh4UGl8MCg0aXwsQXxoRHA0GDwtfFgs=", "This is a very secret text so make sure to encrypt it");
    }

    private void test(String b64eInput, String expected) {
		Decrypt d = new Decrypt();
		String plaintext = d.decrypt(b64eInput);
		assertEquals( expected, plaintext); 
    }

	
}
