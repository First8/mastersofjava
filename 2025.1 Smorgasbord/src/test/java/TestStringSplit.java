import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStringSplit {
    @Test
    public void testStringSplit() {
        String[] result = StringSplit.split("9.99");

        String expectedEuro = "9";
        String expectedCents = "99";
        assertEquals(expectedEuro, result[0]);
        assertEquals(expectedCents, result[1]);
    }
}
