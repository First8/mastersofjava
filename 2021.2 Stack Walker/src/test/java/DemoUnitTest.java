import org.junit.Assert;
import org.junit.Test;

public class DemoUnitTest
{
    @Test
    public void testBattleship_start() {
        // Note:
        // - the battleship will only start safe when the correct mystery code is entered.
        // - if starting fails, it will demo the Stackwalker API from inside the Battleship class.

        Battleship battleship = new Battleship();
        Assert.assertTrue(battleship.start());
    }
}
