import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

/*
 * Inspired by https://github.com/gauthamk89/LZW-Compression/blob/master/src/LZWCompression.java
 */
public class TestHidden {

    private static final String ENCODING = "UTF-8";

    @org.junit.Test
    public void testAnotherString() throws IOException {
        test("Let's check if they didn't test against our simple strings");
    }

    @org.junit.Test
    public void testMOJImage() throws IOException {
        testWithResource("MasterofJava.png");
    }

    @org.junit.Test
    public void testNLJUGLogoFromFile() throws IOException {
        testDecompressWithExample(resource("nljug.gif.lzw"), resource("nljug.gif"));
    }
    
    private static InputStream resource(String name) throws IOException {
    	return Thread.currentThread().getContextClassLoader().getResource(name).openStream();
    }

    @org.junit.Test
    public void testJavaTextFromFile() throws IOException {
        testDecompressWithExample(resource("java.txt.lzw"), resource("java.txt"));
    }

    private void testWithResource(String name) throws IOException {
        LZWCompressor c = new LZWCompressor();
        LZWDecompressor d = new LZWDecompressor();

        try (ByteArrayOutputStream compressed = new ByteArrayOutputStream();) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            c.compress(new DataInputStream(resource(name)), new DataOutputStream(compressed));
            d.decompress(new DataInputStream(new ByteArrayInputStream(compressed.toByteArray())), new DataOutputStream(output));
            assertTrue(IOUtils.contentEquals(resource(name), new ByteArrayInputStream(output.toByteArray())));
        }

    }

    private void test(String testData) throws IOException {
        LZWCompressor c = new LZWCompressor();
        LZWDecompressor d = new LZWDecompressor();

        try (ByteArrayOutputStream compressed = new ByteArrayOutputStream(); ByteArrayOutputStream decompressed = new ByteArrayOutputStream();) {
            c.compress(new DataInputStream(new ByteArrayInputStream(testData.getBytes(ENCODING))), new DataOutputStream(compressed));
            d.decompress(new DataInputStream(new ByteArrayInputStream(compressed.toByteArray())), new DataOutputStream(decompressed));

            assertEquals(testData, new String(decompressed.toByteArray(), ENCODING));
        }

    }
    
    private void testDecompressWithExample(InputStream compressedIn, InputStream originalIn) throws IOException {
        LZWDecompressor d = new LZWDecompressor();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ByteArrayOutputStream compressed = new ByteArrayOutputStream();) {
        	
            d.decompress(new DataInputStream(compressedIn), new DataOutputStream(bos));
            assertTrue(IOUtils.contentEquals(new ByteArrayInputStream(bos.toByteArray()), originalIn));
        }
    }


}
