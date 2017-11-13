import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SwapPairBitsSubmitTest {
    SwapPairBits instance;

    @Before
    public void setup() {
        instance = new SwapPairBits();
    }

    @Test
    public void test1024() {
        assertEquals(1024, instance.execute(1024, 9, 3));
    }

    @Test
    public void test123() {
        assertEquals(95, instance.execute(123, 6, 3));
    }

    @Test
    public void test456() {
        assertEquals(347, instance.execute(123, 9, 6));
    }
}
