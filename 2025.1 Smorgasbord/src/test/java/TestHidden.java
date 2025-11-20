import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHidden {
    @Test
    public void testStringSplit() {
        String[] result = StringSplit.split("5.40");

        String expectedEuro = "5";
        String expectedCents = "40";
        assertEquals(expectedEuro, result[0]);
        assertEquals(expectedCents, result[1]);
    }
}
