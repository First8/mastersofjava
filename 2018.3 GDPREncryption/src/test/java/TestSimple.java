import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class TestSimple {

    private static final String ENCODING = "UTF-8";


    @org.junit.Test
    public void testEasyCase() throws IOException {
        test("Hello World!");
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

}
